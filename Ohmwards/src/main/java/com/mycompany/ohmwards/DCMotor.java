/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ohmwards;
import javax.swing.JButton;
import java.util.Vector;
import javax.swing.JLabel;

/**
 *
 * @author Cameron
 */
public class DCMotor extends Motor {
    
    private int rpm;
    
    public DCMotor(Vector<Double> position, JButton btn) {
        super(position, btn, new JLabel("Torque:"), new JLabel("RPM:"));
        this.rpm = 50;
    }
    
    public int getRPM() {
        return this.rpm;
    }
    
    public void setRPM(int newRPM) {
        this.rpm = newRPM;
    }
}
