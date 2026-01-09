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
public class Servo extends Motor {
    
    private int degree;
    
    public Servo(int degree, boolean state, boolean electronFlow, double vIN, ConnectionType type, Vector<Double> position, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(state, electronFlow, vIN, type, position, voltage, current, resistance, power, name, direction);
        this.degree = degree;
    }
    
    public int getDegree() {
        return this.degree;
    }
 
    public void setDegree(int newDegree) {
        this.degree = newDegree;
    }
    
    // Figure out what this will do
    public void position() {
        // TODO
    }
}
