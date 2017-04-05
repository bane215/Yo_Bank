package YO_BANK;

import java.sql.*;
import java.util.*;

public class Update_Account {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	 static final String DB_URL = "jdbc:mysql://localhost:3306/yo_bank"; 
	 
	 static final String USER = "root"; 
	 static final String PASS = "uproot";

	 
	 Connection conn; 
	 PreparedStatement stmt; 
	 ResultSet rs;
	 
	 Calendar c;
	 java.sql.Date d;
	 Scanner sc;
	 String accid,acctype;
	 
	 float opbal,totalbal,wamt,damt;
	 int f,mf,i;
	 
	 
	 public Update_Account() throws SQLException,Exception
{
		 
		 
		 conn = null; 
		 stmt = null; 
		 rs=null;
		 
		   c=Calendar.getInstance();
		   d=new java.sql.Date(c.getTime().getTime());
		   sc=new Scanner(System.in);
		   accid="";acctype="";
		 
		   opbal=0;totalbal=0;wamt=0;damt=0;
		   i=0;f=0;mf=0;
		 
		 
		 Class.forName(JDBC_DRIVER); 
		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 do
		 {
	     f=0;
		 System.out.println("\nBanking Transactions :- ");
		 System.out.println("1.Deposit\n2.Withdrawal\n0.Back to Previous Menu");				 
		 System.out.println("Enter your choice : ");
		 i=sc.nextInt();
		 switch(i)
		 {
		 case 0: 
			 	f=1;
		 		
			 	break;
		 case 1: 
			 
			 	f=1;

			 	rs=null;
				 do
				 {
					 mf=0;
				 System.out.println("Enter AccountID : ");
				 accid=sc.next();
				 stmt = conn.prepareStatement("select * from account where accid=?");
				 stmt.setString(1,accid);
				 rs=stmt.executeQuery();
				 
				 if(rs.next()==false)
				 {
					 System.out.println("Record Not Found.\nEnter a valid ID.");
				 }
				 else
				 {
				  mf=1;
				 }
				 
				 }while(mf==0);
				 
				 stmt = conn.prepareStatement("select * from accavailable where accid=?");
				 stmt.setString(1,accid);
				 rs=stmt.executeQuery();
				 if(rs.next()!=false)
				 {
				 totalbal=rs.getFloat("totalbal");
				 acctype=rs.getString("acctype");
				 }
				 System.out.println("The current account balance is : Rs."+totalbal);
				 do
				 {
				 System.out.println("Enter amount to be deposited : ");
				 damt=(sc.nextFloat());
				 
				 if(acctype=="FXD" && ((damt<5000) || (damt%5000!=0)) )
				 {
					 System.out.println("Minimum deposit amount in Fixed_Account should be a multiple of Rs.5000.");
				 }
				 else if(damt<500)
				 {
					 System.out.println("Minimum deposit amount is Rs.500!");
				 }
				 }while(damt<500);
				 
				 			 
				 stmt=conn.prepareStatement("insert into deposit values(?,?,?)");
				 stmt.setString(1,accid);
				 stmt.setFloat(2,damt);
				 stmt.setDate(3,d);
				 stmt.executeUpdate();
				 
				  
				 totalbal+=damt;
				 stmt=conn.prepareStatement("update accavailable set totalbal=? where accid=?");
				 stmt.setFloat(1,totalbal);
				 stmt.setString(2,accid);
				 stmt.executeUpdate();
				 
				 System.out.println("Deposit successful!");
				 System.out.println("New Balance is : Rs."+totalbal);
						 	
		 		break;
		 		
		 case 2: 
			 
			 f=1;
			 rs=null;
		 do
		 {
			 mf=0;
		 System.out.println("Enter AccountID : ");
		 accid=sc.next();
		 stmt = conn.prepareStatement("select * from account where accid=?");
		 stmt.setString(1,accid);
		 rs=stmt.executeQuery();
		 
		 if(rs.next()==false)
		 {
			 System.out.println("Record Not Found.\nEnter a valid ID.");
		 }
		 else
		 {
		  mf=1;
		 }
		 
		 }while(mf==0);
		 
		 
		 stmt = conn.prepareStatement("select * from accavailable where accid=?");
		 stmt.setString(1,accid);
		 rs=stmt.executeQuery();
		 if(rs.next()!=false)
		 {
		 totalbal=rs.getFloat("totalbal");
		 acctype=rs.getString("acctype");
		 }
		 System.out.println("The current account balance is : Rs."+totalbal);
		 
		 
		 boolean bz=false;
		 do
		 {
		 System.out.println("Enter amount to be withdrawn : ");
		 wamt=sc.nextFloat();
		 
		 if(acctype=="SAV" && ((wamt<100) || ((totalbal-wamt)<0)) )
		 {
			 System.out.println("Minimum withdrawal amount in Saving_Account is Rs.100 & minimum account balance is Rs.0 .");
		 }
		 else if(acctype=="SNR" && ((wamt<100) || ((totalbal-wamt)<0)) )
		 {
			 System.out.println("Minimum withdrawal amount in Senior_Citizen_Account is Rs.100 & minimum account balance is Rs.0 .");
		 }
		 else if(acctype=="FXD" && ((totalbal-wamt)!=0))
		 {
			 System.out.println("Partial withdrawal from Fixed_Account not allowed.");
		     System.out.println("Total available balance : "+totalbal);
		 }
		 else if(acctype=="CUR" && ((totalbal-wamt)<(-10000)))
		 {
			 System.out.println("\nOverdraft of more than Rs.10000 is not possible.");
		 }
		 else if(acctype=="YNG" && ((wamt<100) || ((totalbal-wamt)<500)))
		 {
			 System.out.println("Minimum withdrawal amount in Young_Star_Account is Rs.100 & minimum account balance is Rs.500 .");
		 }
		 else
		 {
			 bz=true;
		 }
		 }while(bz==false);
		 
		 			 
		 stmt=conn.prepareStatement("insert into withdraw values(?,?,?)");
		 stmt.setString(1,accid);
		 stmt.setFloat(2,wamt);
		 stmt.setDate(3,d);
		 stmt.executeUpdate();
		 
		  
		 totalbal-=wamt;
		 stmt=conn.prepareStatement("update accavailable set totalbal=? where accid=?");
		 stmt.setFloat(1,totalbal);
		 stmt.setString(2,accid);
		 stmt.executeUpdate();
		 
		 System.out.println("Withdrawal successful!");
		 System.out.println("New Balance is : Rs."+totalbal);
		
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
	 catch(SQLException s)
	 {
		 
	 }
	 catch(Exception e)
	 {
		 
	 }
}
}
