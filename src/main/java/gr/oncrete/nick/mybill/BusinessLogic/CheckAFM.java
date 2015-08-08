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

/**
 *
 * @author nickapos 6 Σεπ 2010
 */
public class CheckAFM {

    boolean isvalid = false;
    int afmSize = 9;
    String afm="";

    /**
     * Just an empty constructor
     *
     */
    public CheckAFM() {
    }


    /**
     * constructor with the incoming afm as an string
     *
     * @param afmS
     */
    public CheckAFM(String afmS) {
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
    protected void checkAFM(String a) {
        int sum = 0;
        int telestis = 2;
        for (int i = afmSize - 2; i >= 0; i--) {//multiply the reverse afm  (without the last digit) with the increasing powers of2
            char afmChar = a.charAt(i);
            sum = sum + Character.getNumericValue(afmChar) * telestis;
            telestis = telestis * 2;
        }
        int modulo = sum % 11;//find the mofulo
        if (modulo == 10 && a.charAt(afmSize - 1) == '0') {//if modulo is 10 and last digit 0 then we are cool
            isvalid = true;
            afm=a;
        } else if (modulo == Character.getNumericValue((a.charAt(afmSize - 1)))) {//else if last digit is something and modulo the same something we are cool too
            isvalid = true;
            afm=a;
        }
    }


    /**
     * return the if the afm is valid to the world
     * @return
     */
    public boolean returnResult() {
        return isvalid;
    }
    /**
     *
     * @return
     */
    public String getAfm()
    {
        return afm;
    }
}
