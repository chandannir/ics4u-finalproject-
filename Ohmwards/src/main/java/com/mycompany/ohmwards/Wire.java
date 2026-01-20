/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ohmwards;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Cameron
 */
public class Wire extends Component {
    private String colour;
    private int gauge;

    public Wire(Vector<Double> position, JButton btn) {
        super(ConnectionType.WIRE, position, btn, new JLabel("Colour:"), new JLabel("Gauge:"));
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
}
