/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ohmwards;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;

/**
 *
 * @author Cameron
 */
public class Ohmwards {
    public static MainMenu main;
    public static User currUser;
    public static SaveDatabase saveDatabase;
    
    // Make sure to connect database here
    public static void main(String[] args) {
        
        addAccount("totallyRoland", "vergyIsStronger");
        addAccount("bongbong", "#bestEmployee");
        addAccount("heathcliff", "catherine");
        
        saveDatabase = new SaveDatabase();
        main = new MainMenu();
        main.setVisible(true);
    }
    
    // Change this to save to the database instead
    public static void addAccount(String username, String password){
        // Create the file for the account
        File newAcc = new File(username + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newAcc))) {
            writer.write(password);
            writer.newLine();
            writer.write("[]");
        } catch (IOException ex) {
            Logger.getLogger(Ohmwards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
