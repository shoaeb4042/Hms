import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class db
{
  Connection connection = null;
  
  public static Connection dbConnector()
  {
    try
    {
    	//define the connection URL
      Class.forName("org.sqlite.JDBC");//for SQLite
      
     // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//for ODBC
      
     // Class.forName("oracle.jdbc.driver.OracleDriver");//for Oracle
      
      return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Public\\Hotel.sqlite");//location of SQLite file
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Failed to connect to the database");
    }
    return null;
  }
}
