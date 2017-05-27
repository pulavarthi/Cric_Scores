package cricapp;
import cricin.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Cricapp extends javax.swing.JFrame implements ActionListener {

    JButton bh, bsd, brs, bpt, bpp, brec,bsc[] = new JButton[4],capp;
    String team1[] = new String[3], team2[] = new String[3], typ[] = new String[3], stts[] = new String[3];
    int i;
    PreparedStatement stmt;
    static connection c=new connection();
    ResultSet rs;

    public Cricapp() {
    		setContentPane(new JLabel(new ImageIcon("bg3other.jpg")));
    		setLayout(null);
    		setTitle("Home Page");            
    		setSize(730, 950);
    		setVisible(true);
    		setDefaultCloseOperation(EXIT_ON_CLOSE);
    		
    		//capp = new JButton("CRIC INPUT");
            //capp.setBounds(50, 520, 100, 50);
            bh = new JButton("HOME");
            bh.setBounds(50, 20, 100, 50);
            bsd = new JButton("FIXTURES");
            bsd.setBounds(200, 20, 100, 50);
            brs = new JButton("RESULT");
            brs.setBounds(350, 20, 100, 50);
            bpt = new JButton("POINT TABLE");
            bpt.setBounds(500, 20, 150, 50);
            bsc[1] = new JButton("SCORECARD1");
            bsc[1].setBounds(30, 130, 200, 100);
            
            bsc[2] = new JButton("SCORECARD2");
            bsc[2].setBounds(280, 130, 200, 100);
            bpp = new JButton("SEARCH PLAYER");
            bpp.setBounds(20, 600, 310, 50);
            brec = new JButton("CRIC INPUT");
            brec.setBounds(380, 600, 310, 50);           
            
            home();
            
    }

    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bh)) {
            Cricapp c=new Cricapp();
            this.dispose();
        }
        
        if (e.getSource().equals(bsd)) {
            scheduleout s=new scheduleout();
            this.dispose();
        }
        /*
        if (e.getSource().equals(capp)) {
            cricinput s=new cricinput();
            this.dispose();
        }
        */
        
        if (e.getSource().equals(brs)) {
            resultout r=new resultout();
            this.dispose();
        }
        
        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            this.dispose();
        }
        if (e.getSource().equals(bsc[1])) {
            scoreboard s=new scoreboard(1);
            this.dispose();
        }
        if (e.getSource().equals(bsc[2])) {
            scoreboard s=new scoreboard(2);
            this.dispose();
        }
        if (e.getSource().equals(bpp)) {
           playerprofile s=new playerprofile();
             this.dispose();
        }
        
        if (e.getSource().equals(brec)) {
            //record r=new record("BATTING");
        	cricinput c=new cricinput();
            this.dispose();
        }
        
    }

    void display() {
              
        
        add(bh);
        bh.addActionListener(this);
        //add(capp);
        //capp.addActionListener(this);
        add(bpt);
        bpt.addActionListener(this);
        add(bsd);
        bsd.addActionListener(this);
        add(brs);
        brs.addActionListener(this);
        add(bpp);
        bpp.addActionListener(this);
        add(brec);
        brec.addActionListener(this);
        add(bsc[1]);
        bsc[1].addActionListener(this);
        add(bsc[2]);
        bsc[2].addActionListener(this);
    }
    
    void home() {
        display();
        for (i = 1; i <= 2; i++) {
            try {
                stmt = connection.con.prepareStatement("Select * from schedule where SR=?");
                stmt.setInt(1, i);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    stts[i] = rs.getString(2);
                    team1[i] = rs.getString(4);
                    team2[i] = rs.getString(5);
                    typ[i] = rs.getString(9);
                    String a = team1[i] + '\t' + "vs" + '\t' + team2[i];
                    a = "<html>" + a + "<br>" + stts[i] + "<html>";
                    bsc[i].setText(a);
                }
            } catch (Exception ee) {
                System.out.println(ee);
                System.out.println("schedule has no values");
            }
        }

    }

    public static void main(String[] args) {
        c.connect();
        Cricapp a = new Cricapp();
    }
}
