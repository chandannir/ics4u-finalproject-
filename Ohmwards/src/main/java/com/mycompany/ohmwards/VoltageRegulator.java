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
public class VoltageRegulator extends PowerSource {
    private double maxVoltage;

    public VoltageRegulator(double maxVoltage, double voltageOut, ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(voltageOut, type, position, btn, voltage, current, resistance, power, name, direction);
        this.maxVoltage = maxVoltage;
    }
    
    public void setMaxVoltage(double newMaxVoltage) {
        this.maxVoltage = newMaxVoltage;
    }
    
    public double getMaxVoltage() {
        return this.maxVoltage;
    }
}
