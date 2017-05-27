package cricin;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import cricin.connection;

public class schedule extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, btm, bpt, bpp,bup;
    JComboBox<String> cb1,cb2,cb3,cb4,cb5;
    JTextField t2[]=new JTextField[3];
    int i,j;
    String type[]={"ODI","TEST","T20"},status[]={"DUE","LIVE","PLAYED"};
    PreparedStatement stmt;
    //connection c;
    ResultSet rs;
    countries cn=new countries();
    
    schedule()
    {
            j1=new JFrame(" Schedule page");
            j1.setLayout(null);
            j1.setSize(730, 700);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            j1.setContentPane(new JLabel(new ImageIcon("bg3other.jpg")));
            display();
            scheduler();
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
        
        
        if (e.getSource().equals(bup)) {
            addschedule();
        }
     }
     
     
     void display() 
     {
    	 	bh = new JButton("CRIC INPUT");
    	 	bh.setBounds(50, 20, 100, 50);
    	 	bsd = new JButton("SCHEDULE");
    	 	bsd.setBounds(200, 20, 100, 50);
            btm = new JButton("TEAMS");
            btm.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(500, 20, 150, 50);
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 500, 210, 50);
            /*
            brec = new JButton("LEAGUE");
            brec.setBounds(380, 500, 210, 50);
            */
            bup = new JButton("ADD SCHEDULE");
            bup.setBounds(10, 580, 690, 50); 
        
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
        
        j1.add(bup);
        bup.addActionListener(this);
    }
     

void scheduler() {
    try {
        int n=0;
            stmt = connection.con.prepareStatement("Select count(distinct LEAGUE) from pointtable");
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(distinct LEAGUE)");
            String l[]=new String[n];
            l[0]="SELECT LEAGUE";
            stmt = connection.con.prepareStatement("Select distinct LEAGUE from pointtable");
            rs = stmt.executeQuery();
            while (rs.next()){
             l[i]=rs.getString(1);
             i++;
            }
            cb5= new JComboBox<String>(l);
             cb5.setBounds(350,150,250,48);
             j1.add(cb5);
            } catch (Exception ee) {
            System.out.println(ee);
            System.out.println("Combo box 5 not displayed");
        }
    String s[]={"status","league","teamA","teamB","date","time","venue","type"};
        cb1=new JComboBox<String>(status);
        cb1.setBounds(350,90,250,48);
        j1.add(cb1);
        cb2=new JComboBox<String>(cn.teamsare);
        cb2.setBounds(350,210,250,48);
        j1.add(cb2);
        cb3=new JComboBox<String>(cn.teamsare);
        cb3.setBounds(350,270,250,48);
        j1.add(cb3);
        cb4=new JComboBox<String>(type);
        cb4.setBounds(350,510,250,48);
        j1.add(cb4);
        t2[0]=new JTextField("");
        t2[0].setBounds(350,330,250,48);
        j1.add(t2[0]);
        t2[1]=new JTextField("");
        t2[1].setBounds(350,390,250,48);
        j1.add(t2[1]);
        t2[2]=new JTextField("");
        t2[2].setBounds(350,450,250,48);
        j1.add(t2[2]);
        JLabel l[]=new JLabel[8];
        for(i=0;i<8;i++)
        {
            l[i]=new JLabel(s[i]);
            l[i].setBounds(150,90+i*60,150,48);
            j1.add(l[i]);
        } 
    }
    

    void addschedule()
    {
        int n=1;
        try {
              stmt=connection.con.prepareStatement("select count(*) from schedule ");
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
            if(n<8)
            {
             stmt=connection.con.prepareStatement("insert into schedule values(?,?,?,?,?,?,?,?,?)");
             stmt.setInt(1, n+1);
             stmt.setString(2, String.valueOf(cb1.getSelectedItem()));
             stmt.setString(3, String.valueOf(cb5.getSelectedItem()));
             stmt.setString(4, String.valueOf(cb2.getSelectedItem()));
             stmt.setString(5, String.valueOf(cb3.getSelectedItem()));
             stmt.setString(6, t2[0].getText());
             stmt.setString(7, t2[1].getText());
             stmt.setString(8, t2[2].getText());
             stmt.setString(9, String.valueOf(cb4.getSelectedItem()));
             stmt.executeUpdate();
             JOptionPane.showMessageDialog(this,"ADDED SUCCESSFULLY");
            }
            else
                JOptionPane.showMessageDialog(this,"STACK IS FULL PLEASE TRY LATER");
            } catch (Exception ee) {System.out.println(ee);}
    }
}
