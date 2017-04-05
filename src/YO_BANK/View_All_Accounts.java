package YO_BANK;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class View_All_Accounts {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	 static final String DB_URL = "jdbc:mysql://localhost:3306/yo_bank"; 
	 
	 static final String USER = "root"; 
	 static final String PASS = "uproot";

	 
	 Connection conn; 
	 PreparedStatement stmt; 
	 ResultSet rs;
	 Scanner sc; 
     ArrayList<String> al;
	 String name,custid,city,accid,accname;
	 Date date;
	 
	 boolean b,b2;
	 float totalbal;
	 int age,c;
	 
	 
	 
	 public View_All_Accounts() throws SQLException,Exception
{
		 
		 
		 conn = null; 
		 stmt = null; 
		 rs=null;
		 sc=new Scanner(System.in);
		 al=new ArrayList<String>();
		 name="";custid="";city="";accid="";accname="";
		 
		 totalbal=0;
		 age=0;
		 b2=true;
		 
		 Class.forName(JDBC_DRIVER); 
		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 rs=null;
		 System.out.println("\nDisplaying account details of all the Available Accounts  :- \n");
		 int tot_acc=0;
		 stmt = conn.prepareStatement("select counter from account");
	     rs=stmt.executeQuery();
	     rs.last();
	     rs.getRow();
	     int mn=rs.getInt("counter");
	    
	     
         do
	     {
        	 b=false;c=0; 
	      for(int nt=1;nt<=mn;nt++)
	      {
	    	  stmt = conn.prepareStatement("select * from account where counter=?");
			  stmt.setInt(1,nt);
			  rs=stmt.executeQuery();
	    	  
	     if(rs.next()!=false)
	     {
	    	accid=rs.getString("accid");
	    	custid=rs.getString("custid");
	    	accname=rs.getString("accname");
	    	tot_acc++;
	    	al.add(accid);
	    	for(String st:al)
	    	{
	    	if(accid.equals(st)==true)
	    	{
	    		b2=false;
	    		break;
	    	}
	    	}
	     }
	      else
	      {
	    	  continue;
	      }
	  
	    	  stmt = conn.prepareStatement("select * from customer where custid=?");
	    	  stmt.setString(1,custid);
			  rs=stmt.executeQuery();
			  if(rs.next()!=false)
	    	  {
			  name=rs.getString(1);
	    	  age=rs.getInt("age");
	    	  }
	    	  stmt = conn.prepareStatement("select * from address where custid=?");
	    	  stmt.setString(1,custid);   
	    	  rs=stmt.executeQuery();
	    	  if(rs.next()!=false)
	    	  {
	    	  city=rs.getString("city");
	    	  }
	    	  stmt = conn.prepareStatement("select * from accavailable where accid=?");
	    	  stmt.setString(1,accid);   
	    	  rs=stmt.executeQuery();
	    	  if(rs.next()!=false)
	    	  {
	    	  totalbal=rs.getFloat("totalbal");
	    	  date=rs.getDate("aod");
	    	  }
	    	
	    		     
	     System.out.println("\nDisplaying details of Account Number "+tot_acc+" : ");
	     
	     System.out.println("Name : "+name.toUpperCase());
	     System.out.println("Age: "+age);
	     System.out.println("City : "+city.toUpperCase());
	     System.out.println("AccountID : "+accid.toUpperCase());
	     System.out.println("Account_type : "+accname.toUpperCase());
	     System.out.println("Account Opening Date : "+date);
	     System.out.println("Total Balance : "+totalbal);
	     do
	     {
	     System.out.println("PRESS 0 to EXIT to previous menu or 1 to CONTINUE..");			     
	     c=sc.nextInt();
	     if(c==1)
	     {
	    	 b=true;
	    	 break;
	     }
	     else if(c==0)
	     {
	    	 b=false;
	    	 break;
	     }
	     else
	     {
	    	 System.out.println("Invalid Input!!");
	     }
	     }while((c!=1) || (c!=0));
	      
	     if(b==false)
	     {break;}
	     }
	     }while((b==true) && (b2==true));
	     
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