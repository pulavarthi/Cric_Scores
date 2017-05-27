package cricin;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class scoreboard extends javax.swing.JFrame implements ActionListener {
    JFrame j1;
    JButton bh, bsd, btm, bpt, bpp, brec,bbk,bst,b1,b2,b3,b4,b5,b9,b0;
    JComboBox<?> b6,b8,ctm1,ctm2;
    JRadioButton rb[]=new JRadioButton[3];
    ButtonGroup jb=new ButtonGroup();
    JTextField t2[]=new JTextField[8];
    JLabel lrn,lw,lr,lovr,lply[]=new JLabel[2],lbw,lb,lnb;
    int i,k,brd,bwr=0,bww=0,maxovr=200,inning=1,rn,chase=1000,mtch,wckt,sx[]=new int[3],fr[]=new int[3],ball=0,wickets=0,runs=0,ovr=0,check=0,pl=0,prn[]=new int[2],pbl[]=new int[2];
    String d,extra[]={"1","2","3","4","5","6"},stts[]=new String[4],league;
    String team2[]=new String[21],team1[]=new String[12],TM[]=new String[3],bstts="BATTING",bwstts="BOWLING",typ;
    float rr=0,ovrs,bo=0;
    PreparedStatement stmt;
    //connection c;
    ResultSet rs;
    
    
    public scoreboard(int n)
    {
        brd=n;
            j1=new JFrame("Scoreboard page");
            j1.setLayout(null);
            j1.setSize(715, 950);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            j1.setResizable(false);
            display();
            showscore();
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
        }
        
        if (e.getSource().equals(bbk)) {
            cricinput cr=new cricinput();
            j1.dispose();
        }
        
        if (e.getSource().equals(b1)) {
              runs+=1;
              prn[pl]+=1;//runs scored
              pbl[pl]+=1;//balls faced
              bwr+=1;//balls count for bowler
              lrn.setText(""+runs);  
              ball++;
              over();
              if(pl==0)
              pl=1;
              else pl=0;
          }
          if (e.getSource().equals(b2)) {
              runs+=2;
              prn[pl]+=2;
              pbl[pl]+=1;
              bwr+=2;
              lrn.setText(String.valueOf(runs));
              ball++;
              over();
          }
          if (e.getSource().equals(b3)) {
              runs+=3;
              prn[pl]+=3;
              pbl[pl]+=1;
              bwr+=3;
              lrn.setText(String.valueOf(runs));
              ball++;
              over();
              if(pl==0)
              pl=1;
              else pl=0;
          }
          if (e.getSource().equals(b4)) {
              runs+=4;
              prn[pl]+=4;
              pbl[pl]+=1;
              bwr+=4;
              lrn.setText(String.valueOf(runs));
              ball++;
              fr[pl]++;
              over();
          }
          if (e.getSource().equals(b5)) {
              runs+=6;
              prn[pl]+=6;
              pbl[pl]+=1;
              bwr+=6;
              lrn.setText(String.valueOf(runs));
              ball++;
              sx[pl]++;
              over();
          }
          if (e.getSource().equals(b6)) {
              runs+=Integer.parseInt(String.valueOf(b6.getSelectedItem()));
              lrn.setText(String.valueOf(runs));
              bwr+=1;
              over();
          }
          
          if (e.getSource().equals(b8)) {
              runs+=Integer.parseInt(String.valueOf(b8.getSelectedItem()));
              lrn.setText(String.valueOf(runs));
              ball++;
              over();
          }
          if (e.getSource().equals(b9)) {
              wickets++;
              pbl[pl]+=1;
              bww++;
              bstts="OUT";
              ball++;
              over();
              if(pl==0)
              rb[0].setText("SELECT PLAYER");
              else
              rb[1].setText("SELECT PLAYER");
              if(pl==0&&ball==0)
                  i=1;
              else if(pl==1&&ball==0)
                  i=0;
              else i=pl;
              prn[i]=0;
              pbl[i]=0;
              fr[i]=0;
              sx[i]=0;
              bstts="BATTING";
              lw.setText(String.valueOf(wickets));
          }
          
           if (e.getSource().equals(b0)) {
              ball++;
              pbl[pl]+=1;
              over();
          } 
           for(i=0;i<2;i++)
           if(e.getSource().equals(rb[i]))
               pl=i;
           
           if(e.getSource().equals(ctm2))
               {
                   if (rb[2].isSelected())
                   {
                   rb[2].setText(String.valueOf(ctm2.getSelectedItem()));
               try{
                   stmt=connection.con.prepareStatement("Select * from "+"scorecardball"+brd+" where PLAYER=?");
                   stmt.setString(1, rb[2].getText());
                   rs=stmt.executeQuery();
               if(rs.absolute(1))
               {
                   while(rs.next()){
                   bwr=rs.getInt(4);
                   bo=rs.getFloat(5);
                   bww=rs.getInt(6);
                   bwstts="BOWLING";
                   }
                   //System.out.println(p+bwr+" "+bww);
               }
               else addinfo(3);
               }
               catch(Exception ee){ System.out.println(ee);}
               }
                   for(i=0;i<2;i++)
                       if (rb[i].isSelected())
                       {
                       rb[i].setText(String.valueOf(ctm2.getSelectedItem()));
                       try{
                       stmt=connection.con.prepareStatement("Select * from "+"scorecardbat"+brd+" where PLAYER=?");
                   stmt.setString(1, rb[i].getText());
                   rs=stmt.executeQuery();
               if(rs.absolute(1))
               {}
               else
                   addinfo(1);
                       }
                       catch(Exception ee){System.out.println();}
                 }}
           
           if(e.getSource().equals(ctm1))
               {
                   i=2;
                   if (rb[i].isSelected())
                   {
                   rb[i].setText(String.valueOf(ctm1.getSelectedItem()));
               try{
                   stmt=connection.con.prepareStatement("Select * from "+"scorecardball"+brd+" where PLAYER=?");
                   stmt.setString(1, rb[i].getText());
                   rs=stmt.executeQuery();
               if(rs.absolute(1))
               {
                   while(rs.next()){
                   bwr=rs.getInt(4);
                   bo=rs.getFloat(5);
                   bww=rs.getInt(6);
                   bwstts="BOWLING";
                   }
                   //System.out.println(p+bwr+" "+bww);
               }
               else addinfo(3);
               }
               catch(Exception ee){ System.out.println(ee);}
               }
                   for(i=0;i<2;i++)
                       if (rb[i].isSelected())
                       {
                       rb[i].setText(String.valueOf(ctm1.getSelectedItem()));
                       try{
                       stmt=connection.con.prepareStatement("Select * from "+"scorecardbat"+brd+" where PLAYER=?");
                   stmt.setString(1, rb[i].getText());
                   rs=stmt.executeQuery();
               if(rs.absolute(1))
               {}
               else
                   addinfo(1);
                       }
                       catch(Exception ee){System.out.println();}
                 }}
     }
     
     
     void display() 
     {
         bh = new JButton("HOME");
            bh.setBounds(80, 20, 100, 50);
            bsd = new JButton("SCHEDULE");
            bsd.setBounds(230, 20, 100, 50);
            btm = new JButton("TEAMS");
            btm.setBounds(380, 20, 100, 50);
            bpt = new JButton("POINT TABLE");
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
    }
     
      void showscore() 
    {
        JLabel b7,l1,l2,l3,l4;
        team1[0]="";
        team2[0]="";
        String s="";
        l1=new JLabel("runs=");
      l1.setBounds(100,100,100,60);
      l2=new JLabel("wickets=");
      l2.setBounds(100,180,100,60);
      
      lb=new JLabel("extras noballs");
      lb.setBounds(580,160,100,60);
      lnb=new JLabel("extras byes");
      lnb.setBounds(580,240,100,60);
      l3=new JLabel("overs=");
      l3.setBounds(100,260,100,60);
      l4=new JLabel("R.R=");
      l4.setBounds(100,320,100,60);
      lrn=new JLabel("0");
      lrn.setBounds(210,100,100,60);
      lw=new JLabel("0");
      lw.setBounds(210,180,100,60);
       lovr=new JLabel("0");
      lovr.setBounds(210,260,100,60);
      lr=new JLabel("0");
      lr.setBounds(210,320,100,60);
      b1=new JButton("1");
      b1.setBounds(450,100,60,60);
      b2=new JButton("2");
      b2.setBounds(450,170,60,60);
      b3=new JButton("3");
      b3.setBounds(450,240,60,60);
      b4=new JButton("4");
      b4.setBounds(450,310,60,60);
      b5=new JButton("6");
      b5.setBounds(450,380,60,60);
      b6=new JComboBox<Object>(extra);
      b6.setBounds(520,170,60,60);
      b7=new JLabel("extra");
      b7.setBounds(540,170,60,30);
      lply[0]=new JLabel("0");
      lply[0].setBounds(220,400,50,60);
      lply[1]=new JLabel("0");
      lply[1].setBounds(220,460,50,60);
      lbw=new JLabel("0");
      lbw.setBounds(350,400,50,60);
      b8=new JComboBox<Object>(extra);
      b8.setBounds(520,240,60,60);
      b9=new JButton("W");
      b9.setBounds(520,310,60,60);
      b0=new JButton("0");
      b0.setBounds(520,100,60,60);
      rb[0]=new JRadioButton("PLAYER 1");
      rb[0].setBounds(90,400,100,60);
      rb[1]=new JRadioButton("PLAYER 2");
      rb[1].setBounds(90,460,100,60);
      rb[2]=new JRadioButton("BOWLER");
      rb[2].setBounds(250,400,100,60);
      int r[]=new int[2];
      try{
      for(i=1;i>=0;i--)
            {
            stmt=connection.con.prepareStatement("select * from "+"score"+brd+" where SN=? ");
            stmt.setInt(1,i+1);
             rs=stmt.executeQuery();
             while(rs.next())
            {
             TM[i+1]=rs.getString(2);
             r[i]=rs.getInt(3);
             wickets=rs.getInt(4);
             d=String.valueOf(rs.getFloat(5));
             typ=rs.getString(7);
            }}
             if(r[1]!=0)
                 chase=r[1];
                 runs=r[0];
             if(typ.equals("ODI"))
                 maxovr=5;
             else if(typ.equals("T20"))
                 maxovr=2;}
      catch(Exception ee){System.out.println(ee);}
             try{
                 
             String ss[]=d.split("\\.");
             ovr=Integer.parseInt(ss[0]);
             ball=Integer.parseInt(ss[1]);
              }
             catch(Exception ee){System.out.println(ee);}
             i=0;
             try{
            stmt=connection.con.prepareStatement("select * from "+"scorecardbat"+brd+" where STATUS=?");
            stmt.setString(1,"BATTING");
            rs=stmt.executeQuery();
            while(rs.next())
            {
             rb[i].setText(rs.getString(3));
             prn[i]=rs.getInt(4);
             pbl[i]=rs.getInt(5);
             fr[i]=rs.getInt(6);
             sx[i]=rs.getInt(7);
             i++;
            }
      stmt=connection.con.prepareStatement("select * from "+"scorecardball"+brd+" where STATUS=?");
            stmt.setString(1,"BOWLING");
            rs=stmt.executeQuery();
            while(rs.next())
            {
             s=rs.getString(3);
             rb[2].setText(s);
             bwr=rs.getInt(4);
             bo=rs.getFloat(5);
             bww=rs.getInt(6);
            }
        for(int j=1;j<=11;j++)
            {
            stmt=connection.con.prepareStatement("select * from "+TM[1]+" where SR=?");
            stmt.setInt(1,j);
            rs=stmt.executeQuery();
             while(rs.next())
             team1[j]=rs.getString(2);
            stmt=connection.con.prepareStatement("select * from "+TM[2]+" where SR=?"); 
            stmt.setInt(1,j);
             rs=stmt.executeQuery();
             while(rs.next())
             team2[j]=rs.getString(2);
        }
      }
      catch(Exception ee){System.out.println(ee);}
      
      
      ctm1=new JComboBox<Object>(team1);
      ctm1.setBounds(250,460,100,60);
      j1.add(ctm1);
      ctm1.addActionListener(this);
      ctm2=new JComboBox<Object>(team2);
      ctm2.setBounds(375,460,100,60);
      
      lrn.setText(String.valueOf(runs));
      lw.setText(String.valueOf(wickets));
      lovr.setText(String.valueOf(d));
      lply[0].setText(String.valueOf(prn[0]));
      lply[1].setText(String.valueOf(prn[1]));
      lbw.setText(String.valueOf(""+bwr+"-"+bww));
      j1.add(l1);
      j1.add(lb);
      j1.add(lnb);
      j1.add(l2);
      j1.add(l3);
      j1.add(l4);
      j1.add(lrn);
      j1.add(lw);
      j1.add(lovr);
      j1.add(lr);
      j1.add(b1);
      b1.addActionListener(this);
      j1.add(b2);
      b2.addActionListener(this);
      j1.add(b3);
      b3.addActionListener(this);
      j1.add(b4);
      b4.addActionListener(this);
      j1.add(b5);
      b5.addActionListener(this);
      j1.add(b6);
      b6.addActionListener(this);
      j1.add(b7);
      j1.add(lply[0]);
      j1.add(lply[1]);
      j1.add(lbw);
      j1.add(b8);
      b8.addActionListener(this);
      j1.add(b9);
      b9.addActionListener(this);
      j1.add(b0);
      b0.addActionListener(this);  
      j1.add(ctm2);
      ctm2.addActionListener(this);
      j1.add(rb[0]);
      j1.add(rb[1]);
      j1.add(rb[2]);
      rb[0].addActionListener(this);
      rb[1].addActionListener(this);
      rb[2].addActionListener(this);
      jb.add(rb[0]);
      jb.add(rb[1]);
      jb.add(rb[2]);
    }
      
      
      
      void over()
    {
            
         if(ovr==maxovr||wickets==10||runs>chase)
        {
            inning();
        }
        else
        {
            addinfo(0);
            addinfo(2);
            d=String.valueOf(ovr)+'.'+String.valueOf(ball);
            lovr.setText(d);
            float n=(ovr*6)+ball;
            n=runs/n;  
            n=n*6;
            lr.setText(String.valueOf(n));
            lply[pl].setText(String.valueOf(prn[pl]));
            lbw.setText(""+bwr+"-"+bww);
            try{
                 stmt=connection.con.prepareStatement("update "+"score"+brd+" set RUNS=?,WICKETS=?,OVERS=?,RR=? where SN=?");
                 stmt.setInt(1, runs);
                 stmt.setInt(2, wickets);
                 stmt.setFloat(3, Float.parseFloat(d));
                 stmt.setFloat(4, n);
                 stmt.setInt(5,1);
                 stmt.executeUpdate();
                }catch(Exception ee){ System.out.println(ee);}
        }
        if(ball==6)
        {
           ball=0;
           ovr++;
           if(pl==0)
              pl=1;
              else pl=0;
           rb[2].setText("BOWLER");
           d=String.valueOf(ovr)+'.'+String.valueOf(ball);
            lovr.setText(d);
        }
    
    }
      
      
      void inning()
      {
          if(inning==1)
          {
           String a=inning+"INNING FISHES";
            JOptionPane.showMessageDialog(this,a);
            chase=runs;
            runs=0;
            ovr=0;
            wickets=0;
            ball=0;
            bwr=0;
            bww=0;
            bo=0;
            prn[0]=0;
            prn[1]=0;
            pbl[0]=0;
            pbl[1]=0;
            pl=0;
            lw.setText(String.valueOf(wickets));
            lply[0].setText("0");
            lply[1].setText("0");
            rb[0].setText("");
            rb[1].setText("");
            rb[2].setText("");
           try{
            stmt=connection.con.prepareStatement("update "+"score"+brd+" set SN=? where TEAMS=?");
            stmt.setInt(1,2);
            stmt.setString(2,TM[1]);
            stmt.executeUpdate();
            stmt=connection.con.prepareStatement("update "+"score"+brd+" set SN=? where TEAMS=?");
            stmt.setInt(1,1);
            stmt.setString(2,TM[2]);
            stmt.executeUpdate();
            stmt=connection.con.prepareStatement("update "+"scorecardbat"+brd+" set STATUS=? where STATUS=?");
        stmt.setString(1,"BAT");
        stmt.setString(2,"BATTING");
        stmt.executeUpdate();
        stmt=connection.con.prepareStatement("update "+"scorecardball"+brd+" set STATUS=? where STATUS=?");
        stmt.setString(1,"BOWL");
        stmt.setString(2,"BOWLING");
        stmt.executeUpdate();
            }
            catch(Exception ee){System.out.println(ee);}
          }
          
          if(inning==2)
          {
               
            stts[brd]="PLAYED";
            try{
            stmt=connection.con.prepareStatement("update schedule set STATUS=? where SR=?");
            stmt.setString(1,stts[brd]);
            stmt.setInt(2,brd);
            stmt.executeUpdate();
            }
            catch(Exception ee){System.out.println(ee);}
            playerinfo();
            result();
            if(runs>chase)
            {
                JOptionPane.showMessageDialog(this,"MATCH FINISHED "+TM[2]+" WON");
                System.out.println("7777");
                pointtable(TM[2],"w");
                System.out.println("7777");
                pointtable(TM[1],"l");
            }
            else if(chase>runs)
            {
                JOptionPane.showMessageDialog(this,"MATCH FINISHED "+TM[1]+" WON");
                System.out.println("7777");
                 pointtable(TM[1],"w");
                 System.out.println("7777");
                pointtable(TM[2],"l");
            }
            else
            {
                JOptionPane.showMessageDialog(this,"MATCH IS TIE");
                 pointtable(TM[2],"t");
                pointtable(TM[1],"t");
            }
          }
          inning++;
      }
      
      void addinfo(int j)
    {
        try{
       if(j==0)
        {float f=prn[pl]*100/pbl[pl];
        stmt=connection.con.prepareStatement("update "+"scorecardbat"+brd+" set RUNS=?,BALLS=?,FOURS=?,SIXS=?,SR=?,STATUS=? where PLAYER=?");
        stmt.setInt(1, prn[pl]);
        stmt.setInt(2, pbl[pl]);   
        stmt.setInt(3, fr[pl]);
        stmt.setInt(4, sx[pl]);
        stmt.setFloat(5, f);
        stmt.setString(6,bstts);
        stmt.setString(7,rb[pl].getText());
        System.out.println(bstts);
        System.out.println("1045");
        stmt.executeUpdate();
        }
        if(j==1)
        {
            int n=0;
          stmt=connection.con.prepareStatement("select count(*) from "+"scorecardbat"+brd);
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
          stmt=connection.con.prepareStatement("insert into "+"scorecardbat"+brd+" values(?,?,?,?,?,?,?,?,?)");
          stmt.setInt(1, n+1);
          stmt.setString(2, TM[inning]);
          stmt.setString(3, rb[i].getText());
          stmt.setInt(4, 0);
          stmt.setInt(5, 0);
          stmt.setInt(6, 0);
          stmt.setInt(7, 0);
          stmt.setFloat(8, 0);
          stmt.setString(9, bstts);
          System.out.println("1245");
          stmt.executeUpdate();  
        }
        if(j==2)
        {
         if(ball==6)
             bwstts="OVER UP";
        stmt=connection.con.prepareStatement("update "+"scorecardball"+brd+" set RUNS=?,OVERS=?,WICKETS=?,STATUS=?,RR=? where PLAYER=?");
        stmt.setInt(1, bwr);
        stmt.setFloat(2, bo+1);
        stmt.setInt(3, bww);
        stmt.setString(4,bwstts);
        stmt.setFloat(5, bwr/(bo+1));
        stmt.setString(6,rb[2].getText());
        System.out.println("1345");
        stmt.executeUpdate();
        }
        if(j==3)
        {
          if(inning==1)
          k=2;
          else k=1;
          bwr=0;
          bo=0;
          bww=0;
          int m=0;
          stmt=connection.con.prepareStatement("select count(*) from "+"scorecardball"+brd);
            rs=stmt.executeQuery();
            while(rs.next())
            m=rs.getInt("count(*)");
          stmt=connection.con.prepareStatement("insert into "+"scorecardball"+brd+" values(?,?,?,?,?,?,?,?)");
          stmt.setInt(1,m+1);
          stmt.setString(2, TM[k]);
          stmt.setString(3, rb[2].getText());
          stmt.setInt(4, 0);
          stmt.setFloat(5,0 );
          stmt.setInt(6, 0);
          stmt.setFloat(7, 0);
          stmt.setString(8,"BOWLING");
          System.out.println("1445");
          stmt.executeUpdate();   
        }
        }
        catch(Exception ee){System.out.println(ee);}
    }
      
      
      void playerinfo() 
    {
        String player=null,team=null;
        int wickets=0,overs=0,runs=0,fours=0,sixes=0,test=0,odi=0,t20=0,cen=0,fifty=0;
        try{
            int n=0;
            stmt=connection.con.prepareStatement("select count(*) from "+"scorecardbat"+brd);
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
            for(i=1;i<=n;i++)
            {
                stmt=connection.con.prepareStatement("select * from "+"scorecardbat"+brd+" where SN=?");
                stmt.setInt(1, i);
            rs=stmt.executeQuery();
             while(rs.next())
            {
             team=rs.getString(2);
             player=rs.getString(3);
             runs=rs.getInt(4);
             fours=rs.getInt(6);
             sixes=rs.getInt(7);
             System.out.println("1545");
            }
                stmt=connection.con.prepareStatement("select * from "+team+" where PLAYER=?");
              stmt.setString(1, player);
            rs=stmt.executeQuery();
             while(rs.next())
            {
               test=rs.getInt(3);
               odi=rs.getInt(4);
               t20=rs.getInt(5);
               runs+=rs.getInt(6);
               fours+=rs.getInt(7);
               sixes+=rs.getInt(5);
               cen=rs.getInt(9);
               fifty=rs.getInt(10);
               System.out.println("1645");
            }
            
              
             if(maxovr==5)
                 odi++;
             if(maxovr>5)
                 test++;
             if(maxovr==2)
                 t20++;
             if(runs>=50&&runs<100)
                 fifty++;
             if(runs>=100)
                 cen++;
              stmt=connection.con.prepareStatement("update "+team+" set RUNS=?,FOURS=?,SIX=?,CENTURY=?,FIFTY=?,TESTBAT=?,ODIBAT=?,T20BAT=? where PLAYER=?");
        stmt.setInt(1, runs);
        stmt.setInt(2, fours);
        stmt.setInt(3, sixes);
        stmt.setInt(4, cen);
        stmt.setInt(5, fifty);
        stmt.setInt(6, test);
        stmt.setInt(7, odi);
        stmt.setInt(8, t20);
        stmt.setString(9,player);
        stmt.executeUpdate();
        System.out.println("1745");
         
            }
            
            stmt=connection.con.prepareStatement("select count(*) from "+"scorecardball"+brd);
            rs=stmt.executeQuery();
            while(rs.next())
            n=rs.getInt("count(*)");
            for(i=1;i<=n;i++)
            {
             stmt=connection.con.prepareStatement("select * from "+"scorecardball"+brd);
            rs=stmt.executeQuery();
             while(rs.next())
            {
             team=rs.getString(2);
             player=rs.getString(3);
             wickets=rs.getInt(6);
             overs=rs.getInt(5);
            }
              stmt=connection.con.prepareStatement("select * from "+team+" where PLAYER=?");
              stmt.setString(1, player);
            rs=stmt.executeQuery();
            System.out.println("1845");
             while(rs.next())
            {
               test=rs.getInt(13);
               odi=rs.getInt(14);
               t20=rs.getInt(15);
               wickets+=rs.getInt(12);
             overs+=rs.getInt(11);
            }
             if(maxovr==5)
                 odi++;
             if(maxovr>5)
                 test++;
             if(maxovr==2)
                 t20++;
             
             stmt=connection.con.prepareStatement("update "+team+" set TESTBAll=?,ODIBAll=?,T20BAll=?,OVERS=?,WICKETS=? where PLAYER=?");
             stmt.setInt(1, test);
             stmt.setInt(2, odi);
             stmt.setInt(3, t20);      
             stmt.setInt(4, overs);
             stmt.setFloat(5, wickets);
             stmt.setString(6, player);
        stmt.executeUpdate();
        System.out.println("1945");
        }}
        catch(Exception ee){System.out.println(ee);}
    
    }
      
      
      void result()
    {
        int t=0;
        String rsl[]=new String[10];
       try{
           stmt=connection.con.prepareStatement("select count(*) from result");
            rs=stmt.executeQuery();
            while(rs.next())
            t=rs.getInt("count(*)");
           for(i=t;i>-1;i--)
           {int j=i+1;
           stmt=connection.con.prepareStatement("update result set SR=? where SR=?");
           stmt.setInt(1,j);
           stmt.setInt(2, i);}
           stmt.executeUpdate();
           System.out.println("2045");
          /* stmt=connection.con.prepareStatement("delete from result where SR=?");
           stmt.setInt(1, 9);
           stmt.executeUpdate();*/
           stmt=connection.con.prepareStatement("select * from schedule where SR=?");
           stmt.setInt(1, brd);
           rs=stmt.executeQuery();
           System.out.println("2145");
while(rs.next())
{
    rsl[2]=rs.getString(3);
    rsl[3]=rs.getString(4);
    rsl[4]=rs.getString(5);
    rsl[5]=rs.getString(6);
    rsl[6]=rs.getString(7);
    rsl[7]=rs.getString(8);
    rsl[8]=rs.getString(9);
}
 stmt=connection.con.prepareStatement("delete from schedule where STATUS=?");
           stmt.setString(1, "PLAYED");
           stmt.executeUpdate();
           System.out.println("2245");
           for(i=brd+1;i<9;i++)
           { stmt=connection.con.prepareStatement("update schedule set SR=? where SR=?");
          int j=i-1;
           stmt.setInt(1,j);
           stmt.setInt(2, i);
           stmt.executeUpdate();}
           System.out.println("2345");
        rsl[9]=""+chase+"/"+runs;
           stmt=connection.con.prepareStatement("insert into result values(?,?,?,?,?,?,?,?,?,?)");
           stmt.setInt(1,1);
           stmt.setString(2,"PLAYED");
           stmt.setString(3,rsl[2]);
           stmt.setString(4,rsl[3]);
           stmt.setString(5,rsl[4]);
           stmt.setString(6,rsl[5]);
           stmt.setString(7,rsl[6]);
           stmt.setString(8,rsl[7]);
           stmt.setString(9,rsl[8]);
           stmt.setString(10,rsl[9]);
        stmt.executeUpdate();
        System.out.println("2445");
       }
       catch(Exception ee){System.out.println(ee);}
       league=rsl[2];
    }
      
      void pointtable(String team,String s)
      {
          int played=0,wins=0,lose=0,tie=0,point=0;
         try{
             stmt = connection.con.prepareStatement("Select * from pointtable where LEAGUE=? and TEAMS=?");
            stmt.setString(1, league);
            stmt.setString(2, team);
            rs = stmt.executeQuery();
            System.out.println("2545");
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
             stmt = connection.con.prepareStatement("update pointtable set PLAYED=?,WINS=?,LOSE=?,TIE=?,POINTS=? where LEAGUE=? and TEAMS=?");
            stmt.setInt(1, played);
            stmt.setInt(2, wins);
            stmt.setInt(3, lose);
            stmt.setInt(4, tie);
            stmt.setInt(5, point);
            stmt.setString(6, league);
            stmt.setString(7, team);
            stmt.executeUpdate();
            System.out.println("2645");
            }
            catch(Exception ee){System.out.println(ee);}
     
      }
}