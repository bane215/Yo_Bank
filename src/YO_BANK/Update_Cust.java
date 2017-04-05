package YO_BANK;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Update_Cust {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	 static final String DB_URL = "jdbc:mysql://localhost:3306/yo_bank"; 
	 
	 static final String USER = "root"; 
	 static final String PASS = "uproot";

	 
	 Connection conn; 
	 PreparedStatement stmt; 
	 ResultSet rs;
	 
	 Scanner sc;
	 BufferedReader br;
	 String name,sex,idtype,idproof,custid,flatno,blockno,sector,pincode,city,district,state,accid,acctype,accname;
	 
	 float opbal,totalbal;
	 int age,mf;
	 
	 
	 public Update_Cust() throws SQLException,Exception
{
		 
		 
		 conn = null; 
		 stmt = null; 
		 rs=null;
		 
		 sc=new Scanner(System.in);
		 br=new BufferedReader(new InputStreamReader(System.in));
		 name="";sex="";idtype="";idproof="";custid="";flatno="";blockno="";sector="";pincode="";city="";district="";state="";accid="";acctype="";accname="";
		 		 
		 Class.forName(JDBC_DRIVER); 
		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 		 
		 rs=null;
		 do
		 {
			 mf=0;
		 System.out.println("Enter CustomerID : ");
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
		 mf=1;
		 }
		 }while(mf==0);
		 custid=rs.getString("custid");
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
		 System.out.println("Particulars of the customer with the given AccountID are : ");
	    	 System.out.println("Name : " + name);
	    	 System.out.println("Age : "+age);
	    	 System.out.println("Sex : "+sex);
	    	 System.out.println(idtype.toUpperCase()+" : " + idproof); 
	    	 System.out.println("Flat_No. : " + flatno);
	    	 System.out.println("Block_No. : " + blockno);
	    	 System.out.println("Sector : " + sector);
	    	 System.out.println("Pincode : "+pincode);
	    	 System.out.println("City : " + city);
	    	 System.out.println("District : "+district);
	    	 System.out.println("State : "+state);		    	 
	     
	     System.out.println("Enter Name to update : ");
		 name=br.readLine();
		 System.out.println("Enter Flat number to update : ");
		 flatno=br.readLine();
		 System.out.println("Enter Block number to update : ");
		 blockno=br.readLine();
		 System.out.println("Enter Sector to update : ");
		 sector=br.readLine();
		 System.out.println("Enter Pincode to update : ");
		 pincode=br.readLine();
		 System.out.println("Enter City to update : ");
		 city=br.readLine();
		 System.out.println("Enter District to update : ");
		 district=br.readLine();
		 System.out.println("Enter State to update : ");
		 state=br.readLine();
		 				 
		 stmt = conn.prepareStatement("update address set flatno=?,blockno=?,sector=?,pincode=?,city=?,district=?,state=? where custid=?");
		 stmt.setString(1,flatno.toUpperCase());
		 stmt.setString(2,blockno.toUpperCase());
		 stmt.setString(3,sector.toUpperCase());
		 stmt.setString(4,pincode);
		 stmt.setString(5,city.toUpperCase());
		 stmt.setString(6,district.toUpperCase());
		 stmt.setString(7,state.toUpperCase());
		 stmt.setString(8,custid.toUpperCase());
		 stmt.executeUpdate();
		 
		 stmt = conn.prepareStatement("update customer set name=? where custid=?");
		 stmt.setString(1,name.toUpperCase());
		 stmt.setString(2,custid.toUpperCase());
		 stmt.executeUpdate();
		 
		 			 
		 System.out.println("Record updated SUCCESSFULLY!");
		 
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
