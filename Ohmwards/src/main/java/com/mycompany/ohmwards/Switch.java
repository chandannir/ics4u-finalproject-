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
public class Switch extends Component {
    
    private String direction;
    private String state;
    
    private boolean electronFlow;
    private boolean isClosed;
    
    public Switch(Vector<Double> position, JButton btn) {
        super(ConnectionType.SWITCH, position, btn, new JLabel("Direction:"), new JLabel("State:"));
        direction = "Up";
        state = "false";
        this.electronFlow = false;
        this.isClosed = false;
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
