package dev.ajimenez.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection() {

        // Your database you are trying to connect to including database name, username, and password
        // Used dotENV since that is what I am used to
        Dotenv dotenv = Dotenv.load();
        String details = dotenv.get("DB_KEY");

        try {
            Connection conn = DriverManager.getConnection(details);
            return conn;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

    }

}
