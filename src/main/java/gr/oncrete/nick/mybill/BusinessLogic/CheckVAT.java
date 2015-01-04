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
package gr.oncrete.nick.myBill.BusinessLogic;

/**
 *
 * @author nickapos 6 Σεπ 2010
 */
public class CheckVAT extends CheckAFM {

    
    /**
     * Just an empty constructor
     *
     */
    public CheckVAT() {
    }


    /**
     * constructor with the incoming afm as an string
     *
     * @param afmS
     */
    public CheckVAT(String afmS) {
        if (!afmS.equals("000000000")) {
            if (afmS.length() > afmSize) {
                System.out.println("Too Long");
            } else if (afmS.length() == afmSize) {
                this.checkAFM(afmS);
            } else if (afmS.length() < afmSize) {
                this.checkAFM(this.padStr(afmS));

            }
        }
    }

    /**
     * pad the afm if it has less than 9 digits
     * @param st
     * @return
     */
    private String padStr(String st) {
        String paddedStr = "";
        if (st.length() < afmSize) {
            int pads = afmSize - st.length();
            for (int i = 0; i < pads; i++) {
                paddedStr = paddedStr + "0";
            }
        }
        paddedStr = paddedStr + st;
        return paddedStr;
    }

    /**
     *
     * check the incoming afm if it is valid
     * @param a
     */
    @Override
    protected void checkAFM(String a) {
        int sum = 0;

        for(int i=0,o=8;i<afmSize-2;i++,o--)
        {
            char afmChar = a.charAt(i);
            sum=sum+Character.getNumericValue(afmChar)*o;
        }
        while(sum>0)
        {
            sum=sum-97;
        }
        String checkDigits = a.substring(a.length()-2, a.length());
        System.out.println(checkDigits);
        int check =Integer.parseInt(checkDigits);
        if (check==Math.abs(sum)) {
            isvalid = true;
            afm=a;
        } 
    }


    /**
     * return the result of the test to the world
     * @return
     */
    public boolean returnResult() {
        return isvalid;
    }
    /**
     *
     * @return
     */
    public String getVAT()
    {
        return afm;
    }
}
