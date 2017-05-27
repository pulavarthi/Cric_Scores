package cricapp;

import cricin.*;
import javax.swing.*;
import java.awt.event.*;

public class search extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, brs, bpt, bpp, brec,bk,bsrch;
    JTextField t1;
    JLabel l1;
    int i,j;
    
    search()
    {
         bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50);
            j1=new JFrame("Search player page");
            j1.setLayout(null);
            j1.setSize(715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            l1=new JLabel("Enter player name");
            l1.setBounds(150,150,150,50);
            j1.add(l1);
            t1 = new JTextField("");            
            t1.setBounds(300, 150, 150, 50);
            j1.add(t1);
            bsrch = new JButton("SEARCH");
            bsrch.setBounds(330, 250, 100, 50);
           j1. add(bsrch);
            bsrch.addActionListener(this);
            
    }
    
    
     public void actionPerformed(ActionEvent e) {
         if (e.getSource().equals(bh)) {
            Cricapp c=new Cricapp();
            j1.dispose();
        }
        
        if (e.getSource().equals(bsd)) {
            scheduleout s=new scheduleout();
            j1.dispose();
        }
        
        if (e.getSource().equals(brs)) {
            resultout r=new resultout();
            j1.dispose();
        }
        
        if (e.getSource().equals(bpt)) {
            pointtable p=new pointtable();
            j1.dispose();
            // add(cb1);
            //cb1.addActionListener(this);
        }
        
         if(e.getSource().equals(bk))
         {
             Cricapp c=new Cricapp();
             j1.dispose();
         }
         
         if (e.getSource().equals(bpp)) {
             search p=new search();
             j1.dispose();
        }
         /*
        if (e.getSource().equals(brec)) {
            record r=new record("BATTING");
            j1.dispose();
        }
        */
        
        if (e.getSource().equals(bsrch)) {
            playerprofile p=new playerprofile(t1.getText());//implementation not provided
            
            j1.dispose();
        }
     }
     
     
     
     void display() 
     {
         bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            brs = new JButton("RESULT");
            brs.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLLE");
            bpt.setBounds(530, 20, 150, 50);
            bpp = new JButton("PLAYER");
            bpp.setBounds(20, 600, 310, 50);
            //brec = new JButton("RECORDS");
            //brec.setBounds(380, 600, 310, 50);
            bk = new JButton("BACK");
            bk.setBounds(10, 650, 690, 50); 
        j1.add(bh);
        bh.addActionListener(this);
        j1.add(bpt);
        bpt.addActionListener(this);
        j1.add(bsd);
        bsd.addActionListener(this);
        j1.add(brs);
        brs.addActionListener(this);
        j1.add(bpp);
        bpp.addActionListener(this);
        //j1.add(brec);
        //brec.addActionListener(this);
        j1.add(bk);
        bk.addActionListener(this);
        
    }
}