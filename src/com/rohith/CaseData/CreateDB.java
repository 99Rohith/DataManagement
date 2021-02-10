/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohith.CaseData;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author rohith
 */
public class CreateDB {
    public static Connection conn = null;
    public static int nColumns = 43;
    CreateDB(String dbPath)
    {
        try {
            conn = ConnectDB.Connect(dbPath);
            createTables();
            ConnectDB.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(CreateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void createTables() throws SQLException
    {
        recordsTable();
        //lrsTable();
        //test();
        //test2();
    }
    public static String[] dataBaseColumns()
    {
        String[] dbColumns = new String[nColumns];
        dbColumns[0] = "name_client";
        dbColumns[1] = "cell_no";
        dbColumns[2] = "address";
        dbColumns[3] = "aadhar";
        dbColumns[4] = "lrs";
        dbColumns[5] = "laop_no";
        dbColumns[6] = "cl_no_laop";
        dbColumns[7] = "village";
        dbColumns[8] = "reach_no";
        dbColumns[9] = "date_dn";
        dbColumns[10] = "award_No";
        dbColumns[11] = "date_award";
        dbColumns[12] = "date_filing_laop";
        dbColumns[13] = "name_court";
        dbColumns[14] = "doj_lc";
        dbColumns[15] = "section";
        dbColumns[16] = "appeal_no";
        dbColumns[17] = "date_filing_appeal";
        dbColumns[18] = "doj_appeal";
        dbColumns[19] = "appleant_no";
        dbColumns[20] = "wp_no";
        dbColumns[21] = "date_filing_wp";
        dbColumns[22] = "doj_wp";
        dbColumns[23] = "cc_no";
        dbColumns[24] = "date_filing_cc";
        dbColumns[25] = "doj_cc";
        dbColumns[26] = "dhr_no";
        dbColumns[27] = "ep_amount";
        dbColumns[28] = "ep_no";
        dbColumns[29] = "doorder_ep";
        dbColumns[30] = "pv_amount";
        dbColumns[31] = "dopv";
        dbColumns[32] = "it_amount";
        dbColumns[33] = "slp_ca_no";
        dbColumns[34] = "doj_slp_ca";
        dbColumns[35] = "c_p_no";
        dbColumns[36] = "c_p_amount";
        dbColumns[37] = "do_allowed";
        dbColumns[38] = "doorder_cp";
        dbColumns[39] = "misc";
        dbColumns[40] = "expenses";
        dbColumns[41] = "nooperator";
        dbColumns[42] = "doadjustment";
        return dbColumns;
    }
    public static String[] dbColumnDTypes()
    {
        String[] dbDTypes = new String[nColumns];
        dbDTypes[0] = "TEXT NOT NULL";
        dbDTypes[1] = "TEXT";
        dbDTypes[2] = "TEXT";
        dbDTypes[3] = "TEXT";
        dbDTypes[4] = "TEXT";
        dbDTypes[5] = "TEXT";
        dbDTypes[6] = "TEXT";
        dbDTypes[7] = "TEXT";
        dbDTypes[8] = "TEXT";
        dbDTypes[9] = "TEXT";
        dbDTypes[10] = "TEXT";
        dbDTypes[11] = "TEXT";
        dbDTypes[12] = "TEXT";
        dbDTypes[13] = "TEXT";
        dbDTypes[14] = "TEXT";
        dbDTypes[15] = "TEXT";
        dbDTypes[16] = "TEXT";
        dbDTypes[17] = "TEXT";
        dbDTypes[18] = "TEXT";
        dbDTypes[19] = "TEXT";
        dbDTypes[20] = "TEXT";
        dbDTypes[21] = "TEXT";
        dbDTypes[22] = "TEXT";
        dbDTypes[23] = "TEXT";
        dbDTypes[24] = "TEXT";
        dbDTypes[25] = "TEXT";
        dbDTypes[26] = "TEXT";
        dbDTypes[27] = "TEXT";
        dbDTypes[28] = "TEXT";
        dbDTypes[29] = "TEXT";
        dbDTypes[30] = "REAL";
        dbDTypes[31] = "TEXT";
        dbDTypes[32] = "REAL";
        dbDTypes[33] = "TEXT";
        dbDTypes[34] = "TEXT";
        dbDTypes[35] = "TEXT";
        dbDTypes[36] = "REAL";
        dbDTypes[37] = "TEXT";
        dbDTypes[38] = "TEXT";
        dbDTypes[39] = "TEXT";
        dbDTypes[40] = "REAL";
        dbDTypes[41] = "TEXT";
        dbDTypes[42] = "TEXT";
        return dbDTypes;
    }
    public void recordsTable() throws SQLException
    {
        String query = "CREATE TABLE IF NOT EXISTS records(\n" +
                        "s_no INTEGER PRIMARY KEY AUTOINCREMENT,\n";
        String[] dbColumns = dataBaseColumns();
        String[] dbDTypes = dbColumnDTypes();
        for(int i=0;i<(nColumns-1);i++)
        {
            query += dbColumns[i] + " " + dbDTypes[i] + ",\n";
        }
        query += dbColumns[nColumns-1] + " " + dbDTypes[nColumns-1] + ");";
        Statement st = conn.createStatement();
        st.execute(query);
    }
    /*public void lrsTable() throws SQLException
    {
        String query1 = "CREATE TABLE IF NOT EXISTS lrs(\n" +
                        "p_no INTEGER,\n" +
                        "name TEXT,\n" +
                        "FOREIGN KEY(p_no) REFERENCES records(s_no) ON DELETE CASCADE\n" +
                        ");";
        Statement st = conn.createStatement();
        st.execute(query1);
    }*/
    public static String getInsertQuery()
    {
        String query = "INSERT INTO records(";
        String[] dbColumns = CreateDB.dataBaseColumns();
        for(int i=0;i<CreateDB.nColumns-1;i++)
        {
            query += dbColumns[i] + ",";
        }
        query += dbColumns[CreateDB.nColumns-1];
        query +=    ") VALUES(?,?,?,?,?,"
                    + "?,?,?,?,?,?,"
                    + "?,?,?,"
                    + "?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?);";
        return query;
    }
    public static String getUpdateQuery()
    {
        String[] dbColumns = CreateDB.dataBaseColumns();
        String query = "UPDATE records SET ";
        for(int i=0;i<CreateDB.nColumns-1;i++)
        {
            query += dbColumns[i] + "= ? , ";
        }
        query += dbColumns[CreateDB.nColumns-1] + "= ? ";
        query += "WHERE s_no = ? ;";
        return query;
    }
    public void test() throws SQLException
    {
        /*String query = "INSERT INTO records(Name_Client,cell_no) VALUES('ROHITH','12345');";
        Statement st = conn.createStatement();
        st.execute(query);*/
        
        String query2 = "SELECT COUNT(*) AS cnt FROM records WHERE cell_no=?;";
        PreparedStatement ps1 = conn.prepareStatement(query2);
        String phnNo = "12345";
        ps1.setString(1,phnNo);
        ResultSet rs1 = ps1.executeQuery();
        if(rs1.next())
        {
            System.out.println(rs1.getString("cnt"));
        }
        
        /*String query3 = "INSERT INTO lrs(p_no,name) VALUES('1','nandi');";
        Statement st2 = conn.createStatement();
        st2.execute(query);*/
        String testQuery = this.getInsertQuery();
        PreparedStatement ps = conn.prepareStatement(testQuery);
        ps.setString(1, "Kolanakuduru Ramasundra Rao blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah");
        ps.setString(2, "850xxxxxx05");
        ps.setString(3, "N.R. peta Gudur");
        ps.setString(4, "102742389734");
        ps.setString(5, "Kolanakuduru Venkata Siva Kumar,\nKolanaKuduru Venkata Naga Sai Manidhar");
        ps.setString(6, "13 of 2012");
        ps.setString(7, "1");
        ps.setString(8, "Kolanakuduru");
        ps.setString(9, "7B");
        ps.setString(10, "12/1/13");
        ps.setString(11, "12 of 2011");
        ps.setString(12, "11/2/2005");
        ps.setString(13, "12/1/12");
        ps.setString(14, "SCJ");
        ps.setString(15, "12/3/13");
        ps.setString(16, "3B");
        ps.setString(17, "17 of 2019");
        ps.setString(18, "18/2/2019");
        ps.setString(19, "20/12/20");
        ps.setString(20, "36");
        ps.setString(21, "18 of 2012");
        ps.setString(22, "18/2/2019");
        ps.setString(23, "19/2/2019");
        ps.setString(24, "21 of 2011");
        ps.setString(25, "19/2/2019");
        ps.setString(26, "19/2/2019");
        ps.setString(27, "8414 of 2019");
        ps.setString(28, "300");
        ps.setString(29, "2 of 2021");
        ps.setString(30, "5/5/22");
        ps.setString(31, "3000");
        ps.setString(32, "12/12/22");
        ps.setString(33, "1562");
        ps.setString(34, "26 of 2019");
        ps.setString(35, "15/2/20");
        ps.setString(36, "25 of 2019");
        ps.setString(37, "300");
        ps.setString(38, "25/10/2019");
        ps.setString(39, "25/6/2011");
        ps.setString(40, "Daughter : kk, Son : PP");
        ps.setString(41, "15000");
        ps.setString(42, "Mani");
        ps.setString(43, "23/1/2020");
        
        ps.execute();
    }
    public static void test2() throws SQLException
    {
        String s_no = "1";
        String query2 = "SELECT * FROM records WHERE s_no=?;";
        PreparedStatement ps1 = conn.prepareStatement(query2);
        ps1.setString(1,s_no);
        ResultSet rs1 = ps1.executeQuery();
        if(rs1.next())
        {
            System.out.println(rs1.getString("name_client"));
        }
    }
}