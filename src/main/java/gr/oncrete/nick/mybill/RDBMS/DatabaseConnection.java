/*
myBill, bills tracking program
Copyright (C) 2010  Nick Apostolakis

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 /*
 * DatabaseConnection.java
 *
 * Created on 25 Μάϊος 2005, 7:38 μμ
 */
package gr.oncrete.nick.mybill.RDBMS;

import gr.oncrete.nick.mybill.BusinessLogic.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class DatabaseConnection {

    private final static Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    static Connection conn;
    Config mp = new Config();

    /**
     * Creates a new instance of DatabaseConnection
     */
    protected DatabaseConnection() {
        try {
            //mp.loadProperties();
            Class.forName(mp.getDBDriver());
            //db_file_name_prefix="file:/Users/nickapos/Documents/projects/myBill/mybilldb/mybilldb";
            //"jdbc:hsqldb:
            conn = DriverManager.getConnection(mp.getDBUrl()
                    + mp.getDBName(), // filenames
                    mp.getDBUname(), // username
                    mp.getDBPass());                      // password

        }
        catch (ClassNotFoundException clnf) {
            clnf.printStackTrace();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * @param db_file_name_prefix
     */
    protected DatabaseConnection(String db_file_name_prefix) {
        try {
            //mp.loadProperties();
            Class.forName(mp.getDBDriver());
            //db_file_name_prefix="file:/Users/nickapos/Documents/projects/myBill/mybilldb/mybilldb";
            //"jdbc:hsqldb:
            conn = DriverManager.getConnection(mp.getDBUrl()
                    + db_file_name_prefix, // filenames
                    mp.getDBUname(), // username
                    mp.getDBPass());                      // password

        }
        catch (ClassNotFoundException clnf) {
            clnf.printStackTrace();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    //singleton implementation
    static private DatabaseConnection _instance = null;

    /**
     *
     * @param db_file_name_prefix
     * @return
     */
    static public DatabaseConnection instance(String db_file_name_prefix) {
        if (null == _instance) {
            _instance = new DatabaseConnection(db_file_name_prefix);
        }
        return _instance;

    }

    /**
     *
     * @return
     */
    static public DatabaseConnection instance() {
        if (null == _instance) {
            _instance = new DatabaseConnection();
        }
        return _instance;

    }

    /**
     *
     * This method will shutdown the connection with the database.
     *
     */
    static public void shutdown() {
        try {
            conn.close();    // if there are no other open connection
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * @param expression
     * @throws SQLException
     */
    static public synchronized void query(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();// statement objects can be reused with

        // repeated calls to execute but we
        // choose to make a new one each time
        rs = st.executeQuery(expression);    // run the query

        // do something with the result set.
        dump(rs);
        st.close();    // NOTE!! if you close a statement the associated ResultSet is

        // closed too
        // so you should copy the contents to some other object.
        // the result set is invalidated also  if you recycle an Statement
        // and try to execute some other query before the result set has been
        // completely examined.
    }

    /**
     *
     * This method is supposed to return its result in a way that can be used by
     * the calling method. Something like an arraylist or a linked list. one
     * string field per line (arraylist)
     *
     * @param expression
     * @return
     * @throws SQLException
     */
    static public synchronized ArrayList queryReturnResult(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;
        ArrayList lst = new ArrayList();

        st = conn.createStatement();
        rs = st.executeQuery(expression);
        if (rs != null) {
            dump(rs, lst);
        }
        st.close();
        return lst;

    }

    /**
     *
     * This method is supposed to return its result in a way that can be used by
     * the calling method. Something like an arraylist or a linked list. one
     * string array per line (arraylist)
     *
     * @param expression
     * @return
     * @throws SQLException
     */
    static public synchronized ArrayList queryReturnResultA(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;
        ArrayList lst = new ArrayList();

        st = conn.createStatement();
        rs = st.executeQuery(expression);
        if (rs != null) {
            dumpArrayPerRow(rs, lst);
        }
        st.close();
        return lst;

    }

    /**
     *
     * This method is supposed to return its result in a way that can be used by
     * the calling method. Something like an arraylist or a linked list. one
     * string array per line (linked list)
     *
     * @param expression
     * @return
     * @throws SQLException
     */
    static public synchronized LinkedList queryReturnResultL(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;
        LinkedList lst = new LinkedList();

        st = conn.createStatement();
        rs = st.executeQuery(expression);
        if (rs != null) {
            dumpLinkedListPerRow(rs, lst);
        }
        st.close();
        return lst;

    }

    /**
     *
     * @param expression
     * @throws SQLException
     */
    static public synchronized void update(String expression) throws SQLException {

        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
            LOGGER.log(Level.INFO, String.format("db error : %s", expression));
        }

        st.close();
    }    // void update()

    /**
     *
     * A function that prints out the contents of the Result Set
     *
     * @param rs
     * @throws SQLException
     */
    static public void dump(ResultSet rs) throws SQLException {

        // the order of the rows in a cursor
        // are implementation dependent unless you use the SQL ORDER statement
        ResultSetMetaData meta = rs.getMetaData();
        int colmax = meta.getColumnCount();
        int i;

        // the result set is a cursor into the data.  You can only
        // point to one row at a time
        // assume we are pointing to BEFORE the first row
        // rs.next() points to next row and returns true
        // or false if there is no next row, which breaks the loop
        while (rs.next()) {
            for (i = 0; i < colmax; ++i) {
                Object o = rs.getObject(i + 1);    // Is SQL the first column is indexed

                LOGGER.log(Level.INFO, o.toString());
            }
        }
    }

    /**
     *
     * An overloaded function like the above with the only difference that it
     * puts the data of the Result Set in an array list and then prints them out
     * in the console. The array list is then processed from the calling
     * function.
     *
     * @param rs
     * @param lst
     * @throws SQLException
     */
    static public void dump(ResultSet rs, ArrayList lst) throws SQLException {

        // the order of the rows in a cursor
        // are implementation dependent unless you use the SQL ORDER statement
        ResultSetMetaData meta = rs.getMetaData();
        int colmax = meta.getColumnCount();
        int i;
        // the result set is a cursor into the data.  You can only
        // point to one row at a time
        // assume we are pointing to BEFORE the first row
        // rs.next() points to next row and returns true
        // or false if there is no next row, which breaks the loop
        while (rs.next()) {
            String o = null;
            for (i = 0; i < colmax; ++i) {
                if (rs.getString(i + 1) != null) {
                    o = rs.getString(i + 1);    // Is SQL the first column is indexed with 1 not 0
                    // System.out.print (o.toString () + " ");
                } else {
                    o = "";
                }
                lst.add(o.toString());
            }
        }
    }

    /**
     *
     * An overloaded function like the above with the only difference that it
     * puts the data of the Result Set in an linked list as a string array per
     * line. each column in a table cell and all together form a row. The array
     * list is then processed from the calling function.
     *
     * @param rs
     * @param lst
     * @throws SQLException
     */
    static public void dumpLinkedListPerRow(ResultSet rs, LinkedList<String[]> lst) throws SQLException {

        // the order of the rows in a cursor
        // are implementation dependent unless you use the SQL ORDER statement
        ResultSetMetaData meta = rs.getMetaData();
        int colmax = meta.getColumnCount();
        int i;
        // the result set is a cursor into the data.  You can only
        // point to one row at a time
        // assume we are pointing to BEFORE the first row
        // rs.next() points to next row and returns true
        // or false if there is no next row, which breaks the loop
        while (rs.next()) {
            String[] s = new String[colmax];
            for (i = 0; i < colmax; ++i) {
                if (rs.getString(i + 1) != null) {
                    s[i] = rs.getString(i + 1);    // Is SQL the first column is indexed with 1 not 0
                    //System.out.print (s[i] + " ");
                } else {
                    s[i] = "";
                }
            }
            lst.add(s);
        }
    }

    /**
     *
     * An overloaded function like the above with the only difference that it
     * puts the data of the Result Set in an array list as a string array per
     * line. each column in a table cell and all together form a row. The array
     * list is then processed from the calling function.
     *
     * @param rs
     * @param lst
     * @throws SQLException
     */
    static public void dumpArrayPerRow(ResultSet rs, ArrayList<String[]> lst) throws SQLException {

        // the order of the rows in a cursor
        // are implementation dependent unless you use the SQL ORDER statement
        ResultSetMetaData meta = rs.getMetaData();
        int colmax = meta.getColumnCount();
        int i;
        // the result set is a cursor into the data.  You can only
        // point to one row at a time
        // assume we are pointing to BEFORE the first row
        // rs.next() points to next row and returns true
        // or false if there is no next row, which breaks the loop
        while (rs.next()) {
            String[] s = new String[colmax];
            for (i = 0; i < colmax; ++i) {
                if (rs.getString(i + 1) != null) {
                    s[i] = rs.getString(i + 1);    // Is SQL the first column is indexed with 1 not 0
                    // System.out.print (o.toString () + " ");
                } else {
                    s[i] = "";
                }
            }
            lst.add(s);
        }
    }

    /**
     * This method is used to set the autocommit flag off and so enabling the
     * transactional behaviour of the database
     */
    static public void setAutoCommitOff() {
        try {
            conn.setAutoCommit(false);
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * This method is used to set the autocommit flag on and so disabling the
     * transactional behaviour of the database.
     *
     * Now each sql command will be commited exactly after its execution
     * automatically.
     */
    static public void setAutoCommitOn() {
        try {
            conn.setAutoCommit(true);
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * This method calls the jdbc method rollback for transaction rollback.
     */
    static public void rollbackTransaction() {
        try {
            conn.rollback();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * This method calls the jdbc method commit. It commits the current
     * transaction
     */
    static public void commitTransaction() {
        try {
            conn.commit();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
