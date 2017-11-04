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

import gr.oncrete.nick.mybill.BusinessLogic.InsertBills;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author nickapos
 */
public class CheckInsertBillsTest {

    int AFMSIZE = 9;

    /**
     *
     */
    public CheckInsertBillsTest() {
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
     * test insert bill without comment
     */
    @Test
    public void testInsertSqlWithNoComment() {
        System.out.println("test insert bill without comment");
        String a = "insert into bills (cid,price,dateofissue,dayofpayment) values (464,225,'2015-08-19','2015-08-19')";
        InsertBills instance = new InsertBills();
        String returnStr = instance.parseinsertBillsArgumentsWithoutId("464", "225", "2015-08-19", "2015-08-19", "");
        assertTrue(returnStr.equals(a));

    }

    /**
     * test insert bill with comment
     */
    @Test
    public void testInsertSqlWithComment() {
        System.out.println("test insert bill with comment");
        String a = "insert into bills (cid,price,dateofissue,dayofpayment,comment) values (465,12,'2015-08-19','2015-08-19','ee')";
        InsertBills instance = new InsertBills();
        String returnStr = instance.parseinsertBillsArgumentsWithoutId("465", "12", "2015-08-19", "2015-08-19", "ee");
        assertTrue(returnStr.equals(a));

    }

    /**
     * test insert bill no cid argument
     */
    @Test
    public void testInsertSqlWithZeroArgs1() {
        System.out.println("test insert bill no cid argument");
        String a = "";
        InsertBills instance = new InsertBills();
        String returnStr = instance.parseinsertBillsArgumentsWithoutId("", "12", "2015-08-19", "2015-08-19", "ee");
        assertTrue(returnStr.equals(a));

    }

    /**
     * test insert bill with no price argument
     */
    @Test
    public void testInsertSqlWithZeroArgs2() {
        System.out.println("test insert bill with no price argument");
        String a = "";
        InsertBills instance = new InsertBills();
        String returnStr = instance.parseinsertBillsArgumentsWithoutId("465", "", "2015-08-19", "2015-08-19", "ee");
        assertTrue(returnStr.equals(a));

    }

    /**
     * test insert bill with no date of issue argument
     */
    @Test
    public void testInsertSqlWithZeroArgs3() {
        System.out.println("test insert bill with no date of issue argument");
        String a = "";
        InsertBills instance = new InsertBills();
        String returnStr = instance.parseinsertBillsArgumentsWithoutId("465", "12", "", "2015-08-19", "ee");
        assertTrue(returnStr.equals(a));

    }

    /**
     * test insert bill with no date of payment argument
     */
    @Test
    public void testInsertSqlWithZeroArgs5() {
        System.out.println("test insert bill with no date of payment argument");
        String a = "";
        InsertBills instance = new InsertBills();
        String returnStr = instance.parseinsertBillsArgumentsWithoutId("465", "12", "2015-08-19", "", "ee");
        assertTrue(returnStr.equals(a));

    }

}
