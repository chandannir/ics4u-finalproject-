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
public class Stepper extends Motor {
    
    private double step;
    
    public Stepper(Vector<Double> position, JButton btn) {
        super(position, btn, new JLabel("Torque:"), new JLabel("Step:"));
        this.step = 1.2;
    }
    
    public double getStep() {
        return this.step;
    }
 
    public void setStep(double newDegree) {
        this.step = newDegree;
    }
    
    // TODO figure out how to calcuate the degree
    // Or what this function will do
    public void calcSteps() {
        // TODO
    }
}
