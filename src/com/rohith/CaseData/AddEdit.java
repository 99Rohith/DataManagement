/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohith.CaseData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rohith
 */
public class AddEdit extends javax.swing.JDialog {

    /**
     * Creates new form AddEdit
     */
    String dbPath;
    Vector TxtFields = new Vector<String>();
    boolean edit;
    int s_no;
    PreparedStatement ps1,ps2,ps3;
    ResultSet rs1;
    Connection conn;
    public static void setWindow(AddEdit window)
    {
        //Centralize the window
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int w=window.getSize().width;
        int h=window.getSize().height;
        int x=(dim.width - w)/2;
        int y=(dim.height -h)/2;
        window.setLocation(x,y);
        
        //set window size
        window.setSize(dim.width/2,dim.height);
    }
    public void Initialize()
    {
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field5.setText("");
        field6.setText("");
        field7.setText("");
        field8.setText("");
        field9.setText("");
        field10.setCalendar(null);
        field11.setText("");
        field12.setCalendar(null);
        field13.setCalendar(null);
        field14.setText("");
        field15.setCalendar(null);
        field16.setText("");
        field17.setText("");
        field18.setCalendar(null);
        field19.setCalendar(null);
        field20.setText("");
        field21.setText("");
        field22.setCalendar(null);
        field23.setCalendar(null);
        field24.setText("");
        field25.setCalendar(null);
        field26.setCalendar(null);
        field27.setText("");
        field28.setValue(0);
        field29.setText("");
        field30.setCalendar(null);
        field31.setValue(0);
        field32.setCalendar(null);
        field33.setValue(0);
        field34.setText("");
        field35.setCalendar(null);
        field36.setText("");
        field37.setValue(0);
        field38.setCalendar(null);
        field39.setCalendar(null);
        field40.setText("");
        field41.setValue(0);
        field42.setText("");
    }
    public void InitializeEdit()
    {
        conn = ConnectDB.Connect(dbPath);
        String query = "SELECT * FROM records WHERE s_no = ?";
        try {
            ps1 = conn.prepareStatement(query);
            ps1.setString(1, Integer.toString(s_no));
            rs1 = ps1.executeQuery();
            String[] dbColumns = CreateDB.dataBaseColumns();
            if(rs1.next())
            {
                field1.setText(rs1.getString(dbColumns[0]));
                field2.setText(rs1.getString(dbColumns[1]));
                field3.setText(rs1.getString(dbColumns[2]));
                field4.setText(rs1.getString(dbColumns[3]));
                field5.setText(rs1.getString(dbColumns[4]));
                field6.setText(rs1.getString(dbColumns[5]));
                field7.setText(rs1.getString(dbColumns[6]));
                field8.setText(rs1.getString(dbColumns[7]));
                field9.setText(rs1.getString(dbColumns[8]));
                field10.setDate(StoD(rs1.getString(dbColumns[9])));
                field11.setText(rs1.getString(dbColumns[10]));
                field12.setDate(StoD(rs1.getString(dbColumns[11])));
                field13.setDate(StoD(rs1.getString(dbColumns[12])));
                field14.setText(rs1.getString(dbColumns[13]));
                field15.setDate(StoD(rs1.getString(dbColumns[14])));
                field16.setText(rs1.getString(dbColumns[15]));
                field17.setText(rs1.getString(dbColumns[16]));
                field18.setDate(StoD(rs1.getString(dbColumns[17])));
                field19.setDate(StoD(rs1.getString(dbColumns[18])));
                field20.setText(rs1.getString(dbColumns[19]));
                field21.setText(rs1.getString(dbColumns[20]));
                field22.setDate(StoD(rs1.getString(dbColumns[21])));
                field23.setDate(StoD(rs1.getString(dbColumns[22])));
                field24.setText(rs1.getString(dbColumns[23]));
                field25.setDate(StoD(rs1.getString(dbColumns[24])));
                field26.setDate(StoD(rs1.getString(dbColumns[25])));
                field27.setText(rs1.getString(dbColumns[26]));
                field28.setValue(Float.parseFloat(rs1.getString(dbColumns[27])));
                field29.setText(rs1.getString(dbColumns[28]));
                field30.setDate(StoD(rs1.getString(dbColumns[29])));
                field31.setValue(Float.parseFloat(rs1.getString(dbColumns[30])));
                field32.setDate(StoD(rs1.getString(dbColumns[31])));
                field33.setValue(Float.parseFloat(rs1.getString(dbColumns[32])));
                field34.setText(rs1.getString(dbColumns[33]));
                field35.setDate(StoD(rs1.getString(dbColumns[34])));
                field36.setText(rs1.getString(dbColumns[35]));
                field37.setValue(Float.parseFloat(rs1.getString(dbColumns[36])));
                field38.setDate(StoD(rs1.getString(dbColumns[37])));
                field39.setDate(StoD(rs1.getString(dbColumns[38])));
                field40.setText(rs1.getString(dbColumns[39]));
                field41.setValue(Float.parseFloat(rs1.getString(dbColumns[40])));
                field42.setText(rs1.getString(dbColumns[41]));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectDB.closeConnection(conn);
    }
    public AddEdit(java.awt.Frame parent,String dbPath,boolean edit,int s_no) {
        super(parent, true);
        initComponents();
        setWindow(this);
        this.dbPath = dbPath;
        this.edit = edit;
        this.s_no = s_no;
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        if(!edit)
        {
            this.setTitle("Add Record");
            Initialize();
        }
        else
        {
            this.setTitle("Edit Record");
            InitializeEdit();
        }
    }
    public Date StoD(String sDate)//Convert String to date
    {
        if(sDate.isEmpty())
        {
            return null;
        }
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
        } catch (ParseException ex) {
            Logger.getLogger(AddEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String FormatDate(Date dateFromDateChooser)
    {
        if(dateFromDateChooser == null)
        {
            return "";
        }
        else
        {
            return String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);
        }
    }
    public String FormatNum(javax.swing.JSpinner jsp)
    {
        try {
            jsp.commitEdit();
        } catch (ParseException ex) {
            return Integer.toString(0);
            //Logger.getLogger(AddEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.toString((Integer) jsp.getValue());
    }
    public boolean preProcessing()
    {
        TxtFields.clear();
        if(field1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Name of the Client can't be empty");
            return false;
        }
        else
        {
            TxtFields.add(field1.getText());
        }
        TxtFields.add(field2.getText());
        TxtFields.add(field3.getText());
        TxtFields.add(field4.getText());
        TxtFields.add(field5.getText());
        TxtFields.add(field6.getText());
        TxtFields.add(field7.getText());
        TxtFields.add(field8.getText());
        TxtFields.add(field9.getText());
        TxtFields.add(FormatDate(field10.getDate()));
        TxtFields.add(field11.getText());
        TxtFields.add(FormatDate(field12.getDate()));
        TxtFields.add(FormatDate(field13.getDate()));
        TxtFields.add(field14.getText());
        TxtFields.add(FormatDate(field15.getDate()));
        TxtFields.add(field16.getText());
        TxtFields.add(field17.getText());
        TxtFields.add(FormatDate(field18.getDate()));
        TxtFields.add(FormatDate(field19.getDate()));
        TxtFields.add(field20.getText());
        TxtFields.add(field21.getText());
        TxtFields.add(FormatDate(field22.getDate()));
        TxtFields.add(FormatDate(field23.getDate()));
        TxtFields.add(field24.getText());
        TxtFields.add(FormatDate(field25.getDate()));
        TxtFields.add(FormatDate(field26.getDate()));
        TxtFields.add(field27.getText());
        TxtFields.add(FormatNum(field28));
        TxtFields.add(field29.getText());
        TxtFields.add(FormatDate(field30.getDate()));
        TxtFields.add(FormatNum(field31));
        TxtFields.add(FormatDate(field32.getDate()));
        TxtFields.add(FormatNum(field33));
        TxtFields.add(field34.getText());
        TxtFields.add(FormatDate(field35.getDate()));
        TxtFields.add(field36.getText());
        TxtFields.add(FormatNum(field37));
        TxtFields.add(FormatDate(field38.getDate()));
        TxtFields.add(FormatDate(field39.getDate()));
        TxtFields.add(field40.getText());
        TxtFields.add(FormatNum(field41));
        if(field42.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Name of the Operator can't be empty");
            return false;
        }
        else
        {
            TxtFields.add(field42.getText());
        }
        TxtFields.add(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        field1 = new javax.swing.JTextField();
        field2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        field4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        field6 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        field7 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        field8 = new javax.swing.JTextField();
        field9 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        field11 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        field14 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        field16 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        field17 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        field24 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        field21 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        field20 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        field3 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        field5 = new javax.swing.JTextArea();
        field10 = new com.toedter.calendar.JDateChooser();
        field12 = new com.toedter.calendar.JDateChooser();
        field13 = new com.toedter.calendar.JDateChooser();
        field15 = new com.toedter.calendar.JDateChooser();
        field18 = new com.toedter.calendar.JDateChooser();
        field22 = new com.toedter.calendar.JDateChooser();
        field23 = new com.toedter.calendar.JDateChooser();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        field27 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        field29 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        field34 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        field36 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        field42 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        field26 = new com.toedter.calendar.JDateChooser();
        field25 = new com.toedter.calendar.JDateChooser();
        field28 = new javax.swing.JSpinner();
        field30 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        field19 = new com.toedter.calendar.JDateChooser();
        field31 = new javax.swing.JSpinner();
        field32 = new com.toedter.calendar.JDateChooser();
        field33 = new javax.swing.JSpinner();
        field35 = new com.toedter.calendar.JDateChooser();
        field37 = new javax.swing.JSpinner();
        field38 = new com.toedter.calendar.JDateChooser();
        field39 = new com.toedter.calendar.JDateChooser();
        field41 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        field40 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        MenuSave = new javax.swing.JMenuItem();
        MenuRst = new javax.swing.JMenuItem();
        MenuCancel = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setAutoscrolls(true);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel8.setText("1.  Name of the Client");

        field1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field1.setText("jTextField3");
        field1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field1ActionPerformed(evt);
            }
        });
        field1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field1KeyPressed(evt);
            }
        });

        field2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field2.setText("jTextField3");
        field2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field2ActionPerformed(evt);
            }
        });
        field2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field2KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setText("2.  Cell No.");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel10.setText("3.  Address");

        field4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field4.setText("jTextField3");
        field4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field4ActionPerformed(evt);
            }
        });
        field4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field4KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel13.setText("4.  Aadhar No.");

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel14.setText("5.  LR's");

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel18.setText("6.  LAOP No.");

        field6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field6.setText("jTextField3");
        field6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field6ActionPerformed(evt);
            }
        });
        field6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field6KeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel19.setText("7.  Client No. in LAOP");

        field7.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field7.setText("jTextField3");
        field7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field7ActionPerformed(evt);
            }
        });
        field7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field7KeyPressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel20.setText("8.  Name of Village");

        field8.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field8.setText("jTextField3");
        field8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field8ActionPerformed(evt);
            }
        });
        field8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field8KeyPressed(evt);
            }
        });

        field9.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field9.setText("jTextField3");
        field9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field9ActionPerformed(evt);
            }
        });
        field9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field9KeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel21.setText("9.  Reach No.");

        jLabel22.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel22.setText("10. Date of D.N");

        field11.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field11.setText("jTextField3");
        field11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field11ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel23.setText("11. Award No.");

        jLabel24.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel24.setText("12. Date of Award");

        jLabel25.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel25.setText("13. Date of filing");

        field14.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field14.setText("jTextField3");
        field14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field14ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel26.setText("14. Name of the court");

        jLabel27.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel27.setText("15. Date of Judgement in Lower Court");

        field16.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field16.setText("jTextField3");
        field16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field16ActionPerformed(evt);
            }
        });
        field16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field16KeyPressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel28.setText("16. Section");

        jLabel29.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel29.setText("17. Appeal No.");

        field17.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field17.setText("jTextField3");
        field17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field17ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel30.setText("18. Date of filing");

        jLabel31.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel31.setText("24. C.C No.");

        field24.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field24.setText("jTextField3");
        field24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field24ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel32.setText("23. Date of Judgement in W.P");

        jLabel33.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel33.setText("22. Date of filing");

        jLabel34.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel34.setText("21. W.P No.");

        field21.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field21.setText("jTextField3");
        field21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field21ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel35.setText("20. Appealeant No.");

        field20.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field20.setText("jTextField3");
        field20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field20ActionPerformed(evt);
            }
        });
        field20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field20KeyPressed(evt);
            }
        });

        field3.setColumns(20);
        field3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field3.setRows(5);
        jScrollPane2.setViewportView(field3);

        field5.setColumns(20);
        field5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field5.setRows(5);
        jScrollPane3.setViewportView(field5);

        field10.setBackground(java.awt.Color.white);
        field10.setForeground(java.awt.Color.black);
        field10.setDateFormatString("dd-MM-yyyy");
        field10.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field10KeyPressed(evt);
            }
        });

        field12.setBackground(java.awt.Color.white);
        field12.setForeground(java.awt.Color.black);
        field12.setDateFormatString("dd-MM-yyyy");
        field12.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field13.setBackground(java.awt.Color.white);
        field13.setForeground(java.awt.Color.black);
        field13.setDateFormatString("dd-MM-yyyy");
        field13.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field15.setBackground(java.awt.Color.white);
        field15.setForeground(java.awt.Color.black);
        field15.setDateFormatString("dd-MM-yyyy");
        field15.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field18.setBackground(java.awt.Color.white);
        field18.setForeground(java.awt.Color.black);
        field18.setDateFormatString("dd-MM-yyyy");
        field18.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field22.setBackground(java.awt.Color.white);
        field22.setForeground(java.awt.Color.black);
        field22.setDateFormatString("dd-MM-yyyy");
        field22.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field23.setBackground(java.awt.Color.white);
        field23.setForeground(java.awt.Color.black);
        field23.setDateFormatString("dd-MM-yyyy");
        field23.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel36.setText("25. Date of filing");

        jLabel37.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel37.setText("26. Date of Judgement in C.C");

        field27.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field27.setText("jTextField3");
        field27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field27ActionPerformed(evt);
            }
        });
        field27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field27KeyPressed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel38.setText("27. D.Hr No.");

        jLabel39.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel39.setText("28. E.P Amount");

        jLabel41.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel41.setText("29. E.P No.");

        field29.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field29.setText("jTextField3");
        field29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field29ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel42.setText("30. Date of Order in E.P");

        jLabel43.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel43.setText("31. P.V amount");

        jLabel44.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel44.setText("32. Date of P.V");

        jLabel45.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel45.setText("33. Income Tax Amount");

        jLabel46.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel46.setText("34. SLP/CA No.");

        field34.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field34.setText("jTextField3");
        field34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field34ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel47.setText("35. Date of Judgement in SLP/CA No.");

        jLabel48.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel48.setText("36. Check Petition No.");

        field36.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field36.setText("jTextField3");
        field36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field36ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel49.setText("37. Check Petition Amount");

        jLabel50.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel50.setText("38. Date of Allowed");

        jLabel51.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel51.setText("39. Date of Order");

        jLabel52.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel52.setText("41. Expenses");

        field42.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field42.setText("jTextField3");
        field42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field42ActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel53.setText("42. Name of Operator");

        field26.setBackground(java.awt.Color.white);
        field26.setForeground(java.awt.Color.black);
        field26.setDateFormatString("dd-MM-yyyy");
        field26.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field25.setBackground(java.awt.Color.white);
        field25.setForeground(java.awt.Color.black);
        field25.setDateFormatString("dd-MM-yyyy");
        field25.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field30.setBackground(java.awt.Color.white);
        field30.setForeground(java.awt.Color.black);
        field30.setDateFormatString("dd-MM-yyyy");
        field30.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("19. Date of Judgement in Appeal No.");

        field19.setBackground(java.awt.Color.white);
        field19.setForeground(java.awt.Color.black);
        field19.setDateFormatString("dd-MM-yyyy");
        field19.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field32.setBackground(java.awt.Color.white);
        field32.setForeground(java.awt.Color.black);
        field32.setDateFormatString("dd-MM-yyyy");
        field32.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field35.setBackground(java.awt.Color.white);
        field35.setForeground(java.awt.Color.black);
        field35.setDateFormatString("dd-MM-yyyy");
        field35.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field37.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field38.setBackground(java.awt.Color.white);
        field38.setForeground(java.awt.Color.black);
        field38.setDateFormatString("dd-MM-yyyy");
        field38.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field39.setBackground(java.awt.Color.white);
        field39.setForeground(java.awt.Color.black);
        field39.setDateFormatString("dd-MM-yyyy");
        field39.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        field41.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("40. Miscelaneous");

        field40.setColumns(20);
        field40.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        field40.setRows(5);
        jScrollPane1.setViewportView(field40);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field30, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field29, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field28, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field27, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field24, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field25, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field26, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(18, 18, 18)
                                .addComponent(field33, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel47)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(field35, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addGap(12, 12, 12)
                                    .addComponent(field23, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field36, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(18, 18, 18)
                                .addComponent(field31, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(field32, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field34, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field42, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field41, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field38, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field37, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(158, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(18, 18, 18)
                                .addComponent(field39, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(150, 150, 150)
                                        .addComponent(field15, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(field19, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field14, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel27)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field20, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field21, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field22, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field16, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field17, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field18, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(field11, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(field13, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(field12, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(field9, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field10, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel14)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(field9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(field10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(field11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(field12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(field13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(field20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(field21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel33)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addComponent(field23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(field26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(field27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(field28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(field29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(field31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(field32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(field33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(field34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47)
                    .addComponent(field35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(field37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(field42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );

        scrollPane.setViewportView(jPanel1);

        jMenuBar1.setBackground(java.awt.Color.orange);
        jMenuBar1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        jMenu2.setText("File");
        jMenu2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        MenuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuSave.setText("Save");
        MenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSaveActionPerformed(evt);
            }
        });
        jMenu2.add(MenuSave);

        MenuRst.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        MenuRst.setText("Reset");
        MenuRst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRstActionPerformed(evt);
            }
        });
        jMenu2.add(MenuRst);

        MenuCancel.setText("Exit");
        MenuCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCancelActionPerformed(evt);
            }
        });
        jMenu2.add(MenuCancel);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Help");
        jMenu1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void field1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field1ActionPerformed

    private void field2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field2ActionPerformed

    private void field4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field4ActionPerformed

    private void field6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field6ActionPerformed

    private void field7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field7ActionPerformed

    private void field8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field8ActionPerformed

    private void field9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field9ActionPerformed

    private void field11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field11ActionPerformed

    private void field14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field14ActionPerformed

    private void field16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field16ActionPerformed

    private void field17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field17ActionPerformed

    private void field24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field24ActionPerformed

    private void field21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field21ActionPerformed

    private void field20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field20ActionPerformed

    private void field27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field27ActionPerformed

    private void field29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field29ActionPerformed

    private void field34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field34ActionPerformed

    private void field36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field36ActionPerformed

    private void field42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field42ActionPerformed

    private void field1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field2.requestFocus();
        }
    }//GEN-LAST:event_field1KeyPressed

    private void field2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field3.requestFocus();
        }
    }//GEN-LAST:event_field2KeyPressed

    private void field4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field5.requestFocus();
        }
    }//GEN-LAST:event_field4KeyPressed

    private void field6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field6KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field7.requestFocus();
        }
    }//GEN-LAST:event_field6KeyPressed

    private void field7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field7KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field8.requestFocus();
        }
    }//GEN-LAST:event_field7KeyPressed

    private void field8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field8KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field9.requestFocus();
        }
    }//GEN-LAST:event_field8KeyPressed

    private void field9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field9KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field10.requestFocus();
        }
    }//GEN-LAST:event_field9KeyPressed

    private void field10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_field10KeyPressed

    private void field16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field16KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field17.requestFocus();
        }
    }//GEN-LAST:event_field16KeyPressed

    private void field20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field20KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            field21.requestFocus();
        }
    }//GEN-LAST:event_field20KeyPressed

    private void field27KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field27KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_field27KeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        About ab = new About(this);
        ab.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MenuRstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRstActionPerformed
        // TODO add your handling code here:
        Initialize();
    }//GEN-LAST:event_MenuRstActionPerformed

    private void MenuCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_MenuCancelActionPerformed

    private void MenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSaveActionPerformed
        // TODO add your handling code here:
        Connection conn = ConnectDB.Connect(dbPath);
        boolean flag = preProcessing();
        if(flag && (!edit))
        {
            try {
                String query = CreateDB.getInsertQuery();
                ps2 = conn.prepareStatement(query);
                for(int i=0;i<CreateDB.nColumns;i++)
                {
                    ps2.setString(i+1, (String) TxtFields.get(i));
                }
                ps2.execute();
                JOptionPane.showMessageDialog(this, "Record Added Successfully");
            } 
            catch (SQLException ex) {
                Logger.getLogger(AddEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
            Initialize();
        }
        else if(flag && (edit))
        {
            try {
                String query = CreateDB.getUpdateQuery();
                ps3 = conn.prepareStatement(query);
                for(int i=0;i<CreateDB.nColumns;i++)
                {
                    ps3.setString(i+1, (String) TxtFields.get(i));
                }
                ps3.setString(CreateDB.nColumns+1,Integer.toString(s_no));
                ps3.execute();
                JOptionPane.showMessageDialog(this, "Record Updated Successfully");
            } 
            catch (SQLException ex) {
                Logger.getLogger(AddEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
        ConnectDB.closeConnection(conn);
    }//GEN-LAST:event_MenuSaveActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuCancel;
    private javax.swing.JMenuItem MenuRst;
    private javax.swing.JMenuItem MenuSave;
    private javax.swing.JTextField field1;
    private com.toedter.calendar.JDateChooser field10;
    private javax.swing.JTextField field11;
    private com.toedter.calendar.JDateChooser field12;
    private com.toedter.calendar.JDateChooser field13;
    private javax.swing.JTextField field14;
    private com.toedter.calendar.JDateChooser field15;
    private javax.swing.JTextField field16;
    private javax.swing.JTextField field17;
    private com.toedter.calendar.JDateChooser field18;
    private com.toedter.calendar.JDateChooser field19;
    private javax.swing.JTextField field2;
    private javax.swing.JTextField field20;
    private javax.swing.JTextField field21;
    private com.toedter.calendar.JDateChooser field22;
    private com.toedter.calendar.JDateChooser field23;
    private javax.swing.JTextField field24;
    private com.toedter.calendar.JDateChooser field25;
    private com.toedter.calendar.JDateChooser field26;
    private javax.swing.JTextField field27;
    private javax.swing.JSpinner field28;
    private javax.swing.JTextField field29;
    private javax.swing.JTextArea field3;
    private com.toedter.calendar.JDateChooser field30;
    private javax.swing.JSpinner field31;
    private com.toedter.calendar.JDateChooser field32;
    private javax.swing.JSpinner field33;
    private javax.swing.JTextField field34;
    private com.toedter.calendar.JDateChooser field35;
    private javax.swing.JTextField field36;
    private javax.swing.JSpinner field37;
    private com.toedter.calendar.JDateChooser field38;
    private com.toedter.calendar.JDateChooser field39;
    private javax.swing.JTextField field4;
    private javax.swing.JTextArea field40;
    private javax.swing.JSpinner field41;
    private javax.swing.JTextField field42;
    private javax.swing.JTextArea field5;
    private javax.swing.JTextField field6;
    private javax.swing.JTextField field7;
    private javax.swing.JTextField field8;
    private javax.swing.JTextField field9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
