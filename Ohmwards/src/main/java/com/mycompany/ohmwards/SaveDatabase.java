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
                + "	username TEXT NOT NULL,"
                + "	password TEXT NOT NULL"
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
                
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    // Checks if an account exists
    // Returns true if the account exists
    public boolean findAccount(String username) {
        var sql = "SELECT name FROM Users";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var pstmt = conn.createStatement();

                var rs = pstmt.executeQuery(sql);
                
                while (rs.next()) {
                    if (rs.getString("name").contains(username)) {
                        return true;
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

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
               
                var pstmt = conn.prepareStatement(sql);
                
                pstmt.setString(1, username);
                
                var rs = pstmt.executeQuery(sql);
               
                if(rs.getString("password").contains(password)) {
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
