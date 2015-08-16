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

import gr.oncrete.nick.mybill.BusinessLogic.InvertRecord;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nickapos
 */
public class CheckInvertRecord {

  
    /**
     *
     */
    public CheckInvertRecord() {
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
     * test 0 length id
     */
    @Test
    public void checkZeroLengthID() {
        System.out.println("test 0 length id");
        String a="";
        InvertRecord instance = new InvertRecord();       
        assertTrue(instance.convertID(a)==-3);
           
    }

    /**
     * test non int id
     */
    @Test
    public void checkNonIntID() {
        System.out.println("test non int id");
        String a = "1.1";
        InvertRecord instance = new InvertRecord();       
        assertTrue(instance.convertID(a)==-1);
           
    }
    
       

}