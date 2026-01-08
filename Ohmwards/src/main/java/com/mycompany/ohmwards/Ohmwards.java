/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ohmwards;
import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cameron
 */
public class Ohmwards {
    public static MainMenu main;
    public static User currUser;
    
    public static void main(String[] args) {  
        addAccount("totallyRoland", "vergyIsStronger");
        addAccount("bongbong", "#bestEmployee");
        addAccount("heathcliff", "catherine");
        
        main = new MainMenu();
        main.setVisible(true);
    }
    
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
