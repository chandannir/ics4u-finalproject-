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
public class LED extends Component {
    private String colour;
    private String direction;
    private boolean isEmitting;

    public LED(Vector<Double> position, JButton btn) {
        super(ConnectionType.MODULE, position, btn, new JLabel("Direction:"), new JLabel("Colour"));
        this.colour = "Red";
        this.direction = "Upwards";
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
