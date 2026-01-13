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
public class Transistor extends Switch {
    
    private double voltageRequired;
    private double voltageOut;
    
    public Transistor(double voltageRequired, double voltageOut, boolean electronFlow, boolean isClosed, ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(electronFlow, isClosed, type, position, btn, voltage, current, resistance, power, name, direction);
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
