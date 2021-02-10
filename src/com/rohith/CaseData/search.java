/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohith.CaseData;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author rohith
 */
public class search extends JFrame {
    Connection conn = null;
    String dbPath;
    int numRows = 0;
    DefaultTableModel df;
    PreparedStatement pst1,pst2,pst3,pst4,pst5,pst;
    ResultSet rs1,rs2,rs3,rs4,rs5,rs;
    Vector s_noList = new Vector<Integer>();
    JFrame searchFrame = this;
    public static void setWindow(search window)
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
    }
    public search(String dbPath) {
        this.dbPath = dbPath;
        initComponents();
        setWindow(this);
        this.setTitle("Search");
        df = (DefaultTableModel)TableSearch.getModel();
        Initialize();
        TxtSearch.setText("");
        TxtSearch.requestFocus();
    }
    public boolean check_s_no(int x)
    {
        if(s_noList.contains(x))
            return true;
        return false;
    }
    public void Initialize()
    {
        conn = ConnectDB.Connect(dbPath);
        s_noList.clear();
        numRows = 0;
        df.setRowCount(0);
        try {
            String query1 = "SELECT s_no,name_client,laop_no,appeal_no,"
                            + "village,reach_no FROM records";
            pst = conn.prepareStatement(query1);
            rs = pst.executeQuery();
            while(rs.next())
            {
                //System.out.println("1st query success");
                if(!check_s_no(rs.getInt("s_no")))
                {
                    numRows++;
                    s_noList.add(rs.getInt("s_no"));
                    String name_client = rs.getString("name_client");
                    String village = rs.getString("village");
                    String reach_no = rs.getString("reach_no");
                    String laop_no = rs.getString("laop_no");
                    String appeal_no = rs.getString("appeal_no");
                    String view_BtnStr = "View";
                    String edit_BtnStr = "Edit";
                
                    df.addRow(new Object[]
                    {
                        numRows,
                        name_client,
                        village,
                        reach_no,
                        laop_no,
                        appeal_no,
                        view_BtnStr,
                        edit_BtnStr
                    });
                }
            }
            Action view = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf( e.getActionCommand() );
                    viewForm vf = new viewForm(searchFrame, (int)s_noList.get(modelRow),dbPath);
                    vf.setVisible(true);
                    TxtSearch.requestFocus();
                }
            };
            Action edit = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf( e.getActionCommand() );
                    df.setRowCount(0);
                    AddEdit ae = new AddEdit(searchFrame,dbPath,true,(int)s_noList.get(modelRow));
                    ae.setVisible(true);
                    TxtSearch.requestFocus();
                }
            };
 
            ButtonColumn buttonColumn2 = new ButtonColumn(TableSearch, view, 6);
            buttonColumn2.setMnemonic(KeyEvent.VK_D);
            
            ButtonColumn buttonColumn1 = new ButtonColumn(TableSearch, edit, 7);
            buttonColumn1.setMnemonic(KeyEvent.VK_D);
        } catch (SQLException ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectDB.closeConnection(conn);
    }
    public void SearchFunc()
    {
        conn = ConnectDB.Connect(dbPath);
        numRows = 0;
        s_noList.clear();
        df.setRowCount(0);
        String squery = TxtSearch.getText();
        if(squery.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Type Something to Search!");
        }
        else
        {
        try {
            String query1 = "SELECT s_no,name_client,laop_no,appeal_no,"
                            + "village,reach_no FROM records WHERE reach_no = ?";
            pst1 = conn.prepareStatement(query1);
            pst1.setString(1, squery);
            rs1 = pst1.executeQuery();
            while(rs1.next())
            {
                //System.out.println("1st query success");
                if(!check_s_no(rs1.getInt("s_no")))
                {
                    numRows++;
                    s_noList.add(rs1.getInt("s_no"));
                    String name_client = rs1.getString("name_client");
                    String village = rs1.getString("village");
                    String reach_no = rs1.getString("reach_no");
                    String laop_no = rs1.getString("laop_no");
                    String appeal_no = rs1.getString("appeal_no");
                    String view_BtnStr = "View";
                    String edit_BtnStr = "Edit";
                
                    df.addRow(new Object[]
                    {
                        numRows,
                        name_client,
                        village,
                        reach_no,
                        laop_no,
                        appeal_no,
                        view_BtnStr,
                        edit_BtnStr
                    });
                }
            }
            String query2 = "SELECT s_no,name_client,laop_no,appeal_no,"
                            + "village,reach_no FROM records WHERE laop_no = ?";
            pst2 = conn.prepareStatement(query2);
            pst2.setString(1, squery);
            rs2 = pst2.executeQuery();
            while(rs2.next())
            {
                //System.out.println("1st query success");
                if(!check_s_no(rs2.getInt("s_no")))
                {
                    numRows++;
                    s_noList.add(rs2.getInt("s_no"));
                    String name_client = rs2.getString("name_client");
                    String village = rs2.getString("village");
                    String reach_no = rs2.getString("reach_no");
                    String laop_no = rs2.getString("laop_no");
                    String appeal_no = rs2.getString("appeal_no");
                    String view_BtnStr = "View";
                    String edit_BtnStr = "Edit";
                
                    df.addRow(new Object[]
                    {
                        numRows,
                        name_client,
                        village,
                        reach_no,
                        laop_no,
                        appeal_no,
                        view_BtnStr,
                        edit_BtnStr
                    });
                }
            }
            String query3 = "SELECT s_no,name_client,laop_no,appeal_no,"
                            + "village,reach_no FROM records WHERE appeal_no = ?";
            pst3 = conn.prepareStatement(query3);
            pst3.setString(1, squery);
            rs3 = pst3.executeQuery();
            while(rs3.next())
            {
                //System.out.println("1st query success");
                if(!check_s_no(rs3.getInt("s_no")))
                {
                    numRows++;
                    s_noList.add(rs3.getInt("s_no"));
                    String name_client = rs3.getString("name_client");
                    String village = rs3.getString("village");
                    String reach_no = rs3.getString("reach_no");
                    String laop_no = rs3.getString("laop_no");
                    String appeal_no = rs3.getString("appeal_no");
                    String view_BtnStr = "View";
                    String edit_BtnStr = "Edit";
                
                    df.addRow(new Object[]
                    {
                        numRows,
                        name_client,
                        village,
                        reach_no,
                        laop_no,
                        appeal_no,
                        view_BtnStr,
                        edit_BtnStr
                    });
                }
            }
            squery = squery + "%";
            String query4 = "SELECT s_no,name_client,laop_no,appeal_no,"
                            + "village,reach_no FROM records WHERE village LIKE ?";
            pst4 = conn.prepareStatement(query4);
            pst4.setString(1, squery);
            rs4 = pst4.executeQuery();
            while(rs4.next())
            {
                if(!check_s_no(rs4.getInt("s_no")))
                {
                    numRows++;
                    s_noList.add(rs4.getInt("s_no"));
                    String name_client = rs4.getString("name_client");
                    String village = rs4.getString("village");
                    String reach_no = rs4.getString("reach_no");
                    String laop_no = rs4.getString("laop_no");
                    String appeal_no = rs4.getString("appeal_no");
                    String view_BtnStr = "View";
                    String edit_BtnStr = "Edit";
                
                    df.addRow(new Object[]
                    {
                        numRows,
                        name_client,
                        village,
                        reach_no,
                        laop_no,
                        appeal_no,
                        view_BtnStr,
                        edit_BtnStr
                    });
                }
            }
            squery = "%" + squery;
            String query5 = "SELECT s_no,name_client,laop_no,appeal_no,"
                            + "village,reach_no FROM records WHERE name_client LIKE ?";
            pst5 = conn.prepareStatement(query5);
            pst5.setString(1, squery);
            rs5 = pst5.executeQuery();
            while(rs5.next())
            {
                if(!check_s_no(rs5.getInt("s_no")))
                {
                    numRows++;
                    s_noList.add(rs5.getInt("s_no"));
                    String name_client = rs5.getString("name_client");
                    String village = rs5.getString("village");
                    String reach_no = rs5.getString("reach_no");
                    String laop_no = rs5.getString("laop_no");
                    String appeal_no = rs5.getString("appeal_no");
                    String view_BtnStr = "View";
                    String edit_BtnStr = "Edit";
                
                    df.addRow(new Object[]
                    {
                        numRows,
                        name_client,
                        village,
                        reach_no,
                        laop_no,
                        appeal_no,
                        view_BtnStr,
                        edit_BtnStr
                    });
                }
            }
            Action view = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf( e.getActionCommand() );
                    viewForm vf = new viewForm(searchFrame, (int)s_noList.get(modelRow),dbPath);
                    vf.setVisible(true);
                    TxtSearch.requestFocus();
                }
            };
            Action edit = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf( e.getActionCommand() );
                    df.setRowCount(0);
                    AddEdit ae = new AddEdit(searchFrame,dbPath,true,(int)s_noList.get(modelRow));
                    ae.setVisible(true);
                    TxtSearch.requestFocus();
                }
            };
 
            ButtonColumn buttonColumn2 = new ButtonColumn(TableSearch, view, 6);
            buttonColumn2.setMnemonic(KeyEvent.VK_D);
            
            ButtonColumn buttonColumn1 = new ButtonColumn(TableSearch, edit, 7);
            buttonColumn1.setMnemonic(KeyEvent.VK_D);
            if(numRows==0)
            {
                JOptionPane.showMessageDialog(this, "No Search Results found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        ConnectDB.closeConnection(conn);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        TxtSearch = new javax.swing.JTextField();
        BtnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableSearch = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        MenuAdd = new javax.swing.JMenuItem();
        MenuDelete = new javax.swing.JMenuItem();
        MenuExit = new javax.swing.JMenuItem();
        MenuHelp = new javax.swing.JMenu();
        MenuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.SystemColor.info);
        jPanel1.setForeground(javax.swing.UIManager.getDefaults().getColor("FormattedTextField.selectionBackground"));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Data Management");

        jButton1.setBackground(java.awt.Color.magenta);
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton1.setForeground(java.awt.Color.black);
        jButton1.setText("All Records");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TxtSearch.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        TxtSearch.setText("kola");
        TxtSearch.setAlignmentX(10.0F);
        TxtSearch.setAlignmentY(10.0F);
        TxtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSearchActionPerformed(evt);
            }
        });
        TxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtSearchKeyPressed(evt);
            }
        });

        BtnSearch.setBackground(java.awt.Color.cyan);
        BtnSearch.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        BtnSearch.setForeground(java.awt.Color.blue);
        BtnSearch.setText("Search");
        BtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSearchActionPerformed(evt);
            }
        });

        TableSearch.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        TableSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No", "Name", "Village", "Reach No.", "LAOP No.", "Appeal No.", "View", "Edit"
            }
        ));
        TableSearch.setRowHeight(24);
        jScrollPane2.setViewportView(TableSearch);

        jMenuBar1.setBackground(java.awt.Color.orange);
        jMenuBar1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        MenuFile.setText("File");
        MenuFile.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        MenuFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuFileMouseClicked(evt);
            }
        });
        MenuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuFileActionPerformed(evt);
            }
        });

        MenuAdd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        MenuAdd.setText("Add");
        MenuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAddActionPerformed(evt);
            }
        });
        MenuFile.add(MenuAdd);

        MenuDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        MenuDelete.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        MenuDelete.setText("Delete");
        MenuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuDeleteActionPerformed(evt);
            }
        });
        MenuFile.add(MenuDelete);

        MenuExit.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        MenuExit.setText("Exit");
        MenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuExitActionPerformed(evt);
            }
        });
        MenuFile.add(MenuExit);

        jMenuBar1.add(MenuFile);

        MenuHelp.setText("Help");
        MenuHelp.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        MenuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuHelpActionPerformed(evt);
            }
        });

        MenuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        MenuAbout.setText("About");
        MenuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAboutActionPerformed(evt);
            }
        });
        MenuHelp.add(MenuAbout);

        jMenuBar1.add(MenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(TxtSearch)
                .addGap(18, 18, 18)
                .addComponent(BtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TxtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSearchActionPerformed
        // TODO add your handling code here:
        SearchFunc();
        TxtSearch.requestFocus();
    }//GEN-LAST:event_BtnSearchActionPerformed

    private void TxtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSearchActionPerformed

    private void MenuHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuHelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuHelpActionPerformed

    private void MenuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuFileActionPerformed

    private void MenuFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuFileMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuFileMouseClicked

    private void MenuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAddActionPerformed
        // TODO add your handling code here:
        AddEdit ac = new AddEdit(this,dbPath,false,0);
        ac.setVisible(true);
        df.setRowCount(0);
        TxtSearch.requestFocus();
    }//GEN-LAST:event_MenuAddActionPerformed

    private void MenuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuDeleteActionPerformed
        // TODO add your handling code here:
        df.setRowCount(0);
        DeleteRecord dr = new DeleteRecord(this,dbPath);
        dr.setVisible(true);
        TxtSearch.requestFocus();
    }//GEN-LAST:event_MenuDeleteActionPerformed

    private void MenuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAboutActionPerformed
        // TODO add your handling code here:
        About ab = new About(this);
        ab.setVisible(true);
        TxtSearch.requestFocus();
    }//GEN-LAST:event_MenuAboutActionPerformed

    private void MenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_MenuExitActionPerformed

    private void TxtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            SearchFunc();
            TxtSearch.requestFocus();
        }
    }//GEN-LAST:event_TxtSearchKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Initialize();
        TxtSearch.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSearch;
    private javax.swing.JMenuItem MenuAbout;
    private javax.swing.JMenuItem MenuAdd;
    private javax.swing.JMenuItem MenuDelete;
    private javax.swing.JMenuItem MenuExit;
    private javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuHelp;
    private javax.swing.JTable TableSearch;
    private javax.swing.JTextField TxtSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
