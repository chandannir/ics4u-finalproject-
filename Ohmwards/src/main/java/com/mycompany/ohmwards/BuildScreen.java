/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ohmwards;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JButton;

/**
 *
 * @author Cameron
 */
public class BuildScreen extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BuildScreen.class.getName());
    
    private Vector<Integer> pos1 = new Vector<Integer>();
    private Vector<Integer> pos2 = new Vector<Integer>();

    /**
     * Creates new form BuildScreen
     */
    public BuildScreen() {
        initComponents();
        if (jPanel2 instanceof GridPanel) {
            GridPanel gridPanel = (GridPanel) jPanel2;
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
    private static class GridPanel extends javax.swing.JPanel {
        private static final double GRID_SIZE = 70;
        private static final int DOT_SIZE = 8; 
        private static final int CLICK_TOLERANCE = 10; 
        
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
                g2d.setColor(new java.awt.Color(0, 100, 255)); // Blue color
                
                for (double x = 0; x <= width; x += GRID_SIZE) {
                    for (double y = 0; y <= height; y += GRID_SIZE) {
                        int dotX = (int) x - DOT_SIZE / 2;
                        int dotY = (int) y - DOT_SIZE / 2;
                        g2d.fillOval(dotX, dotY, DOT_SIZE, DOT_SIZE);
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
                            .addComponent(voltLabel))
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
        // Validation Checks
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
                // Place a btn at the midPoint
                double minX = (pos1.get(0) + pos2.get(0)) / 2.0;
                double minY = (pos1.get(1) + pos2.get(1)) / 2.0;
                
                jPanel2.setLayout(null);
                JButton component = new JButton("Component");
                jPanel2.add(component);
                component.setBounds((int) minX - 35, (int) minY - 15, 70, 30);
                jPanel2.setVisible(true); 
                component.setVisible(true);
                
                component.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        // Code to run when button is clicked
                        System.out.println("Button clicked!");
                    }
                });
            }
        }
        pos1.clear();
        pos2.clear();
    }

    private void choiceItemStateChanged(java.awt.event.ItemEvent evt){
        // Reset positions
        pos1.clear();
        pos2.clear();
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
