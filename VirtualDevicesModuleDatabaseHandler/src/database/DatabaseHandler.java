/**
DatabaseHandler.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package database;

import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class DatabaseHandler
{
    /*
     *  Constants
     */

    Database database;

    /*
     *  Instance variables
     */

    /*
     *  Constructors
     */
    /**
     *
     */
    public DatabaseHandler()
    {
        this.database = new Database();
    }

    /*
     *  Public methods
     */
    /**
     *
     * @param statements
     * @param routingKey
     * @return
     */
    public boolean insertData(String statements)
    {
        boolean state = false;

        //writeSqlQueriesToFile(statements, routingKey);

        this.database.executeInsert(statements);

        return state;
    }

    /**
     *
     * @param statements
     * @return
     */
    public boolean updateData(String statements)
    {
        boolean state = false;

        state = this.database.executeUpdate(statements);

        return state;
    }

    /**
     *
     * @param statements
     * @return
     */
    public HashMap<String, String> selectData(String statements)
    {
        HashMap<String, String> resultSet = new HashMap<>();

        // execute sql select statement
        resultSet = this.database.executeSelect(statements);

        return resultSet;
    }
    
    public boolean validateLogin(String username, String password)
    {
        boolean accessGranted = false;
        
        HashMap<String, String> rs = this.selectData("SELECT user_id "
                                                   + "FROM middleware.user_table "
                                                   + "WHERE access_id = '" + username + "' AND "
                                                   + "password = '" + password + "'");
        
        if (!rs.isEmpty())
        {
            accessGranted = true;
        }
        
        return accessGranted;
    }

    /*
     *  Private methods
     */
    private void writeSqlQueriesToFile(String statements, String routingKey)
    {
        try
        {
            // Create file 
            FileWriter fstream = new FileWriter("sql_files/Device_" + routingKey + ".sql", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(statements);
            //Close the output stream
            out.close();
        }
        catch (Exception e)
        {
            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
