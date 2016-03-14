/**

SQLQueue.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.

*/

package vddatabasehandler;

import java.lang.invoke.MethodHandles;
import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLQueue {
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    private LinkedList<String> sqlQueue = null;
    private VDSQLHandler sqlHandler = null;
    
    public SQLQueue()
    {
        this.sqlQueue = new LinkedList<String>();
        Thread sqlHandlerThread = new Thread(new VDSQLHandler(this));
        sqlHandlerThread.start();
    }
    
    public synchronized void pushSQLCommand(String sqlCommand)
    {
        logger.info("Added new sql command to sqlSyncQueue --> " + sqlCommand);
        
        sqlQueue.addLast(sqlCommand);
        
        notifyAll();
    }
    
    public synchronized String pullSQLCommand()
    {
        while(sqlQueue.size()==0)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e){}
        }
        
        String sqlCommand = sqlQueue.removeFirst();
        
        logger.info("Removed sql command from sqlSyncQueue --> " + sqlCommand);
        
        return sqlCommand;
    }
    
    public synchronized boolean hasElements()
    {
        return this.sqlQueue.size() > 0;
    }
    
}

