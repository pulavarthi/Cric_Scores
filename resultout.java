package cricapp;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import cricin.*;

public class resultout extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bk;
    int i,j,k=1;
    PreparedStatement stmt;
    //connection c;
    ResultSet rs;
    
    resultout()
    {
         	bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50);
            j1=new JFrame("Result page");
            j1.setLayout(null);
            j1.setSize(715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            result();
        }
    
    
     public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(bk))
         {
             Cricapp c=new Cricapp();
             j1.dispose();
         }
     }
    
    void result() {
        j1.add(bk);
        bk.addActionListener(this);
        JButton l[][] = new JButton[4][2];
        String[] x = new String[20];
        int n=15, m = 10,p=0;
                  try {
                    stmt=connection.con.prepareStatement("select count(*) from schedule ");
            rs=stmt.executeQuery();
            while(rs.next())
            p=rs.getInt("count(*)");
        for (i = 0; i < 4 && k<=(p+1); i++) 
        {
            n=15;
            for(j=0;j<1 && k<=(p+1);j++,k++)
        {
                    stmt = connection.con.prepareStatement("Select * from result where SR=?");
                    stmt.setInt(1, k);
                    l[i][j] = new JButton("NO SCHEDULE");
                    l[i][j].setBounds(n, m, 330, 150);
                    j1.add(l[i][j]);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        x[2] = rs.getString(4) + '\t' + "vs" + '\t' + rs.getString(5);
                        x[1] = "<html>" + rs.getString(2) + "<br>" + x[2] + "<html>";
                        x[3] = "<html>" + x[1] + "<br>" + rs.getString(10) + "<html>";
                        x[4] = rs.getString(6) + '\t' + rs.getString(7) + '\t' + rs.getString(8);
                        x[5] = "<html>" + x[4] + "<br>" + rs.getString(9) + "<html>";
                        x[6] = "<html>" + x[3] + "<br>" + x[5] + "<html>";
                    }
                    l[i][j].setText(x[6]);
               n+=345;
                } 
                }
            m+=165;
        }
            catch (Exception ee) {
                    System.out.println(ee);
        }
    }}
    

