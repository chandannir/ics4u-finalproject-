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
    
    private double resistance;
    
    public Resistor(ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(type, position, btn, voltage, current, resistance, power, name, direction);
        this.resistance = resistance;
    }
    
    public void setResistance(double newResistance) {
        this.resistance = newResistance;
    }
    
    public double getResistance() {
        return this.resistance;
    }
}
