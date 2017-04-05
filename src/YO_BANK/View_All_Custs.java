package YO_BANK;

import java.sql.*; 

public class View_All_Custs {

static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
static final String DB_URL = "jdbc:mysql://localhost:3306/yo_bank"; 
static final String USER = "root"; 
static final String PASS = "uproot"; 

Connection conn;
PreparedStatement stmt;
ResultSet rs;

public View_All_Custs() throws SQLException,Exception{ 
conn = null; 
stmt = null; 

Class.forName(JDBC_DRIVER); 
conn = DriverManager.getConnection(DB_URL,USER,PASS); 
stmt=conn.prepareStatement("SELECT * FROM customer");
rs = stmt.executeQuery(); 

while(rs.next()){ 
String id = rs.getString("custid").toUpperCase();
Thread.sleep(1000);

int age = rs.getInt("age"); 
String name = rs.getString("name").toUpperCase(); 
String sex = rs.getString("sex").toUpperCase();

System.out.print("CustID : " + id); 
System.out.print(", Name : " + name); 
System.out.print(", Age : " + age); 
System.out.println(", Sex : " + sex);

}
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