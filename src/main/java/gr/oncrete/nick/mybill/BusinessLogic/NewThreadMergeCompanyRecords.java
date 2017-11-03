/*
 *  Copyright (C) 2010 nickapos
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gr.oncrete.nick.mybill.BusinessLogic;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
/**
 *This class wil be used to change the currency of all the records of the database
 * from one to another
 * @author nickapos 6 Σεπ 2010
 */
public class NewThreadMergeCompanyRecords extends SwingWorker<String,String>{

    MergeCompaniesRecords mc;
    

    /**
     *
     * @param rateStr
     */
    public NewThreadMergeCompanyRecords(List listTobeMerged,String newcompanyID,JLabel lbl) {
        mc = new MergeCompaniesRecords(listTobeMerged,newcompanyID,lbl);
        
    }

    @Override
    public String doInBackground() {
        mc.mergeCompanyRecords();
        return "finished";
    }
}
