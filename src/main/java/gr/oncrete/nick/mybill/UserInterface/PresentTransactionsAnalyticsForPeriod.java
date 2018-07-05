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

import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectAverageExpensesPerCompanyInRange;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JComboBox;

/**
 *
 * @author nickapos
 */
public class PresentTransactionsAnalyticsForPeriod extends javax.swing.JFrame {

    JComboBox box;
    String[] comboBoxActions = {java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("INSERT"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("EDIT"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("DELETE")};//the dropdown list at the action combo boxes

    /**
     * Creates new form AboutWindow
     *
     * @param a
     */
    public PresentTransactionsAnalyticsForPeriod(JComboBox a) {
        box = a;
        initComponents();
    }

    /**
     *
     */
    public PresentTransactionsAnalyticsForPeriod() {
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

        northPanel = new javax.swing.JPanel();
        periodBeginingLabel = new javax.swing.JLabel();
        periodStartYear = new javax.swing.JComboBox();
        periodStartMonth = new javax.swing.JComboBox();
        periodStartDay = new javax.swing.JComboBox();
        periodEndingjLabel = new javax.swing.JLabel();
        periodEndYear = new javax.swing.JComboBox();
        periodEndMonth = new javax.swing.JComboBox();
        periodEndDay = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        numOfRecordsRadioButton = new javax.swing.JRadioButton();
        amountRadioButton = new javax.swing.JRadioButton();
        averageRadioButton = new javax.swing.JRadioButton();
        centerPanel = new javax.swing.JPanel();
        goButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("PresentTransactionsAnalyticsForPeriod.title")); // NOI18N
        setName("Manage Categories"); // NOI18N

        northPanel.setLayout(new java.awt.GridLayout(3, 4));

        periodBeginingLabel.setText("Period beginning");
        northPanel.add(periodBeginingLabel);

        periodStartYear.setModel(new javax.swing.DefaultComboBoxModel(this.getYearsCombo()));
        periodStartYear.setSelectedItem(IntStream.rangeClosed(2005, 2100));
        northPanel.add(periodStartYear);

        periodStartMonth.setModel(new javax.swing.DefaultComboBoxModel(this.getMonthsCombo()));
        northPanel.add(periodStartMonth);

        periodStartDay.setModel(new javax.swing.DefaultComboBoxModel(this.getDaysCombo()));
        northPanel.add(periodStartDay);

        periodEndingjLabel.setText("Period ending");
        northPanel.add(periodEndingjLabel);

        periodEndYear.setModel(new javax.swing.DefaultComboBoxModel(this.getYearsCombo()));
        northPanel.add(periodEndYear);

        periodEndMonth.setModel(new javax.swing.DefaultComboBoxModel(this.getMonthsCombo()));
        northPanel.add(periodEndMonth);

        periodEndDay.setModel(new javax.swing.DefaultComboBoxModel(this.getDaysCombo()));
        northPanel.add(periodEndDay);

        jLabel1.setText(bundle.getString("PresentTransactionsAnalyticsForPeriod.jLabel1.text")); // NOI18N
        northPanel.add(jLabel1);

        numOfRecordsRadioButton.setText(bundle.getString("PresentTransactionsAnalyticsForPeriod.numOfRecordsRadioButton.text")); // NOI18N
        numOfRecordsRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numOfRecordsRadioButtonActionPerformed(evt);
            }
        });
        northPanel.add(numOfRecordsRadioButton);

        amountRadioButton.setText(bundle.getString("PresentTransactionsAnalyticsForPeriod.amountRadioButton.text")); // NOI18N
        amountRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountRadioButtonActionPerformed(evt);
            }
        });
        northPanel.add(amountRadioButton);

        averageRadioButton.setText(bundle.getString("PresentTransactionsAnalyticsForPeriod.averageRadioButton.text")); // NOI18N
        averageRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                averageRadioButtonActionPerformed(evt);
            }
        });
        northPanel.add(averageRadioButton);

        getContentPane().add(northPanel, java.awt.BorderLayout.NORTH);

        centerPanel.setLayout(new java.awt.BorderLayout());

        goButton.setText(bundle.getString("PresentTransactionsAnalyticsForPeriod.goButton.text")); // NOI18N
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });
        centerPanel.add(goButton, java.awt.BorderLayout.NORTH);

        resultTextArea.setColumns(20);
        resultTextArea.setRows(5);
        jScrollPane1.setViewportView(resultTextArea);

        centerPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        String startYear = (String) periodStartYear.getSelectedItem();
        String startMonth = (String) periodStartMonth.getSelectedItem();
        String startDay = (String) periodStartDay.getSelectedItem();
        String endYear = (String) periodEndYear.getSelectedItem();
        String endMonth = (String) periodEndMonth.getSelectedItem();
        String endDay = (String) periodEndDay.getSelectedItem();
        String startPeriod = startYear + "-" + startMonth + "-" + startDay;
        String endPeriod = endYear + "-" + endMonth + "-" + endDay;

        String resultStr = "";
        if (numOfRecordsRadioButton.isSelected()) {
            resultStr = "";
            resultTextArea.setText(resultStr);
            SelectAverageExpensesPerCompanyInRange analytics = new SelectAverageExpensesPerCompanyInRange(startPeriod.replace(" ", ""), endPeriod.replace(" ", ""), "NumOfRecords");
            resultStr = analytics.toString();
        } else if (amountRadioButton.isSelected()) {
            resultStr = "";
            resultTextArea.setText(resultStr);
            SelectAverageExpensesPerCompanyInRange analytics = new SelectAverageExpensesPerCompanyInRange(startPeriod.replace(" ", ""), endPeriod.replace(" ", ""), "Amount");
            resultStr = analytics.toString();
        } else if (averageRadioButton.isSelected()) {
            resultStr = "";
            resultTextArea.setText(resultStr);
            SelectAverageExpensesPerCompanyInRange analytics = new SelectAverageExpensesPerCompanyInRange(startPeriod.replace(" ", ""), endPeriod.replace(" ", ""), "Average");
            resultStr = analytics.toString();
        } else {
            resultStr = "";
            resultTextArea.setText(resultStr);
            SelectAverageExpensesPerCompanyInRange analytics = new SelectAverageExpensesPerCompanyInRange(startPeriod.replace(" ", ""), endPeriod.replace(" ", ""));
            resultStr = analytics.toString();
        }
        resultTextArea.setText(resultStr);
    }//GEN-LAST:event_goButtonActionPerformed

    private void numOfRecordsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numOfRecordsRadioButtonActionPerformed
        amountRadioButton.setSelected(false);
        averageRadioButton.setSelected(false);
    }//GEN-LAST:event_numOfRecordsRadioButtonActionPerformed

    private void amountRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountRadioButtonActionPerformed
        numOfRecordsRadioButton.setSelected(false);
        averageRadioButton.setSelected(false);
    }//GEN-LAST:event_amountRadioButtonActionPerformed

    private void averageRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averageRadioButtonActionPerformed
        numOfRecordsRadioButton.setSelected(false);
        amountRadioButton.setSelected(false);
    }//GEN-LAST:event_averageRadioButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PresentTransactionsAnalyticsForPeriod().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton amountRadioButton;
    private javax.swing.JRadioButton averageRadioButton;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton goButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel northPanel;
    private javax.swing.JRadioButton numOfRecordsRadioButton;
    private javax.swing.JLabel periodBeginingLabel;
    private javax.swing.JComboBox periodEndDay;
    private javax.swing.JComboBox periodEndMonth;
    private javax.swing.JComboBox periodEndYear;
    private javax.swing.JLabel periodEndingjLabel;
    private javax.swing.JComboBox periodStartDay;
    private javax.swing.JComboBox periodStartMonth;
    private javax.swing.JComboBox periodStartYear;
    private javax.swing.JTextArea resultTextArea;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void displayApp() {
        this.setVisible(true);
    }

    private String[] getYearsCombo() {
        List<Integer> years = IntStream.rangeClosed(2005, 2100).boxed().collect(Collectors.toList());
        return years.toString().replace("[", "").replace("]", "").split(",");
    }

    private String[] getDaysCombo() {
        List<Integer> days = IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList());
        return days.toString().replace("[", "").replace("]", "").split(",");
    }

    private String[] getMonthsCombo() {
        List<Integer> months = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
        return months.toString().replace("[", "").replace("]", "").split(",");
    }

}
