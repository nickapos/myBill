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
        loadFileButton = new javax.swing.JButton();
        ImportButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle"); // NOI18N
        setTitle(bundle.getString("ImportBankStatementFrame.title")); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.GridLayout(3, 2));

        bankLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bankLabel.setText(bundle.getString("ImportBankStatementFrame.bankLabel.text")); // NOI18N
        jPanel1.add(bankLabel);

        bankComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TSB", "Bank of Scotland" }));
        jPanel1.add(bankComboBox);

        categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoryLabel.setText(bundle.getString("ImportBankStatementFrame.categoryLabel.text")); // NOI18N
        categoryLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(categoryLabel);

        importBankSCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.getCategoriesCombo()));
        jPanel1.add(importBankSCategoryComboBox);

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
        if (bankName.equals("TSB")||bankName.equals("Bank of Scotland")) {
            this.importTSBData(data);

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
            if (bankName.equals("TSB")||bankName.equals("Bank of Scotland")) {
                ParseTSBCsv tsb = new ParseTSBCsv(file.getAbsolutePath());
                ArrayList contentList = tsb.getContent();
                Object[][] contentStrArr = this.convertArrayListTo2DStringArray(contentList, tsb.getNumOfFields());
                String[] columnNames = tsb.getColumnNames();
                if (contentList.size() > 0) {
                    recordTable.setModel(new ImportBankStatementTableModel(contentStrArr, columnNames));
                    //recordTable.setModel(new MyTableModel(contentStrArr, columnNames));
                    recordTable.setAutoCreateRowSorter(true);//add a primitive sort by column function
                }
            } else {
                System.out.println("No parsing template found");
            }
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
    private javax.swing.JComboBox bankComboBox;
    private javax.swing.JLabel bankLabel;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JComboBox importBankSCategoryComboBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JTable recordTable;
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
            //System.out.println(line.toString());
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
        int noOfLines = data.length;
        for (int i = 0; i < noOfLines; i++) {
            String unCorrectedDate = (String) data[i][0];
            String descCompName = (String) data[i][4];
            String afm = Integer.toString(descCompName.hashCode()).substring(0, 9);
            String debitAm = (String) data[i][5];
            String creditAm = (String) data[i][6];
            boolean importField = (boolean) data[i][8];
            //retrieve the category details
            String categId = getCategID();

            if (importField) {
                //get the category id for the import
                String companyID = getCompID(descCompName,afm,categId);
                if (debitAm.length() > 0) {
                    //System.out.println("This a withdrawal");
                    //System.out.println("I will import");
                    //System.out.println("Record data:" +"Company"+companyID+" "+ this.correctDate(unCorrectedDate) + " afm:" + afm + " company:" + descCompName + " withdrawal:" + debitAm);
                    InsertBills bill = new InsertBills(Integer.parseInt(companyID),debitAm,this.correctDate(unCorrectedDate),this.correctDate(unCorrectedDate),"Auto imported field");
                    
                } else {
                    //System.out.println("This a deposit");
                    //System.out.println("I will import");
                    //System.out.println("Record data:" +"Company"+companyID+" "+ this.correctDate(unCorrectedDate) + " afm:" + afm + " company:" + descCompName + " deposit:" + creditAm);
                    InsertIncome income = new InsertIncome(Integer.parseInt(companyID),creditAm,this.correctDate(unCorrectedDate),"Auto imported field");
                }

            } else {
                System.out.println("I will not import");
                System.out.println("Record data:" + this.correctDate(unCorrectedDate) + " afm:" + afm + " company:" + descCompName + "-" + debitAm + "-" + creditAm);
            }
        }
    }

    /**
     * Convert the incoming date to the date used by the database
     *
     * @param uncDate
     * @return
     */
    private String correctDate(String uncDate) {
        String[] splitDate = uncDate.split("/");
        String day = splitDate[0];
        String month = splitDate[1];
        String year = splitDate[2];
        //System.out.println("year:"+year+"month:"+month+"day:"+day);
        return year + "-" + month + "-" + day;
    }

    private String getCategID() {
        //retrieve the category details
        String categName = (String) importBankSCategoryComboBox.getSelectedItem();
        Category categ = new Category();
        categ.selectCatByName(categName);
        return categ.getCatID();
    }

    private String getCompID(String name,String afm,String catid) {
        //findout if the company exists
        SelectCompanyDetails company = new SelectCompanyDetails();
        company.SelectCompanyDetailsWithAfm(afm);
        String companyId = "";
        if (company.resultsExist()) {
            companyId = company.getID();
        } else {
            //System.out.println("I am going to enter the following company details"+name+"-"+afm+"-"+catid);
            InsertCompany in= new InsertCompany();
            in.insertCompany(name, afm, catid);
            SelectCompanyDetails newCompany = new SelectCompanyDetails();
            newCompany.SelectCompanyDetailsWithAfm(afm);
            companyId=newCompany.getID();
        }
        return companyId;
    }
}
