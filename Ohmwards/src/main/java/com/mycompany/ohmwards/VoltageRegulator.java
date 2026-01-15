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

    public VoltageRegulator(Vector<Double> position, JButton btn) {
        super(5, ConnectionType.POWERSOURCE, position, btn);
        this.maxVoltage = 12;
    }
    
    public void setMaxVoltage(double newMaxVoltage) {
        this.maxVoltage = newMaxVoltage;
    }
    
    public double getMaxVoltage() {
        return this.maxVoltage;
    }
}
