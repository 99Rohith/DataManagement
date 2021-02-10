/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohith.CaseData;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Frame;

/**
 *
 * @author rohith
 */
public class viewForm extends javax.swing.JDialog {

    /**
     * Creates new form viewForm
     */
    public int s_no;
    public String dbPath;
    Frame parent;
    public String[] fieldNames()
    {
        String[] fnames = new String[CreateDB.nColumns];
        fnames[0] = "Name of the Client";
        fnames[1] = "Cell No.";
        fnames[2] = "Address";
        fnames[3] = "Aadhar No.";
        fnames[4] = "LR names";
        fnames[5] = "LAOP No.";
        fnames[6] = "Client No. in LAOP";
        fnames[7] = "Name of Village";
        fnames[8] = "Reach No.";
        fnames[9] = "Date of D.N";
        fnames[10] = "Award No.";
        fnames[11] = "Date of Award";
        fnames[12] = "Date filing";
        fnames[13] = "Name of the Court";
        fnames[14] = "Date of Judgement in Lower Court";
        fnames[15] = "Section";
        fnames[16] = "Appeal No.";
        fnames[17] = "Date of filing";
        fnames[18] = "Date of Judgement in Appeal No.";
        fnames[19] = "Appleant No.";
        fnames[20] = "W.P No.";
        fnames[21] = "Date of filing";
        fnames[22] = "Date of Judgement in W.P";
        fnames[23] = "C.C No.";
        fnames[24] = "Date of filing";
        fnames[25] = "Date of Judgement in C.C";
        fnames[26] = "D.Hr No.";
        fnames[27] = "E.P Amount";
        fnames[28] = "E.P No.";
        fnames[29] = "Date of Order in E.P";
        fnames[30] = "P.V amount";
        fnames[31] = "Date of P.V";
        fnames[32] = "Income Tax Amount";
        fnames[33] = "SLP/CA No.";
        fnames[34] = "Date of Judgement in SLP/CA No.";
        fnames[35] = "Check Petition No.";
        fnames[36] = "Check Petition Amount";
        fnames[37] = "Date of Allowed";
        fnames[38] = "Date of Order";
        fnames[39]= "Miscelaneous";
        fnames[40] = "Expenses";
        fnames[41] = "Name of Operator";
        fnames[42] = "Date of Adjustment";
        return fnames;
    }
    public String getTabs(int n)
    {
        String tabs = "";
        for(int i=0;i<n;i++)
        {
            tabs += "&emsp";
        }
        return tabs;
    }
    public String getFullText()
    {
        String txt = "";
        txt = "<!DOCTYPE html>" + 
                "<html>" +
                "<head>" +
                "<title>" +
                    "Details" + 
                "</title>" +
                "<style>" +
                ".tab {" +
                    "tab-size: 2;" +
                "}" +
                "h1 {" +
                    "text-align:center;" +
                "}" +
                "body {" +
                    "background-color: #ffffba;" +
                    //"text-align:center;" +
                "}" +
                "p {" +
                    "font-size: 16px;" +
                    //"text-align:center;" +
                "}" +
                "</style>" +
                "</head>" +
                //"<h1>Client Details</h1>" +
                "<body>";
        String[] fnames = fieldNames();
        String[] dbColumns = CreateDB.dataBaseColumns();
        try {
            Connection conn = ConnectDB.Connect(dbPath);
            String query = "SELECT * FROM records WHERE s_no=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,Integer.toString(s_no));
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                for(int i=0;i<CreateDB.nColumns;i++)
                {
                    txt += "<br><p>" + getTabs(16) + "<b>" + Integer.toString(i+1) + ". "
                            + fnames[i] + getTabs(1) + " : "+ getTabs(1) + "</b>";
                    txt += rs.getString(dbColumns[i]);
                    txt += "</p><br>";
                }
                txt += "<br><p>" + getTabs(16) + "<b>" + Integer.toString(CreateDB.nColumns+1) + ". Record ID" + getTabs(1) + " : "+ getTabs(1) + "</b>";
                txt += Integer.toString(s_no);
                txt += "</p><br>";
            }
            txt += "<br><br><br><br><br><br>";
            ConnectDB.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(viewForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt += "</body>" + 
                "</html>";
        return txt;
    }
    public static void setWindow(viewForm window)
    {
        //Centralize the window
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //int w = (int) dim.getWidth();
        //int h = (int) dim.getHeight();
        //int x = (dim.width - w)/2;
        //int y = (dim.height - h)/2;
        //window.setLocation(x, y);
        
        //set window size
        window.setSize(dim.width,dim.height);
        window.setResizable(true);
    }
    public viewForm(java.awt.Frame parent,int s_no,String dbPath) {
        super(parent, true);
        this.s_no=s_no;
        this.dbPath=dbPath;
        this.parent = parent;
        initComponents();
        setWindow(this);
        this.setTitle("Details");
        editorPane.setContentType("text/html");
        String txt = getFullText();
        editorPane.setText(txt);
        editorPane.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(editorPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
