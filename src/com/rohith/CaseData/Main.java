/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohith.CaseData;

/**
 *
 * @author rohith
 */
public class Main {
    public static void main(String args[])
    {
        String del = System.getProperty("file.separator");
        //String dbPath = System.getProperty("user.dir") + del + "Database" + del + "caseData.sqlite";
        String dbPath = "/home/rohith/Documents/Case_Data_Management_System/Database/caseData.sqlite";
        CreateDB cdb = new CreateDB(dbPath);
        search sr = new search(dbPath);
        sr.setVisible(true);
        /*temp tp = new temp();
        tp.setVisible(true);*/
    }
}
