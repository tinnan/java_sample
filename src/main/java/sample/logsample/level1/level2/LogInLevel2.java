package sample.logsample.level1.level2;

import sample.logsample.PlayWithLogger;

public class LogInLevel2 extends PlayWithLogger {
	
	public void logit() {
		logger.debug("Hello, this is level 2 [Debug].");
		logger.info("Hello, this is level 2 [Info].");
	}
}
