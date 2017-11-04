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
public class SelectAllCompaniesIDS extends SelectAllBillsDetails {

    String sql1 = "select a.cid,a.companyname,a.afm,b.categoryname from companies a left join categories b on a.catid=b.catid;";
    private final static Logger LOGGER = Logger.getLogger(SelectAllCompaniesIDS.class.getName());

    /**
     *
     */
    public SelectAllCompaniesIDS() {

    }

    protected void readDBReport() {
        super.runQueryL(this.sql1);
        LinkedList results = (LinkedList) this.getIds();
        if (results.isEmpty()) {
            LOGGER.log(Level.INFO, "result set is empty. Dump database operation not proceeding");
            results = new LinkedList<String>();
            //System.exit(1);

        } else {
            rows = results;
        }
    }
}
