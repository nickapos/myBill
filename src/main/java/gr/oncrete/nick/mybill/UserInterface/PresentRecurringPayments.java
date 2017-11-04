/*
 *  Copyright (C) 2010 nickapos
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

 /*
 * AboutWindow.java
 * This class will create a window with a text area and depending on the need
 * will present in the text are a message, be it an about or a readme.
 * Created on 11 Αυγ 2010, 9:51:33 μμ
 */
package gr.oncrete.nick.mybill.UserInterface;

import gr.oncrete.nick.mybill.BusinessLogic.FindRecurringPayments;
import java.util.*;
import java.util.stream.IntStream;
import javax.swing.JComboBox;

/**
 *
 * @author nickapos
 */
public class PresentRecurringPayments extends javax.swing.JFrame {

    JComboBox box;
    String[] comboBoxActions = {java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("INSERT"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("EDIT"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("DELETE")};//the dropdown list at the action combo boxes

    /**
     * Creates new form AboutWindow
     *
     * @param a
     */
    public PresentRecurringPayments(JComboBox a) {
        box = a;
        initComponents();
    }

    /**
     *
     */
    public PresentRecurringPayments() {
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

        cetnerPanel1 = new javax.swing.JPanel();
        periodBeginingLabel4 = new javax.swing.JLabel();
        recordFreqComboBox = new javax.swing.JComboBox();
        periodEndingjLabel1 = new javax.swing.JLabel();
        numberOfPaymentsLabel = new javax.swing.JLabel();
        periodBeginingLabel5 = new javax.swing.JLabel();
        averageAmountLabel = new javax.swing.JLabel();
        periodBeginingLabel7 = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        southPanel = new javax.swing.JPanel();
        goButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("PresentRecurringPayments.title_1")); // NOI18N
        setName("Manage Categories"); // NOI18N

        cetnerPanel1.setLayout(new java.awt.GridLayout(4, 2));

        periodBeginingLabel4.setText(bundle.getString("PresentRecurringPayments.periodBeginingLabel4.text")); // NOI18N
        cetnerPanel1.add(periodBeginingLabel4);

        recordFreqComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6", "7", "8", "9", "10", "11", "12" }));
        recordFreqComboBox.setSelectedItem(IntStream.rangeClosed(2005, 2100));
        cetnerPanel1.add(recordFreqComboBox);

        periodEndingjLabel1.setText(bundle.getString("PresentRecurringPayments.periodEndingjLabel1.text")); // NOI18N
        cetnerPanel1.add(periodEndingjLabel1);

        numberOfPaymentsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numberOfPaymentsLabel.setText(bundle.getString("PresentRecurringPayments.numberOfPaymentsLabel.text")); // NOI18N
        cetnerPanel1.add(numberOfPaymentsLabel);

        periodBeginingLabel5.setText(bundle.getString("PresentRecurringPayments.periodBeginingLabel5.text")); // NOI18N
        cetnerPanel1.add(periodBeginingLabel5);

        averageAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        averageAmountLabel.setText(bundle.getString("PresentRecurringPayments.averageAmountLabel.text")); // NOI18N
        cetnerPanel1.add(averageAmountLabel);

        periodBeginingLabel7.setText(bundle.getString("PresentRecurringPayments.periodBeginingLabel7.text")); // NOI18N
        cetnerPanel1.add(periodBeginingLabel7);

        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalLabel.setText(bundle.getString("PresentRecurringPayments.totalLabel.text")); // NOI18N
        cetnerPanel1.add(totalLabel);

        getContentPane().add(cetnerPanel1, java.awt.BorderLayout.CENTER);

        southPanel.setLayout(new java.awt.BorderLayout());

        goButton.setText(bundle.getString("PresentRecurringPayments.goButton.text")); // NOI18N
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });
        southPanel.add(goButton, java.awt.BorderLayout.NORTH);

        getContentPane().add(southPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        int freq = Integer.parseInt((String) recordFreqComboBox.getSelectedItem());
        FindRecurringPayments recPayments = new FindRecurringPayments(freq);
        numberOfPaymentsLabel.setText(String.format("%d", recPayments.getNumberOfRecurringPayments()));
        averageAmountLabel.setText(String.format("%6.2f", recPayments.returnMostFrequentTransactionsAverage()));
        totalLabel.setText(String.format("%6.2f", recPayments.returnMostFrequentTransactionsSum()));

    }//GEN-LAST:event_goButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PresentRecurringPayments().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel averageAmountLabel;
    private javax.swing.JPanel cetnerPanel1;
    private javax.swing.JButton goButton;
    private javax.swing.JLabel numberOfPaymentsLabel;
    private javax.swing.JLabel periodBeginingLabel4;
    private javax.swing.JLabel periodBeginingLabel5;
    private javax.swing.JLabel periodBeginingLabel7;
    private javax.swing.JLabel periodEndingjLabel1;
    private javax.swing.JComboBox recordFreqComboBox;
    private javax.swing.JPanel southPanel;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void displayApp() {
        this.setVisible(true);
    }

}
