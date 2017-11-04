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

import gr.oncrete.nick.mybill.BusinessLogic.FileHandlers.MyFileWriter;
import gr.oncrete.nick.mybill.BusinessLogic.FileHandlers.NewThreadFileWriter;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.DumpDatabase;
import java.util.*;

/**
 *
 * @author nickapos
 */
public class ExportCSVByYear extends javax.swing.JFrame {

    /**
     * Creates new form AboutWindow
     */
    public ExportCSVByYear() {
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
        yearsComboBox = new javax.swing.JComboBox();
        validateButton = new javax.swing.JButton();
        rowsAffectedLabel = new javax.swing.JLabel();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        jLabel1.setText(bundle.getString("ExportCSVByYear.jLabel1.text")); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("ExportCSVByYear.title")); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(0, 1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("ExportCSVByYear.jLabel2.text")); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2);

        yearsComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.yearsCombo(125)));
        getContentPane().add(yearsComboBox);

        validateButton.setText(bundle.getString("ExportCSVByYear.validateButton.text")); // NOI18N
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(validateButton);

        rowsAffectedLabel.setText(bundle.getString("ExportCSVByYear.rowsAffectedLabel.text")); // NOI18N
        getContentPane().add(rowsAffectedLabel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * this little method will return to the world an string array to be used in
     * combo boxes
     *
     * @param numOfYears
     * @return
     */
    private String[] yearsCombo(int numOfYears) {
        int startyear = 2006;
        String[] years = new String[numOfYears];
        for (int o = startyear, i = 0; i < 125; o++, i++) {
            years[i] = "" + o;
        }
        return years;
    }

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateButtonActionPerformed

        String selYear = (String) yearsComboBox.getSelectedItem();
        //System.out.println("Selected year "+ selYear);
        DumpDatabase dump = new DumpDatabase();
        ArrayList a = dump.getEforiaCsv("" + selYear);
        //this.writeFile("eforia-"+df1.format(d1)+".csv", a);
        this.writeFileNewThread("eforia-" + selYear + ".csv", a);

    }//GEN-LAST:event_validateButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ExportCSVByYear().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel rowsAffectedLabel;
    private javax.swing.JButton validateButton;
    private javax.swing.JComboBox yearsComboBox;
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
        int counter = 0;
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
