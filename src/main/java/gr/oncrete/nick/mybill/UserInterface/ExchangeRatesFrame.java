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

import gr.oncrete.nick.mybill.BusinessLogic.NewThreadExchangeRatesParser;
import javax.swing.*;

/**
 *
 * @author nickapos
 */
public class ExchangeRatesFrame extends javax.swing.JFrame {

    NewThreadExchangeRatesParser eParser = new NewThreadExchangeRatesParser();
    //these two instance variables will be fed into the exchange rates parser in order
    //for us to be able to update the content of the text field when we click the use it
    //button
    JTextField expensesFcTextField = null;
    JTextField incomeFcTextField = null;
    //this will hold the exchange rates data values so that we can pass it back to the main app if needed
    String[][] exchangeData = null;

    /**
     * Creates new form ExchangeRatesWindow
     */
    public ExchangeRatesFrame(JTextField expensesForeignCurrencyTextField, JTextField incomeForeignCurrencyTextField) {
        expensesFcTextField = expensesForeignCurrencyTextField;
        incomeFcTextField = incomeForeignCurrencyTextField;
        this.initClass();
    }

    public ExchangeRatesFrame() {
        this.initClass();
    }

    /**
     * This is a helper method that will be used from both constructors in order
     * to avoid code repetition
     */
    private void initClass() {
        initComponents();
        eParser.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        retrieveRatesButton = new javax.swing.JButton();
        flippedRateButton = new javax.swing.JButton();
        useItButton = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportRatesTable = new javax.swing.JTable();
        bottomPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("ExchangeRatesFrame.title")); // NOI18N

        retrieveRatesButton.setText(bundle.getString("ExchangeRatesFrame.retrieveRatesButton.text")); // NOI18N
        retrieveRatesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrieveRatesButtonActionPerformed(evt);
            }
        });
        topPanel.add(retrieveRatesButton);

        flippedRateButton.setText(bundle.getString("ExchangeRatesFrame.flippedRateButton.text")); // NOI18N
        flippedRateButton.setToolTipText(bundle.getString("ExchangeRatesFrame.flippedRateButton.toolTipText")); // NOI18N
        flippedRateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flippedRateButtonActionPerformed(evt);
            }
        });
        topPanel.add(flippedRateButton);

        useItButton.setText(bundle.getString("ExchangeRatesFrame.useItButton.text")); // NOI18N
        useItButton.setToolTipText(bundle.getString("ExchangeRatesFrame.useItButton.toolTipText")); // NOI18N
        useItButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useItButtonActionPerformed(evt);
            }
        });
        topPanel.add(useItButton);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        centerPanel.setLayout(new java.awt.BorderLayout());

        reportRatesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Currency", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportRatesTable.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(reportRatesTable);

        centerPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void retrieveRatesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrieveRatesButtonActionPerformed

        if (!eParser.isEmpty()) {
            String[] columnNames = {java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("CURRENCY"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("ONE EURO VALUE IN CURRENCY")};
            exchangeData = eParser.presentRatesArray(0);
            reportRatesTable.setModel(new ExchangeRatesTableModel(exchangeData, columnNames));
            reportRatesTable.setAutoCreateRowSorter(true);//add a primitive sort by column function
        } else {
            this.parserIsEmptyMessage();
        }

    }//GEN-LAST:event_retrieveRatesButtonActionPerformed

    private void flippedRateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flippedRateButtonActionPerformed

        if (!eParser.isEmpty()) {
            String[] columnNames = {java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("CURRENCY"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("VALUE OF ONE CURRENCY COIN IN EURO")};
            exchangeData = eParser.presentRatesArray(1);
            reportRatesTable.setModel(new ExchangeRatesTableModel(exchangeData, columnNames));
            reportRatesTable.setAutoCreateRowSorter(true);//add a primitive sort by column function
        } else {
            this.parserIsEmptyMessage();
        }
    }//GEN-LAST:event_flippedRateButtonActionPerformed
    /**
     * This method will checkout if there is a selected cell in the exchange
     * rates table and if there is, at the click of the button Use it, it will
     * pass it back to the exchanges rate text field so that it can be used by
     * the app
     *
     * @param evt
     */
    private void useItButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useItButtonActionPerformed
        if (exchangeData != null && expensesFcTextField != null && incomeFcTextField != null) {
            String selectedValue = exchangeData[reportRatesTable.getSelectedRow()][1];
            expensesFcTextField.setText(selectedValue);
            incomeFcTextField.setText(selectedValue);
        }
    }//GEN-LAST:event_useItButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ExchangeRatesFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton flippedRateButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable reportRatesTable;
    private javax.swing.JButton retrieveRatesButton;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton useItButton;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void presentExchangeRateFrame() {
        this.setVisible(true);
    }

    /**
     * Call this method if eparser is empty and it will popup a little message
     *
     */
    private void parserIsEmptyMessage() {
        PopupMessageFrame p = new PopupMessageFrame();
        p.setNotification(java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("WAITING FOR DATA DOWNLOAD TO COMPLETE. PLEASE PRESS THE BUTTON AGAIN IN A FEW SECONDS!"));
    }
}
