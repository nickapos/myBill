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
package gr.oncrete.nick.mybill.businesslogic;

import gr.oncrete.nick.mybill.rdbms.SelectFromTable;
import java.util.*;

/**
 * This class creates a sorted list of the companies for the dropdown menu in
 * insert bill tab
 *
 * @author nickapos
 */
public class Companies {

    String sql = "select cid, companyName from companies";
    HashMap companiesMap;

    /**
     *
     */
    public Companies() {
        companiesMap = new HashMap();
        this.companies();
    }

    /**
     * Fill the companies hash table with the companies id and name
     *
     */
    private void companies() {
        SelectFromTable sel = new SelectFromTable();
        ArrayList a = sel.executeQuery(sql);

        for (int o = 0; o < a.size(); o += 2) {
            companiesMap.put(a.get(o), a.get(o + 1));
        }

    }

    /**
     *
     * @return
     */
    public String[] companyNames() {
        ArrayList<String> cNamesArrayList = new ArrayList<String>();
        Set<String> set = companiesMap.keySet();
        Iterator<String> e = set.iterator();
        while (e.hasNext()) {
            String r = e.next();
            cNamesArrayList.add((String) companiesMap.get(r));
        }
        Collections.sort(cNamesArrayList);
        String[] cNameStr = cNamesArrayList.toArray(new String[cNamesArrayList.size()]);
        return cNameStr;

    }
}
