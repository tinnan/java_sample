package sample.logsample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.logsample.level1.LogInLevel1;
import sample.logsample.level1.level2.LogInLevel2;

public class PlayWithLogger {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	public static void main(String[] args) {
		PlayWithLogger th = new PlayWithLogger();
		th.run();
	}
	
	public void run() {
		logger.debug("Hello, this is Paren.");
		LogInLevel1 l1 = new LogInLevel1();
		LogInLevel2 l2 = new LogInLevel2();
		l1.logit();
		l2.logit();
	}
}
