package YO_BANK;

import java.sql.*;
import java.util.*;


public class Banking_Main {
	 
	 
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	 static final String DB_URL = "jdbc:mysql://localhost:3306/yo_bank"; 
	 
	 static final String USER = "root"; 
	 static final String PASS = "uproot";
	 
	 static int count=0;
	 
	 public static void main(String[] args){
		 
		 try
		 {

		 Connection conn = null; 
		 PreparedStatement stmt = null; 
		 ResultSet rs=null;
		 
		 Scanner sc=new Scanner(System.in);
		 String empid="",empidproof="",empidproofchk="",pass="",passchk="";
		 boolean b=false,bo=false;
		 int ch=0,ch2=0;
		 
		
		 
		 Class.forName(JDBC_DRIVER); 
		 conn = DriverManager.getConnection(DB_URL,USER,PASS); 
		 
		 System.out.println("****EMPLOYEE LOGIN****");
		 do
		 {
			 Banking_Main.count++;
		 System.out.println("Enter your EmpID : ");
		 empid=sc.next();
		 stmt = conn.prepareStatement("select * from employee where empid=?");
		 stmt.setString(1,empid);
		 rs=stmt.executeQuery();
		 if(Banking_Main.count==3)
			{
				System.out.println("Exceeded maximum try!!\nAborting!!!");
				System.exit(0);
			}
		 if(rs.next()!=true)
		 {
			 System.out.println("\nID NOT FOUND.Enter a valid ID.");
		 }
		 else
		 {
		 bo=true;
		 }
		 
	     }while(bo!=true);
		 		 
		 
		 
		 Banking_Main.count=0;
		 do
		 {
			 Banking_Main.count++;
		 System.out.println("Enter your ID_Proof : ");
		 empidproofchk=sc.next();
		 System.out.println("Enter Password : ");
		 passchk=sc.next();
		 		 
	      
	    	 empidproof = rs.getString("empidproof"); 
	    	 pass = rs.getString("pass"); 
	     				 
	    	if(empidproof.equals(empidproofchk) && pass.equals(passchk))
	    	{
	    		System.out.print("*WELCOME to YO_BANK*");
	    		//Banking_Main.count=5;
	    		b=true;
	    	}
	    	else
	    	{
	    		System.out.println("Either password or id_proof is incorrect.");
	    	}
		 }while(Banking_Main.count!=3 && b!=true);
		 
		if(Banking_Main.count==3)
		{
			System.out.println("Exceeded maximum try!!\nAborting!!!");
			System.exit(0);
		}
	
		 do{
			 System.out.println("\n\n***Main Menu***");
			 System.out.println("1.Open an Account.");
			 System.out.println("2.Update Information.");
			 System.out.println("3.Close an Account.");
			 System.out.println("4.View details in a Group .");
			 System.out.println("5.View details Individually.");
			 System.out.println("0.Exit.");
			 System.out.println("Enter your choice : ");
			 ch=sc.nextInt();
			 
			 switch(ch)
			 {
			 case 0:
				 System.out.println("EXITING!!");
				 System.exit(0);
				 break;
			 case 1:
				        new Open_Account();
				        break;
				        
			 case 2:    
				 
				 do
				 {
					 System.out.println("\n1.Update an Account by AccountID.");
					 System.out.println("2.Update an AccountHolders' Information by CustomerID.");
					 System.out.println("0.Back to Previous Menu.");
					 System.out.println("Enter your choice : ");
					 ch2=sc.nextInt();
					 switch(ch2)
					 {
					 case 0:
						 	break;
					 case 1:
						 	new Update_Account();
						 	break;
					 case 2:
						    new Update_Cust();
						    break;
				     default:
				    	 	System.out.println("Invalid Input!");
					 }
				 }while(ch2!=0);
				 
				 break;
				        
			 case 3:    
				  		new Delete_Account();
				  		break;
				  		
			 case 4:
				 do
				 {
					 System.out.println("\n1.View the details of all Available Accounts.");
					 System.out.println("2.View the details of all AccountHolders.");
					 System.out.println("0.Back to Previous Menu.");
					 System.out.println("Enter your choice : ");
					 ch2=sc.nextInt();
					 switch(ch2)
					 {
					 case 0:
						 	break;
					 case 1:
						 	new View_All_Accounts();
						 	break;
					 case 2:
						    new View_All_Custs();
						    break;
				     default:
				    	 	System.out.println("Invalid Input!");
					 }
				 }while(ch2!=0);
				 
				 		break;
				  
			 case 5:
				 do
				 {
					 System.out.println("\n1.View the details of an Account by AccountID.");
					 System.out.println("2.View the details of a Customer by  CustomerID.");
					 System.out.println("0.Back to Previous Menu.");
					 System.out.println("Enter your choice : ");
					 ch2=sc.nextInt();
					 switch(ch2)
					 {
					 case 0:
						 	break;
					 case 1:
						 	new View_An_Account();
						 	break;
					 case 2:
						    new View_A_Cust();
						    break;
				     default:
				    	 	System.out.println("Invalid Input!");
					 }
				 }while(ch2!=0);
			
				         break;
			
			 default:
				   System.out.println("Invalid Input!!");
			 	 
			 }
			 	 
		 }while(ch!=0);
		 	 
	 rs.close(); 
	 stmt.close(); 
	 conn.close(); 
		 
		 }
	  
	 catch(SQLException se)
	 { 
	  se.printStackTrace(); 
	 }
		 
	 catch(Exception e)
	 { 
	 e.printStackTrace(); 
	 }
	 
	finally
	{ 
	   System.out.println("Goodbye!"); 
	}
		 
	}
	

}
