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

import gr.oncrete.nick.myBill.BusinessLogic.FileHandlers.*;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *This class is intentend to be used when we want a write or read file activity
 * to be preformed in a new thread.
 * @author nickapos 23 Νοε 2010
 */
public class NewThreadExchangeRatesParser extends SwingWorker<String, String> {

    ExchangeRatesParser parserNet,parserFile,parser;

    
    /**
     *
     */
    
    public NewThreadExchangeRatesParser(JTextField foreignCurrencyTextField,JCheckBox foreignCurrencyCheckBox) {
        parserFile = new ExchangeRatesParser(foreignCurrencyTextField,foreignCurrencyCheckBox);
        this.initParser();
    }
            
            
    public NewThreadExchangeRatesParser() {
        parserFile = new ExchangeRatesParser();
        this.initParser();
        
    }
    
    /**
     * This is a helper method that will 
     * be used from both constructors in order to avoid code repetition
     */
    private void initParser()
    {
        parserFile.prepareRatesFromDisk();
        parser=parserFile;
    }

    /**
     * this method executes tha parser in a separate
     * thread
     * it is called by the constructor. no need to call it from the caller class
     * @return
     */
    @Override
    public String doInBackground() {
        parserNet = new ExchangeRatesParser();
        parserNet.prepareRatesFromNet();
        parser=parserNet;
        return "done";
    }

    /**
     *with the inversion flag we can choose to invert or not the exchange rate
     * 0 for normal
     * 1 for inversion
     *
     * @return a string that will contain the exchange rate values
     *
     */
    public String presentRates(int invert) {
        return parserFile.presentRates(invert);
    }

    /**
     *with the inversion flag we can choose to invert or not the exchange rate
     * 0 for normal
     * 1 for inversion
     *
     * @return a string array that will contain the exchange rate values
     */
    public Object[][] presentRatesArray(int invert) {
        return parser.presentRatesArray(invert);
    }

    public boolean isEmpty() {
        if (parser == null) {
            return true;
        } else if (parser != null && parser.isMapEmpty()) {
            return true;
        }
        return false;
    }
}
