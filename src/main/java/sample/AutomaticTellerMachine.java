package sample;

import java.util.Scanner;

public class AutomaticTellerMachine {
	// initial supply of bank notes
    private int supply_1000_ = 10;
    private int supply_500_ = 20;
    private int supply_100_ = 50;
    
    public void run() {
    	while (true) {
    		Scanner sc = new Scanner(System.in);
            System.out.print("Enter amount to withdraw: ");
            int amt = Integer.parseInt(sc.nextLine());
            
            if (!validateInput(amt)) {
                System.out.println("ERROR: The machine supplies only 1000, 500 and 100 Baht bank note.");
                continue;
            }
            
            if (!validateAmount(amt)) {
                System.out.println("ERROR: Money supply is unsufficient for your transaction.");
                continue;
            }
            
            try {
                int[] withdrawn = withdraw(amt);
                System.out.println(String.format("Money withdrawn.\n1000 Baht note = %d\n500 Baht note = %d\n100 Baht note = %d", withdrawn[0], withdrawn[1], withdrawn[2]));
            } catch (NoteSupplyCriticalException e) {
                System.out.println("ERROR: Bank note supply is unsufficient for your transaction.");
                continue;
            }
            report();
        }
    }
    
    private int showAllMoney() {
        return (supply_1000_ * 1000) + (supply_500_ * 500) + (supply_100_ * 100);
    }
    
    // See if customer input a valid input.
    private boolean validateInput(int amt) {
        return amt >= 100 && amt % 100 == 0;
    }
    
    // See if money supply is sufficient.
    private boolean validateAmount(int amt) {
        return showAllMoney() >= amt;
    }
    
    // calculate and return number of each bank note type customer will receive.
    private int[] withdraw(int amt) throws NoteSupplyCriticalException {
        // Needed notes count
        int ths = amt / 1000;
        amt = amt - (ths * 1000);
        int fhs = amt / 500;
        amt = amt - (fhs * 500);
        int hds = amt / 100;
        
        // Adjust to supply
        int rem_ths = 0;
        if (this.supply_1000_ < ths) { // Thousandth note supply deficit.
            rem_ths = ths - this.supply_1000_;
            ths = this.supply_1000_; // Can have only what left of the supply.
        }
        
        // Still remain unsatisfied demand for thousandth note.
        // It is delegated to five-hundredth note's demand.
        if (rem_ths > 0) {
            // Needs for 500 goes up
            fhs += rem_ths * 2; // 1000 = 500 * 2
        }
        
        int rem_fhs = 0;
        if (this.supply_500_ < fhs) { // Five-hundredth note supply deficit.
            rem_fhs = fhs - this.supply_500_;
            fhs = this.supply_500_; // Can have only what left of the supply.
        }
        
        // Still remain unsatisfied demand for five-hundredth note.
        // It is delegated to one-hundredth note's demand.
        if (rem_fhs > 0) {
            // Needs for 100 goes up
            hds += rem_fhs * 5; // 500 = 100 * 5
        }
        
        if (this.supply_100_ < hds) {
            // Not enough bank note supply!
            throw new NoteSupplyCriticalException("Note enough bank note.");
        }
        // reduce supply.
        this.supply_1000_ -= ths;
        this.supply_500_ -= fhs;
        this.supply_100_ -= hds;
        
        return new int[] { ths, fhs, hds };
    }
    
    public void report() {
        System.out.println(String.format("Current supply.\n1000 x %d\n500 x %d\n100 x %d", this.supply_1000_, this.supply_500_, this.supply_100_));
    }
    
    private static class NoteSupplyCriticalException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 675379810750538870L;

		public NoteSupplyCriticalException(String msg) {
            super(msg);
        }
    }
    
    public static void main(String args[]) {
        AutomaticTellerMachine atm = new AutomaticTellerMachine();
        atm.run();
    }
}