package YO_BANK;
import java.sql.*;
import java.util.*;

public class View_An_Account {



	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	 static final String DB_URL = "jdbc:mysql://localhost:3306/yo_bank"; 
	 
	 static final String USER = "root"; 
	 static final String PASS = "uproot";

	 Calendar c;
	 java.sql.Date d;
	 
	 Connection conn; 
	 PreparedStatement stmt; 
	 ResultSet rs;
	 
	 Scanner sc;
	 String name,custid,city,district,state,accid,accname;
	 
	 
	 int age,mf,f,i;
	 float opbal,totalbal;
	 	 
	 public View_An_Account() throws SQLException,Exception
{
		 
		 
		 conn = null; 
		 stmt = null; 
		 rs=null;
		 
		 sc=new Scanner(System.in);
		 name="";custid="";city="";district="";state="";accid="";accname="";
		 
		 c=Calendar.getInstance();
		 d=new java.sql.Date(c.getTime().getTime());
		   
		 age=0;mf=0;

		 
		 Class.forName(JDBC_DRIVER); 
		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 rs=null;
		 
		 do
		 {
	     f=0;
		 System.out.println("1.Press 1 to Enter AccountID : \n0.Back to Previous Menu");				 
		 System.out.println("Enter your choice : ");
		 i=sc.nextInt();
		 switch(i)
		 {
		 case 0: 
			 	f=1;
		 		
					

			 	break;
		 case 1: 
			 

				do
		 {
			 mf=0;
		 System.out.println("\nEnter AccountID :");
		 accid=sc.next();
		 stmt = conn.prepareStatement("select * from account where accid=?");
		 stmt.setString(1,accid);
		 rs=stmt.executeQuery();
		 if(rs.next()==false)
		 {
			 System.out.println("No Record Found.\nEnter a valid ID.");
		 }
		 else
		 {
		 custid=rs.getString("custid");
		 accname=rs.getString("accname");
		 mf=1;
		 }
		 }while(mf==0);
		 stmt = conn.prepareStatement("select * from customer where custid=?");
		 stmt.setString(1,custid);
		 rs=stmt.executeQuery();
	
		 while(rs.next()){ 
	    	 name = rs.getString("name"); 
	    	 	     }
	     stmt = conn.prepareStatement("select * from address where custid=?");
		 stmt.setString(1,custid);
		 rs=stmt.executeQuery();
		 while(rs.next()){ 
	    	 city=rs.getString("city");
	    	 district=rs.getString("district");
	         state=rs.getString("state");
	     }
		 
		 stmt = conn.prepareStatement("select * from accavailable where accid=?");
		 stmt.setString(1,accid);
		 rs=stmt.executeQuery();
		 while(rs.next()){ 
	    	 d=rs.getDate("aod");
	    	 opbal=rs.getFloat("opbal");
	         totalbal=rs.getFloat("totalbal");
	     }
		 
		 System.out.println("\nParticulars of the customer with the given AccountID are : ");
	    	 System.out.println("\nName : " + name.toUpperCase());
	    	 System.out.println("City : " + city.toUpperCase());
	    	 System.out.println("District : "+district.toUpperCase());
	    	 System.out.println("State : "+state.toUpperCase());
	    	 System.out.println("\nAccount Type : "+accname);
	    	 System.out.println("Account Opening Date : "+d);
	    	 System.out.println("Opening Balance : "+opbal);
	    	 System.out.println("Current Balance : "+totalbal);
	    	 System.out.println();
				     
	     
					 	
		 		break;
			 
			 	
		 default:f=0;System.out.println("Invalid Input!!");
		 }
		 }while(f==0);	
	 
	     try
		 {
		 rs.close(); 
		 stmt.close(); 
		 conn.close();
		 }
		 catch(SQLException sq)
		 {
			 
		 }
		 catch(Exception e)
		 {
			 
		 }

}
}
