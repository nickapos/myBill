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

import gr.oncrete.nick.mybill.RDBMS.SelectFromTable;
import java.util.*;

/**
 *
 * @author nickapos
 * this class will be used to retrieve all the details available for a company
 */
public class SelectCompanyDetails {

    private String name, afm, id,catid;
    private boolean exists = false;
    private String sql1 = "select cid,companyname,afm,catid from companies where";
    private SelectFromTable sel = new SelectFromTable();

    /**
     *
     */
    public SelectCompanyDetails() {
    }

    /**
     *
     * @param n
     */
    public void SelectCompanyDetailsWithName(String n) {
        String sql = sql1 + " companyname='" + n + "';";
        this.splitResults(sql);
    }

    /**
     *
     * @param a
     */
    public void SelectCompanyDetailsWithAfm(String a) {
        String sql = sql1 + " afm='" + a + "';";
        this.splitResults(sql);
    }

    /**
     *
     * @param id
     */
    public void SelectCompanyDetailsWithID(String id) {
        String sql = sql1 + " cid=" + id + ";";
        this.splitResults(sql);
    }

    private void splitResults(String sql) {
        //System.out.println(sql);
        ArrayList<String> a = sel.executeQuery(sql);
        
        if (a.size() > 0) {
            exists = true;
            id = a.get(0);
            name = a.get(1);
            afm = a.get(2);
            catid=a.get(3);
            //System.out.println(id+name+afm);
        }
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getAfm() {
        return afm;
    }

    /**
     *
     * @return
     */
    public String getCatID() {
        return catid;
    }
    /**
     *
     * @return
     */
    public boolean resultsExist()
    {
        return exists;
    }

    /**
     *
     * @return the full details of the company in a string
     */
    public String companyToString()
    {
        return "id: "+this.getID()+" catid: "+this.getCatID()+" afm: "+this.getAfm()+" name: "+this.getName();
    }
}
