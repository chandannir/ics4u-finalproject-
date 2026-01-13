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
public class Switch extends Component {
    
    private boolean electronFlow;
    private boolean isClosed;
    
    public Switch(boolean electronFlow, boolean isClosed, ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction) {
        super(type, position, btn, voltage, current, resistance, power, name, direction);
    }
    
    public void setElectronFlow(boolean newElectronFlow) {
        this.electronFlow = newElectronFlow;
    }
    
    public void setIsClosed(boolean newIsClosed) {
        this.isClosed = newIsClosed;
    }
    public boolean getElectronFlow() {
        return this.electronFlow;
    }
    
    public boolean getIsClosed() {
        return this.isClosed;
    }
}
