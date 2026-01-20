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
public class Servo extends Motor {
    
    private int degree;
    
    public Servo(Vector<Double> position, JButton btn) {
        super(position, btn, new JLabel("Torque:"), new JLabel("Degree:"));
        this.degree = 180;
    }
    
    public int getDegree() {
        return this.degree;
    }
 
    public void setDegree(int newDegree) {
        this.degree = newDegree;
    }
}
