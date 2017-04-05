package YO_BANK;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Open_Account {
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
	 BufferedReader br;
	 String name,sex,idtype,idproof,custid,flatno,blockno,sector,pincode,city,district,state,accid,acctype,accname;
	 
	 float opbal,totalbal;
	 int ch,age,i,f,mf;
	 boolean b,bo,ol;
	 Integer rand;
	 Random r;
	 
	 public Open_Account() throws SQLException,Exception
{
		 
		 
		 conn = null; 
		 stmt = null; 
		 rs=null;
		 
		 c=Calendar.getInstance();
		 d=new java.sql.Date(c.getTime().getTime());
		 sc=new Scanner(System.in);
		 br=new BufferedReader(new InputStreamReader(System.in));
		 name="";sex="";idtype="";idproof="";custid="";flatno="";blockno="";sector="";pincode="";city="";district="";state="";accid="";acctype="";accname="";
		 
		 opbal=0;totalbal=0;
		 ch=0;age=0;i=0;f=0;mf=0;
		 b=false;bo=false;ol=false;
		 r=new Random(System.nanoTime());
		 
		 Class.forName(JDBC_DRIVER); 
		 conn = DriverManager.getConnection(DB_URL,USER,PASS);
		 
		 do
		 {
	     f=0;
		 System.out.println("\nAccount Opening :- ");
		 System.out.println("1.New Customer\n2.Existing Customer\n0.Back to Previous Menu");				 
		 System.out.println("Enter your choice : ");
		 i=sc.nextInt();
		 switch(i)
		 {
		 case 0: 
			 	f=1;
		 		
			 	break;
		 case 1: 
			 
			 	f=1;

					do
		 {
	     f=0;
		 System.out.println("\nAvailable Account types : ");
		 System.out.println("1.Saving\n2.Current\n3.Fixed\n4.Senior Citizen\n5.Young Stars");				 
		 System.out.println("Enter the type of Account to be opened(1 to 5) : ");
		 i=sc.nextInt();
		 switch(i)
		 {
		 case 1: f=1;acctype="SAV";accname="SAVING_ACCOUNT";break;
		 case 2: f=1;acctype="CUR";accname="CURRENT_ACCOUNT";break;
		 case 3: f=1;acctype="FXD";accname="FIXED_ACCOUNT";break;
		 case 4: f=1;acctype="SNR";accname="SENIOR_CITIZEN_ACCOUNT";break;
		 case 5: f=1;acctype="YNG";accname="YOUNG_STAR_ACCOUNT";break;
		 default:f=0;System.out.println("Invalid Input!!");
		 }
		 }while(f==0);
		 
		 
		 
	 System.out.println("Enter  Name : ");
	 name=br.readLine();
	 do
	 {
		 f=0;
	 System.out.println("Enter  Age : ");
	 age=sc.nextInt();
	 
	 if(acctype=="YNG" && age>17)
	 {
		 System.out.println("Age should be less than 18 years.");
		 f=0;
	 }
	 else if(acctype=="SNR" && age<60)
	 {
		 System.out.println("Age should be greater than or equal to 60 years.");
		 f=0;
	 }
	 else if(age<1 || age>100)
	 {
		 System.out.println("Age must be in range of 1-100 years.");
		 f=0;
	 }
	 else 
	 {
		 f=1;
	 }
	 }while(f==0);
	 
	 System.out.println("Enter  Sex : ");
	 sex=br.readLine();
	 do
	 {
		 ol=false;
	 System.out.println("Enter Customer ID : ");
	 custid=br.readLine();;
	 stmt = conn.prepareStatement("select * from customer where custid=?");
	 stmt.setString(1,custid.toUpperCase()); 
	 rs=stmt.executeQuery();
	 if(rs.next()==true)
	 {
		 System.out.println("ID not available.\nDuplicate entry for ID "+custid+" not allowed.");
	 }
	 else if(rs.next()==false)
	 {
		 ol=true;
	 }
	 }while(ol==false);
	 do
	 {
		 f=0;
	 System.out.println("Available ID types : ");
	 System.out.println("\t1.PanCard\t2.Voter ID\t3.UID\t4.Passport.");
	 System.out.println("Enter  ID_type(1 to 4) : ");
	 i=sc.nextInt();
	 switch(i)
	 {
	 case 1: f=1;idtype="PanCard";break;
	 case 2: f=1;idtype="Voter ID";break;
	 case 3: f=1;idtype="UID";break;
	 case 4: f=1;idtype="Passport";break;
	 default:System.out.println("Invalid Input!!");
	 }
	 }while(f==0);
	 
	 System.out.println("Enter  ID_proof No. : ");
	 idproof=br.readLine();
	 System.out.println("Enter  Flat_No : ");
	 flatno=br.readLine();
	 System.out.println("Enter  Block_No : ");
	 blockno=br.readLine();
	 System.out.println("Enter  Sector : ");
	 sector=br.readLine();
	 System.out.println("Enter  PinCode : ");
	 pincode=br.readLine();
	 System.out.println("Enter  City/Area : ");
	 city=br.readLine();
	 System.out.println("Enter  District : ");
	 district=br.readLine();
	 System.out.println("Enter  State : ");
     state=br.readLine();	
	 				 
	 boolean bk=false;
	 do
	 {
		 System.out.println("Input Opening Balance : ");
		 opbal=sc.nextFloat();
		 
		 if(acctype=="YNG" && opbal<500)
	 {
		 System.out.println("Minimum_opening_balance of Young_Star_Account is Rs.500");
	 }
		 else if(acctype=="SNR" && opbal<1500)
		 {
			 System.out.println("Minimum_opening_balance of Senior_Citizen_Account is Rs.1500");
		 }
		 else if(acctype=="SAV" && opbal<2000)
		 {
			 System.out.println("Minimum_opening_balance of Saving_Account is Rs.2000");
		 }
		 else if(acctype=="CUR" && opbal<5000)
		 {
			 System.out.println("Minimum_opening_balance of Current_Account is Rs.5000");
		 }
		 else if(acctype=="FXD" && opbal<10000)
		 {
			 System.out.println("Minimum_opening_balance of Fixed_Account is Rs.10000");
		 }
		 else
		 {
			bk=true; 
		 }
	 }while(bk==false);
	 
	 
	 totalbal=opbal;
	 
	 rand=new Integer(r.nextInt(99999));
	 if(rand<0)
	 {
		 rand=(-rand);
		 accid=(rand.toString());
	 }
	 else
	 {
		 accid=(rand.toString());
	 }
	 
	 				 
	 stmt = conn.prepareStatement("insert into account values(?,?,?,?)");
	 stmt.setString(1,null);
	 stmt.setString(2,accid.toUpperCase());
	 stmt.setString(3,custid.toUpperCase());
	 stmt.setString(4,accname.toUpperCase());
	 stmt.executeUpdate();
	 
	 stmt=conn.prepareStatement("insert into customer values(?,?,?,?,?,?)");
	 stmt.setString(1,name.toUpperCase());
	 stmt.setInt(2,age);
	 stmt.setString(3,sex.toUpperCase());
	 stmt.setString(4,idtype.toUpperCase());
	 stmt.setString(5,idproof.toUpperCase());
	 stmt.setString(6,custid.toUpperCase());
	 stmt.executeUpdate();
	 
	 stmt=conn.prepareStatement("insert into address values(?,?,?,?,?,?,?,?)");
	 stmt.setString(1,custid.toUpperCase());
	 stmt.setString(2,flatno.toUpperCase());
	 stmt.setString(3,blockno.toString());
	 stmt.setString(4,sector.toUpperCase());
	 stmt.setString(5,pincode.toUpperCase());
	 stmt.setString(6,city.toUpperCase());
	 stmt.setString(7,district.toUpperCase());
	 stmt.setString(8,state.toUpperCase());
	 stmt.executeUpdate();
	 
	 stmt=conn.prepareStatement("insert into accavailable values(?,?,?,?,?)");
	 stmt.setString(1,accid.toUpperCase());
	 stmt.setString(2,acctype.toUpperCase());
	 stmt.setDate(3,d);
	 stmt.setFloat(4,opbal);
	 stmt.setFloat(5,totalbal);
	 stmt.executeUpdate();
	
	 System.out.println("Account Successfully Opened!");
	 break;
		 		
		 case 2: 
			 
			 f=1;
			 
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
					 System.out.println("Record Not Found.\nEnter a valid ID.");
				 }
				 else
				 {
				  mf=1;
				 }
				 
				 }while(mf==0);

				f=1;

					do
		 {
	     f=0;
		 System.out.println("\nAvailable Account types : ");
		 System.out.println("1.Saving\n2.Current\n3.Fixed\n4.Senior Citizen\n5.Young Stars");				 
		 System.out.println("Enter the type of Account to be opened(1 to 5) : ");
		 i=sc.nextInt();
		 switch(i)
		 {
		 case 1: f=1;acctype="SAV";accname="SAVING_ACCOUNT";break;
		 case 2: f=1;acctype="CUR";accname="CURRENT_ACCOUNT";break;
		 case 3: f=1;acctype="FXD";accname="FIXED_ACCOUNT";break;
		 case 4: f=1;acctype="SNR";accname="SENIOR_CITIZEN_ACCOUNT";break;
		 case 5: f=1;acctype="YNG";accname="YOUNG_STAR_ACCOUNT";break;
		 default:f=0;System.out.println("Invalid Input!!");
		 }
		 }while(f==0);

		boolean bk2=false;
	 do
	 {
		 System.out.println("Input Opening Balance : ");
		 opbal=sc.nextFloat();
		 
		 if(acctype=="YNG" && opbal<500)
	 {
		 System.out.println("Minimum_opening_balance of Young_Star_Account is Rs.500");
	 }
		 else if(acctype=="SNR" && opbal<1500)
		 {
			 System.out.println("Minimum_opening_balance of Senior_Citizen_Account is Rs.1500");
		 }
		 else if(acctype=="SAV" && opbal<2000)
		 {
			 System.out.println("Minimum_opening_balance of Saving_Account is Rs.2000");
		 }
		 else if(acctype=="CUR" && opbal<5000)
		 {
			 System.out.println("Minimum_opening_balance of Current_Account is Rs.5000");
		 }
		 else if(acctype=="FXD" && opbal<10000)
		 {
			 System.out.println("Minimum_opening_balance of Fixed_Account is Rs.10000");
		 }
		 else
		 {
			bk2=true; 
		 }
	 }while(bk2==false);
	 
	 
	 totalbal=opbal;
	 
	 rand=new Integer(r.nextInt(99999));
	 if(rand<0)
	 {
		 rand=(-rand);
		 accid=(rand.toString());
	 }
	 else
	 {
		 accid=(rand.toString());
	 }
	 
	 				 
	 stmt = conn.prepareStatement("insert into account values(?,?,?,?)");
	 stmt.setString(1,null);
	 stmt.setString(2,accid.toUpperCase());
	 stmt.setString(3,custid.toUpperCase());
	 stmt.setString(4,accname.toUpperCase());
	 stmt.executeUpdate();
						
		stmt=conn.prepareStatement("insert into accavailable values(?,?,?,?,?)");
	 stmt.setString(1,accid.toUpperCase());
	 stmt.setString(2,acctype.toUpperCase());
	 stmt.setDate(3,d);
	 stmt.setFloat(4,opbal);
	 stmt.setFloat(5,totalbal);
	 stmt.executeUpdate();			
	
	 System.out.println("Account Successfully Opened!");
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
