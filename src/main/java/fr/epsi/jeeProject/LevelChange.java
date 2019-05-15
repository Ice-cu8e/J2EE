package fr.epsi.jeeProject;

import fr.epsi.jeeProject.listener.StartupListener;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LevelChange implements LevelChangeMBean {
    private static Logger logger = LogManager.getLogger(StartupListener.class);

    public String getLoggingLevel(){
        return logger.getLevel().toString() ;
    }

    public synchronized void LoggingLevel(String level){
        logger.info("Setting logging level to: " + level);
        Level newLevel = Level.toLevel(level);
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(newLevel);
        ctx.updateLoggers();
    }
}
