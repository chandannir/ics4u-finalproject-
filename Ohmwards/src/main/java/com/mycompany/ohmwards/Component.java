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
public abstract class Component {
    private ConnectionType type;
    private Vector<Double> position;
    private JButton compBtn;
    private double voltage;
    private double current;
    private double resistance;
    private double power;
    
    public JLabel field1;
    public JLabel field2;
    
    public Component(ConnectionType type, Vector<Double> position, JButton btn, JLabel field1, JLabel field2)
    {
        this.type = type;
        this.position = position;
        compBtn = btn;
        
        this.field1 = field1;
        this.field2 = field2;
        
        Ohmwards.circ.addComponent(this);
    }
    
    // Getters + Setters
     
    // Calculate values
}
