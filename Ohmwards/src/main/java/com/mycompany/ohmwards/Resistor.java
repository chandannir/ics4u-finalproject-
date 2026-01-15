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
public class Resistor extends Component {
    
    private int resistance;
    
    public Resistor(Vector<Double> position, JButton btn) {
        super(ConnectionType.RESISTOR, position, btn);
        this.resistance = 220;
    }
    
    public void setResistance(int newResistance) {
        this.resistance = newResistance;
    }
    
    public int getResistance() {
        return this.resistance;
    }
}
