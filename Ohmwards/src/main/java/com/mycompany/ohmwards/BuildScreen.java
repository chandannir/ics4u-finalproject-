/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ohmwards;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;

/**
 *
 * @author Cameron
 */
public class BuildScreen extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BuildScreen.class.getName());
    
    private Vector<Integer> pos1 = new Vector<Integer>();
    private Vector<Integer> pos2 = new Vector<Integer>();
    private java.util.List<java.awt.Point> componentIntersections = new java.util.ArrayList<>();
    private java.util.Map<JButton, java.awt.Point[]> componentMap = new java.util.HashMap<>();
    private java.awt.Point[] firstComponentIntersections = null;
    private javax.swing.JTextField circuitCheckField;
    
    private static class ComponentNode {
        JButton component;
        java.awt.Point[] intersections;
        ComponentNode next;
        
        ComponentNode(JButton component, java.awt.Point[] intersections) {
            this.component = component;
            this.intersections = intersections;
            this.next = null;
        }
    }
    
    private ComponentNode circuitHead = null;
    
    private Component comp;
    private String fieldTxt1;
    private String fieldTxt2;
    private java.util.Map<JButton, Component> buttonComponentMap = new java.util.HashMap<>();
    private java.util.Map<JButton, String[]> buttonFieldTextMap = new java.util.HashMap<>();
    
    private void updateCircuitTotals() {
        Calculations.CircuitType circuitType = Calculations.CircuitType.SERIES;
        if (circuitCheckField != null) {
            String text = circuitCheckField.getText();
            if (text != null && text.toLowerCase().contains("parallel")) {
                circuitType = Calculations.CircuitType.PARALLEL;
            }
        }
        Calculations.Result result = Calculations.calculateTotals(buttonComponentMap.values(), circuitType);
        voltLabel.setText(String.format("Voltage: %.2f V", result.getTotalVoltage()));
        resLabel.setText(String.format("Resistance: %.2f \u03a9", result.getTotalResistance()));
        curntLabel.setText(String.format("Current: %.2f A", result.getTotalCurrent()));
        powLabel.setText(String.format("Power: %.2f W", result.getTotalPower()));
    }
    
    /**
     * Creates new form BuildScreen
     */
    public BuildScreen() {
        initComponents();
        
        // Component choices for application...
        componentChoice.add("Resistor");
        componentChoice.add("DC Motor");
        componentChoice.add("Servo Motor");
        componentChoice.add("Stepper Motor");
        componentChoice.add("LED");
        componentChoice.add("Wire");
        componentChoice.add("Voltage Regulator");
        componentChoice.add("Transistor");
        componentChoice.add("Relay");
        componentChoice.add("Switch");
        componentChoice.add("Power Supply");
        
        if (jPanel2 instanceof GridPanel) {
            GridPanel gridPanel = (GridPanel) jPanel2;
            gridPanel.setComponentIntersections(componentIntersections);
            gridPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    java.awt.Point intersection = gridPanel.getIntersectionAt(e.getX(), e.getY());
                    if (intersection != null) {
                        java.awt.Point gridCoords = gridPanel.getGridCoordinates(intersection);
                        System.out.println("Intersection clicked at screen: (" + intersection.x + ", " + intersection.y + 
                                         "), grid: (" + gridCoords.x + ", " + gridCoords.y + ")");
                        
                        if(pos1.isEmpty()){
                            pos1.add(intersection.x);
                            pos1.add(intersection.y);
                        }
                        else{
                            pos2.add(intersection.x);
                            pos2.add(intersection.y);
                        }
                    }
                }
            });
        }
    }
    
    /**
     * Custom JPanel that displays a visual grid with dots at intersections
     */
    private class GridPanel extends javax.swing.JPanel {
        private static final double GRID_SIZE = 70;
        private static final int DOT_SIZE = 8; 
        private static final int CLICK_TOLERANCE = 10;
        private java.util.List<java.awt.Point> componentIntersections;
        
        public void setComponentIntersections(java.util.List<java.awt.Point> intersections) {
            this.componentIntersections = intersections;
        } 
        
        public GridPanel() {
            setBackground(new java.awt.Color(255, 255, 255));
            setOpaque(true);
            // Enable mouse events for intersection clicks
            setFocusable(true);
        }
        
        public java.awt.Point getIntersectionAt(int screenX, int screenY) {
            // Find nearest grid intersection
            int gridX = (int) (Math.round(screenX / GRID_SIZE) * GRID_SIZE);
            int gridY = (int) (Math.round(screenY / GRID_SIZE) * GRID_SIZE);
            
            // Check click within tolerance of an intersection
            double distance = Math.sqrt(Math.pow(screenX - gridX, 2) + Math.pow(screenY - gridY, 2));
            if (distance <= CLICK_TOLERANCE) {
                return new java.awt.Point(gridX, gridY);
            }
            return null;
        }
        
        
        public java.awt.Point getGridCoordinates(java.awt.Point intersection) {
            if (intersection == null) return null;
            int col = (int) (intersection.x / GRID_SIZE);
            int row = (int) (intersection.y / GRID_SIZE);
            return new java.awt.Point(col, row);
        }
        
        public boolean isDotVisibleAt(double x, double y) {
            if (componentIntersections == null || componentIntersections.isEmpty()) {
                return true;
            }
            for (java.awt.Point intersection : componentIntersections) {
                double dx = Math.abs(x - intersection.x);
                double dy = Math.abs(y - intersection.y);
                if ((dx == GRID_SIZE && dy == 0) || (dx == 0 && dy == GRID_SIZE)) {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            
            if (width <= 0 || height <= 0) {
                return;
            }
            
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
            try {
                // anti-aliasing for smoother lines
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
                                    java.awt.RenderingHints.VALUE_ANTIALIAS_OFF);
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_STROKE_CONTROL,
                                    java.awt.RenderingHints.VALUE_STROKE_PURE);
                
                // Draw grid lines
                g2d.setColor(new java.awt.Color(150, 150, 150));
                g2d.setStroke(new java.awt.BasicStroke(1.0f));
                
                // Draw vertical lines
                for (double x = 0; x <= width; x += GRID_SIZE) {
                    int lineX = (int) x;
                    g2d.drawLine(lineX, 0, lineX, height);
                }
                
                // Draw horizontal lines 
                for (double y = 0; y <= height; y += GRID_SIZE) {
                    int lineY = (int) y;
                    g2d.drawLine(0, lineY, width, lineY);
                }
                
                // Draw dots at each intersection
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
                                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new java.awt.Color(0, 100, 255));
                
                for (double x = 0; x <= width; x += GRID_SIZE) {
                    for (double y = 0; y <= height; y += GRID_SIZE) {
                        boolean shouldDraw = false;
                        
                        if (componentIntersections == null || componentIntersections.isEmpty()) {
                            shouldDraw = true;
                        } else {
                            for (java.awt.Point intersection : componentIntersections) {
                                double dx = Math.abs(x - intersection.x);
                                double dy = Math.abs(y - intersection.y);
                                if ((dx == GRID_SIZE && dy == 0) || (dx == 0 && dy == GRID_SIZE)) {
                                    shouldDraw = true;
                                    break;
                                }
                            }
                        }
                        
                        if (shouldDraw) {
                            int dotX = (int) x - DOT_SIZE / 2;
                            int dotY = (int) y - DOT_SIZE / 2;
                            g2d.fillOval(dotX, dotY, DOT_SIZE, DOT_SIZE);
                        }
                    }
                }
            } finally {
                g2d.dispose();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        placeBtn = new javax.swing.JButton();
        jPanel2 = new GridPanel();
        componentChoice = new java.awt.Choice();
        exitBtn = new javax.swing.JButton();
        voltLabel = new javax.swing.JLabel();
        curntLabel = new javax.swing.JLabel();
        resLabel = new javax.swing.JLabel();
        powLabel = new javax.swing.JLabel();
        projectTitle = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        placeBtn.setBackground(new java.awt.Color(255, 0, 0));
        placeBtn.setForeground(new java.awt.Color(255, 255, 255));
        placeBtn.setText("Place");
        placeBtn.addActionListener(this::placeBtnActionPerformed);

        jPanel2.setPreferredSize(new java.awt.Dimension(800, 358));

        componentChoice.setBackground(new java.awt.Color(0, 153, 0));
        componentChoice.setForeground(new java.awt.Color(255, 255, 255));
        componentChoice.addItemListener(this::choiceItemStateChanged);

        exitBtn.setBackground(new java.awt.Color(0, 51, 204));
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setText("Exit");
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        voltLabel.setForeground(new java.awt.Color(255, 255, 255));
        voltLabel.setText("Voltage: ");

        curntLabel.setForeground(new java.awt.Color(255, 255, 255));
        curntLabel.setText("Current: ");
        resLabel.setForeground(new java.awt.Color(255, 255, 255));
        resLabel.setText("Resistance: ");

        powLabel.setForeground(new java.awt.Color(255, 255, 255));
        powLabel.setText("Power: ");

        circuitCheckField = new javax.swing.JTextField();
        circuitCheckField.setBackground(new java.awt.Color(51, 51, 51));
        circuitCheckField.setForeground(new java.awt.Color(255, 255, 255));
        circuitCheckField.setText("Circuit Type: Open");
        circuitCheckField.setEditable(false);

        projectTitle.setText("Unknown #1");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Project Title:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(placeBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(componentChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(exitBtn))
                                    .addComponent(projectTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel1)))
                        .addGap(151, 151, 151)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(powLabel)
                            .addComponent(resLabel)
                            .addComponent(curntLabel)
                            .addComponent(voltLabel)
                            .addComponent(circuitCheckField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 260, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(projectTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(placeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(componentChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(circuitCheckField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(voltLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(curntLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(powLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        HomePage home = new HomePage();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void placeBtnActionPerformed(java.awt.event.ActionEvent evt){
        if(pos1.isEmpty() || pos2.isEmpty()){
            JOptionPane.showMessageDialog(null, "Both positions need to be selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if((!pos1.get(0).equals(pos2.get(0))) && (!pos1.get(1).equals(pos2.get(1)))){
                JOptionPane.showMessageDialog(null, "Cannot place components diagonally!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(pos1.equals(pos2)){
                JOptionPane.showMessageDialog(null, "Both positions need to be different!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                GridPanel gridPanel = (GridPanel) jPanel2;
                java.awt.Point p1 = new java.awt.Point(pos1.get(0), pos1.get(1));
                java.awt.Point p2 = new java.awt.Point(pos2.get(0), pos2.get(1));
                
                if (!gridPanel.isDotVisibleAt(p1.x, p1.y) || !gridPanel.isDotVisibleAt(p2.x, p2.y)) {
                    JOptionPane.showMessageDialog(null, "Can only place components between intersections with dots!", "Error", JOptionPane.ERROR_MESSAGE);
                    pos1.clear();
                    pos2.clear();
                    return;
                }
                
                double midX = (pos1.get(0) + pos2.get(0)) / 2.0;
                double midY = (pos1.get(1) + pos2.get(1)) / 2.0;
                
                Vector<Double> midPos = new Vector<>();
                midPos.add(midX);
                midPos.add(midY);
                
                componentIntersections.add(p1);
                componentIntersections.add(p2);
                
                jPanel2.setLayout(null);
                JButton component = new JButton("Component");
                jPanel2.add(component);
                component.setBounds((int) midX - 35, (int) midY - 15, 70, 30);
                jPanel2.setVisible(true); 
                component.setVisible(true);
                componentMap.put(component, new java.awt.Point[]{p1, p2});
                
                ComponentNode newNode = new ComponentNode(component, new java.awt.Point[]{p1, p2});
                if (circuitHead == null) {
                    circuitHead = newNode;
                    firstComponentIntersections = new java.awt.Point[]{p1, p2};
                } else {
                    ComponentNode current = circuitHead;
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = newNode;
                }
                
                checkCircuitType();
                updateCircuitTotals();
                
                switch(componentChoice.getSelectedItem()){
                    case "Resistor":
                        component.setText("Resistor");
                        Resistor res = new Resistor(midPos, component);
                        fieldTxt1 = Integer.toString(res.getResistance());
                        fieldTxt2 = res.getType();
                        comp = res;
                        break;
                    case "DC Motor":
                        component.setText("DC Motor");
                        DCMotor mot = new DCMotor(midPos, component);
                        fieldTxt1 = Integer.toString(mot.getTorque());
                        fieldTxt2 = Integer.toString(mot.getRPM());
                        comp = mot;
                        break;
                    case "Servo Motor":
                        component.setText("Servo");
                        Servo servoMot = new Servo(midPos, component);
                        fieldTxt1 = Integer.toString(servoMot.getTorque());
                        fieldTxt2 = Integer.toString(servoMot.getDegree());
                        comp = servoMot;
                        break;
                    case "Stepper Motor":
                        component.setText("Stepper");
                        Stepper stepMot = new Stepper(midPos, component);
                        fieldTxt1 = Integer.toString(stepMot.getTorque());
                        fieldTxt2 = Double.toString(stepMot.getStep());
                        comp = stepMot;
                        break;
                    case "LED":
                        component.setText("LED");
                        LED led = new LED(midPos, component);
                        fieldTxt1 = led.getDirection();
                        fieldTxt2 = led.getColour();
                        comp = led;
                        break;
                    case "Wire":
                        component.setText("Wire");
                        Wire wire = new Wire(midPos, component);
                        fieldTxt1 = wire.getColour();
                        fieldTxt2 = Integer.toString(wire.getGauge());
                        comp = wire;
                        break;
                    case "Voltage Regulator":
                        component.setText("Volt Reg");
                        VoltageRegulator vt = new VoltageRegulator(midPos, component);
                        fieldTxt1 = Double.toString(vt.getVoltageOut());
                        fieldTxt2 = Double.toString(vt.getMaxVoltage());
                        comp = vt;
                        break;
                    case "Transistor":
                        component.setText("Transistor");
                        Transistor transistor = new Transistor(midPos, component);
                        fieldTxt1 = Double.toString(transistor.getVoltageRequired());
                        fieldTxt2 = Double.toString(transistor.getVoltageOut());
                        comp = transistor;
                        break;
                    case "Relay":
                        component.setText("Relay");
                        Relay relay = new Relay(midPos, component);
                        fieldTxt1 = Double.toString(relay.getVoltageOut());
                        fieldTxt2 = Double.toString(relay.getVoltsRequired());
                        comp = relay;
                        break;
                    case "Switch":
                        component.setText("Switch");
                        Switch switchComp = new Switch(midPos, component);
                        fieldTxt1 = switchComp.getDirection();
                        fieldTxt2 = switchComp.getState();
                        comp = switchComp;
                        break;
                    case "Power Supply":
                        component.setText("Power Supply");
                        PowerSupply powerSupp = new PowerSupply(midPos, component);
                        fieldTxt1 = Double.toString(powerSupp.getVoltageOut());
                        fieldTxt2 = powerSupp.getBrand();
                        comp = powerSupp;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "There was an error with the system!", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
                
                if (comp != null) {
                    buttonComponentMap.put(component, comp);
                    buttonFieldTextMap.put(component, new String[]{fieldTxt1, fieldTxt2});
                }
                
                component.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        JDialog componentDialog = new JDialog(BuildScreen.this, "Component", true);
                        componentDialog.setLayout(new FlowLayout());
                        Component thisComp = buttonComponentMap.get(component);
                        String[] texts = buttonFieldTextMap.get(component);
                        String localField1 = texts != null ? texts[0] : "";
                        String localField2 = texts != null ? texts[1] : "";
                        
                        JTextField field1 = new JTextField(10);
                        field1.setText(localField1);
                        JTextField field2 = new JTextField(10);
                        field2.setText(localField2);
                        JButton deleteBtn = new JButton("Trash");
                        deleteBtn.setBackground(Color.RED);
                        deleteBtn.setForeground(Color.WHITE);
                        JButton editBtn = new JButton("Edit");
                        editBtn.setBackground(Color.GREEN);
                        editBtn.setForeground(Color.WHITE);
                        
                        JPanel panel = new JPanel();
                        if (thisComp != null) {
                            panel.add(thisComp.field1); panel.add(field1);
                            panel.add(thisComp.field2); panel.add(field2);
                        } else {
                            panel.add(field1);
                            panel.add(field2);
                        }
                        
                        boolean isCircuitClosed = circuitCheckField != null && 
                            circuitCheckField.getText() != null && 
                            circuitCheckField.getText().toLowerCase().contains("closed");
                        
                        if (isCircuitClosed && thisComp instanceof Resistor) {
                            Calculations.CircuitType circuitType = Calculations.CircuitType.SERIES;
                            if (circuitCheckField.getText().toLowerCase().contains("parallel")) {
                                circuitType = Calculations.CircuitType.PARALLEL;
                            }
                            double voltageDrop = Calculations.calculateVoltageDrop(
                                thisComp, 
                                buttonComponentMap.values(), 
                                circuitType
                            );
                            JLabel voltageDropLabel = new JLabel(String.format("Voltage Drop: %.2f V", voltageDrop));
                            panel.add(voltageDropLabel);
                        }
                        
                        panel.add(editBtn);
                        panel.add(deleteBtn);
                        
                        componentDialog.add(panel);
                        componentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        componentDialog.pack();
                        
                        editBtn.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                String newField1 = field1.getText();
                                String newField2 = field2.getText();
                                
                                if (thisComp != null) {
                                    try {
                                        if (thisComp instanceof Resistor) {
                                            ((Resistor) thisComp).setResistance(Integer.parseInt(newField1));
                                        } else if (thisComp instanceof PowerSource) {
                                            ((PowerSource) thisComp).setVoltageOut(Double.parseDouble(newField1));
                                        }
                                    } catch (NumberFormatException ignore) {
                                        
                                    }
                                }
                                
                                buttonFieldTextMap.put(component, new String[]{newField1, newField2});
                                updateCircuitTotals();
                            }
                        });
                        
                        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                java.awt.Point[] intersections = componentMap.get(component);
                                if (intersections != null) {
                                    componentIntersections.remove(intersections[0]);
                                    componentIntersections.remove(intersections[1]);
                                    componentMap.remove(component);
                                    buttonComponentMap.remove(component);
                                    buttonFieldTextMap.remove(component);
                                    
                                    ComponentNode prev = null;
                                    ComponentNode current = circuitHead;
                                    while (current != null) {
                                        if (current.component == component) {
                                            if (prev == null) {
                                                circuitHead = current.next;
                                            } else {
                                                prev.next = current.next;
                                            }
                                            break;
                                        }
                                        prev = current;
                                        current = current.next;
                                    }
                                    
                                    if (firstComponentIntersections != null && 
                                        (intersections[0].equals(firstComponentIntersections[0]) || 
                                         intersections[0].equals(firstComponentIntersections[1]) ||
                                         intersections[1].equals(firstComponentIntersections[0]) || 
                                         intersections[1].equals(firstComponentIntersections[1]))) {
                                        if (circuitHead != null) {
                                            firstComponentIntersections = circuitHead.intersections;
                                        } else {
                                            firstComponentIntersections = null;
                                        }
                                    }
                                }
                                component.setVisible(false);
                                jPanel2.remove(component);
                                componentDialog.dispose();
                                jPanel2.repaint();
                                checkCircuitType();
                                updateCircuitTotals();
                            }
                        });
                        
                        editBtn.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                System.out.println("Edited...");
                            }
                        });
                        
                        componentDialog.setLocationRelativeTo(component);
                        componentDialog.setVisible(true);
                    }
                });
                
                jPanel2.repaint();
            }
        }
        pos1.clear();
        pos2.clear();
    }

    private void choiceItemStateChanged(java.awt.event.ItemEvent evt){
        pos1.clear();
        pos2.clear();
    }
    
    private void checkCircuitType() {
        if (circuitCheckField == null) {
            return;
        }
        
        if (componentMap.isEmpty()) {
            circuitCheckField.setText("Open");
            return;
        }
        
        java.awt.Point[] firstIntersections = null;
        int minX = Integer.MAX_VALUE;
        
        for (java.util.Map.Entry<JButton, java.awt.Point[]> entry : componentMap.entrySet()) {
            java.awt.Point[] inters = entry.getValue();
            int compMinX = Math.min(inters[0].x, inters[1].x);
            if (compMinX < minX) {
                minX = compMinX;
                firstIntersections = inters;
            }
        }
        
        if (firstIntersections == null) {
            circuitCheckField.setText("Open");
            return;
        }
        
        java.awt.Point firstSmallerX = firstIntersections[0].x < firstIntersections[1].x 
            ? firstIntersections[0] 
            : firstIntersections[1];
        
        boolean closed = false;
        for (java.util.Map.Entry<JButton, java.awt.Point[]> entry : componentMap.entrySet()) {
            java.awt.Point[] inters = entry.getValue();
            if (inters == firstIntersections) {
                continue;
            }
            if (firstSmallerX.equals(inters[0]) || firstSmallerX.equals(inters[1])) {
                closed = true;
                break;
            }
        }
        
        if (!closed) {
            circuitCheckField.setText("Open");
            return;
        }
        
        int verticalBranches = 0;
        for (java.awt.Point[] inters : componentMap.values()) {
            if (inters[0].y != inters[1].y) {
                verticalBranches++;
            }
        }
        
        String circuitType = (verticalBranches > 2) ? "Closed, Parallel" : "Closed, Series";
        circuitCheckField.setText(circuitType);
    }
    
    private boolean isCircuitClosed(java.awt.Point firstSmallerX) {
        if (circuitHead == null || circuitHead.next == null) {
            return false;
        }
        
        ComponentNode current = circuitHead.next;
        while (current != null) {
            java.awt.Point p1 = current.intersections[0];
            java.awt.Point p2 = current.intersections[1];
            
            if (firstSmallerX.equals(p1) || firstSmallerX.equals(p2)) {
                return true;
            }
            
            current = current.next;
        }
        
        return false;
    }
    
    private java.util.Map<java.awt.Point, java.util.Set<java.awt.Point>> buildComponentGraph() {
        java.util.Map<java.awt.Point, java.util.Set<java.awt.Point>> graph = new java.util.HashMap<>();
        
        for (java.awt.Point[] intersections : componentMap.values()) {
            java.awt.Point p1 = intersections[0];
            java.awt.Point p2 = intersections[1];
            
            graph.putIfAbsent(p1, new java.util.HashSet<>());
            graph.putIfAbsent(p2, new java.util.HashSet<>());
            
            graph.get(p1).add(p2);
            graph.get(p2).add(p1);
        }
        
        return graph;
    }
    
    private boolean dfsPathExists(java.util.Map<java.awt.Point, java.util.Set<java.awt.Point>> graph, 
                                  java.awt.Point current, java.awt.Point target, 
                                  java.util.Set<java.awt.Point> visited) {
        if (current.equals(target)) {
            return true;
        }
        
        visited.add(current);
        java.util.Set<java.awt.Point> neighbors = graph.get(current);
        if (neighbors != null) {
            for (java.awt.Point neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    if (dfsPathExists(graph, neighbor, target, visited)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private java.util.List<java.awt.Point> buildCircuitPath(java.awt.Point start, java.awt.Point end) {
        java.util.Map<java.awt.Point, java.util.Set<java.awt.Point>> graph = buildComponentGraph();
        java.util.List<java.awt.Point> path = new java.util.ArrayList<>();
        java.util.Set<java.awt.Point> visited = new java.util.HashSet<>();
        
        if (buildPath(graph, start, end, visited, path)) {
            return path;
        }
        
        return null;
    }
    
    private boolean buildPath(java.util.Map<java.awt.Point, java.util.Set<java.awt.Point>> graph,
                             java.awt.Point current, java.awt.Point target,
                             java.util.Set<java.awt.Point> visited,
                             java.util.List<java.awt.Point> path) {
        path.add(current);
        
        if (current.equals(target)) {
            return true;
        }
        
        visited.add(current);
        java.util.Set<java.awt.Point> neighbors = graph.get(current);
        if (neighbors != null) {
            for (java.awt.Point neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    if (buildPath(graph, neighbor, target, visited, path)) {
                        return true;
                    }
                }
            }
        }
        
        path.remove(path.size() - 1);
        visited.remove(current);
        return false;
    }
    
    private int countYChanges(java.util.List<java.awt.Point> path) {
        if (path.size() < 2) {
            return 0;
        }
        
        int yChanges = 0;
        int lastY = path.get(0).y;
        
        for (int i = 1; i < path.size(); i++) {
            int currentY = path.get(i).y;
            if (currentY != lastY) {
                yChanges++;
                lastY = currentY;
            }
        }
        
        return yChanges;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BuildScreen().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice componentChoice;
    private javax.swing.JLabel curntLabel;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton placeBtn;
    private javax.swing.JLabel powLabel;
    private javax.swing.JTextField projectTitle;
    private javax.swing.JLabel resLabel;
    private javax.swing.JLabel voltLabel;
    // End of variables declaration//GEN-END:variables
}
