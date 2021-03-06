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
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author nickapos
 */
public class CheckAFMTest {

    /**
     *
     */
    public CheckAFMTest() {
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
     * test 000000000 afm
     */
    @Test
    public void testCheckAFMZeros() {
        System.out.println("check zeros");
        String a = "000000000";
        CheckAFM instance = new CheckAFM(a);
        assertFalse(instance.returnResult());

    }

    /**
     * test 123456789 afm
     */
    @Test
    public void testCheckAFM123() {
        System.out.println("check random number");
        String a = "123456789";
        CheckAFM instance = new CheckAFM(a);
        assertFalse(instance.returnResult());

    }

    /**
     * test a true afm
     */
    @Test
    public void testCheckAFMTrue() {
        System.out.println("check real afm");
        String a = "999678050";
        CheckAFM instance = new CheckAFM(a);
        assertTrue(instance.returnResult());

    }

}
