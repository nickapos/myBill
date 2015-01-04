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

import gr.oncrete.nick.mybill.BusinessLogic.Companies;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectCompanyDetails;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectAllBillsDetailsForSpecificCompany;
import gr.oncrete.nick.mybill.BusinessLogic.NewThreadMergeCompanyRecords;
import javax.swing.JComboBox;
import java.util.List;

/**
 *
 * @author nickapos
 */
public class MergeCompanyRecordsFrame extends javax.swing.JFrame {

    JComboBox box;

    /** Creates new form AboutWindow
     * @param a
     */
    public MergeCompanyRecordsFrame(JComboBox a) {
        box = a;
        initComponents();
    }

    /**
     * 
     */
    public MergeCompanyRecordsFrame() {
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

        westPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        compToBeMergedComboBox = new javax.swing.JComboBox();
        compToRemainComboBox = new javax.swing.JComboBox();
        bottomPanel = new javax.swing.JPanel();
        goButton = new javax.swing.JButton();
        progressLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("MergeCompanyRecordsFrame.title")); // NOI18N
        setName("Manage Categories"); // NOI18N

        westPanel.setLayout(new java.awt.GridLayout(0, 1));

        jLabel1.setText(bundle.getString("MergeCompanyRecordsFrame.jLabel1.text")); // NOI18N
        westPanel.add(jLabel1);

        jLabel2.setText(bundle.getString("MergeCompanyRecordsFrame.jLabel2.text")); // NOI18N
        westPanel.add(jLabel2);

        getContentPane().add(westPanel, java.awt.BorderLayout.WEST);

        rightPanel.setLayout(new java.awt.GridLayout(0, 1));

        compToBeMergedComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.getCompaniesCombo()));
        compToBeMergedComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compToBeMergedComboBoxActionPerformed(evt);
            }
        });
        rightPanel.add(compToBeMergedComboBox);

        compToRemainComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.getCompaniesCombo()));
        rightPanel.add(compToRemainComboBox);

        getContentPane().add(rightPanel, java.awt.BorderLayout.EAST);

        bottomPanel.setLayout(new java.awt.GridLayout(1, 0));

        goButton.setText(bundle.getString("MergeCompanyRecordsFrame.goButton.text")); // NOI18N
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(goButton);

        progressLabel.setText(bundle.getString("MergeCompanyRecordsFrame.progressLabel.text")); // NOI18N
        bottomPanel.add(progressLabel);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void compToBeMergedComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compToBeMergedComboBoxActionPerformed
    }//GEN-LAST:event_compToBeMergedComboBoxActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        String compToBeMerged = (String) compToBeMergedComboBox.getSelectedItem();
        SelectCompanyDetails compToBeMergedDetails = new SelectCompanyDetails();
        compToBeMergedDetails.SelectCompanyDetailsWithName(compToBeMerged);
        SelectAllBillsDetailsForSpecificCompany billRecordsToBeChanged=new SelectAllBillsDetailsForSpecificCompany(compToBeMergedDetails.getID());
        billRecordsToBeChanged.getAllRows();
        List billRecordsToBeChangedList=billRecordsToBeChanged.getIds();


        String compToRemain = (String) compToRemainComboBox.getSelectedItem();
        SelectCompanyDetails compToRemainDetails = new SelectCompanyDetails();
        compToRemainDetails.SelectCompanyDetailsWithName(compToRemain);

        NewThreadMergeCompanyRecords merge = new NewThreadMergeCompanyRecords(billRecordsToBeChangedList,compToRemainDetails.getID(),progressLabel);
        merge.doInBackground();
    }//GEN-LAST:event_goButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MergeCompanyRecordsFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JComboBox compToBeMergedComboBox;
    private javax.swing.JComboBox compToRemainComboBox;
    private javax.swing.JButton goButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel progressLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel westPanel;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void displayApp() {
        this.setVisible(true);
    }

    private String[] getCompaniesCombo() {
        Companies com = new Companies();
        return com.companyNames();
    }
}
