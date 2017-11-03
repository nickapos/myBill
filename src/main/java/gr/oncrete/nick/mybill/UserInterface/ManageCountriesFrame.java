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
import gr.oncrete.nick.mybill.BusinessLogic.DeleteCategory;
import gr.oncrete.nick.mybill.BusinessLogic.InsertCategory;
import gr.oncrete.nick.mybill.BusinessLogic.UpdateInfo.UpdateCategoryRecord;
import javax.swing.JComboBox;

/**
 *
 * @author nickapos
 */
public class ManageCountriesFrame extends javax.swing.JFrame {
    JComboBox box;
    String[] comboBoxActions = {java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("INSERT"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("EDIT"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("DELETE")};//the dropdown list at the action combo boxes
    /** Creates new form AboutWindow
     * @param a
     */
    public ManageCountriesFrame(JComboBox a) {
        box=a;
        initComponents();
    }
    /**
     * 
     */
    public ManageCountriesFrame() {
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
        jLabel3 = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        catActionComboBox = new javax.swing.JComboBox();
        countShortNComboBox = new javax.swing.JComboBox();
        catNameTextField = new javax.swing.JTextField();
        bottomPanel = new javax.swing.JPanel();
        retrieveCategoryButton = new javax.swing.JButton();
        goButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("ManageCountriesFrame.title")); // NOI18N
        setName("Manage Categories"); // NOI18N

        westPanel.setLayout(new java.awt.GridLayout(0, 1));

        jLabel1.setText(bundle.getString("ManageCountriesFrame.jLabel1.text")); // NOI18N
        westPanel.add(jLabel1);

        jLabel2.setText(bundle.getString("ManageCountriesFrame.jLabel2.text")); // NOI18N
        westPanel.add(jLabel2);

        jLabel3.setText(bundle.getString("ManageCountriesFrame.jLabel3.text")); // NOI18N
        westPanel.add(jLabel3);

        getContentPane().add(westPanel, java.awt.BorderLayout.WEST);

        rightPanel.setLayout(new java.awt.GridLayout(0, 1));

        catActionComboBox.setModel(new javax.swing.DefaultComboBoxModel(comboBoxActions));
        catActionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catActionComboBoxActionPerformed(evt);
            }
        });
        rightPanel.add(catActionComboBox);

        countShortNComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.getCategoriesCombo()));
        countShortNComboBox.setEnabled(false);
        rightPanel.add(countShortNComboBox);

        catNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catNameTextFieldActionPerformed(evt);
            }
        });
        rightPanel.add(catNameTextField);

        getContentPane().add(rightPanel, java.awt.BorderLayout.EAST);

        bottomPanel.setLayout(new java.awt.GridLayout(1, 0));

        retrieveCategoryButton.setText(bundle.getString("ManageCountriesFrame.retrieveCategoryButton.text")); // NOI18N
        bottomPanel.add(retrieveCategoryButton);

        goButton.setText(bundle.getString("ManageCountriesFrame.goButton.text")); // NOI18N
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(goButton);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void catNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catNameTextFieldActionPerformed

    private void catActionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catActionComboBoxActionPerformed
        int a = catActionComboBox.getSelectedIndex();
        if (a == 0) //the action is insert
        {
            this.countShortNComboBox.setEnabled(false);
        } else if (a == 1) //the action is edit
        {
            this.countShortNComboBox.setEnabled(true);
        } else if (a == 2) //the action is delete
        {
            this.countShortNComboBox.setEnabled(true);
        }
    }//GEN-LAST:event_catActionComboBoxActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        int a = catActionComboBox.getSelectedIndex();
        if (a == 0) //the action is insert
        {
            String catName = this.catNameTextField.getText();
            if (catName.length() > 0) {
                InsertCategory inC = new InsertCategory(catName);
                this.emptyFields();
                this.refreshMainCatIDCombo();
            }
        } else if (a == 1) //the action is edit
        {
            String oldCatName = (String) this.countShortNComboBox.getSelectedItem();
            String newName = catNameTextField.getText();
            if (newName.length() > 0) {
                UpdateCategoryRecord u = new UpdateCategoryRecord(oldCatName,newName);

                this.emptyFields();
                this.refreshMainCatIDCombo();
            }
        } else if (a == 2) //the action is delete
        {
            String catName = (String) this.countShortNComboBox.getSelectedItem();
            if (catName.length() > 0) {
                DeleteCategory d = new DeleteCategory();
                d.DeleteCategoryWithName(catName);
                this.emptyFields();
                this.refreshMainCatIDCombo();
            }
        }
    }//GEN-LAST:event_goButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ManageCountriesFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JComboBox catActionComboBox;
    private javax.swing.JTextField catNameTextField;
    private javax.swing.JComboBox countShortNComboBox;
    private javax.swing.JButton goButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton retrieveCategoryButton;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel westPanel;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void displayApp() {
        this.setVisible(true);
    }

    private void emptyFields() {

        this.catNameTextField.setText("");
        this.refreshCatIDCombo();
    }

    private String[] getCategoriesCombo() {
        Categories com = new Categories();
        return com.categoriesNames();
    }

    private void refreshCatIDCombo() {
        countShortNComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.getCategoriesCombo()));
    }

    /**
     * refresh the combo at the mail interface
     */
    private void refreshMainCatIDCombo() {
        box.setModel(new javax.swing.DefaultComboBoxModel(this.getCategoriesCombo()));
    }

}
