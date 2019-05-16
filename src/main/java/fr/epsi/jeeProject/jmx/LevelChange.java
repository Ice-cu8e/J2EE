package fr.epsi.jeeProject.jmx;

import fr.epsi.jeeProject.listener.StartupListener;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LevelChange implements LevelChangeMBean {
    private static Logger logger = LogManager.getLogger(LevelChange.class);

    public String getLoggingLevel(){
        return logger.getLevel().toString() ;
    }
    public synchronized void setLogDebug(){
        logger.info("Setting logging level to: " + "Debug");
        Level newLevel = Level.toLevel("Debug");
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(newLevel);
        ctx.updateLoggers();
    }
    public synchronized void setLogInfo(){
        logger.info("Setting logging level to: " + "Info");
        Level newLevel = Level.toLevel("Info");
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(newLevel);
        ctx.updateLoggers();
    }
    public synchronized void setLogError(){
        logger.info("Setting logging level to: " + "Error");
        Level newLevel = Level.toLevel("Error");
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(newLevel);
        ctx.updateLoggers();
    }
}
