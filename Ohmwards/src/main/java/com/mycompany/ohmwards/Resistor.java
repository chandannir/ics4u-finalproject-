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
public class Resistor extends Component {
    
    private int resistance;
    private String type;
    
    public Resistor(Vector<Double> position, JButton btn) {
        super(ConnectionType.RESISTOR, position, btn, new JLabel("Resistance:"), new JLabel("Type / Code:"));
        this.resistance = 220;
        this.type = "Carbon";
    }
    
    public void setResistance(int newResistance) {
        this.resistance = newResistance;
    }
    public int getResistance() {
        return this.resistance;
    }
    
    public String getType(){
        return type;
    }
    public void setType(String newType){
        type = newType;
    }
}
