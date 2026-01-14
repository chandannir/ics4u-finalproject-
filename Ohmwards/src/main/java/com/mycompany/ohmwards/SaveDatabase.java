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
    private static Connection conn;
    
    public SaveDatabase() {
        // Creates a connection our database and handles errors
        try (Connection tmpConn = DriverManager.getConnection(url)) {
            if (tmpConn != null) {
                conn = tmpConn;
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
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
}
