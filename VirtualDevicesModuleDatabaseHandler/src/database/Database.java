/**
Database.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package database;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Database
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    private DatabaseConfiguration databaseConfiguration = null;
    private PreparedStatement pst = null;
    private Connection conn = null;

    public Database()
    {
        
    }
    
    private void connect()
    {
        this.databaseConfiguration = new DatabaseConfiguration();
        
        if (this.databaseConfiguration != null)
        {
            try
            {
                this.conn = DriverManager.getConnection(this.databaseConfiguration.getDatabaseUrl(), this.databaseConfiguration.getDatabaseUser(), this.databaseConfiguration.getDatabasePassword());
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            System.err.println("Error getting database connection information...");
        }
    }

    public HashMap<String, String> executeSelect(String statement)
    {
	HashMap<String, String> resultSet = new HashMap<>();
	try
	{
            if (this.conn == null)
            {
                this.connect();
            }
            
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(statement);
	    while (rs.next())
	    {
		System.out.println(rs.getString(1));
		
                // put select output into hashmap
		resultSet.put("field", "value");
	    }
	}
	catch (SQLException ex)
	{
	    logger.error(ex);

	}
	finally
	{
	    try
	    {
		if (this.pst != null)
		{
		    this.pst.close();
		}
	    }
	    catch (SQLException ex)
	    {
		logger.error(ex);
	    }
	}
	return resultSet;
    }
    
    public void executeInsert(String statement)
    {
	try
	{
            if (this.conn == null)
            {
                this.connect();
            }
            
	    Statement st = this.conn.createStatement();
            st.execute(statement);
	}
	catch (SQLException ex)
	{
	    logger.error(ex);

	}
	finally
	{
	    try
	    {
		if (this.pst != null)
		{
		    this.pst.close();
		}
	    }
	    catch (SQLException ex)
	    {
		logger.error(ex);
	    }
	}
    }
    
    public boolean executeUpdate(String statement)
    {
	boolean state = false;

        try
	{
            if (this.conn == null)
            {
                this.connect();
            }
            
	    Statement st = this.conn.createStatement();
            st.executeQuery(statement);
	    
	    state = true;
	}
	catch (SQLException ex)
	{
	    logger.error(ex);
	}
	finally
	{
	    try
	    {
		if (this.pst != null)
		{
		    this.pst.close();
		}
	    }
	    catch (SQLException ex)
	    {
		logger.error(ex);
	    }
	}
	
	return state;
    }
}