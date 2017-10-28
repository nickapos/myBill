/*
 * Copyright (C) 2016 nickapos
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package gr.oncrete.nick.mybill.BusinessLogic;

/**
 *
 * @author nickapos
 */
public class ParseN26Csv extends ParseCsv {
    //tsb has 8 fields in its csv
    static final int numOfFields = 10;
    private String[] header= {"Date","Payee","Account number","Transaction type","Payment reference","Category","Amount (EUR)","Amount (Foreign Currency)","Type Foreign Currency","Exchange Rate"};

    public ParseN26Csv() {
        super(numOfFields);

    }

    public ParseN26Csv(int fields) {
        super( fields);

    }

    public ParseN26Csv(String file, String delim,int numberOfFields) {
        super( delim, numberOfFields);
    }

    @Override
    public int getNumOfFields()
    {
        return numOfFields;
    }
    
    @Override
    public String[] getColumnNames()
    {
        return header;
    }
}
