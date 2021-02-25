package dev.araga.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

        String details = "jdbc:postgresql://34.67.137.97:5432/BankAPIDB?user=Samuel&password=quest456";

        try {
            Connection conn = DriverManager.getConnection(details);
            return conn;
        } catch (SQLException sqlException) {
           sqlException.printStackTrace();
            return null;

        }

    }
}
