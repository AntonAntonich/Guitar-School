package com.anton.gs.controller.listener;

import com.anton.gs.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.log(Level.INFO, "pool destroyed");
        CustomConnectionPool.getInstance().destroyPool();
    }
}
