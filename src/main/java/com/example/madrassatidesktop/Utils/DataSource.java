package com.example.madrassatidesktop.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private Connection con;
    private static DataSource data;
    private final String url = "jdbc:mysql://localhost:3306/madrassati";
    private final String user = "root";
    private final String pwd = "";

    private DataSource() {
        try {
            // Ensure the driver is registered
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion établie");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Connection getCon() {
        try {
            // Check if the connection is closed or null, and if so, re-establish it
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url, user, pwd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;
    }
}
