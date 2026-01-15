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
public class Diode extends Component {
    private boolean electronFlow;
    private Direction direction;
    
    public Diode(Vector<Double> position, JButton btn) {
        super(ConnectionType.MODULE, position, btn);
        this.electronFlow = false;
        this.direction = Direction.LEFT;
    }
   
    public boolean getElectronFlow() {
        return electronFlow;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setElectronFlow(boolean newFlow) {
        this.electronFlow = newFlow;
    }
    
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }
    
    public void Break() {
        // TODO!
    }
}
