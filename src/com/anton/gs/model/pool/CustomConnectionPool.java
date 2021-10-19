package com.anton.gs.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.anton.gs.model.pool.DbConfiguration.DB_CONNECTION_POOL_SIZE;
import static com.anton.gs.model.pool.DbConfiguration.DB_URL;

public final class  CustomConnectionPool {
    private static Logger logger = LogManager.getLogger();

    private static CustomConnectionPool pool;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private Deque<Connection> freeConnections;
    private Deque<Connection> occupiedConnections;

    private CustomConnectionPool() {
        init();
        connectionChecker();
    }

    public static CustomConnectionPool getInstance() {
        if (!isCreated.get()) {
            try {
                lock.lock();
                if (pool == null) {
                    pool = new CustomConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return pool;
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            lock.lock();
            while (freeConnections.isEmpty()) {
                condition.await();
            }
            connection = freeConnections.poll();
            occupiedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "thread using connection interrupted thread: "
                    + Thread.currentThread().getName(), e);

        } finally {
            lock.unlock();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if(connection instanceof ProxyConnection) {
            try {
                lock.lock();
                occupiedConnections.remove(connection);
                freeConnections.add(connection);
                condition.signal();

            } finally {
                lock.unlock();
            }

        } else {
            //log
        }
    }

    public void destroyPool() {
        while (!freeConnections.isEmpty()) {
            ProxyConnection pc = (ProxyConnection) freeConnections.poll();
            if (pc != null) {
                pc.reallyClose();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        while (DriverManager.getDrivers().hasMoreElements()) {
            Driver d = DriverManager.getDrivers().nextElement();
            try {
                DriverManager.deregisterDriver(d);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "connecting to db  failed, driver: " + d, e);

            }
        }

    }

    private void connectionChecker() {
        if (freeConnections.isEmpty()) {
            logger.log(Level.ERROR, "connecting pool is empty");
            throw new RuntimeException();
        } else if (freeConnections.size() == DB_CONNECTION_POOL_SIZE) {
            logger.log(Level.INFO, "connecting pool filed");
        } else if (freeConnections.size() < DB_CONNECTION_POOL_SIZE) {
            logger.log(Level.ERROR, "connecting pool is not enough filed");
            init();
        }
    }

    private void init() {
        freeConnections = new ArrayDeque<>(DB_CONNECTION_POOL_SIZE);
        occupiedConnections = new ArrayDeque<>();
        for (int i = 0; i < DB_CONNECTION_POOL_SIZE; i++) {
            freeConnections.add(ConnectionCreator.createConnection());
        }

    }
}
