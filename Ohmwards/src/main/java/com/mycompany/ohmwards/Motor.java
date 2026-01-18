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
public abstract class Motor extends Component {
    private int torque;
    private boolean state;
    private boolean electronFlow;
    private double vIN;

    public Motor(Vector<Double> position, JButton btn, JLabel lab1, JLabel lab2) {
        super(ConnectionType.MODULE, position, btn, lab1, lab2);
        this.state = false;
        this.electronFlow = false;
        this.vIN = 5.0;
    }
    
    public boolean getState() {
        return state;
    }
    
    public boolean getElectronFlow() {
        return electronFlow;
    }
    
    public double vIN() {
        return vIN;
    }
    
    public void setElectronFlow(boolean newFlow) {
        this.electronFlow = newFlow;
    }
    
    public void setState(boolean newState) {
        this.state = newState;
    }
    
    public void setVIN(double newVIN) {
        this.vIN = newVIN;
    }
    
    public boolean getSignal() {
        // TODO
        return true;
    }
    
    // Possibly show a pop up that the motor is moving
    public void move() {
    // TODO
    }
}
