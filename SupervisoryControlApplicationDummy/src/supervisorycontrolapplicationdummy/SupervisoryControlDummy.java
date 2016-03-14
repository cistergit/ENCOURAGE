/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supervisorycontrolapplicationdummy;

import com.rabbitmq.client.QueueingConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import encourager.RabbitManager;

/**
 *
 * @author cister
 */
public class SupervisoryControlDummy
{
    private static RabbitManager rabbitManager;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {        
        // load configuration file
        final String EXCHANGE_VDTOAPP = "VDToApp";
        final String QUEUE_VDTOAPP = "VDToApp_Queue";
        final String ROUTING_KEY_WILDCARD = "#";
        
        // TODO
        try
        {
            rabbitManager = new RabbitManager()
            {

                @Override
                public Runnable handleRabbitMessage(QueueingConsumer.Delivery dlvr)
                {
                    handleMiddlewarePluginDummyMessage(dlvr);
                    
                    return null;
                }
            };
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        try
        {
            rabbitManager.subscribe(QUEUE_VDTOAPP, EXCHANGE_VDTOAPP, ROUTING_KEY_WILDCARD);
            //rabbitManager.startConsuming();
        }
        catch (Exception ex)
        {
            Logger.getLogger(SupervisoryControlDummy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void handleMiddlewarePluginDummyMessage(QueueingConsumer.Delivery deliver)
    {
        final String EXCHANGE_COMMANDS = "Commands";

//        int processingTime = 80 + (int)(Math.random() * ((100 - 80) + 1));
        int processingTime = 100;
        try
        {
            Thread.sleep(processingTime);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(SupervisoryControlDummy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            rabbitManager.publish(EXCHANGE_COMMANDS, deliver.getEnvelope().getRoutingKey(), MessagesConstants.getShortXMLCommand(), deliver.getProperties().getCorrelationId(), false, false);
//			System.out.println("published " + MessagesConstants.getShortXMLCommand());
        }
        catch (Exception ex)
        {
            Logger.getLogger(SupervisoryControlDummy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
