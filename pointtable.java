package cricin;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;


public class pointtable extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, brs, bpt, bpp, brec,bk,bw,bt,bl,bc;
    JComboBox<?> cb;
     JLabel lpb[][] = new JLabel[20][10];
    JRadioButton rb[]=new JRadioButton[10];
    ButtonGroup bg=new ButtonGroup();
    int i,j,k,change=0;
    String league,team;
    PreparedStatement stmt;
    //connection c;
    ResultSet rs;
    
    public pointtable()
    {
          bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50);
            j1=new JFrame("Point table page");
            j1.setLayout(null);
            j1.setSize(715, 950);
            j1.setVisible(true);
            //j1.getContentPane().setBackground(Color.WHITE);
            //j1.setContentPane(new JLabel(new ImageIcon("trophy.jpg")));
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            pointtable();
       }
    
    
     public void actionPerformed(ActionEvent e) {
         
         if (e.getSource().equals(bh))
        {
            cricinput c=new cricinput();
            j1.dispose();
        }
         /*
        if (e.getSource().equals(bsd)) {
            schedule s=new schedule();
            j1.dispose();
        }
        
        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            j1.dispose();
            
        }
        */
        if (e.getSource().equals(bpp)) {
           playerprofile p=new playerprofile();
           j1.dispose();
        }
        
        if (e.getSource().equals(brec)) {
        }
        
        if (e.getSource().equals(bk)) {
            cricinput c=new cricinput();
            j1.dispose();
        }
        
        if (e.getSource().equals(cb)) {
            league=String.valueOf(cb.getSelectedItem());
            pointtable();
        }
        
        for(i=0;i<k;i++)
        {
            if (rb[i].isSelected()) 
            {
                change=i;
                team=rb[i].getText();
            }
        }
        
        if (e.getSource().equals(bw)) {
            change("w");
        }
        
        if (e.getSource().equals(bl)) {
            change("l");
        }
        
        if (e.getSource().equals(bt)) {
            change("t");
        }
        
        if (e.getSource().equals(bc)) {
          clear();   
        }
     }
     
     
     void display() 
     {
         String s[]={"TEAMS","PLAYED","WIN","LOSE","TIE","POINTS"},l[];
         JLabel l1[]=new JLabel[20];
         bh = new JButton("HOME");
            bh.setBounds(50, 20, 100, 50);
            /*
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            brs = new JButton("TEAMS");
            brs.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 100, 50);
            */
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 600, 310, 50);
            brec = new JButton("RECORDS");
            brec.setBounds(380, 600, 310, 50);
            bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50); 
            bw = new JButton("WIN");
            bw.setBounds(20, 535, 150, 65);
            bl = new JButton("LOSE");
            bl.setBounds(190, 535, 150, 65);
            bt = new JButton("TIE");
            bt.setBounds(360, 535, 150, 65);
            bc = new JButton("CLEAR");
            bc.setBounds(530, 535, 150, 65);
        j1.add(bh);
        bh.addActionListener(this);
        /*
        j1.add(bpt);
        bpt.addActionListener(this);
        j1.add(bsd);
        bsd.addActionListener(this);
        j1.add(brs);       
        brs.addActionListener(this);
        */
        j1.add(bk);
        bk.addActionListener(this);
        j1.add(bw);
        bw.addActionListener(this);
        j1.add(bl);
        bl.addActionListener(this);
        j1.add(bt);
        bt.addActionListener(this);
        j1.add(bc);
        bc.addActionListener(this);
        
        i=0;
        int n=0;
        try {
            stmt = connection.con.prepareStatement("Select count(distinct LEAGUE) from pointtable");
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(distinct LEAGUE)");
            l=new String[n];
            l[0]="";
            stmt = connection.con.prepareStatement("Select distinct LEAGUE from pointtable");
            rs = stmt.executeQuery();
            while (rs.next()){
             l[i]=rs.getString(1);
             i++;
            }
            league=l[0];
            cb= new JComboBox<Object>(l);
             cb.setBounds(200,20,250,50);
             j1.add(cb);
            cb.addActionListener(this);
            } catch (Exception ee) {
            System.out.println(ee);
        }
        for(j=0;j<6;j++)
            {
                    l1[j] = new JLabel(s[j]);
                    l1[j].setBounds(50+(110*j),105,80,50);
                    j1.add(l1[j]);
            }
        
    }
     
     
     void pointtable() 
    {
       
        k=0;
        
        try {
            stmt =connection.con.prepareStatement("Select * from pointtable where LEAGUE=?");
            stmt.setString(1, league);
            rs = stmt.executeQuery();
            while (rs.next()){
                rb[k]= new JRadioButton();
                rb[k].setBounds(10,135+(40*k),150,60);
                j1.add(rb[k]);
                 bg.add(rb[k]);
                 //rb[k].addActionListener(this);
            for(j=0;j<5;j++)
            {
                lpb[k][j] = new JLabel();
                lpb[k][j].setBounds(160+(110*j),135+(40*k),80,60);
                j1.add(lpb[k][j]);
            }
                    rb[k].setText(rs.getString(1));
                    lpb[k][0].setText(String.valueOf(rs.getInt(2)));
                    lpb[k][1].setText(String.valueOf(rs.getInt(3)));
                    lpb[k][2].setText(String.valueOf(rs.getInt(4)));
                    lpb[k][3].setText(String.valueOf(rs.getInt(5)));
                    lpb[k][4].setText(String.valueOf(rs.getInt(6)));
                    k++;
            }
            
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }

     void change(String s)
     {
         int played=0,wins=0,lose=0,tie=0,point=0;
         try{
             stmt =connection.con.prepareStatement("Select * from pointtable where LEAGUE=? and TEAMS=?");
            stmt.setString(1, league);
            stmt.setString(2, team);
            rs = stmt.executeQuery();
            while (rs.next()){
                played=rs.getInt(2);
                wins=rs.getInt(3);
                lose=rs.getInt(4);
                tie=rs.getInt(5);
                point=rs.getInt(6);
            }}
            catch(Exception ee){System.out.println(ee);}
             
         if(s.equals("w"))
         {
             wins++;
             played++;
             point+=2;
         }
         else if(s.equals("l"))
         {
             played++;
             lose++;
         }
         else
         {
             played++;
             tie++;
             point++;
         }
         try{
             stmt =connection.con.prepareStatement("update pointtable set PLAYED=?,WINS=?,LOSE=?,TIE=?,POINTS=? where LEAGUE=? and TEAMS=?");
            stmt.setInt(1, played);
            stmt.setInt(2, wins);
            stmt.setInt(3, lose);
            stmt.setInt(4, tie);
            stmt.setInt(5, point);
            stmt.setString(6, league);
            stmt.setString(7, team);
            stmt.executeUpdate();
            }
            catch(Exception ee){System.out.println(ee);}
         lpb[change][0].setText(String.valueOf(played));
         lpb[change][1].setText(String.valueOf(wins));
         lpb[change][2].setText(String.valueOf(lose));
         lpb[change][3].setText(String.valueOf(tie));
         lpb[change][4].setText(String.valueOf(point));
     }
     
     
     
     /*
     pointtable(String t1,String s)
     {
         int played=0,wins=0,lose=0,tie=0,point=0;
         try{
             stmt =connection.con.prepareStatement("Select * from pointtable where LEAGUE=? and TEAMS=?");
            stmt.setString(1, league);
            stmt.setString(2, t1);
            rs = stmt.executeQuery();
            while (rs.next()){
                played=rs.getInt(2);
                wins=rs.getInt(3);
                lose=rs.getInt(4);
                tie=rs.getInt(5);
                point=rs.getInt(6);
            }}
            catch(Exception ee){System.out.println(ee);}
             
         if(s.equals("w"))
         {
             wins++;
             played++;
             point+=2;
         }
         else if(s.equals("l"))
         {
             played++;
             lose++;
         }
         else
         {
             played++;
             tie++;
             point++;
         }
         try{
             stmt =connection.con.prepareStatement("update pointtable set PLAYED=?,WINS=?,LOSE=?,TIE=?,POINTS=? where LEAGUE=? and TEAMS=?");
            stmt.setInt(1, played);
            stmt.setInt(2, wins);
            stmt.setInt(3, lose);
            stmt.setInt(4, tie);
            stmt.setInt(5, point);
            stmt.setString(6, league);
            stmt.setString(7, team);
            stmt.executeUpdate();
            }
            catch(Exception ee){System.out.println(ee);}
         lpb[change][0].setText(String.valueOf(played));
         lpb[change][1].setText(String.valueOf(wins));
         lpb[change][2].setText(String.valueOf(lose));
         lpb[change][3].setText(String.valueOf(tie));
         lpb[change][4].setText(String.valueOf(point));
     }
     
     */
     void clear()
     {
         try{
             stmt = connection.con.prepareStatement("update pointtable set PLAYED=?,WINS=?,LOSE=?,TIE=?,POINTS=?  where LEAGUE=? and TEAMS=?");
             stmt.setInt(1, 0);
             stmt.setInt(2, 0);
             stmt.setInt(3, 0);
             stmt.setInt(4, 0);
             stmt.setInt(5, 0);
             stmt.setString(6, league);
             stmt.setString(7, team);
             stmt.executeUpdate();
            }
            catch(Exception ee){System.out.println(ee);}
         lpb[change][0].setText("0");
         lpb[change][1].setText("0");
         lpb[change][2].setText("0");
         lpb[change][3].setText("0");
         lpb[change][4].setText("0");
     }
}
