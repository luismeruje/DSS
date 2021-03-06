/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceUtilizador;

import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import negocio.GestorTurnos;
import negocio.Turno;
import negocio.UC;

/**
 *
 * @author LuisFerreira
 */
public class GerirUCGUI extends javax.swing.JFrame {

    /**
     * Creates new form GerirUCGUI
     */
    public String nomeUC;
    
    public GerirUCGUI(String uc) {
        this.nomeUC = uc;
        initComponents();
        List<Turno> turnos = GestorTurnos.getInfoTurnos(uc);
        if(turnos!=null){
            Iterator it =turnos.iterator();
            int i = 0;

            DefaultTableModel model = new DefaultTableModel(new Object[]{"Nr. Turno","Sala","Tipo","Docente"} ,turnos.size());
            jTable1.setModel(model);
            jTable1.setRowSelectionAllowed(true);
            jTable1.setColumnSelectionAllowed(false);
            while(it.hasNext()){
                Turno turno = (Turno)it.next();
                jTable1.setValueAt(turno.getNumero(),i,0);
                jTable1.setValueAt(turno.getSala(),i,1);
                jTable1.setValueAt(turno.getTipo(),i,2);
                jTable1.setValueAt(turno.getIdDocente(),i,3);
                i++;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Gerir Turno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jButton1)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int row = jTable1.getSelectedRow();
       if(row>=0){
            int numeroTurno = (int)jTable1.getValueAt(row,0);
            JFrame novoUserFrame = new GerirTurnoGUI(nomeUC,numeroTurno);
            novoUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            novoUserFrame.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
