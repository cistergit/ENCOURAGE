/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encourageperformancetestapp;

import cister.rabbitmq.library.Constants;
import com.rabbitmq.client.QueueingConsumer;
import java.lang.invoke.MethodHandles;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import encourager.RabbitManager;
import encourager.RabbitUtils;

/**
 *
 * @author cister
 */
public class MultipleMPGTestDriver
{
    private String rabbitHost;
    private String rabbitVHost;
    private String rabbitUsername;
    private String rabbitPassword;
    private String middlewarePluginName;
    private String middlewarePluginMacroCell;
    private RabbitManager rabbitManager;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    
    private final String EXCHANGE_TESTS = "TestsControl";
    private final String QUEUE_TESTS = "TestsControl_queue";
    private final String ROUTING_KEY_WILDCARD = "#";


    public MultipleMPGTestDriver()
    {
        logger.debug("CONSTRUTOR!");
    }

private String device_to_mpg(String devname) {
		return devname.substring(0,devname.lastIndexOf("."));
}

    public void startApp() throws Exception
    {
        logger.debug("APPLICATION!");
        
        logger.entry();
        logger.debug("Starting MiddlewarePluginDummy application...");
        
        Properties properties = RabbitUtils.loadPropertiesFile("configs/highFrequency.properties");

        this.rabbitHost = properties.getProperty("RabbitHost");
        this.rabbitVHost = properties.getProperty("RabbitVHost");
        this.rabbitUsername = properties.getProperty("RabbitUser");
        this.rabbitPassword = properties.getProperty("RabbitPassword");
        this.middlewarePluginMacroCell = properties.getProperty("MacroCell");
        
        logger.debug("Properties loaded:\nRabbitHost: " + rabbitHost + "\nRabbitVHost: " + rabbitVHost + "\nRabbitUsername: " + rabbitUsername + 
                                      "\nRabbitPassword: " + rabbitPassword + "\n");

        logger.debug("Subscribing to RabbitMQ to receive responses...");

/*            for (int index = 0;index<this.devices.size();index++) {
				String device = this.devices.get(index);
				System.out.println(device_to_mpg(device) + " -> " +  device);
			}*/

        this.rabbitManager = new RabbitManager(this.rabbitUsername, this.rabbitPassword, this.rabbitHost, this.rabbitVHost)
        {
            @Override
            public Runnable handleRabbitMessage(QueueingConsumer.Delivery dlvr)
            {
				String ctrlReceived = new String(dlvr.getBody());
				System.out.println("ctrlReceived: "+ctrlReceived);
				return null;
            }
        };
        
        this.rabbitManager.subscribe(QUEUE_TESTS, EXCHANGE_TESTS, ROUTING_KEY_WILDCARD);
        
        // execute test
        Thread performanceTestsThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    executeTest();
                } catch (Exception ex)
                {
                    Logger.getLogger(MultipleMPGTestDriver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        performanceTestsThread.start();
    }

	private String[] CreateSatureTest() {
/*		return new String[] {
			"LIST",
			"RATETOTAL laptop0 DKD. 10 10"
		};*/
		return new String[] {
			"LIST",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 DKD. 1 500",
			"RATETOTAL laptop0 NEST. 1 500",
			"RATETOTAL laptop0 NEST. 1 500",
			"RATETOTAL laptop0 NEST. 1 500",
			"RATETOTAL laptop0 NEST. 1 500",
			"RATETOTAL laptop0 NEST. 1 500",
			"RATETOTAL laptop0 UPC. 1 500",
			"RATETOTAL laptop0 UPC. 1 500",
			"RATETOTAL laptop0 UPC. 1 500",
			"RATETOTAL laptop0 UPC. 1 500",
			"RATETOTAL laptop0 UPC. 1 500"
//			"RATETOTAL laptop0 DKD. 1 1"
		};
	}

	private String[] CreateNonSaturedTest() {
		// FROM HOME:
//		int which_time = 160; // rate = 60 Hz, all fine? 870/890 msg/sec consuma 900 ok for short
//		int which_time = 150; // rate = ?? Hz, all fine? 890 msg/sec bad for short, ok for Spanish
//		int which_time = 140; // rate = ?? Hz, all fine? 920 msg/sec bad // saturo for Spanish
//		int which_time = 450; // rate = ?? Hz, for big messages. 340 pub/sec, 290 deliv/sec, seemed good, was bad
//		int which_time = 470; // rate = ?? Hz, for big messages. 305 msg/sec, good for big
		// FROM CISTER'S:
//		int which_time = 550; // boia, funziona
//		int how_many_msgs = 450;

//		int which_time = 500;
//		int how_many_msgs = 500;
		
//		int which_time = 450;
//		int how_many_msgs = 550; // finally, some problems! :-D

//		int which_time = 140; // now small
//		int how_many_msgs = 1800; // easy

//		int which_time = 130; // now small
//		int how_many_msgs = 1900; // finally, bad!


//		int which_time = 300; // for the comparison small
//		int how_many_msgs = 2000; // 
//		int which_time = 400; // for the comparison small
//		int how_many_msgs = 1700; // 

//		int which_time = 1500; // for the comparison big
//		int how_many_msgs = 500; // 

//		int which_time = 150; // funzia magnificamente short
//		int how_many_msgs = 2000; // 

//		int which_time = 140; // funzia short
//		int how_many_msgs = 2000; // 

// CISTER Spanish:
//		int which_time = 160; // funzia
//		int which_time = 150; // funzia
//		int which_time = 140; // sembra funziare, su tempi lunghi mostra che é al limite
//		int which_time = 130; // saturated
//		int how_many_msgs = 2000;

// cool stuff:
		int which_time = 500;
		int how_many_msgs = 10;

		return new String[] {
			"LIST",
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs,
			"RATETOTAL laptop0 DKD. "+which_time+" "+how_many_msgs
		};
	}

    /**
     *
     */
    public void executeTest() throws Exception
    {
        logger.entry();
        String[] commandsToSend = null;
//		commandsToSend = CreateSatureTest();
		commandsToSend = CreateNonSaturedTest();

		for (int j = 0; j < commandsToSend.length ; j++) {
			try
			{
				Thread.currentThread().sleep(100);
				this.rabbitManager.publish(EXCHANGE_TESTS, "", commandsToSend[j], null, false, false);
			} catch (Exception ex)
			{
				Logger.getLogger(MultipleMPGTestDriver.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		try
		{
			Thread.currentThread().sleep(5000);
			this.rabbitManager.publish(EXCHANGE_TESTS, "", "GO", null, false, false);
		} catch (Exception ex)
		{
			Logger.getLogger(MultipleMPGTestDriver.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    public static void main(String args[])
    {
		MultipleMPGTestDriver testDriver = new MultipleMPGTestDriver();
//		System.out.println("n_gw: "+Constants.all_devices.size());
        try
        {
            testDriver.startApp();
        } catch (Exception ex)
        {
            Logger.getLogger(MultipleMPGTestDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
