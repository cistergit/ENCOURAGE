/**
VDDatabaseHandler.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package vddatabasehandler;

import java.lang.invoke.MethodHandles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author cister
 */
public class VDDatabaseHandler
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    // sql queue instance variable
    private static SQLQueue sqlSyncQueue;
    public static int print_info=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
		if (args.length > 0 && args[0].equals("noDBsilent"))
		{
			print_info=0;
			sqlSyncQueue = null;
		}
		else if (args.length > 0 && args[0].equals("noDBprint"))
		{
			print_info=2;
			sqlSyncQueue = null;
		}
		else if (args.length > 0 && args[0].equals("noDBminimal"))
		{
			print_info=1;
			sqlSyncQueue = null;
		}
		else if (args.length > 0 && args[0].equals("withDB"))
		{
			sqlSyncQueue = new SQLQueue();
		}
        else
		{
			System.out.println("excepted parameter: either noDBsilent, noDBprint, or withDB\n");
			System.exit(-1);
		}
        // TODO code application logic here
        VDSQLConsumer consumer = new VDSQLConsumer(sqlSyncQueue);
        
        logger.info("Consuming! Let's start some database handlers...");
    }
}