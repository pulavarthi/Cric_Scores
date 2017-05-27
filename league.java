package cricin;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class league extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, btm, bpt, bpp, brec,bup,bbk,b[],b1[];
    JRadioButton rb[]=new JRadioButton[15],rb1[];
    JComboBox<?> cb;
    JLabel l1[];
    JTextField t;
    ButtonGroup bg=new ButtonGroup();
    int i,n,m=0;
    String league,temp;
    PreparedStatement stmt;
    //connection c;
    ResultSet rs;
    countries cn=new countries();
    
    league(String s)
    {
        league=s;
            j1=new JFrame("League page");
            j1.setLayout(null);
            j1.setSize(715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            if(s.equals("NEW LEAGUE"))
                newleague();
            else
            teams();
         }
    
    
     public void actionPerformed(ActionEvent e) {
         if (e.getSource().equals(bh))
        {
            cricinput c=new cricinput();
            j1.dispose();
        }
        if (e.getSource().equals(bsd)) {
            schedule s=new schedule();
            j1.dispose();
        }
        if (e.getSource().equals(btm)) {
            teams t=new teams();
            j1.dispose();
        }
        
        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
        }
        
        if (e.getSource().equals(bpp)) {
            playerprofile p=new playerprofile();
            j1.dispose();
        }
        
        if (e.getSource().equals(brec)) {
            league l=new league("");
            j1.dispose();
        }
        System.out.println(n);
        for(int k=0;k<n;k++)
        {
            if (e.getSource().equals(b[k])) 
            {
                if(k<m)
                alter("-",l1[k].getText());
                else
                alter("+",l1[k].getText());
            }
            if (e.getSource().equals(b1[k])) 
            {
                alter("++",l1[k].getText());
            }
        }
       
       if (e.getSource().equals(cb)) {
           league=String.valueOf(cb.getSelectedItem());
            league l=new league(league);
            j1.dispose();
        }
        
        if (e.getSource().equals(bbk)) {
            cricinput c=new cricinput();
            j1.dispose();
        }
     }
     
     
     void display() 
     {
         	bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            btm = new JButton("TEAMS");
            btm.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 150, 50);
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 600, 310, 50);
            brec = new JButton("LEAGUE");
            brec.setBounds(380, 600, 310, 50);
            bbk = new JButton("BACK");
            bbk.setBounds(10, 650, 690, 50); 
        j1.add(bh);
        bh.addActionListener(this);
        j1.add(bpt);
        bpt.addActionListener(this);
        j1.add(bsd);
        bsd.addActionListener(this);
        j1.add(btm);
        btm.addActionListener(this);
        j1.add(bpp);
        bpp.addActionListener(this);
        j1.add(brec);
        brec.addActionListener(this);
        j1.add(bbk);
        bbk.addActionListener(this);
        i=0;
        int n=0;
         String l[];
        try {
            stmt = connection.con.prepareStatement("Select count(distinct LEAGUE) from pointtable");
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(distinct LEAGUE)");
            l=new String[n+1];
            stmt = connection.con.prepareStatement("Select distinct LEAGUE from pointtable");
            rs = stmt.executeQuery();
            while (rs.next()){
             l[i]=rs.getString(1);
             i++;
            }  
            l[i]="NEW LEAGUE";
            if(league.equals("")){}
            else
                for(int j=i-1;j>=0;j--)
                    if(l[j].equals(league))
                    {
                        temp=l[0];
                        l[0]=l[j];
                        l[j]=temp;
                    }
            cb= new JComboBox<Object>(l);
             cb.setBounds(200,70,300,60);
             j1.add(cb);
            cb.addActionListener(this);
                league=l[0];
            } catch (Exception ee) {
            System.out.println(ee);
        }
    }
     
     void teams()
     {
          int k=0,inc=0,l,j;
          i=0;
          String ll[];
        try {
            stmt = connection.con.prepareStatement("Select count(*) from teams");
            rs = stmt.executeQuery();
            while(rs.next())
            {
                n=rs.getInt("count(*)");
            }
            l1=new JLabel[n];
            b1=new JButton[n];
            b=new JButton[n];
            ll=new String[n];
            stmt =connection.con.prepareStatement("Select * from pointtable where LEAGUE=?");
            stmt.setString(1, league);
            rs = stmt.executeQuery();
            while (rs.next()){
                l1[i]=new JLabel(rs.getString(1));
                l1[i].setBounds(50,120+(40*i),150,60);
                j1.add(l1[i]);
                b[i]=new JButton("-");
                b[i].setBounds(1,135+(40*i),50,30);
                j1.add(b[i]);
                b[i].addActionListener(this);
                ll[i]=rs.getString(1);
                    i++;
            }
            m=i;
            l=0;
            stmt = connection.con.prepareStatement("Select * from teams");
            rs = stmt.executeQuery();
            while (rs.next())
            {
                inc=0;
                for(j = 0; j < m; j++)
             if(ll[j].equals(rs.getString(1)))
            {
                inc++;
                break;
            }
             if(inc==0)
             {
                l1[i]=new JLabel(rs.getString(1));
                l1[i].setBounds(320+(l*220),120+(40*k),150,60);
                j1.add(l1[i]);
                b[i]=new JButton("+");
                b[i].setBounds(251+(l*220),135+(40*k),50,30);
                j1.add(b[i]);
                b[i].addActionListener(this);
                    i++;
                    k++;
                    if(k==11)
                    {
                        k=0;
                        l++;
                    }
            }}
        } catch (Exception ee) {
            System.out.println(ee);
        }
        j1.repaint();
     }
     
     
     void newleague()
     {
         int k=0,j=0;
          i=0;
          t=new JTextField("");
          t.setBounds(200,70,300,50);
          j1.add(t);
          j1.remove(cb);
        try {
            stmt = connection.con.prepareStatement("Select count(*) from teams");
            rs = stmt.executeQuery();
            while(rs.next())
            {
                n=rs.getInt("count(*)");
            }
            l1=new JLabel[n];
            b1=new JButton[n];
            b=new JButton[n];
            stmt = connection.con.prepareStatement("Select * from teams");
            rs = stmt.executeQuery();
            while (rs.next()){
                l1[i]=new JLabel(rs.getString(1));
                l1[i].setBounds(320+(j*220),120+(40*k),150,60);
                j1.add(l1[i]);
                b1[i]=new JButton("+");
                b1[i].setBounds(251+(j*220),135+(40*k),50,30);
                j1.add(b1[i]);
                b1[i].addActionListener(this);
                    i++;
                    k++;
                    if(k==11)
                    {
                        j++;
                        k=0;
                    }
            }
        } catch (Exception ee) {
            System.out.println(ee);
        }
        
        j1.repaint();
     }
     
     void alter(String s,String team)
     {
         try{
         if(s.equals("-"))
         {
             stmt=connection.con.prepareStatement("delete from pointtable where TEAMS=?");
             stmt.setString(1, team);
             stmt.executeUpdate();
             league lg=new league(league);
             j1.dispose();
         }
         else
             if(s.equals("+"))
         {
             stmt=connection.con.prepareStatement("insert into pointtable values(?,?,?,?,?,?,?)");
             stmt.setString(1, team);
             stmt.setInt(2, 0);
             stmt.setInt(3, 0);
             stmt.setInt(4, 0);
             stmt.setInt(5, 0);
             stmt.setInt(6, 0);
             stmt.setString(7, String.valueOf(cb.getSelectedItem()));
             stmt.executeUpdate();
             league lg=new league(league);
             j1.dispose();
         }
         else
             {
                 if(t.getText().equals(""))
                     JOptionPane.showMessageDialog(this,"LEAGUE NAME IS NOT MENTIONED");
                 else
                 {
                 stmt=connection.con.prepareStatement("insert into pointtable values(?,?,?,?,?,?,?)");
             stmt.setString(1, team);
             stmt.setInt(2, 0);
             stmt.setInt(3, 0);
             stmt.setInt(4, 0);
             stmt.setInt(5, 0);
             stmt.setInt(6, 0);
             league=t.getText();
             stmt.setString(7, league);
             stmt.executeUpdate();
             league lg=new league(league);
             j1.dispose();
             }}
         }
         catch(Exception ee){System.out.println(ee);}
     }
     
     
     
     }
