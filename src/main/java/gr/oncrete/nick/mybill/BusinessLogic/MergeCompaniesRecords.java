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

import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectBillDetails;
import gr.oncrete.nick.mybill.BusinessLogic.UpdateInfo.UpdateBillRecord;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;

/**
 * This class wil be used to all the records of the database of a specific
 * company to another
 *
 * @author nickapos 6 Σεπ 2010
 */
public class MergeCompaniesRecords {

    private List idListTobeMerged;
    private String newCompanyID;
    private JLabel l;

    /**
     *
     * @param listTobeMerged the id's list to be merget into the new category
     * @param newCompanyID the id of the new company
     * @param a label for the progress of the work the id of the new category
     */
    public MergeCompaniesRecords(List listTobeMerged, String companyID, JLabel lbl) {
        idListTobeMerged = listTobeMerged;
        l = lbl;
        newCompanyID = companyID;
    }

    /**
     *
     */
    public MergeCompaniesRecords() {
    }

    /**
     * this method will preform the actual conversion
     */
    public void mergeCompanyRecords() {

        if (newCompanyID.length() > 0) {

            //change id of category from the old to the new one
            Iterator it = idListTobeMerged.iterator();

            int counter = 0;
            String mergeProgressText = "";
            while (it.hasNext()) {
                //get bill details
                SelectBillDetails bdt = new SelectBillDetails();
                String[] id = (String[]) it.next();
                bdt.SelectBillDetailsWithID(id[0]);
                UpdateBillRecord upbdt = new UpdateBillRecord();
                upbdt.updateBillRecordWithCid(bdt.getBID(), newCompanyID, bdt.getPrice(), bdt.getDateOfIssue(), bdt.getDateOfPayment(), bdt.getComment());

                counter++;
                double percentage = 100 * counter / idListTobeMerged.size();
                mergeProgressText = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("MergeCompanyRecordsFrame.progressLabel.text") + counter + " " + percentage + "%";
                l.setText(mergeProgressText);
            }

        }

    }
}
