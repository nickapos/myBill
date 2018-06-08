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
import gr.oncrete.nick.mybill.BusinessLogic.InsertBills;
import gr.oncrete.nick.mybill.BusinessLogic.InsertCompany;
import gr.oncrete.nick.mybill.BusinessLogic.InsertIncome;
import gr.oncrete.nick.mybill.BusinessLogic.ParseBOSCsv;
import gr.oncrete.nick.mybill.BusinessLogic.ParseCsv;
import gr.oncrete.nick.mybill.BusinessLogic.ParsePancretaBankCsv;
import gr.oncrete.nick.mybill.BusinessLogic.ParseTSBCsv;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.Category;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectCompanyDetails;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nickapos
 */
public class ImportBankStatementFrame extends javax.swing.JFrame {

    /**
     * Creates new form AboutWindow
     */
    public ImportBankStatementFrame() {
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

        jPanel1 = new javax.swing.JPanel();
        bankLabel = new javax.swing.JLabel();
        bankComboBox = new javax.swing.JComboBox();
        categoryLabel = new javax.swing.JLabel();
        importBankSCategoryComboBox = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        foreignExchangeCheckBox = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        foreignExchangeTextField = new javax.swing.JTextField();
        showRatesButton = new javax.swing.JButton();
        loadFileButton = new javax.swing.JButton();
        ImportButton = new javax.swing.JButton();
        selectAllButton = new javax.swing.JButton();
        unselectAllButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        recordsImportedLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("ImportBankStatementFrame.title")); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.GridLayout(6, 2));

        bankLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bankLabel.setText(bundle.getString("ImportBankStatementFrame.bankLabel.text")); // NOI18N
        jPanel1.add(bankLabel);

        bankComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TSB", "Bank of Scotland", "Pancretan Bank", "N26", "Fidor UK", "Fidor DE", "Alpha Bank" }));
        bankComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(bankComboBox);

        categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoryLabel.setText(bundle.getString("ImportBankStatementFrame.categoryLabel.text")); // NOI18N
        categoryLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(categoryLabel);

        importBankSCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.getCategoriesCombo()));
        jPanel1.add(importBankSCategoryComboBox);

        jLabel1.setText(bundle.getString("ImportBankStatementFrame.jLabel1.text")); // NOI18N
        jPanel3.add(jLabel1);

        foreignExchangeCheckBox.setText(bundle.getString("ImportBankStatementFrame.foreignExchangeCheckBox.text")); // NOI18N
        foreignExchangeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foreignExchangeCheckBoxActionPerformed(evt);
            }
        });
        jPanel3.add(foreignExchangeCheckBox);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        foreignExchangeTextField.setEditable(false);
        foreignExchangeTextField.setText(bundle.getString("ImportBankStatementFrame.foreignExchangeTextField.text")); // NOI18N
        jPanel4.add(foreignExchangeTextField);

        showRatesButton.setText(bundle.getString("ImportBankStatementFrame.showRatesButton.text")); // NOI18N
        showRatesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showRatesButtonActionPerformed(evt);
            }
        });
        jPanel4.add(showRatesButton);

        jPanel1.add(jPanel4);

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

        selectAllButton.setText(bundle.getString("ImportBankStatementFrame.selectAllButton.text")); // NOI18N
        selectAllButton.setEnabled(false);
        selectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed(evt);
            }
        });
        jPanel1.add(selectAllButton);

        unselectAllButton.setText(bundle.getString("ImportBankStatementFrame.unselectAllButton.text")); // NOI18N
        unselectAllButton.setEnabled(false);
        unselectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unselectAllButtonActionPerformed(evt);
            }
        });
        jPanel1.add(unselectAllButton);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("ImportBankStatementFrame.jLabel2.text")); // NOI18N
        jPanel1.add(jLabel2);

        recordsImportedLabel.setText(bundle.getString("ImportBankStatementFrame.recordsImportedLabel.text")); // NOI18N
        jPanel1.add(recordsImportedLabel);

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        recordTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Transaction Date", "Transaction Type", "Sort Code", "Account Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(recordTable);

        jPanel2.add(jScrollPane1);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportButtonActionPerformed
        String bankName = (String) bankComboBox.getSelectedItem();
        System.out.println("Selected Bank: " + bankName);
        Object[][] data = getTableData(recordTable);
        if (bankName.equals("TSB") || bankName.equals("Bank of Scotland")) {
            this.importTSBData(data);

        } else if (bankName.equals("Pancretan Bank")) {
            this.importPancretabankData(data);
        } else {
            System.out.println("No parsing template found");
        }
    }//GEN-LAST:event_ImportButtonActionPerformed

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed

        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        //In response to a button click:
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            System.out.println("Opening: " + file.getAbsolutePath());
            String bankName = (String) bankComboBox.getSelectedItem();
            System.out.println("Selected Bank: " + bankName);
            if (bankName.equals("TSB")) {
                ParseTSBCsv tsb = new ParseTSBCsv();
                ArrayList contentList = tsb.parseData(file.getAbsolutePath());
                this.displayStatementContent(contentList, tsb);
                selectAllButton.setEnabled(true);
                unselectAllButton.setEnabled(true);
            } else if ( bankName.equals("Bank of Scotland")) {
                ParseBOSCsv bos = new ParseBOSCsv();
                ArrayList contentList = bos.filterMonthsAndNegValues(file.getAbsolutePath());
                this.displayStatementContent(contentList, bos);
                selectAllButton.setEnabled(true);
                unselectAllButton.setEnabled(true);
            } 
            else if (bankName.equals("Pancretan Bank")) {
                ParsePancretaBankCsv panc = new ParsePancretaBankCsv(";", 8);
                ArrayList contentList = panc.parseData(file.getAbsolutePath());
                this.displayStatementContent(contentList, panc);
                selectAllButton.setEnabled(true);
                unselectAllButton.setEnabled(true);
            }else if (bankName.equals("N26")) {
                ParsePancretaBankCsv panc = new ParsePancretaBankCsv(";", 8);
                ArrayList contentList = panc.parseData(file.getAbsolutePath());
                this.displayStatementContent(contentList, panc);
                selectAllButton.setEnabled(true);
                unselectAllButton.setEnabled(true);
            }else if (bankName.equals("Fidor UK")) {
                ParsePancretaBankCsv panc = new ParsePancretaBankCsv(";", 8);
                ArrayList contentList = panc.parseData(file.getAbsolutePath());
                this.displayStatementContent(contentList, panc);
                selectAllButton.setEnabled(true);
                unselectAllButton.setEnabled(true);
            }else if (bankName.equals("Fidor DE")) {
                ParsePancretaBankCsv panc = new ParsePancretaBankCsv(";", 8);
                ArrayList contentList = panc.parseData(file.getAbsolutePath());
                this.displayStatementContent(contentList, panc);
                selectAllButton.setEnabled(true);
                unselectAllButton.setEnabled(true);
            }else if (bankName.equals("Alpha Bank")) {
                ParsePancretaBankCsv panc = new ParsePancretaBankCsv(";", 8);
                ArrayList contentList = panc.parseData(file.getAbsolutePath());
                this.displayStatementContent(contentList, panc);
                selectAllButton.setEnabled(true);
                unselectAllButton.setEnabled(true);
            }
            else {
                System.out.println("No parsing template found");
            }
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }//GEN-LAST:event_loadFileButtonActionPerformed
    /**
     * populate the table with the incoming data
     *
     * @param contentList
     * @param parser
     */
    private void displayStatementContent(ArrayList contentList, ParseCsv parser) {
        Object[][] contentStrArr = this.convertArrayListTo2DStringArray(contentList, parser.getNumOfFields());
        String[] columnNames = parser.getColumnNames();
        if (contentList.size() > 0) {
            recordTable.setModel(new ImportBankStatementTableModel(contentStrArr, columnNames));
            //recordTable.setModel(new MyTableModel(contentStrArr, columnNames));
            recordTable.setAutoCreateRowSorter(true);//add a primitive sort by column function
        }
    }

    private void foreignExchangeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foreignExchangeCheckBoxActionPerformed
        //if the checkbox is selected enable the foreun currency text field. other wise disable it
        if (this.foreignExchangeCheckBox.isSelected()) {
            this.foreignExchangeTextField.setEnabled(true);
            this.foreignExchangeTextField.setEditable(true);
        } else {
            this.foreignExchangeTextField.setEnabled(false);
            this.foreignExchangeTextField.setEditable(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_foreignExchangeCheckBoxActionPerformed

    private void showRatesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showRatesButtonActionPerformed
//Textratestextfield foreignCurrencyTextField
        //foreignCurrencyCheckBox
        ExchangeRatesFrame exrf = new ExchangeRatesFrame(foreignExchangeTextField, foreignExchangeTextField);
        exrf.presentExchangeRateFrame();        // TODO add your handling code here:
    }//GEN-LAST:event_showRatesButtonActionPerformed

    private void selectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllButtonActionPerformed
        this.selectUnselectRecords(true);
    }//GEN-LAST:event_selectAllButtonActionPerformed

    private void unselectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unselectAllButtonActionPerformed
        this.selectUnselectRecords(false);
    }//GEN-LAST:event_unselectAllButtonActionPerformed

    private void bankComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bankComboBoxActionPerformed

    private void selectUnselectRecords(boolean set){
        String bankName = (String) bankComboBox.getSelectedItem();
        System.out.println("Selected Bank: " + bankName);
        Object[][] data = getTableData(recordTable);
        int length=data.length;
        int boolColumn=-1;
        if (bankName.equals("TSB") || bankName.equals("Bank of Scotland")) {
            boolColumn=8;
        } else if (bankName.equals("Pancretan Bank")) {
            boolColumn=8;
        } else {
            System.out.println("No parsing template found");
        }
        ImportBankStatementTableModel model = (ImportBankStatementTableModel) recordTable.getModel();
        for(int i=0;i<length;i++)
        {
            model.setValueAt(set, i, boolColumn);
        }
    }
    
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
    private javax.swing.JComboBox bankComboBox;
    private javax.swing.JLabel bankLabel;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JCheckBox foreignExchangeCheckBox;
    private javax.swing.JTextField foreignExchangeTextField;
    private javax.swing.JComboBox importBankSCategoryComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JTable recordTable;
    private javax.swing.JLabel recordsImportedLabel;
    private javax.swing.JButton selectAllButton;
    private javax.swing.JButton showRatesButton;
    private javax.swing.JButton unselectAllButton;
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

    private Object[][] convertArrayListTo2DStringArray(ArrayList content, int numOfFields) {
        int length = content.size();
        Object[][] csvStringArr = new Object[length][numOfFields + 1];
        Iterator iterContent = content.iterator();
        int lineCounter = 0;
        while (iterContent.hasNext()) {
            ArrayList line = (ArrayList) iterContent.next();
            System.out.println(line.toString());
            for (int fieldCount = 0; fieldCount < numOfFields; fieldCount++) {
                csvStringArr[lineCounter][fieldCount] = (String) line.get(fieldCount);
            }
            csvStringArr[lineCounter][numOfFields] = true;

            lineCounter++;
        }
        System.out.println("Total no of columns: " + numOfFields);
        return csvStringArr;
    }

    private Object[][] getTableData(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j);
            }
        }
        return tableData;
    }

    /**
     * This method will convert the incoming data from a TSB export to the
     * appropriate format for the database
     *
     * @param data
     */
    private void importTSBData(Object[][] data) {
        int importedRecordNo = 0;
        int noOfLines = data.length;
        for (int i = 0; i < noOfLines; i++) {
            String unCorrectedDate = (String) data[i][0];
            boolean importField = (boolean) data[i][8];
            
            //this if block will be activated if both the import field is checked and the first character of the date field is numeric. 
            //if there is a header in here that means that this wont be numeric and the import will be skipped
            if (importField&& Character.isDigit(unCorrectedDate.charAt(0))) {

                
                String descCompName = (String) data[i][4];
                String debitAm = (String) data[i][5];
                String creditAm = (String) data[i][6];
                //retrieve the category details
                String categId = getCategID();

            //System.out.println("descComp: "+descCompName+" and its hash code is: "+descCompName.hashCode());
                //Making sure afm is less than 9 digits, while allowing for smaller numbers to be allowed without problem
                String afmFull = Integer.toString(descCompName.hashCode());
                String afm = "";
                if (afmFull.length() > 9) {
                    afm = afmFull.substring(0, 9);
                } else {
                    afm = afmFull;
                }

                //get the category id for the import
                String companyID = getCompID(descCompName, afm, categId);
                if (debitAm.length() > 0) {
                    //System.out.println("This a withdrawal");
                    //System.out.println("I will import");
                    //System.out.println("Record data:" +"Company"+companyID+" "+ this.correctDate(unCorrectedDate) + " afm:" + afm + " company:" + descCompName + " withdrawal:" + debitAm);
                    InsertBills bill = new InsertBills(Integer.parseInt(companyID), this.applyExchangeRate(debitAm), this.tsbCorrectDate(unCorrectedDate), this.tsbCorrectDate(unCorrectedDate), "Auto imported field");

                } else {
                    //System.out.println("This a deposit");
                    //System.out.println("I will import");
                    //System.out.println("Record data:" +"Company"+companyID+" "+ this.correctDate(unCorrectedDate) + " afm:" + afm + " company:" + descCompName + " deposit:" + creditAm);
                    InsertIncome income = new InsertIncome(Integer.parseInt(companyID), this.applyExchangeRate(creditAm), this.tsbCorrectDate(unCorrectedDate), "Auto imported field");
                }
                importedRecordNo++;
                recordsImportedLabel.setText("" + importedRecordNo);

            } else {
                System.out.println("I will not import");
                System.out.println("Record number: " +i);
            }
        }
    }

    /**
     * This method will convert the incoming data from a TSB export to the
     * appropriate format for the database
     *
     * @param data
     */
    private void importPancretabankData(Object[][] data) {
        int importedRecordNo = 0;
        int noOfLines = data.length;
        for (int i = 0; i < noOfLines; i++) {
            String recordType = (String) data[i][0];
            String unCorrectedDate = (String) data[i][1];
            String descCompName = (String) data[i][4];
            String amountWithCur = (String) data[i][6];

            boolean importField = (boolean) data[i][8];
            //retrieve the category details
            String categId = getCategID();

            //Stripping EUR out of the amount string
            String[] amountCurArr = amountWithCur.split(" ");
            String amount = amountCurArr[0];

            //If we have an empty comment, its probably an internal transfer.
            if (descCompName.contentEquals("")) {
                descCompName = "Pancretabank Internal";
            }
            //System.out.println("descComp: "+descCompName+" and its hash code is: "+descCompName.hashCode());
            //Making sure afm is less than 9 digits, while allowing for smaller numbers to be allowed without problem
            String afmFull = Integer.toString(descCompName.hashCode());
            String afm = "";
            if (afmFull.length() > 9) {
                afm = afmFull.substring(0, 9);
            } else {
                afm = afmFull;
            }

            if (importField) {
                //get the category id for the import
                String companyID = getCompID(descCompName, afm, categId);
                if (recordType.equalsIgnoreCase("Withdrawal")) {
                    //System.out.println("This a withdrawal");
                    //System.out.println("I will import");
                    //System.out.println("Record data:" +"Company:"+companyID+" Corrected Date: "+ this.pancretaCorrectDate(unCorrectedDate) + " afm:" + afm + " company des:" + descCompName + " withdrawal:" + this.applyExchangeRate(amount));
                    InsertBills bill = new InsertBills(Integer.parseInt(companyID), this.applyExchangeRate(amount), this.pancretaCorrectDate(unCorrectedDate), this.pancretaCorrectDate(unCorrectedDate), "Auto imported field");

                } else if (recordType.equalsIgnoreCase("Deposit")) {
                    //System.out.println("This a deposit");
                    //System.out.println("I will import");
                    //System.out.println("Record data:" +"Company"+companyID+" "+ this.pancretaCorrectDate(unCorrectedDate) + " afm:" + afm + " company:" + descCompName + " deposit:" + amount);
                    InsertIncome income = new InsertIncome(Integer.parseInt(companyID), this.applyExchangeRate(amount), this.pancretaCorrectDate(unCorrectedDate), "Auto imported field");
                }
                importedRecordNo++;
                recordsImportedLabel.setText("" + importedRecordNo);

            } else {
                System.out.println("I will not import");
                System.out.println("Record data:" + this.pancretaCorrectDate(unCorrectedDate) + " afm:" + afm + " company:" + descCompName + "-" + amount);
            }
        }
    }

    /**
     * Convert the incoming date to the date used by the database for tsb data
     *
     * @param uncDate
     * @return
     */
    private String tsbCorrectDate(String uncDate) {
        String[] splitDate = uncDate.split("/");
        String day = splitDate[0];
        String month = splitDate[1];
        String year = splitDate[2];
        //System.out.println("year:"+year+"month:"+month+"day:"+day);
        return year + "-" + month + "-" + day;
    }

    /**
     * Convert the incoming date to the date used by the database for tsb data
     *
     * @param uncDate
     * @return
     */
    private String pancretaCorrectDate(String uncDate) {
        String[] splitDate = uncDate.split("/");
        String day = splitDate[1];
        String month = splitDate[0];
        String year = splitDate[2];
        //System.out.println("year: "+year+"month: "+month+"day: "+day);
        return year + "-" + month + "-" + day;
    }

    private String getCategID() {
        //retrieve the category details
        String categName = (String) importBankSCategoryComboBox.getSelectedItem();
        Category categ = new Category();
        categ.selectCatByName(categName);
        return categ.getCatID();
    }

    private String getCompID(String name, String afm, String catid) {
        //findout if the company exists
        SelectCompanyDetails company = new SelectCompanyDetails();
        company.SelectCompanyDetailsWithAfm(afm);
        String companyId = "";
        if (company.resultsExist()) {
            companyId = company.getID();
        } else {
            //System.out.println("I am going to enter the following company details"+name+"-"+afm+"-"+catid);
            InsertCompany in = new InsertCompany();
            in.insertCompany(name, afm, catid);
            SelectCompanyDetails newCompany = new SelectCompanyDetails();
            newCompany.SelectCompanyDetailsWithAfm(afm);
            companyId = newCompany.getID();
        }
        return companyId;
    }

    private String applyExchangeRate(String amount) {
        String convertedAmount = "";
        //apply the exchange rate
        if (foreignExchangeCheckBox.isEnabled() && foreignExchangeTextField.getText().length() > 0 && amount.length() > 0) {
            double exchangeRate = Double.parseDouble(foreignExchangeTextField.getText());
            convertedAmount = "" + Double.parseDouble(amount) * exchangeRate;
        } else {
            convertedAmount = amount;
        }
        return convertedAmount;
    }
}
