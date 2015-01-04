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
package gr.oncrete.nick.mybill.BusinessLogic;

import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectCompanyDetails;
import gr.oncrete.nick.mybill.BusinessLogic.UpdateInfo.UpdateCompanyRecord;
import java.util.List;
import java.util.Iterator;
import javax.swing.JLabel;

/**
 *This class will be used to change the all the records of the database the belong to
 * a specific category to another
 * @author nickapos 6 Σεπ 2010
 */
public class MergeCategories {

    private List idListTobeMerged;
    private String newCategoryID;
    private JLabel l;

    /**
     *
     * @param listTobeMerged the id's list to be merget into the new category
     * @param newCategoryID the id of  the new category
     * @param a label for the progress of the work the id of  the new category
     */
    public MergeCategories(List listTobeMerged, String categoryID, JLabel lbl) {
        idListTobeMerged = listTobeMerged;
        l = lbl;
        newCategoryID = categoryID;
    }

    /**
     *
     */
    public MergeCategories() {
    }

    /**
     * this method will preform the actual conversion
     */
    public void mergeCategories() {

        if (newCategoryID.length() > 0) {
            
            //change id of category from the old to the new one
            Iterator it = idListTobeMerged.iterator();

            int counter = 0;
            String expensesProgressText = "";
            while (it.hasNext()) {
                //get company details
                SelectCompanyDetails cdt = new SelectCompanyDetails();
                String[] id = (String[]) it.next();
                cdt.SelectCompanyDetailsWithID(id[0]);
                UpdateCompanyRecord upcr = new UpdateCompanyRecord(cdt.getID(),cdt.getName(),cdt.getAfm(),newCategoryID);

                counter++;
                double percentage =100*counter/idListTobeMerged.size();
                expensesProgressText = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("MergeCategories.progressLabel.text") + counter+" "+percentage +"%";
                l.setText(expensesProgressText);
            }




        }

    }
}
