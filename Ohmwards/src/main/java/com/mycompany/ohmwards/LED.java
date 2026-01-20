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

    public LED(Vector<Double> position, JButton btn) {
        super(ConnectionType.MODULE, position, btn, new JLabel("Direction:"), new JLabel("Colour"));
        this.colour = "Red";
        this.direction = "Upwards";
    }
    
    public String getColour() {
        return this.colour;
    }
    
    public String getDirection() {
        return direction;
    }
    
    public void setColour(String newColour) {
        this.colour = newColour;
    }
    
    public void setDirection(String newDirection) {
        direction = newDirection;
    }
}
