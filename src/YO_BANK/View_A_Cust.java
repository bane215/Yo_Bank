package YO_BANK;
import java.sql.*;
import java.util.*;

public class View_A_Cust {



	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	 static final String DB_URL = "jdbc:mysql://localhost:3306/yo_bank"; 
	 
	 static final String USER = "root"; 
	 static final String PASS = "uproot";

	 
	 Connection conn; 
	 PreparedStatement stmt; 
	 ResultSet rs;
	 
	 Scanner sc;
	 String name,sex,idtype,idproof,custid,flatno,blockno,sector,pincode,city,district,state,accid,acctype,accname;
	 
	 
	 int age,mf,f,i;
	 
	 	 
	 public View_A_Cust() throws SQLException,Exception
{
		 
		 
		 conn = null; 
		 stmt = null; 
		 rs=null;
		 
		 sc=new Scanner(System.in);
		 name="";sex="";idtype="";idproof="";custid="";flatno="";blockno="";sector="";pincode="";city="";district="";state="";accid="";acctype="";accname="";
		 
		 
		 age=0;mf=0;

		 
		 Class.forName(JDBC_DRIVER); 
		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 rs=null;
		 
		 do
		 {
	     f=0;
		 System.out.println("1.Press 1 to Enter CustID : \n0.Back to Previous Menu");				 
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
		 System.out.println("\nEnter CustID :");
		 custid=sc.next();
		 stmt = conn.prepareStatement("select * from customer where custid=?");
		 stmt.setString(1,custid);
		 rs=stmt.executeQuery();
		 if(rs.next()==false)
		 {
			 System.out.println("No Record Found.\nEnter a valid ID.");
		 }
		 else
		 {
		 custid=rs.getString("custid");
		 mf=1;
		 }
		 }while(mf==0);
		 stmt = conn.prepareStatement("select * from customer where custid=?");
		 stmt.setString(1,custid);
		 rs=stmt.executeQuery();
	
		 while(rs.next()){ 
	    	 name = rs.getString("name"); 
	    	 age = rs.getInt("age"); 
	    	 sex = rs.getString("sex"); 
	    	 idtype = rs.getString("idtype");
	    	 idproof=rs.getString("idproof");
	     }
	     stmt = conn.prepareStatement("select * from address where custid=?");
		 stmt.setString(1,custid);
		 rs=stmt.executeQuery();
		 while(rs.next()){ 
	    	 flatno = rs.getString("flatno"); 
	    	 blockno = rs.getString("blockno"); 
	    	 sector = rs.getString("sector"); 
	    	 pincode = rs.getString("pincode");
	    	 city=rs.getString("city");
	    	 district=rs.getString("district");
	         state=rs.getString("state");
	     }
		 System.out.println("\nParticulars of the customer with the given AccountID are : ");
	    	 System.out.println("\nName : " + name.toUpperCase());
	    	 System.out.println("Age : "+age);
	    	 System.out.println("Sex : "+sex.toUpperCase());
	    	 System.out.println(idtype.toUpperCase()+" : " + idproof); 
	    	 System.out.println("Flat_No. : " + flatno.toUpperCase());
	    	 System.out.println("Block_No. : " + blockno.toUpperCase());
	    	 System.out.println("Sector : " + sector.toUpperCase());
	    	 System.out.println("Pincode : "+pincode);
	    	 System.out.println("City : " + city.toUpperCase());
	    	 System.out.println("District : "+district.toUpperCase());
	    	 System.out.println("State : "+state.toUpperCase());		    	 
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
