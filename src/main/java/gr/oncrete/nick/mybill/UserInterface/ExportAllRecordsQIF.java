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

import gr.oncrete.nick.mybill.BusinessLogic.FileHandlers.NewThreadFileWriter;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.DumpDatabaseinQIF;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class ExportAllRecordsQIF extends javax.swing.JFrame {

    private final static Logger LOGGER = Logger.getLogger(ExportAllRecordsQIF.class.getName());
    //the counter that will hold the number of affected rows
    int counter = 0;

    /**
     * Creates new form AboutWindow
     */
    public ExportAllRecordsQIF() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        exportRecordsButton = new javax.swing.JButton();
        rowsAffectedLabel = new javax.swing.JLabel();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        jLabel1.setText(bundle.getString("ExportAllRecordsQIF.jLabel1.text")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("ExportAllRecordsQIF.title")); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(0, 1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("ExportAllRecordsQIF.jLabel2.text")); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2);

        exportRecordsButton.setText(bundle.getString("ExportAllRecordsQIF.exportRecordsButton.text")); // NOI18N
        exportRecordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportRecordsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exportRecordsButton);

        rowsAffectedLabel.setText(bundle.getString("ExportAllRecordsQIF.rowsAffectedLabel.text")); // NOI18N
        getContentPane().add(rowsAffectedLabel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportRecordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportRecordsButtonActionPerformed
        DumpDatabaseinQIF qifDump = new DumpDatabaseinQIF();

        ArrayList billsQIFArrayList = qifDump.getBillsQIF();
        ArrayList incomeQIFArrayList = qifDump.getIncomesQIF();

        this.writeFileNewThread("mybillExpenseData.qif", billsQIFArrayList);
        this.writeFileNewThread("mybillIncomeData.qif", incomeQIFArrayList);

    }//GEN-LAST:event_exportRecordsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ExportAllRecordsQIF().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportRecordsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel rowsAffectedLabel;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void displayGUI() {
        this.setVisible(true);
    }

    /**
     * this method can be used to write a file in a new separate thread
     *
     * @param filename
     * @param a
     */
    private void writeFileNewThread(String filename, ArrayList a) {
        String content = "";
        Iterator i = a.iterator();

        while (i.hasNext()) {
            String nextLine = (String) i.next();
            if (nextLine.length() > 0) {
                content += nextLine + "\n";
                LOGGER.log(Level.INFO, nextLine);
                this.updateRowsAffectedCount(++counter);
            }
        }
        NewThreadFileWriter m = new NewThreadFileWriter(filename, content);
        m.execute();

    }

    /**
     * this method will update the rows affected label with the number of rows
     * exported
     *
     * @param rows
     */
    private void updateRowsAffectedCount(int rows) {
        String defaultLabel = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" ROWS EXPORTED: ");
        rowsAffectedLabel.setText(defaultLabel + rows);
    }
}
