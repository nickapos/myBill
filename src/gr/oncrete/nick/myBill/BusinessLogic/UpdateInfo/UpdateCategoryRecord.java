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
package gr.oncrete.nick.myBill.BusinessLogic.UpdateInfo;



/**
 *
 * @author nickapos
 */
public class UpdateCategoryRecord extends UpdateRecord{
    String sql = "update categories set categoryname='";
    
    /**
     *
     * @param oldName
     * @param name
     */
    public UpdateCategoryRecord(String oldName,String name) {
        //String sql1=sql+name+"' where catid="+id;
        String sql1=sql+name+"' where categoryname='"+oldName+"'";
        //System.out.println(sql1);
        super.runQuery(sql1);
    }

    
}
