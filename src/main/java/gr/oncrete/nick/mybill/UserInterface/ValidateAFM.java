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

import gr.oncrete.nick.mybill.BusinessLogic.CheckAFM;
import gr.oncrete.nick.mybill.BusinessLogic.CheckVAT;

/**
 *
 * @author nickapos
 */
public class ValidateAFM extends javax.swing.JFrame {

    /** Creates new form AboutWindow */
    public ValidateAFM() {
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

        jLabel2 = new javax.swing.JLabel();
        afmTextField = new javax.swing.JTextField();
        validateButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        afmResultLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("ValidateAFM.title")); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout(5, 1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("ValidateAFM.jLabel2.text")); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2);
        getContentPane().add(afmTextField);

        validateButton.setText(bundle.getString("ValidateAFM.validateButton.text")); // NOI18N
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(validateButton);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle.getString("ValidateAFM.jLabel1.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1);

        afmResultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        afmResultLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(afmResultLabel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateButtonActionPerformed
        String incAFM = afmTextField.getText();
        System.out.println(incAFM);
        CheckAFM c = new CheckAFM(incAFM);
        CheckVAT cv = new CheckVAT(incAFM);
        if (c.returnResult() && !cv.returnResult()) {
            afmResultLabel.setText("AFM " + c.getAfm() + " is valid.");
        } else if (cv.returnResult() && !c.returnResult()) {
            afmResultLabel.setText("VAT " + c.getAfm() + " is valid.");
        } else {
            afmResultLabel.setText("AFM or VAT " + c.getAfm() + " is not valid.");
        }

    }//GEN-LAST:event_validateButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ValidateAFM().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afmResultLabel;
    private javax.swing.JTextField afmTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton validateButton;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void validateAFM() {
        this.setVisible(true);
    }
}
