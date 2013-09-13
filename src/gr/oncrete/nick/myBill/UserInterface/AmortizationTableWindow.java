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

/**
 *
 * @author nickapos
 */
public class AmortizationTableWindow extends javax.swing.JFrame {

    /** Creates new form AboutWindow */
    public AmortizationTableWindow() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        aboutTextArea = new javax.swing.JTextArea();

        aboutTextArea.setColumns(20);
        aboutTextArea.setEditable(false);
        aboutTextArea.setRows(5);
        jScrollPane1.setViewportView(aboutTextArea);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AmortizationTableWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea aboutTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void setAmTableInitial()
    {
        String amortizationTableContents="This is will hold the amortization table";
        aboutTextArea.setText(amortizationTableContents);
    }
    public void setAmTableString(String content)
    {
        aboutTextArea.setText(content);
    }
    
    /**
     *
     */
    public void runAmTable()
    {
        this.setVisible(true);
    }
}
