package cricin;
import java.sql.*;

public class connection {
  public static  Connection con;
  public  void connect()
  {
       try {
    	  
         Class.forName("com.mysql.jdbc.Driver");
         
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket", "root", "sandeep");
            
       }
       
       catch(Exception ee){System.out.println(ee);
      }
  }
}
