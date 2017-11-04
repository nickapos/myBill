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
package gr.oncrete.nick.mybill.BusinessLogic;

import gr.oncrete.nick.mybill.RDBMS.InsertIntoTable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class DeleteCategory {
private final static Logger LOGGER = Logger.getLogger(DeleteCategory.class.getName());
    /**
     *
     */
    public DeleteCategory() {

    }

    /**
     *
     * @param id
     */
    public void DeleteCategoryWithID(String id) {
        String sql = "delete from categories where catid=" + id;

        if (id.length() > 0) {
            InsertIntoTable in = new InsertIntoTable(sql);
            LOGGER.log(Level.INFO, in.toString());
        }
    }

    /**
     *
     * @param catName
     */
    public void DeleteCategoryWithName(String catName) {
        String sql = "delete from categories where categoryName='" + catName + "'";
        if (catName.length() > 0) {
            InsertIntoTable in = new InsertIntoTable(sql);
            LOGGER.log(Level.INFO, in.toString());
        }
    }

}
