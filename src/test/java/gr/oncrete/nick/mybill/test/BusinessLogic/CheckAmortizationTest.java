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

import gr.oncrete.nick.mybill.BusinessLogic.Amortization;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author nickapos
 */
public class CheckAmortizationTest {
    private static final double DELTA = 0.5;
    /**
     *
     */
    public CheckAmortizationTest() {
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
     * test amortization case 30000 amount
     * 15 years or 180 months payoff period
     * 10% interest
     */
    
    @Test
    public void testCheckAmortizationYearly1() {
        System.out.println("Monthly case 10000 amount 5 years or 60 months payoff period 10% interest");
        Amortization instance = new Amortization(10000,60,10,1);
        System.out.println("amort result "+instance.calculateAmortization());
        double resultAm=instance.getInstallment();
        assertEquals(2637.97,resultAm,DELTA);
           
    }

    

   @Test
    public void testCheckAmortizationTriMonthly() {
        System.out.println("TriMonthly test amortization case 10000 amount 3 years or 24 months payoff period 5% interest");
        Amortization instance = new Amortization(10000,36,5,4);
        System.out.println("amort result "+instance.calculateAmortization());
        double resultAm=instance.getInstallment();
        assertEquals(902.58,resultAm,DELTA);

    }

   @Test
    public void testCheckAmortizationSixmonthly() {
        System.out.println("Sixmonthly test amortization case 10000 amount 5 years or 60 months payoff period 10% interest");
        Amortization instance = new Amortization(10000,60,10,2);
        System.out.println("amort result "+instance.calculateAmortization());
        double resultAm=instance.getInstallment();
        assertEquals(1295.05,resultAm,DELTA);

    }

}