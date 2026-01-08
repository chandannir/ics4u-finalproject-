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
public class Motor extends Component {
    private boolean state;
    private boolean electronFlow;
    private double vIN;

    public Motor(boolean state, boolean electronFlow, double vIN, ConnectionType type, Vector<Double> position, double voltage, double current, double resistance, double power, String name, String direction) {
        this.state = state;
        this.electronFlow = electronFlow;
        this.vIN = vIN;
        super(type, position, voltage, current, resistance, power, name, direction);
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
}
