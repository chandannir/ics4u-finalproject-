/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ohmwards;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author aahmi1
 */
public class SaveDatabase {
    // Path of where the database file will be located
    private static String url = "jdbc:sqlite:ohmwards.db";
    
    public SaveDatabase() {
        // Creates a connection our database and handles errors
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to Datbase");
                
                // SQL statement for creating a new table
                // Possibly encrypt passwords
                var sql = "CREATE TABLE IF NOT EXISTS Users ("
                + "	id INTEGER PRIMARY KEY,"
                + "	username TEXT NOT NULL UNIQUE,"
                + "	password TEXT NOT NULL UNIQUE"
                + ");";
                
                // Statment object to be able to add tables to database
                var stmnt = conn.createStatement();
                
                stmnt.execute(sql);
                
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    // Takes user 
    public void addAccount(String username, String password) {
        var sql = "INSERT INTO Users(username, password) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to Database");
                
                // Statment object to be able to add tables to database
                var Instmnt = conn.prepareStatement(sql);

                Instmnt.setString(1, username);
                Instmnt.setString(2, password);
                
                Instmnt.executeUpdate();
                
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    /// Checks if an account exists
    /// Returns true if the account exist
    /// @param username
    /// @return 
    public boolean findAccount(String username) {
        String sql = "SELECT EXISTS(SELECT 1 FROM Users WHERE username = ?)";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                try (var preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, username);

                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        boolean exists = resultSet.getBoolean(1);
                        return exists;
                    }
                }
            }
                
              conn.close();
            }  
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    // Find the account and checks if the passwords match
    // Returns true if the account exists
    public boolean verifyAccount(String username, String password) {
        var sql = "SELECT password FROM Users WHERE username = ?";

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) { 
                var stmt = conn.prepareStatement(sql);
                
                stmt.setString(1, username);
                
                var rs = stmt.executeQuery();
               
                if(rs.getString("password").equals(password)) {
                        return true;
                }
                
                conn.close();
            }  
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        
        return false;
    }  
}
