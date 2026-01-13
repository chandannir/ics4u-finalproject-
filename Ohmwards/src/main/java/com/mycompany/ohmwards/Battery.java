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
public class Battery extends PowerSource {
    
    public Battery(double voltageOut, ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(voltageOut, type, position, btn, voltage, current, resistance, power, name, direction);
    }
    
    public void surge() {
        // TODO!
    }
}
