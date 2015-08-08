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
/**
 *
 * @author nickapos
 */
public class SelectAllCompaniesIDS extends SelectAllBillsDetails {
    String sql1 = "select a.cid,a.companyname,a.afm,b.categoryname from companies a left join categories b on a.catid=b.catid;";
    /**
     *
     */
    public SelectAllCompaniesIDS()
    {
        
    }

    protected void readDBReport() {
        super.runQueryL(this.sql1);
        LinkedList results = (LinkedList) this.getIds();
        if (results.isEmpty()) {
            System.out.println("result set is empty. Dump database operation aborted");
            results= new LinkedList<String>();
            //System.exit(1);

        } else {

           /* for (int i = 0; i < results.size(); i += 4) {
                //with ids
                String [] row = { (String)results.get(i),(String) results.get(i + 1),(String) results.get(i + 2),(String) results.get(i + 3) };

                rows.add(row);
                //System.out.println(row);
            }*/
            rows=results;
        }
    }
}
