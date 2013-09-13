/*
        myBill, bills tracking program
    Copyright (C) 2010  Nick Apostolakis

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * WarningFrame.java
 *
 * Created on 16 Ιούλιος 2005, 3:01 μμ
 */

package gr.oncrete.nick.myBill.UserInterface;
import javax.swing.JOptionPane;

/**
 *
 * @author  nickapos
 */
public class PopupMessageFrame extends javax.swing.JFrame
{
   
   /** Creates new form WarningFrame */
   public PopupMessageFrame ()
   {
      initComponents ();
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   private void initComponents()//GEN-BEGIN:initComponents
   {
      PopupMessageFrame = new javax.swing.JOptionPane();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      getContentPane().add(PopupMessageFrame, java.awt.BorderLayout.CENTER);

      pack();
   }//GEN-END:initComponents
   
   /**
    * @param args the command line arguments
    */
   public static void main (String args[])
   {
      java.awt.EventQueue.invokeLater (new Runnable ()
      {
         public void run()
         {
            PopupMessageFrame a= new PopupMessageFrame ();
            a.setNotification ("Warning");
            //a.setVisible (true);
         }
      });
   }
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JOptionPane PopupMessageFrame;
   // End of variables declaration//GEN-END:variables
   
   /**
    *Method used to display a waning type
    *popup.
    *
    * @param message
    */
   public void setWarning (String message)
   {
      //Warnings.setMessage (message);
      PopupMessageFrame.showMessageDialog (null, message, "Warning", JOptionPane.ERROR_MESSAGE);
   }
   
   /**
    *
    *Method used to display an information 
    *type popup.
    *
    * @param message
    */
   public void setNotification (String message)
   {
      //Warnings.setMessage (message);
      PopupMessageFrame.showMessageDialog (null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
   }
}
