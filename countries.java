package cricin;
import java.sql.*;
import cricin.connection;
public class countries {
    int i=0,n=0;
    String teamsare[];
    PreparedStatement stmt;
   // connection c;
    ResultSet rs;
     countries()
     {
         try{
        	 //System.out.println("Hello1");
        	String sql="select count(*) from teams"; 
        	//System.out.println(sql);
          stmt=connection.con.prepareStatement(sql);
          //System.out.println("Hello2");
            rs=stmt.executeQuery();
            //System.out.println("Hello");
            while(rs.next())
            n=rs.getInt("count(*)");
            //System.out.println(n);
         teamsare=new String[n+1];
         //System.out.println("Hello1");
         teamsare[0]="choose team";
         //System.out.println("Hello1");
         i=0;
             stmt=connection.con.prepareStatement("select * from teams");
             //System.out.println("Hello1");
             rs=stmt.executeQuery();
             //System.out.println("Hello1");
             while(rs.next())
             {
                 teamsare[++i]=rs.getString(1);
                 //System.out.println(teamsare[i]);
                 
             }
         }
         catch(Exception ee){System.out.println(ee);
         System.out.println("No teams found");}
     }
    
}
