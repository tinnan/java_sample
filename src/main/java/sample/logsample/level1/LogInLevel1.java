package sample.logsample.level1;

import sample.logsample.PlayWithLogger;

public class LogInLevel1 extends PlayWithLogger {
	
	public void logit() {
		logger.debug("Hello, this is level 1 [Debug].");
		logger.info("Hello, this is level 1 [Info].");
	}
}
