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
public abstract class Component {
    private ConnectionType type;
    private Vector<Double> position;
    private JButton compBtn;
    private double voltage;
    private double current;
    private double resistance;
    private double power;
    private String name;
    private Direction direction;
    
    public Component(ConnectionType type, Vector<Double> position, JButton btn, double voltage, double current, double resistance, double power, String name, Direction direction)
    {
        this.type = type;
        this.position = position;
        compBtn = btn;
        this.voltage = voltage;
        this.current = current;
        this.resistance = resistance;
        this.power = power;
        this.name = name;
        this.direction = direction;
    }
    
    // Getters + Setters
    
    public void displayPopup(){
        // Display the info and stuff in a popup menu on click
    }
}
