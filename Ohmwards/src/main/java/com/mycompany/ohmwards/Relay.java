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
public class Relay extends PowerSource {
    private int voltsRequired;
    
    public Relay(Vector<Double> position, JButton btn) {
        super(9, ConnectionType.POWERSOURCE, position, btn, new JLabel("Output:"), new JLabel("Required Vols:"));
        this.voltsRequired = 12;
    }
    
    public void surge() {
    // TODO!
    }
    
    public void convert() {
    // TODO!
    }
}
