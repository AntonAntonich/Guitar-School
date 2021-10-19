package com.anton.gs.model.pool;

import com.anton.gs.model.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.anton.gs.model.pool.DbConfiguration.*;

final class ConnectionCreator {
    private static Logger logger = LogManager.getLogger();

     static ProxyConnection createConnection() {
        Connection connection = null;
        try {

            Class.forName(DB_NAME);


            connection = DriverManager.getConnection(
                    DB_URL,
                    DB_LOGIN,
                    DB_PASSWORD
            );
        } catch (SQLException e) {
            logger.log(Level.ERROR, "connecting to db  failed, url: " + DB_URL, e);
            throw new RuntimeException(e);
        }catch (ClassNotFoundException e) {
            logger.log(Level.ERROR, "connecting to db  failed, db name: " + DB_NAME, e);
            throw new RuntimeException(e);
        }
        ProxyConnection proxyConnection = new ProxyConnection(connection);
        return proxyConnection;
    }
}
