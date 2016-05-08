/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import coordalgorythm.CoordAlgorythm;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Piotrek
 */
public class TrasyPanel extends javax.swing.JPanel {

    /**
     * Creates new form TrasyPanel
     */
    public TrasyPanel() {
        this.fc = new JFileChooser();       
        this.coordAlgo = new CoordAlgorythm();
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

        jDialog1 = new javax.swing.JDialog();
        jTextPath = new javax.swing.JTextField();
        jButtonBrowse = new javax.swing.JButton();
        jTextOdleglosc = new javax.swing.JTextField();
        jLabelInfoOdleglosc = new javax.swing.JLabel();
        jButtonGeneruj = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(472, 452));

        jTextPath.setText("wprowadź ścieżkę do pliku .kml z zapisanym śladem trasy");
        jTextPath.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextPathFocusGained(evt);
            }
        });

        jButtonBrowse.setText("Przeglądaj...");
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed(evt);
            }
        });

        jTextOdleglosc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextOdleglosc.setText("20");
        jTextOdleglosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextOdlegloscActionPerformed(evt);
            }
        });

        jLabelInfoOdleglosc.setText("podaj odległość przesunięcia śladu (m):");

        jButtonGeneruj.setText("Generuj");
        jButtonGeneruj.setEnabled(false);
        jButtonGeneruj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabelInfoOdleglosc, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jTextOdleglosc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGeneruj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextPath, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextOdleglosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInfoOdleglosc)
                    .addComponent(jButtonGeneruj))
                .addContainerGap(389, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseActionPerformed
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            jButtonGeneruj.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonBrowseActionPerformed

    private void jTextPathFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPathFocusGained
       jTextPath.setText("");
    }//GEN-LAST:event_jTextPathFocusGained

    private void jButtonGenerujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerujActionPerformed
        try {
            File selectedFile = fc.getSelectedFile();
            double odleglosc = Double.parseDouble(jTextOdleglosc.getText())/70000.0  ;
            coordAlgo.generateNewKMLFile(selectedFile, odleglosc);
            Desktop.getDesktop().open(Paths.get(selectedFile.getPath() + "new.kml").toFile());
            jButtonGeneruj.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(TrasyPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonGenerujActionPerformed

    private void jTextOdlegloscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextOdlegloscActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextOdlegloscActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBrowse;
    private javax.swing.JButton jButtonGeneruj;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabelInfoOdleglosc;
    private javax.swing.JTextField jTextOdleglosc;
    private javax.swing.JTextField jTextPath;
    // End of variables declaration//GEN-END:variables
    private final coordalgorythm.CoordAlgorythm coordAlgo;
    private final JFileChooser fc;
}
