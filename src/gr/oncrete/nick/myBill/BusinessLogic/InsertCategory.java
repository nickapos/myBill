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

/**
 *
 * @author nickapos
 *
 * This class is used to insert new company entries into the database
 */
public class InsertCategory {

    /**
     * Constructor insert category without an id
     *
     * @param cName
     */
    public InsertCategory(String cName)
    {
        if(cName.length()>0)
        {
            String sql = "insert into categories (categoryname) values ('"+cName+"')";
            InsertIntoTable in = new InsertIntoTable(sql);
            System.out.println(sql);
        }

    }



    /**
     *
     * @param id
     * @param cName
     */
    public InsertCategory(String id, String cName)
    {
        if(id.length()>0 && cName.length()>0)
        {
            String sql = "insert into categories (catid, categoryname) values ("+id+",'"+cName+"')";
            InsertIntoTable in = new InsertIntoTable(sql);
            System.out.println(sql);
        }

    }

}
