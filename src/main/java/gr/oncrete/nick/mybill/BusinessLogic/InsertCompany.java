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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.oncrete.nick.myBill.BusinessLogic;

import gr.oncrete.nick.myBill.RDBMS.InsertIntoTable;
import gr.oncrete.nick.myBill.BusinessLogic.SelectInfo.SelectCompanyDetails;

/**
 *
 * @author nickapos
 *
 * This class is used to insert new company entries into the database
 */
public class InsertCompany {

    InsertIntoTable in;

    /**
     *
     */
    public InsertCompany() {
    }

    /**
     * method without an id
     *
     * @param cName
     * @param afm
     * @param catID
     */
    public void insertCompany(String cName, String afm, String catID) {
        if (cName.length() > 0 && afm.length() > 0 && catID.length() > 0) {
            String sql = "insert into companies (companyname,afm,catid) values ('" + cName + "','" + afm + "'," + catID + ")";
            in = new InsertIntoTable(sql);
            if (!in.hasCompletedSucesfully()) {
                System.out.println("insertion has not completed sucesfully conficting company details are: ");
                this.printConflictingCompany(afm);
            }
            //System.out.println(sql);
        } else {
            in = new InsertIntoTable();
            in.warningPopUp(java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("ERROR IN COMPANY INSERTION"));
        }

    }

    /**
     * Constructor insert company with a specific id
     * used in restoring the database from csv file format
     *
     * @param id
     * @param cName
     * @param afm
     */
    public InsertCompany(String id, String cName, String afm) {
        if (id.length() > 0 && cName.length() > 0 && afm.length() > 0) {
            String sql = "insert into companies (cid, companyname,afm) values (" + id + ",'" + cName + "','" + afm + "')";
            in = new InsertIntoTable(sql);
            if (!in.hasCompletedSucesfully()) {
                System.out.println("insertion has not completed sucesfully conficting company details are: ");
                this.printConflictingCompany(afm);
            }
            //System.out.println(sql);
        } else {
            in = new InsertIntoTable();
            in.warningPopUp(java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("ERROR IN COMPANY INSERTION"));
        }

    }

    /**
     *
     * @param id
     * @param cName
     * @param afm
     * @param catid
     */
    public InsertCompany(String id, String cName, String afm, String catid) {
        if (id.length() > 0 && cName.length() > 0 && afm.length() > 0 && catid.length() > 0) {
            String sql = "insert into companies (cid, companyname,afm,catid) values (" + id + ",'" + cName + "','" + afm + "'," + catid + ")";
            in = new InsertIntoTable(sql);
            if (!in.hasCompletedSucesfully()) {
                System.out.println("insertion has not completed sucesfully conficting company details are: ");
                this.printConflictingCompany(afm);
            }
            //System.out.println(sql);
        } else {
            in = new InsertIntoTable();
            in.warningPopUp(java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("ERROR IN COMPANY INSERTION"));
        }

    }

    /**
     * this private method will return the conficting company in case of a unique constraing conflict in afm (the only possible constraint conflict with the specific table)
     * @param afm
     */
    private void printConflictingCompany(String afm) {
        SelectCompanyDetails conflictingCompany = new SelectCompanyDetails();
        conflictingCompany.SelectCompanyDetailsWithAfm(afm);
        System.out.println(conflictingCompany.companyToString());
    }
}
