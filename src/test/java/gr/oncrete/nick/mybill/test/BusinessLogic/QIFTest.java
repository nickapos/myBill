/*
 *  Copyright (C) 2011 nickapos
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
package gr.oncrete.nick.mybill.test.BusinessLogic;

import gr.oncrete.nick.mybill.BusinessLogic.CheckAFM;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.DumpDatabaseinQIF;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nickapos
 */
public class QIFTest {

    /**
     *
     */
    public QIFTest() {
    }

    /**
     *
     * @throws Exception
     */
    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @org.junit.Before
    public void setUp() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * test reference qif
     */
    @Test
    public void testCheckQIF() {
        System.out.println("check reference qif");
        
        String category = "General Category";
        String company = "My Company";
        String amount = "1.43";
        String date = "2015-12-18";
        String comment = "A comment";
        DumpDatabaseinQIF instance = new DumpDatabaseinQIF();        
        String qifString = instance.convertRecordToQIF(category, company, amount, date, comment);
        String referenceQIFString = "D12/18/2015\nT1.43\nPMy Company\n^";
        assertTrue(referenceQIFString.contentEquals(qifString));
        
    }
    
}
