/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ohmwards;

import java.util.Vector;
import javax.swing.JButton;
/**
 *
 * @author Cameron
 */
public class LED extends Diode {
    private String colour;
    private boolean isEmitting;

    public LED(Vector<Double> position, JButton btn) {
        super(position, btn);
        this.colour = "Red";
        this.isEmitting = false;
    }
    
    public String getColour() {
        return this.colour;
    }
    
    public boolean isEmitting() {
        return this.isEmitting;
    }
    
    public void setColour(String newColour) {
        this.colour = newColour;
    }
    
    public void setIsEmitting(boolean newIsEmitting) {
        this.isEmitting = newIsEmitting;
    }
    
    // Figure out what this will do when grid is set up
    public void emit() {
        // TODO
    }
}
