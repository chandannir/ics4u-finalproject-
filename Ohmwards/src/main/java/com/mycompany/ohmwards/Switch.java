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
    
    public Switch(Vector<Double> position, JButton btn) {
        super(ConnectionType.SWITCH, position, btn, new JLabel("Direction:"), new JLabel("State:"));
        direction = "Up";
        state = "false";
    }
    
    public String getDirection(){
        return direction;
    }
    public void setDirection(String dir){
        direction = dir;
    }
    
    public String getState(){
        return state;
    }
    public void setState(String newState){
        state = newState;
    }
}
