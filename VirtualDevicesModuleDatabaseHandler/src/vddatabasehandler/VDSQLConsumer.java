/**
VDSQLConsumer.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package vddatabasehandler;

import com.rabbitmq.client.QueueingConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import encourager.RabbitManager;

/**
 *
 * @author cister
 */
public class VDSQLConsumer
{
    private static final String VD_DATABASE_EXCHANGE = "VDDatabaseExchange";
    private static final String VD_DATABASE_QUEUE = "VDDatabaseQueue";
    private static final String ROUTING_KEY = "#";
    
    private RabbitManager rabbitManager;
    private SQLQueue sqlSyncQueue;
    
    public VDSQLConsumer(SQLQueue sqlqueue)
    {
        this.sqlSyncQueue = sqlqueue;
        
        try
        {
            this.rabbitManager = new RabbitManager()
            {
				private int count=0;
                @Override
                public Runnable handleRabbitMessage(QueueingConsumer.Delivery delivery)
                {
					if (null != sqlSyncQueue)
						sqlSyncQueue.pushSQLCommand(new String(delivery.getBody()));
					else if (2 == VDDatabaseHandler.print_info)
						System.out.println("DB handler tried consuming ["+new String(delivery.getBody())+"]");
					else if (1 == VDDatabaseHandler.print_info) {
						if (0 == count % 1000)
							System.out.println(""+count+" msgs");
						count ++;
					}

                    return null;
                }
            };
        }
        catch (Exception ex)
        {
            System.err.println("Error creating RabbitManager instance.");
        }
        try {
            this.rabbitManager.subscribe(VD_DATABASE_QUEUE, VD_DATABASE_EXCHANGE, ROUTING_KEY);
        } catch (Exception ex) {
            Logger.getLogger(VDSQLConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
