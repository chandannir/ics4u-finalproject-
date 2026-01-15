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
public class Wire extends Component {
    private String colour;
    private int gauge;

    public Wire(Vector<Double> position, JButton btn) {
        super(ConnectionType.WIRE, position, btn);
        this.colour = "Red";
        this.gauge = 12;
    }
    
    public String getColour() {
        return colour;
    }
    
    public int getGauge() {
        return gauge;
    }
    
    public void setColour(String newColour) {
        colour = newColour;
    }
    
    public void setGauge(int newGauge) {
       gauge = newGauge;
    }
    
    // Checks and compares the powerlimit and current
    // If the current is too high a burnout will happen
    // The user will be told the burnout
    public void burnout() {
        // TODO!
    }
}
