/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encourageperformancetestapp;

import cister.rabbitmq.library.Constants;
import com.rabbitmq.client.QueueingConsumer;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import encourager.RabbitManager;
import encourager.RabbitUtils;

/**
 *
 * @author cister
 */
public class MultipleMPGTest
{
    private String rabbitHost;
    private String rabbitVHost;
    private String rabbitUsername;
    private String rabbitPassword;
    private String middlewarePluginName;
    private RabbitManager rabbitManager;
    private static org.apache.logging.log4j.Logger logger=null;
	//org.apache.logging.log4j.LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
	private int testsRunning = 0;

	private synchronized int testsRunningChange(int diff) {
		testsRunning+=diff;
		return testsRunning;
    }

	private synchronized int testsRunningVal() {
		return testsRunning;
    }

    List<Thread> performanceTestsThreads = new java.util.ArrayList<Thread>();
	List<String> prefixes = new java.util.ArrayList<String>();
	List<String> intervals = new java.util.ArrayList<String>();
	List<String> numberOfMsgs = new java.util.ArrayList<String>();
	List<String> perDevice = new java.util.ArrayList<String>();
	private int m_numOfMyTestThread = 0;
	private synchronized int numOfMyTestThread() {
		m_numOfMyTestThread++;
		return m_numOfMyTestThread;
    }

    private final String EXCHANGE_TESTS = "TestsControl";
    private final String QUEUE_TESTS = "TestsControl_queue";

    private final String EXCHANGE_VDORDERS = "VDOrders";
    private final String QUEUE_VDORDERS = "VDOrders_Queue";
    private final String ROUTING_KEY_WILDCARD = "#";

    
    public MultipleMPGTest(String name)
    {
		logger = org.apache.logging.log4j.LogManager.getLogger(name);
        logger.debug("CONSTRUTOR!");
        this.middlewarePluginName = name;
    }

/*private String device_to_mpg(String devname) {
		return devname.substring(0,devname.lastIndexOf("."));
}*/

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

        logger.debug("Properties loaded:\nRabbitHost: " + rabbitHost + "\nRabbitVHost: " + rabbitVHost + "\nRabbitUsername: " + rabbitUsername + 
                                      "\nRabbitPassword: " + rabbitPassword + "\n");

		logger.debug("Subscribing to RabbitMQ to receive responses...");

			System.out.println("name: "+middlewarePluginName);
/*            for (int index = 0;index<this.devices.size();index++) {
				String device = this.devices.get(index);
				System.out.println(device_to_mpg(device) + " -> " +  device);
			}*/

        this.rabbitManager = new RabbitManager(this.rabbitUsername, this.rabbitPassword, this.rabbitHost, this.rabbitVHost)
        {
            @Override
            public Runnable handleRabbitMessage(QueueingConsumer.Delivery dlvr)
            {
				String exchange = dlvr.getEnvelope().getExchange();
				if (exchange.equals(EXCHANGE_TESTS))
				{
        try
        {
				Thread.currentThread().sleep(200);
        } catch (Exception ex)
        {
            Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
					String ctrlReceived = new String(dlvr.getBody());
					if (ctrlReceived.equals("LIST")) {
						try {
							this.publish(EXCHANGE_TESTS, "", "NAME: "+middlewarePluginName, null, false, false);
						}
						catch (Exception ex)
						{
							Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
					if (ctrlReceived.equals("GO")) {
					    if (0 != testsRunningVal()) {
							try {
								this.publish(EXCHANGE_TESTS, "", "ERROR: "+middlewarePluginName+" already running tests", null, false, false);
							}
							catch (Exception ex)
							{
								Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
							}
							return null;
						}
						logger.info("Starting experiment");
						for (int i=0;i<performanceTestsThreads.size();i++) {
							performanceTestsThreads.get(i).start();
						}
					}
					if (ctrlReceived.startsWith("RATE")) {//log THIS!
					    if (0 != testsRunningVal()) {
							try {
								this.publish(EXCHANGE_TESTS, "", "ERROR: "+middlewarePluginName+" already running tests", null, false, false);
							}
							catch (Exception ex)
							{
								Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
							}
							return null;
						}
						final boolean perDev;
						if (ctrlReceived.startsWith("RATETOTAL")) {perDev = false;}
						else if (ctrlReceived.startsWith("RATEEACH")) {perDev = true;}
						else perDev = true;
						final String[] cfg = ctrlReceived.split(" ");
						for (int i=0;i<cfg.length;i++) {
							System.out.println("["+i+"] = ["+cfg[i]+"]");
						}
						if (middlewarePluginName.equals(cfg[1])) {
							logger.info("Received configuration: ["+cfg[0]+","+cfg[1]+","+cfg[2]+","+cfg[3]+","+cfg[4]+"]");
							System.out.println("executing ["+ctrlReceived+"]");
							try {
								prefixes.add(cfg[2]);
								intervals.add(cfg[3]);
								numberOfMsgs.add(cfg[4]);
								if (perDev == false) {
									perDevice.add("false");
								} else {
									perDevice.add("true");
								}
		performanceTestsThreads.add(new WorkerTestThread(cfg[2], Integer.parseInt(cfg[3]), Integer.parseInt(cfg[4]), perDev, numOfMyTestThread()));
// ALBANO								String routingKey = cfg[2]+"."+ROUTING_KEY_WILDCARD;
								String routingKey = cfg[2]+ROUTING_KEY_WILDCARD;
								this.subscribe(QUEUE_VDORDERS, EXCHANGE_VDORDERS, routingKey);
//								System.out.println("subscribe: "+ QUEUE_VDORDERS+" "+EXCHANGE_VDORDERS+" "+routingKey);
							}
							catch (Exception ex)
							{
								Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					}
					return null;
				}

				if (exchange.equals(EXCHANGE_VDORDERS))
				{
					logger.info("Received response from, with corr id: [RECV," +exchange+","+dlvr.getEnvelope().getRoutingKey()+","+dlvr.getProperties().getCorrelationId()+"]");
					logger.debug("Send ACK for response");
					try
					{
						logger.info("Answering to, with corr id: [SEND,,"+dlvr.getProperties().getReplyTo()+","+dlvr.getProperties().getCorrelationId()+"]");
						this.publish("", dlvr.getProperties().getReplyTo(), "Success", dlvr.getProperties().getCorrelationId(), false, false);
					}
					catch (Exception ex)
					{
						Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
					}
					return null;
                }
				return null;
            }
        };
        
        this.rabbitManager.subscribe(middlewarePluginName, EXCHANGE_TESTS, ROUTING_KEY_WILDCARD);
    }

	//public static double LogNormal(double mean, double stddev) {
	private static Random randGen = new Random();
	private static double ess = -1;
	private static double mu = -1;
	public static synchronized double random_network_delay() {
		if (ess < 0) {
			double mean=100;
			double stddev=20;
			double varx = Math.pow(stddev, 2);
			ess = Math.log(1.0 + (varx/Math.pow(mean,2)));
			mu = Math.log(mean) - (0.5*Math.pow(ess, 2));
		}
		double ret= Math.pow(2.71828, (mu+(ess*randGen.nextGaussian() ) ) );
		if (ret <0) return 0;
		return 0;
// ALBANO		return ret;
	}

	private class WorkerTestThread extends Thread {
		String prefix;
		int interval;
		int numberOfMessages;
		boolean perDev;
		int numTestThread;

		public WorkerTestThread(String a, int b, int c, boolean d, int e) {
			prefix = a;
			interval = b;
			numberOfMessages =  c;
			perDev =  d;
			numTestThread = e;
		}
			@Override
            public void run()
            {
                try
                {
                    executeTest();
                } catch (Exception ex)
                {
                    Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    private void executeTest() throws Exception
    {
		double last_network_delay = 0;
		int newNumTest = testsRunningChange(1);
		System.out.println("testsRunning++ "+newNumTest);
        logger.entry();
        String xmlMessage = null;


		List<String> devices = new java.util.ArrayList<String>();
        for (int index=0 ; index < Constants.all_devices.size() ; index++) {
            String one_device = Constants.all_devices.get(index);
			if (one_device.startsWith(prefix)) {
				devices.add(one_device);
			}
		}

        int j = 0;
		if (perDev) {
			interval /= devices.size();
			numberOfMessages *= devices.size();
		}

		// initial wait
		last_network_delay = random_network_delay();
		int first_sleep = randGen.nextInt((int)(last_network_delay+interval));
		if (first_sleep > 0)
			Thread.currentThread().sleep(first_sleep);

		long time_of_last_message = System.currentTimeMillis();
        while (j < numberOfMessages)
        {
            String device = null;
            int index = new Random().nextInt(devices.size());
            device = devices.get(index);
            
            logger.debug("Device selected "+device);
// ALBANO message big?
//            xmlMessage = Constants.getBigXML();
//            xmlMessage = Constants.getShortXMLMeasure();
            xmlMessage = Constants.getSpanishXMLMeasure();
// ALBANO			System.out.println("I am sending message "+j+"/"+numberOfMessages+" to device ["+device+"]\n");

			int myCorrId = numTestThread*numberOfMessages+j+1;
            logger.info("Publishing message to, with corr id: [SEND," +"SensorData"+","+device+","+myCorrId+"]");
			rabbitManager.publish("SensorData", device, xmlMessage, Integer.toString(myCorrId), false, false);

			logger.debug("Messages published: " + myCorrId);


			double new_network_delay = random_network_delay();

			// if we are doing very high-bandwidth experiments:
			if (new_network_delay > interval) new_network_delay = interval;
			int mpg_interval = 0;
// ALBANO		if (interval > 0) mpg_interval = randGen.nextInt(interval*2);
// ALBANO			int delay = (int)(mpg_interval + new_network_delay - last_network_delay);
			last_network_delay = new_network_delay;
			int delay = interval;
			long new_time_of_last_message = System.currentTimeMillis();
			delay -= (int)(new_time_of_last_message-time_of_last_message);
			time_of_last_message = new_time_of_last_message;

//            logger.warn("Wait " + delay + " milliseconds until next publish");

			if (0<delay) {
				Thread.currentThread().sleep(delay);
			}
			j++;
        }
		int newNumTest1 = testsRunningChange(-1);
		System.out.println("testsRunning-- "+newNumTest);

		if (0 == newNumTest1) {
			performanceTestsThreads = new java.util.ArrayList<Thread>();
			logger.info("Ended experiment");
		}
    }
	}

    public static void main(String args[])
    {
		String name = "Unknown";
		if (args.length>0) name = args[0];
		else {
			try
			{
				name = java.net.InetAddress.getLocalHost().getHostAddress();
			}
			catch (java.net.UnknownHostException ex)
			{
				System.out.println("Hostname can not be resolved");
			}
		}
		MultipleMPGTest middlewarePerformanceTests = new MultipleMPGTest(name);
        try
        {
            middlewarePerformanceTests.startApp();
        } catch (Exception ex)
        {
            Logger.getLogger(MultipleMPGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
