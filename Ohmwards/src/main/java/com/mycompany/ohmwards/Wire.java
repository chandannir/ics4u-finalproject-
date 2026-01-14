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
    private double powerLimit;
    private String colour;
    private int gauge;

    public Wire(double powerLimit, String colour, int gauge, ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(type, position, btn, voltage, current, resistance, power, name, direction);
    }
    
    public double getPowerLimit() {
        return powerLimit;
    }
    
    public String getColour() {
        return colour;
    }
    
    public int getGauge() {
        return gauge;
    }
    
    public void setPowerLimit(double newPowerLimit) {
        powerLimit = newPowerLimit;
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
