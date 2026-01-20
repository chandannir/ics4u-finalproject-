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

    public Motor(Vector<Double> position, JButton btn, JLabel lab1, JLabel lab2) {
        super(ConnectionType.MODULE, position, btn, lab1, lab2);
        torque = 120;
    }
    
    public int getTorque(){
        return torque;
    }
    public void setTorque(int newTorque){
        torque = newTorque;
    }
}
