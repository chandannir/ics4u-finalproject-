/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ohmwards;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Cameron
 */
public class Circuit {
    private ArrayList<Component> components;
    private boolean state;
    
    public Circuit(){
        components = new ArrayList<>();
        this.state = false;
    }
    
    public ArrayList<Component> getComps(){
        return components;
    }
    
    public void addComponent(Component comp){
        // Add Code Here (add a comp to components list and set position to midpoint of pos1 and pos2)
        components.add(comp);
        System.out.println(components);
    }
    
    public void removeComponent(Component comp){
        // Remove comp from ArrayList and delete component from screen
        components.remove(comp);
    }
    
    public boolean checkState(){
        // Look through circuit and check if the circuit is open or closed
        return true;
    }
}
