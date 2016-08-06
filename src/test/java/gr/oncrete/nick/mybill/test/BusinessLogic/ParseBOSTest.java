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


import gr.oncrete.nick.mybill.BusinessLogic.ParseBOSCsv;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nickapos
 */
public class ParseBOSTest {

    /**
     *
     */
    public ParseBOSTest() {
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
    
    @Test
    public void testCheckIntStr() {
        System.out.println("check date with num");
        String a = "08";
        ParseBOSCsv instance = new ParseBOSCsv();  
        assertTrue(instance.isInteger(a));
           
    }
    @Test
    public void testCheckNotIntStr() {
        System.out.println("check date with num");
        String a = "08a";
        ParseBOSCsv instance = new ParseBOSCsv();  
        assertFalse(instance.isInteger(a));
           
    }
    @Test
    public void testCheckNotIntStrEmpty() {
        System.out.println("check date with num");
        String a = "";
        ParseBOSCsv instance = new ParseBOSCsv();  
        assertFalse(instance.isInteger(a));
           
    }
   
    @Test
    public void testCheckDateIntStrWith0() {
        System.out.println("check date with num");
        String a = "01/08/2016";
        ParseBOSCsv instance = new ParseBOSCsv();  
        String result=instance.convertDate(a);
        System.out.println("We got "+result);
        assertTrue(result.equals("01/08/2016"));
           
    }
    @Test
    public void testCheckDateIntStrWithout0() {
        System.out.println("check date with num");
        String a = "01/8/2016";
        ParseBOSCsv instance = new ParseBOSCsv();  
        String result=instance.convertDate(a);
        System.out.println("We got "+result);
        assertTrue(result.equals("01/8/2016"));
           
    }

   
    @Test
    public void testCheckNonIntStr() {
        System.out.println("check date with num");
        String a = "01/Aug/2016";
        ParseBOSCsv instance = new ParseBOSCsv();       
        assertTrue(instance.convertDate(a).equals("01/8/2016"));
           
    }



}