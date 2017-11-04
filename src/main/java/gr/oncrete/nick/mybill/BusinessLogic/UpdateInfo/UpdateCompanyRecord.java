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
package gr.oncrete.nick.mybill.BusinessLogic.UpdateInfo;

/**
 *
 * @author nickapos
 */
public class UpdateCompanyRecord extends UpdateRecord {

    String sql = "update companies set companyname='";

    //update companies set companyname='test2', afm='blabla' where cid=2
    /**
     *
     * @param id
     * @param name
     * @param afm
     * @param catid
     */
    public UpdateCompanyRecord(String id, String name, String afm, String catid) {
        String sql1 = sql + name + "', afm='" + afm + "', catid=" + catid + " where cid=" + id;
        //System.out.println(sql1);
        super.runQuery(sql1);
    }

}
