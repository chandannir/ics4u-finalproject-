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
public class Stepper extends Motor {
    
    private int degree;
    
    public Stepper(boolean state, boolean electronFlow, double vIN, ConnectionType type, Vector<Double> position, double voltage, double current, double resistance, double power, String name, String direction) {
        super(state, electronFlow, vIN, type, position, voltage, current, resistance, power, name, direction);
    }
    
    public int getDegree() {
        return this.degree;
    }
 
    public void setDegree(int newDegree) {
        this.degree = newDegree;
    }
    
    // TODO figure out how to calcuate the degree
    // Or what this function will do
    public void calcSteps() {
        // TODO
    }
}
