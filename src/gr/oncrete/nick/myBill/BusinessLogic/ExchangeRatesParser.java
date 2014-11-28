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
package gr.oncrete.nick.myBill.BusinessLogic;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.text.DecimalFormat;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author nickapos
 */
public class ExchangeRatesParser {

    HashMap rateMap = new HashMap();
    URL exchangeRatesUrl;
    InputStream stream;
    

    /**
     * If you want to parse from the internet call the prepareStreamFromNet
     * method if you want to parse from a file on the disc call the
     * prepareStreamFromFile method
     */
    public ExchangeRatesParser() {
        //if there is a local file load it fast in order for the user to not wait
        // this.prepareRatesFromDisk();
        //rateMap=rateMapFromDisk;
        //then update the rates from the net
        //this.prepareRatesFromNet();
    }

    public void prepareRatesFromNet() {
        try {
            exchangeRatesUrl = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
            stream = exchangeRatesUrl.openStream();
            this.parseXML(stream);
            this.saveExchangeRatesToDisk();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareRatesFromDisk() {
        if (this.isMapEmpty()) {
            this.readRatesFromDisk();
        }
    }

    /**
     * this method will read and parse the exchange rates published by ecb in a
     * hash map
     */
    public void parseXML(InputStream st) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(st);
            doc.getDocumentElement().normalize();

            //get the cube elements
            NodeList nodeLst = doc.getElementsByTagName("Cube");

            for (int s = 0; s < nodeLst.getLength(); s++) {
                Node fstNode = nodeLst.item(s);
                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) fstNode;
                    //reat the attributes currency and rate from each member of the Cube node list
                    if (!fstElmnt.getAttribute("currency").isEmpty() && !fstElmnt.getAttribute("rate").isEmpty()) {
                        // System.out.println(fstElmnt.getAttribute("currency") + "," + fstElmnt.getAttribute("rate"));
                        rateMap.put(fstElmnt.getAttribute("currency"), fstElmnt.getAttribute("rate"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return true if the map is empty
     */
    public boolean isMapEmpty() {
        return rateMap.isEmpty();
    }

    /**
     *
     * @return the number of records in the exchange rates dataset
     */
    public int numberOfRecords() {
        return rateMap.size();
    }

    /**
     *
     * @return the map that contains the exchange rates
     */
    private HashMap getRatesMap() {
        return rateMap;
    }

    /**
     * with the inversion flag we can choose to invert or not the exchange rate
     * 0 for normal 1 for inversion
     *
     * @return a string that will contain the exchange rate values
     */
    public String presentRates(int inversion) {
        String pr = "";
        if (inversion == 0) {
            pr = "The value of 1 euro in these currencies\n";
        } else if (inversion == 1) {
            pr = "The value of one of each currency in euro is\n";
        }
        Set rateKeySet = rateMap.keySet();
        Iterator it = rateKeySet.iterator();

        while (it.hasNext()) {
            String rateSymbol = (String) it.next();
            String rateValue = (String) rateMap.get(rateSymbol);
            if (inversion == 0) {
                pr = pr + "\n" + rateSymbol + "," + rateValue;
            } else if (inversion == 1) {
                String flippedRateValue = "" + (new DecimalFormat("###,###.###").format((double) (1 / Double.parseDouble(rateValue))));
                pr = pr + "\n" + rateSymbol + "," + flippedRateValue;

            }

        }

        return pr;
    }

    /**
     * with the inversion flag we can choose to invert or not the exchange rate
     * 0 for normal 1 for inversion
     *
     * @return a string array that will contain the exchange rate values
     */
    public String[][] presentRatesArray(int inversion) {
        String[][] resultAr = new String[this.numberOfRecords()][2];

        Set rateKeySet = rateMap.keySet();
        Iterator it = rateKeySet.iterator();
        int counter = 0;
        while (it.hasNext()) {
            String rateSymbol = (String) it.next();
            String rateValue = (String) rateMap.get(rateSymbol);
            //cover with one block of code both inversion and straight exchange rates
            if (inversion == 0) {
                resultAr[counter][0] = rateSymbol;
                resultAr[counter][1] = rateValue;
            } else if (inversion == 1) {
                resultAr[counter][0] = rateSymbol;
                resultAr[counter][1] = "" + (new DecimalFormat("###,###.###").format((double) (1 / Double.parseDouble(rateValue))));
            }
            counter++;

        }

        return resultAr;
    }

    /**
     * Use this method to save the exchangeRates to the disk as a serialized
     * hasmap file
     */
    private void saveExchangeRatesToDisk() {
        if (!this.isMapEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream("Rates");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(rateMap);
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    /**
     * this method will read the serialized rates hasmap from the disk if it
     * exists
     *
     */
    private void readRatesFromDisk() {
        try {
            FileInputStream fis = new FileInputStream("Rates");
            ObjectInputStream ois = new ObjectInputStream(fis);
            rateMap = (HashMap) ois.readObject();
            ois.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("Rates cache file not found, will create one.");
        } catch (ClassNotFoundException clnf) {
            clnf.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
