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

import gr.oncrete.nick.mybill.BusinessLogic.Categories;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author nickapos
 */
public class ImportBankStatementFrame extends javax.swing.JFrame {

    /** Creates new form AboutWindow */
    public ImportBankStatementFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bankLabel = new javax.swing.JLabel();
        payoffFrequencyComboBox = new javax.swing.JComboBox();
        categoryLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        loadFileButton = new javax.swing.JButton();
        ImportButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("ImportBankStatementFrame.title")); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.GridLayout(3, 2));

        bankLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bankLabel.setText(bundle.getString("ImportBankStatementFrame.bankLabel.text")); // NOI18N
        jPanel1.add(bankLabel);

        payoffFrequencyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TSB", "Bank of Scotland" }));
        jPanel1.add(payoffFrequencyComboBox);

        categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoryLabel.setText(bundle.getString("ImportBankStatementFrame.categoryLabel.text")); // NOI18N
        categoryLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(categoryLabel);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(this.getCategoriesCombo()));
        jPanel1.add(jComboBox1);

        loadFileButton.setText(bundle.getString("ImportBankStatementFrame.loadFileButton.text")); // NOI18N
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });
        jPanel1.add(loadFileButton);

        ImportButton.setText(bundle.getString("ImportBankStatementFrame.ImportButton.text")); // NOI18N
        ImportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ImportButton);

        getContentPane().add(jPanel1);

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

        jPanel2.add(jScrollPane1);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportButtonActionPerformed
        
    }//GEN-LAST:event_ImportButtonActionPerformed

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed

        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        //In response to a button click:
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            System.out.println("Opening: " + file.getName() );
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }//GEN-LAST:event_loadFileButtonActionPerformed

    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ImportBankStatementFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ImportButton;
    private javax.swing.JLabel bankLabel;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JComboBox payoffFrequencyComboBox;
    // End of variables declaration//GEN-END:variables

   
    
    private String[] getCategoriesCombo() {
        Categories com = new Categories();
        return com.categoriesNames();
    }
    
    /**
     *
     */
    public void showImportStatemenFrame() {
        this.setVisible(true);
    }
}