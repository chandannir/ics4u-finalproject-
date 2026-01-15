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
public class Relay extends PowerSupply {
    private int voltsRequired;
    
    public Relay(double voltageOut, ConnectionType type, Vector<Double> position, JButton btn) {
        super(position, btn);
        this.voltsRequired = 5;
    }
    
    @Override
    public void surge() {
    // TODO!
    }
    
    public void convert() {
    // TODO!
    }
}
