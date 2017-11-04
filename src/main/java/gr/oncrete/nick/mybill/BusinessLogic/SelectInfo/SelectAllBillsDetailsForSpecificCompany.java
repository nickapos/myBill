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
package gr.oncrete.nick.mybill.BusinessLogic.SelectInfo;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class SelectAllBillsDetailsForSpecificCompany extends SelectAllBillsDetails {

    private final static Logger LOGGER = Logger.getLogger(SelectAllBillsDetailsForSpecificCompany.class.getName());

    /**
     *
     */
    public SelectAllBillsDetailsForSpecificCompany(String cid) {
        sql1 = sql1 + " where b.cid=" + cid + " order by dayofpayment";
    }

    /**
     *
     * This method will read the bill info from the database and return the
     * whole set in a properly formatted arraylist in order to be displayed in
     * the report jtable of the program
     */
    protected void readDBReport() {

        super.runQueryL(sql1);
        LinkedList results = (LinkedList) this.getIds();
        if (results.isEmpty()) {
            LOGGER.log(Level.INFO, "result set is empty. Dump database operation not proceeding");
            results = new LinkedList<String>();
            //System.exit(1);

        } else {

            for (int i = 0; i < results.size(); i++) {
                //with ids
                String[] a = (String[]) results.get(i);
                //String row = a[0]+a[1]+a[2]+a[3]+a[4];
                String row = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" DATE OF PAYMENT: ") + a[3] + ", " + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" AMOUNT: ") + a[4];
                rows.add(row);
            }
        }
    }

    /**
     *
     *
     * @return all the bill (or row) entries found in the db
     */
    public List getAllRows() {
        this.readDBReport();
        return rows;

    }

}
