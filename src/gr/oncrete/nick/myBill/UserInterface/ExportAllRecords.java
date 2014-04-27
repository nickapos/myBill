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
package gr.oncrete.nick.myBill.UserInterface;

import java.util.*;
import java.text.*;
import gr.oncrete.nick.myBill.BusinessLogic.SelectInfo.DumpDatabase;
import gr.oncrete.nick.myBill.BusinessLogic.FileHandlers.MyFileWriter;
import gr.oncrete.nick.myBill.BusinessLogic.FileHandlers.NewThreadFileWriter;

/**
 *
 * @author nickapos
 */
public class ExportAllRecords extends javax.swing.JFrame {

    //the counter that will hold the number of affected rows
    int counter=0;
  
    /**
     * Creates new form AboutWindow
     */
    public ExportAllRecords() {
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
        validateButton = new javax.swing.JButton();
        rowsAffectedLabel = new javax.swing.JLabel();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle"); // NOI18N
        jLabel1.setText(bundle.getString("ExportAllRecords.jLabel1.text")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("ExportAllRecords.title")); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(0, 1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("ExportAllRecords.jLabel2.text")); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2);

        validateButton.setText(bundle.getString("ExportAllRecords.validateButton.text")); // NOI18N
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(validateButton);

        rowsAffectedLabel.setText(bundle.getString("ExportAllRecords.rowsAffectedLabel.text")); // NOI18N
        getContentPane().add(rowsAffectedLabel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateButtonActionPerformed

        
     DumpDatabase d = new DumpDatabase();
        //dump companies csv first
        ArrayList a = d.getCompaniesCsv();
        //this.writeFile("companiesCsv.csv", a);
        this.writeFileNewThread("companiesCsv.csv", a);

        //dump categories csv
        a = d.getCategoriesCsv();
        //this.writeFile("categoriesCsv.csv", a);
        this.writeFileNewThread("categoriesCsv.csv", a);

        //dump bills csv next
        a = d.getBillsCsv();
        //this.writeFile("billsCsv.csv", a);
        this.writeFileNewThread("billsCsv.csv", a);

        //dump income csv
        a = d.getIncomeCsv();
        //this.writeFile("incomeCsv.csv", a);
        this.writeFileNewThread("incomeCsv.csv", a);
    

    }//GEN-LAST:event_validateButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ExportAllRecords().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel rowsAffectedLabel;
    private javax.swing.JButton validateButton;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void displayGUI() {
        this.setVisible(true);
    }

    /**
     * method used to export the database into csv's
     *
     * @param filename
     * @param a
     */
    private void writeFile(String filename, ArrayList a) {
        MyFileWriter m = new MyFileWriter();
        m.createFile(filename);
        Iterator i = a.iterator();
        while (i.hasNext()) {
            String nextLine = (String) i.next();
            if (nextLine.length() > 0) {
                System.out.println(nextLine);
                m.writeToFile(nextLine);
            }
        }
        m.closeFile();
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
                content = content + nextLine + "\n";
                System.out.println(nextLine);
                this.updateRowsAffectedCount(++counter);
            }
        }
        NewThreadFileWriter m = new NewThreadFileWriter(filename, content);
        m.execute();

    }
      
    /**
     * this method will update the rows affected label with 
     * the number of rows exported
     * @param rows 
     */
    private void updateRowsAffectedCount(int rows)
    {
        String defaultLabel=" Rows exported: ";
        rowsAffectedLabel.setText(defaultLabel+rows);
    }
}
