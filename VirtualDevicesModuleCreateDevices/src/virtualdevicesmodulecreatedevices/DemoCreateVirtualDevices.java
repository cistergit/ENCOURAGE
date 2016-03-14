/**
DemoCreateVirtualDevices.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package virtualdevicesmodulecreatedevices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.QueueingConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import encourager.RabbitManager;

public class DemoCreateVirtualDevices
{
    private static final String MANAGEMENT_EXCHANGE = "MNG_Exchange";
    
    /**
     * @param args
     * @throws IOException 
     * @throws InterruptedException 
     * @throws ConsumerCancelledException 
     * @throws ShutdownSignalException 
     */
    public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException
    {
        RabbitManager rabbitManager = null;
        try
        {
            rabbitManager = new RabbitManager()
            {
                @Override
                public Runnable handleRabbitMessage(QueueingConsumer.Delivery delivery)
                {
                    System.out.println("!!!! MESSAGE RECEIVED BY TESTINGPUBLISHER !!!!");
                    return null;
                }
            };
        }
        catch (Exception ex)
        {
            System.err.println("Error creating RabbitManager instance.");
        }
        
        String xmlString = readFile("virtual_devices_schemas/encourage_structure.xml");
	
        String routingKey = "create.all";

	boolean replyRequired = false;
        boolean brokerAck = false;
        try {
            rabbitManager.publish(MANAGEMENT_EXCHANGE, routingKey, xmlString, null, replyRequired, brokerAck);
        } catch (Exception ex) {
            Logger.getLogger(DemoCreateVirtualDevices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readFile(String filename) throws IOException
    {
	BufferedReader br = new BufferedReader(new FileReader(filename));
	    
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        br.close();
        
        return sb.toString();
    }

}
