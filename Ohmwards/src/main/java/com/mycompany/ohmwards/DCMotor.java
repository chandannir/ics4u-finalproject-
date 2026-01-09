/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ohmwards;

import java.util.Vector;

/**
 *
 * @author Cameron
 */
public class DCMotor extends Motor {
    
    private int rpm;
    
    public DCMotor(int rpm, boolean state, boolean electronFlow, double vIN, ConnectionType type, Vector<Double> position, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(state, electronFlow, vIN, type, position, voltage, current, resistance, power, name, direction);
        this.rpm = rpm;
    }
    
    public int getRPM() {
        return this.rpm;
    }
    
    public void setRPM(int newRPM) {
        this.rpm = newRPM;
    }
}
