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
public class CarbonResistor extends Resistor {
    
    private String colourCode;
    
    public CarbonResistor(String colourCode, ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(type, position, btn, voltage, current, resistance, power, name, direction);
        this.colourCode = colourCode;
    }
    
    public void setColourCode(String newColourCode) {
        this.colourCode = newColourCode;
    }
    
    public String getColourCode() {
        return this.colourCode;
    }
}
