package com.anton.gs;

import com.anton.gs.model.dao.impl.UserDaoImpl;
import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) throws DaoException, ClassNotFoundException, SQLException {
       // User user = UserDaoImpl.getInstance().createUser("ant-2005@gmail.com");
        //System.out.println(user);

        ResourceBundle bundle = ResourceBundle.getBundle("db");
        Class.forName(bundle.getString("db.name"));
        Connection connection = DriverManager.getConnection(
                bundle.getString("db.url"),
                bundle.getString("db.login"),
                bundle.getString("db.password")
        );

        String a = " asd";
        a.intern();
    }
}
