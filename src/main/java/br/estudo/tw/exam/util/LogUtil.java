package br.estudo.tw.exam.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by torugo on 30/08/15.
 */
public class LogUtil {

    private Logger logger;

    public LogUtil(Logger logger) {
        this.logger = logger;
    }

    public static LogUtil getLogger(Class clazz) {
        return new LogUtil(LogManager.getLogger(clazz));
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void error(Exception e) {
        logger.error(e.getMessage(), e);
    }

    public void error(String msg) {
        logger.error(msg);
    }
}
