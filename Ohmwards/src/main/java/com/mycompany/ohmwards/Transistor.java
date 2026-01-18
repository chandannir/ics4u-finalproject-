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
public class Transistor extends Component {
    
    private double voltageRequired;
    private double voltageOut;
    
    public Transistor(Vector<Double> position, JButton btn) {
        super(ConnectionType.SWITCH, position, btn, new JLabel("Voltage Required:"), new JLabel("Voltage Out:"));
        this.voltageRequired = 9;
        this.voltageOut = 12;
    }
    
    public void setVoltageRequired(double newVoltageRequired) {
        this.voltageRequired = newVoltageRequired;
    }
    
    public void setVoltageOut(double newVoltageOut) {
        this.voltageOut = newVoltageOut;
    }
    
    public double getVoltageRequired() {
        return this.voltageRequired;
    }
    
    public double getVoltageOut() {
        return this.voltageOut;
    }
}
