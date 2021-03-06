/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Michał
 */
public class MissionPanel extends javax.swing.JPanel {

    /**
     * Creates new form MissionPanel
     */
    public MissionPanel() {
        this.fc = new JFileChooser();
        this.fc.setFileFilter(new FileNameExtensionFilter("pliki *.kml", "kml"));
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WybierzKmlButton = new javax.swing.JButton();
        jButtonGeneruj = new javax.swing.JButton();

        WybierzKmlButton.setText("Wybierz plik *.kml");
        WybierzKmlButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WybierzKmlButtonActionPerformed(evt);
            }
        });

        jButtonGeneruj.setText("Generuj");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(WybierzKmlButton)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonGeneruj)
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(WybierzKmlButton)
                .addGap(18, 18, 18)
                .addComponent(jButtonGeneruj)
                .addContainerGap(207, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void WybierzKmlButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WybierzKmlButtonActionPerformed
        // TODO add your handling code here:
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            jButtonGeneruj.setEnabled(true);
        }
    }//GEN-LAST:event_WybierzKmlButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton WybierzKmlButton;
    private javax.swing.JButton jButtonGeneruj;
    // End of variables declaration//GEN-END:variables
  private final JFileChooser fc;
}
