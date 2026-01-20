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
public abstract class PowerSource extends Component{
    private double voltageOut;

    public PowerSource(double voltageOut, ConnectionType type, Vector<Double> position, JButton btn, JLabel lab1, JLabel lab2) {
        super(type, position, btn, lab1, lab2);
        this.voltageOut = voltageOut;
    }
        
    public void setVoltageOut(double newVoltageOut) {
        this.voltageOut = newVoltageOut;
    }   
    
    public double getVoltageOut() {
        return this.voltageOut;
    }
}
