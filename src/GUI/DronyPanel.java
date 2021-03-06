/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import controllers.DatabaseController;
import database.Drony;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Piotrek
 */
public class DronyPanel extends javax.swing.JPanel {

    public DronyPanel() {
        initComponents();
        kontroler = new DatabaseController();
        refreshTableContent();
    }

    private void refreshTableContent() {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();

        List<Drony> drony = kontroler.getAllDrony();
        for (Drony dron : drony) {
            String nazwa = "brak danych";
            if (dron.getParametryDronow() != null) {
                nazwa = dron.getParametryDronow().getModel();
            }
            String stacja = dron.getIDstacji().getNazwastacji();
            Float przebieg = dron.getPrzebieg();
            boolean czyAktywny = dron.getCzyAktywny() != 0;
            boolean czyZadokowany = dron.getCzyZadokowany() != 0;

            model.addRow(new Object[]{nazwa, stacja, przebieg, czyAktywny, czyZadokowany});
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

        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonUsun = new javax.swing.JButton();
        jButtonDodaj = new javax.swing.JButton();
        jButtonPowrot = new javax.swing.JButton();

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nazwa drona", "Stacja", "Przebieg", "Aktywny", "Zadokowany"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        jButtonUsun.setText("Usuń zaznaczonego drona");
        jButtonUsun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsunActionPerformed(evt);
            }
        });

        jButtonDodaj.setText("Dodaj nowego drona");
        jButtonDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajActionPerformed(evt);
            }
        });

        jButtonPowrot.setText("Wydaj polecenie powrotu do bazy");
        jButtonPowrot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPowrotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonUsun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPowrot))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDodaj)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonDodaj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonUsun)
                    .addComponent(jButtonPowrot))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Funkcjonalność aktualnie rozwijana", "Błąd!", 0);
    }//GEN-LAST:event_jButtonDodajActionPerformed

    private void jButtonUsunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsunActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Funkcjonalność aktualnie rozwijana", "Błąd!", 0);
    }//GEN-LAST:event_jButtonUsunActionPerformed

    private void jButtonPowrotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPowrotActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Funkcjonalność aktualnie rozwijana", "Błąd!", 0);
    }//GEN-LAST:event_jButtonPowrotActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodaj;
    private javax.swing.JButton jButtonPowrot;
    private javax.swing.JButton jButtonUsun;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
    private DatabaseController kontroler;
}
