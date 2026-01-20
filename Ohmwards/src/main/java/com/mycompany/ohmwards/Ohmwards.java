/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ohmwards;


/**
 *
 * @author Cameron
 */
public class Ohmwards {
    public static MainMenu main;
    public static User currUser;
    public static SaveDatabase saveDatabase;
    public static Circuit circ;
    public static BuildScreen build;
    
    // Make sure to connect database here
    public static void main(String[] args) {
        saveDatabase = new SaveDatabase();
        
        saveDatabase.addAccount("totallyRoland", "vergyIsStronger");
        saveDatabase.addAccount("bongbong", "#bestEmployee");
        saveDatabase.addAccount("heathcliff", "catherine");
        
        circ = new Circuit();
        main = new MainMenu();
        main.setVisible(true);
    }
}
