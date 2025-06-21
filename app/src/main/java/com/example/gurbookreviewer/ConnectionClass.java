package com.example.gurbookreviewer;

import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionClass {

    private static final String db = "amp";
    private static final String ip = "10.0.2.2";
    private static final String portNum = "3306";
    private static final String uName = "root";
    private static final String pass = "password";

    public Connection CONN() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionString = "jdbc:mysql://" +ip + ":" +portNum + "/" +db;
            conn = DriverManager.getConnection(connectionString, uName, pass);
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", "MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            Log.e("ERROR", "SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", "Exception: " + e.toString());
        }
        return conn;
    }

    public boolean saveReview(String bookName, String bookReview, String userName) //p
    {
        Connection conn = CONN();
        if (conn == null) {
            Log.e("ERROR", "Connection to database failed");
            return false;
        }

        String query = "INSERT INTO review (bookName, bookReview, userName) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bookName);
            stmt.setString(2, bookReview);
            stmt.setString(3, userName);//p
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Log.e("ERROR", "SQL Exception: " + e.getMessage());
            return false;
        }
    }
}
