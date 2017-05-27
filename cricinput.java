package cricin;
import javax.swing.*;

import cricapp.Cricapp;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import cricin.connection;

public class cricinput extends javax.swing.JFrame implements ActionListener {

    JButton bh, bsd, btm, bpt, bpp, brec,bsc[]=new JButton[4],bh1,cin;
    String stts[]=new String[4],typ[]=new String[4],team1[]=new String[4],team2[]=new String[4];
    int brd,i;
   //connection c;
   PreparedStatement stmt;
   ResultSet rs;
   JFrame j;
    public cricinput() {
    	cin = new JButton("CRIC APP");
        cin.setBounds(530, 20, 100, 50);
    	bh1 = new JButton("HOME");
        bh1.setBounds(80, 320, 100, 50);
        bh = new JButton("CRIC INPUT");
        bh.setBounds(80, 20, 100, 50);
        bsd = new JButton("SCHEDULE");
        bsd.setBounds(230, 20, 100, 50);
        btm = new JButton("TEAMS");
        btm.setBounds(380, 20, 100, 50);
        /*
        bpt = new JButton("POINT TABLLE");
        bpt.setBounds(530, 20, 100, 50);
        */
        bsc[1] = new JButton("SCORE1");
        bsc[1].setBounds(30, 130, 300, 150);
        bsc[2] = new JButton("SCORE2");
        bsc[2].setBounds(380, 130, 300, 150);
        bpp = new JButton("PLAYER");
        bpp.setBounds(20, 600, 310, 50);
        brec = new JButton("LEAGUE");
        brec.setBounds(380, 600, 310, 50);
        j=new JFrame("Cric Input page");
        j.setLayout(null);
        j.setSize(715, 950);
        j.setVisible(true);
        //j.setBackground(Color.white);
        //j.getContentPane().setBackground(Color.WHITE);
        j.setContentPane(new JLabel(new ImageIcon("stumps2.jpg")));
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j.setResizable(false);
        display();
    }

    

   public void actionPerformed(ActionEvent e) {
	   
        if (e.getSource().equals(bh))
        {
            home();
            this.dispose();
        }
        if (e.getSource().equals(cin))
        {
            new Cricapp();
            this.dispose();
        }
        if (e.getSource().equals(bsd)) {
            schedule s=new schedule();
            j.dispose();
        }
        if (e.getSource().equals(btm)) {
            teams tq=new teams();
            j.dispose();
        }
        
        if (e.getSource().equals(bsc[1])||e.getSource().equals(bsc[2])) 
        {
           if(e.getSource().equals(bsc[1]))
           brd=1;
           if(e.getSource().equals(bsc[2]))
            brd=2;
            if(stts[brd].equals("LIVE"))
            {scoreboard s=new scoreboard(brd);
             }
             else
            {start s=new start(brd);}
             j.dispose();
        }
       
        if (e.getSource().equals(bpp)) {
            playerprofile p=new playerprofile();
            j.dispose();
        }
        
        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            j.dispose();
        }
        
        if (e.getSource().equals(brec)) {
            league l=new league("");
            j.dispose();
        }
   }
   
   void display() {
        j.add(bh);
        bh.addActionListener(this);
        /*
        j.add(bpt);
        bpt.addActionListener(this);
        */
        j.add(bsd);
        bsd.addActionListener(this);
        j.add(cin);
        cin.addActionListener(this);
        j.add(btm);
        btm.addActionListener(this);
        j.add(bpp);
        bpp.addActionListener(this);
        j.add(brec);
        brec.addActionListener(this);
        j.add(bsc[1]);
        bsc[1].addActionListener(this);
        j.add(bsc[2]);
        bsc[2].addActionListener(this);
        home();
    }
   
   
    void home() {
        try {
            for(i=1;i<=2;i++)
             {
            stmt =connection.con.prepareStatement("Select * from schedule where SR=?");
            stmt.setInt(1, i);
            rs = stmt.executeQuery();
            while (rs.next()) {
                stts[i]=rs.getString(2);
                team1[i]=rs.getString(4);
                team2[i]=rs.getString(5);
                typ[i]=rs.getString(9);
            String a=team1[i]+'\t'+"vs"+'\t'+team2[i];
            a="<html>"+a+"<br>"+stts[i]+"<html>";
            bsc[i].setText(a);
            }}
            
        } catch (Exception ee) {
            System.out.println(ee);
        } }
  

    public static void main(String[] args) {
        connection cc=new connection();
        cc.connect();
        cricinput c = new cricinput();
    }

}
// team,BOWLER ECO AND OVERS,descending order pointtable