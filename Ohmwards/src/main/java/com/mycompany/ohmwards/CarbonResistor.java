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
    
    public CarbonResistor(Vector<Double> position, JButton btn) {
        super(position, btn);
        this.colourCode = "red red brown";
    }
    
    public void setColourCode(String newColourCode) {
        this.colourCode = newColourCode;
    }
    
    public String getColourCode() {
        return this.colourCode;
    }
}
