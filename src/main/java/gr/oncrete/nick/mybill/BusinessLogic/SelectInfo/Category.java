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
package gr.oncrete.nick.myBill.BusinessLogic.SelectInfo;

import gr.oncrete.nick.myBill.RDBMS.SelectFromTable;
import java.util.*;

/**
 * This class will represent a category as it exists in db
 * @author nickapos
 */
public class Category {

    private String categoryName, categoryID;
    

    /**
     *
     */
    public Category() {
        
    }

    /**
     *
     * @param ID
     */
    public void selectCatById(String ID)
    {
        if(ID.length()>0){
        String sql ="select catid, categoryName from categories where catid="+ID;
        this.categories(sql);
        }
    }

    /**
     *
     * @param catName
     */
    public void selectCatByName(String catName)
    {
        if(catName.length()>0){
        String sql ="select catid, categoryName from categories where categoryname='"+catName+"'";
        this.categories(sql);
        }
    }


    /**
     * Fill the categories variables
     *
     */
    private void categories(String sql) {
        SelectFromTable sel = new SelectFromTable();
        ArrayList a = sel.executeQuery(sql);
        categoryID=(String)a.get(0);
        categoryName=(String)a.get(1);
    }

    /**
     *
     * @return
     */
    public String getCatName()
    {
        return categoryName;
    }

    /**
     *
     * @return
     */
    public String getCatID()
    {
        return categoryID;
    }
    
}
