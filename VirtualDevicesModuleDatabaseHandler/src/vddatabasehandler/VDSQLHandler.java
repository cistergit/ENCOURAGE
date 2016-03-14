/**
VDSQLHandler.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package vddatabasehandler;

import database.DatabaseHandler;
import java.lang.invoke.MethodHandles;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author cister
 */
public class VDSQLHandler implements Runnable
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    private SQLQueue protectedSQLQueue;
    private DatabaseHandler databaseHandler;
    
    public VDSQLHandler()
    {
        this.protectedSQLQueue = null;
        this.databaseHandler = null;
    }
    
    public VDSQLHandler(SQLQueue sqlQueue)
    {
        this.protectedSQLQueue = sqlQueue;
        this.databaseHandler = new DatabaseHandler();
    }

    @Override
    public void run()
    {
        logger.debug("VDSQLHandler thread starting...");
        
        while (true)
        {
            String sqlCommand = this.protectedSQLQueue.pullSQLCommand();
            
            logger.debug("Notified and has elements...");
                
            this.databaseHandler.insertData(sqlCommand);
        }
    }
    
    
}
