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
public class PowerSupply extends PowerSource {
    private String brand;
    
    public PowerSupply(Vector<Double> position, JButton btn) {
        super(9, ConnectionType.POWERSOURCE, position, btn, new JLabel("Voltage:"), new JLabel("Brand:"));
        brand = "Microsoft";
    }
    
    public String getBrand(){
        return brand;
    }
    public void setBrand(String newBrand){
        brand = newBrand;
    }
}
