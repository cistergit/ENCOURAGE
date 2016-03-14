/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encourageperformancetestapp;

import cister.rabbitmq.library.Constants;
import com.rabbitmq.client.QueueingConsumer;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
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
public class MiddlewarePerformanceTests
{
    private String rabbitHost;
    private String rabbitVHost;
    private String rabbitUsername;
    private String rabbitPassword;
    private int numberOfMessages;
    private int intervalBetweenMessages;
    private String shadowDevice;
    private String configurationFile;
    private String middlewarePluginMacroCell;
    private RabbitManager rabbitManager;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    
    private final String EXCHANGE_VDORDERS = "VDOrders";
    private final String QUEUE_VDORDERS = "VDOrders_Queue";
    private final String ROUTING_KEY_WILDCARD = "#";
    
    private List<String> devices = Arrays.asList("DKD.DKDEM08.DKDJ080109",
                "DKD.DKDEM09.zwave01625CBE_525373",
                "DKD.DKDEM09.zwave01625CBE_328068",
                "DKD.DKDEM09.zwave01625CBE_393248",
                "DKD.DKDEM09.zwave01625CBE_393253",
                "DKD.DKDEM07.DKDJ070108",
                "DKD.DKDEM04.DKDJ040103",
                "DKD.DKDEM05.DKDJ050108",
                "DKD.DKDEM09.zwave01625CBE_67305521",
                "DKD.DKDEM09.zwave01625CBE_393269",
                "DKD.DKDEM07.DKDJ070106",
                "DKD.DKDEM03.DKDJ030106",
                "DKD.DKDEM09.zwave01625CBE_393219",
                "DKD.DKDEM09.DKDJ090107",
                "DKD.DKDEM09.DKDJ090105",
                "DKD.DKDEM09.zwave01625CBE_50462769",
                "DKD.DKDEM09.zwave01625CBE_262192",
                "NEST.NESTB01.NEST0109",
                "DKD.DKDEM09.zwave01625CBE_33620017",
                "NEST.NESTB01.NEST0102",
                "NEST.NESTB01.NEST0122",
                "DKD.DKDEM04.DKDJ040110",
                "DKD.DKDEM03.DKDJ030109",
                "DKD.DKDEM04.DKDJ040109",
                "DKD.DKDEM09.zwave01625CBE_524640",
                "NEST.NESTB01.NEST0127",
                "DKD.DKDEM04.DKDJ040101",
                "DKD.DKDEM09.zwave01625CBE_65568",
                "NEST.NESTB01.NEST0134",
                "DKD.DKDEM06.DKDJ060109",
                "DKD.DKDEM09.zwave01625CBE_393568",
                "DKD.DKDEM03.DKDJ030101",
                "DKD.DKDEM07.DKDJ070101",
                "NEST.NESTB01.NEST0111",
                "DKD.DKDEM01.DKDJ010110",
                "DKD.DKDEM03.DKDJ030107",
                "DKD.DKDEM09.zwave01625CBE_394045",
                "DKD.DKDEM09.zwave01625CBE_327808",
                "NEST.NESTB01.NEST0103",
                "DKD.DKDEM05.DKDJ050103",
                "DKD.DKDEM09.zwave01625CBE_50528305",
                "DKD.DKDEM04.DKDJ040105",
                "DKD.DKDEM02.DKDJ020108",
                "DKD.DKDEM09.zwave01625CBE_65539",
                "DKD.DKDEM09.zwave01625CBE_67436593",
                "NEST.NESTB01.NEST0115",
                "DKD.DKDEM09.DKDJ090115",
                "DKD.DKDEM09.zwave01625CBE_525152",
                "DKD.DKDEM09.DKDJ090117",
                "DKD.DKDEM09.zwave01625CBE_524336",
                "DKD.DKDEM09.zwave01625CBE_526141",
                "DKD.DKDEM09.DKDJ090110",
                "DKD.DKDEM09.zwave01625CBE_262147",
                "DKD.DKDEM09.zwave01625CBE_459104",
                "DKD.DKDEM09.zwave01625CBE_393789",
                "DKD.DKDEM09.zwave01625CBE_33882161",
                "DKD.DKDEM07.DKDJ070102",
                "DKD.DKDEM06.DKDJ060108",
                "DKD.DKDEM06.DKDJ060103",
                "NEST.NESTB01.NEST0105",
                "DKD.DKDEM01.DKDJ010101",
                "DKD.DKDEM07.DKDJ070110",
                "DKD.DKDEM09.zwave01625CBE_524291",
                "DKD.DKDEM09.zwave01625CBE_458789",
                "DKD.DKDEM04.DKDJ040107",
                "DKD.DKDEM03.DKDJ030111",
                "DKD.DKDEM05.DKDJ050111",
                "DKD.DKDEM09.zwave01625CBE_131075",
                "DKD.DKDEM09.zwave01625CBE_33816625",
                "DKD.DKDEM08.DKDJ080102",
                "DKD.DKDEM05.DKDJ050101",
                "DKD.DKDEM04.DKDJ040111",
                "DKD.DKDEM09.zwave01625CBE_33685553",
                "DKD.DKDEM02.DKDJ020106",
                "UPC.UPCTC02.UPC02001",
                "DKD.DKDEM09.zwave01625CBE_394336",
                "DKD.DKDEM09.DKDJ090102",
                "DKD.DKDEM02.DKDJ020110",
                "NEST.NESTB01.NEST0107",
                "DKD.DKDEM07.DKDJ070111",
                "DKD.DKDEM03.DKDJ030113",
                "DKD.DKDEM06.DKDJ060104",
                "UPC.UPCTC02.UPC02005",
                "DKD.DKDEM01.DKDJ090101",
                "DKD.DKDEM09.zwave01625CBE_50593841",
                "DKD.DKDEM09.DKDJ090103",
                "DKD.DKDEM09.zwave01625CBE_34013233",
                "DKD.DKDEM07.DKDJ070104",
                "DKD.DKDEM09.zwave01625CBE_460093",
                "DKD.DKDEM05.DKDJ050107",
                "DKD.DKDEM09.zwave01625CBE_394557",
                "DKD.DKDEM09.zwave01625CBE_196656",
                "DKD.DKDEM09.zwave01625CBE_394301",
                "DKD.DKDEM09.zwave01625CBE_67371057",
                "DKD.DKDEM06.DKDJ060102",
                "NEST.NESTB01.NEST0126",
                "DKD.DKDEM03.DKDJ030104",
                "DKD.DKDEM09.zwave01625CBE_458784",
                "DKD.DKDEM01.DKDJ010113",
                "DKD.DKDEM01.DKDJ010106",
                "DKD.DKDEM01.DKDJ010108",
                "DKD.DKDEM09.zwave01625CBE_131120",
                "NEST.NESTB01.NEST0133",
                "DKD.DKDEM09.DKDJ090113",
                "DKD.DKDEM09.DKDJ090112",
                "DKD.DKDEM09.zwave01625CBE_327683",
                "DKD.DKDEM01.DKDJ010104",
                "UPC.UPCTC02.UPC02003",
                "NEST.NESTB01.NEST0119",
                "NEST.NESTB01.NEST0112",
                "DKD.DKDEM09.zwave01625CBE_393824",
                "NEST.NESTB01.NEST0117",
                "DKD.DKDEM06.DKDJ060106",
                "DKD.DKDEM02.DKDJ020102",
                "DKD.DKDEM09.zwave01625CBE_525629",
                "DKD.DKDEM08.DKDJ080107",
                "DKD.DKDEM08.DKDJ080104",
                "DKD.DKDEM06.DKDJ060111",
                "NEST.NESTB01.NEST0128",
                "DKD.DKDEM03.DKDJ030110",
                "DKD.DKDEM09.zwave01625CBE_459581",
                "DKD.DKDEM09.zwave01625CBE_131200",
                "DKD.DKDEM09.zwave01625CBE_524349",
                "DKD.DKDEM03.DKDJ030102",
                "DKD.DKDEM09.zwave01625CBE_394813",
                "DKD.DKDEM05.DKDJ050104",
                "NEST.NESTB01.NEST0116",
                "DKD.DKDEM08.DKDJ080110",
                "DKD.DKDEM09.zwave01625CBE_393264",
                "DKD.DKDEM09.zwave01625CBE_196736",
                "DKD.DKDEM05.DKDJ050102",
                "NEST.NESTB01.NEST0120",
                "DKD.DKDEM06.DKDJ060105",
                "DKD.DKDEM09.zwave01625CBE_50790449",
                "DKD.DKDEM08.DKDJ080106",
                "DKD.DKDEM03.DKDJ030108",
                "DKD.DKDEM07.DKDJ070107",
                "NEST.NESTB01.NEST0124",
                "DKD.DKDEM05.DKDJ050109",
                "DKD.DKDEM02.DKDJ020107",
                "NEST.NESTB01.NEST0104",
                "DKD.DKDEM09.zwave01625CBE_460605",
                "DKD.DKDEM09.zwave01625CBE_394080",
                "DKD.DKDEM07.DKDJ070109",
                "DKD.DKDEM09.zwave01625CBE_460349",
                "DKD.DKDEM02.DKDJ020104",
                "NEST.NESTB01.NEST0106",
                "DKD.DKDEM09.zwave01625CBE_65585",
                "NEST.NESTB01.NEST0123",
                "DKD.DKDEM04.DKDJ040106",
                "DKD.DKDEM09.zwave01625CBE_525408",
                "DKD.DKDEM09.zwave01625CBE_395069",
                "DKD.DKDEM09.zwave01625CBE_33751089",
                "DKD.DKDEM07.DKDJ070105",
                "DKD.DKDEM08.DKDJ080103",
                "DKD.DKDEM08.DKDJ080108",
                "NEST.NESTB01.NEST0135",
                "NEST.NESTB01.NEST0130",
                "DKD.DKDEM06.DKDJ060110",
                "NEST.NESTB01.NEST0114",
                "DKD.DKDEM09.zwave01625CBE_50659377",
                "DKD.DKDEM01.DKDJ010112",
                "DKD.DKDEM02.DKDJ020101",
                "NEST.NESTB01.NEST0101",
                "DKD.DKDEM09.zwave01625CBE_524861",
                "NEST.NESTB01.NEST0131",
                "DKD.DKDEM09.zwave01625CBE_458805",
                "DKD.DKDEM05.DKDJ050105",
                "DKD.DKDEM09.zwave01625CBE_196611",
                "DKD.DKDEM09.DKDJ090118",
                "DKD.DKDEM09.zwave01625CBE_327712",
                "DKD.DKDEM04.DKDJ040102",
                "DKD.DKDEM09.zwave01625CBE_327728",
                "NEST.NESTB01.NEST0110",
                "DKD.DKDEM07.DKDJ070103",
                "UPC.UPCTC02.UPC02002",
                "DKD.DKDEM09.DKDJ090116",
                "DKD.DKDEM09.zwave01625CBE_458813",
                "NEST.NESTB01.NEST0142",
                "DKD.DKDEM09.DKDJ090106",
                "DKD.DKDEM09.zwave01625CBE_65924",
                "DKD.DKDEM09.zwave01625CBE_459325",
                "DKD.DKDEM09.zwave01625CBE_65664",
                "DKD.DKDEM05.DKDJ050110",
                "DKD.DKDEM09.zwave01625CBE_67174449",
                "DKD.DKDEM02.DKDJ020103",
                "DKD.DKDEM09.zwave01625CBE_525117",
                "NEST.NESTB01.NEST0136",
                "DKD.DKDEM09.DKDJ090109",
                "DKD.DKDEM09.zwave01625CBE_67239985",
                "DKD.DKDEM06.DKDJ060101",
                "DKD.DKDEM09.zwave01625CBE_524605",
                "DKD.DKDEM09.zwave01625CBE_524896",
                "DKD.DKDEM09.zwave01625CBE_459837",
                "NEST.NESTB01.NEST0125",
                "UPC.UPCTC02.UPC02006",
                "DKD.DKDEM09.zwave01625CBE_393277",
                "DKD.DKDEM02.DKDJ020109",
                "DKD.DKDEM09.zwave01625CBE_458800",
                "DKD.DKDEM01.DKDJ010102",
                "DKD.DKDEM09.zwave01625CBE_524325",
                "DKD.DKDEM09.zwave01625CBE_524320",
                "DKD.DKDEM09.DKDJ090104",
                "DKD.DKDEM09.zwave01625CBE_459069",
                "DKD.DKDEM09.zwave01625CBE_458755",
                "DKD.DKDEM02.DKDJ020111",
                "DKD.DKDEM09.zwave01625CBE_262532",
                "DKD.DKDEM01.DKDJ090108",
                "DKD.DKDEM06.DKDJ060107",
                "NEST.NESTB01.NEST0129",
                "DKD.DKDEM09.zwave01625CBE_524341",
                "NEST.NESTB01.NEST0141",
                "DKD.DKDEM05.DKDJ050106",
                "DKD.DKDEM09.DKDJ090114",
                "DKD.DKDEM09.zwave01625CBE_67567665",
                "DKD.DKDEM04.DKDJ040104",
                "DKD.DKDEM09.zwave01625CBE_50397233",
                "DKD.DKDEM01.DKDJ010109",
                "NEST.NESTB01.NEST0113",
                "DKD.DKDEM09.zwave01625CBE_525885",
                "DKD.DKDEM09.zwave01625CBE_393533",
                "DKD.DKDEM01.DKDJ010105",
                "DKD.DKDEM09.zwave01625CBE_65584",
                "DKD.DKDEM02.DKDJ020105",
                "DKD.DKDEM04.DKDJ040108",
                "DKD.DKDEM09.zwave01625CBE_131460",
                "DKD.DKDEM01.DKDJ010103",
                "DKD.DKDEM03.DKDJ030105",
                "NEST.NESTB01.NEST0132",
                "NEST.NESTB01.NEST0108",
                "DKD.DKDEM09.zwave01625CBE_262272",
                "DKD.DKDEM01.DKDJ010111",
                "DKD.DKDEM08.DKDJ080111",
                "DKD.DKDEM08.DKDJ080105",
                "NEST.NESTB01.NEST0118",
                "DKD.DKDEM03.DKDJ030112",
                "DKD.DKDEM03.DKDJ030103",
                "DKD.DKDEM01.DKDJ010107",
                "DKD.DKDEM08.DKDJ080101",
                "NEST.NESTB01.NEST0121",
                "UPC.UPCTC02.UPC02004",
                "DKD.DKDEM09.DKDJ090111");
    
    public MiddlewarePerformanceTests(String configurationFilename)
    {
        logger.debug("CONSTRUTOR!");
        this.configurationFile = configurationFilename;
    }
    
    public void startApp() throws Exception
    {
        logger.debug("APPLICATION!");
        
        logger.entry();
        logger.debug("Starting MiddlewarePluginDummy application...");
        
        Properties properties = RabbitUtils.loadPropertiesFile(this.configurationFile);

        this.rabbitHost = properties.getProperty("RabbitHost");
        this.rabbitVHost = properties.getProperty("RabbitVHost");
        this.rabbitUsername = properties.getProperty("RabbitUser");
        this.rabbitPassword = properties.getProperty("RabbitPassword");
        this.numberOfMessages = Integer.parseInt(properties.getProperty("NumberOfMessages"));
        this.intervalBetweenMessages = Integer.parseInt(properties.getProperty("IntervalBetweenMessages"));
        this.shadowDevice = properties.getProperty("ShadowDevice");
        this.middlewarePluginMacroCell = properties.getProperty("MacroCell");
        
        logger.debug("Properties loaded:\nRabbitHost: " + rabbitHost + "\nRabbitVHost: " + rabbitVHost + "\nRabbitUsername: " + rabbitUsername + 
                                      "\nRabbitPassword: " + rabbitPassword + "\nNumberOfMessages: " + numberOfMessages + "\n IntervalBetweenMessages: " + intervalBetweenMessages + 
                                      "\n ShadowDevice: " + shadowDevice + "\n");
        
        logger.debug("Subscribing to RabbitMQ to receive responses...");
        
        this.rabbitManager = new RabbitManager(this.rabbitUsername, this.rabbitPassword, this.rabbitHost, this.rabbitVHost)
        {
            @Override
            public Runnable handleRabbitMessage(QueueingConsumer.Delivery dlvr)
            {
                logger.info("Received response: " + dlvr.getProperties().getCorrelationId());
                logger.debug("Send ACK for response");
                try
                {
                    this.publish("", dlvr.getProperties().getReplyTo(), "Success", dlvr.getProperties().getCorrelationId(), false, false);
                }
                catch (Exception ex)
                {
                    Logger.getLogger(MiddlewarePerformanceTests.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return null;
            }
        };
        
        this.rabbitManager.subscribe(QUEUE_VDORDERS, EXCHANGE_VDORDERS, ROUTING_KEY_WILDCARD);
        
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
                    Logger.getLogger(MiddlewarePerformanceTests.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        performanceTestsThread.start();
    }
    
    /**
     *
     */
    public void executeTest() throws Exception
    {
        logger.entry();
        String xmlMessage = null;
        
        int j = 0;
        while (j < 10000)
        {            
            String device = null;
            
            int index = new Random().nextInt(this.devices.size());
               
            device = this.devices.get(index);
            
            logger.debug("Device selected");
            
            logger.debug("Preparing reading xml message...");
                
            xmlMessage = Constants.getBigXML();
            
            this.rabbitManager.publish("SensorData", device, xmlMessage, Integer.toString(j + 1), false, false);
            
            logger.info("Published message " + (j+1) + " to " + device);
            
            logger.debug("Messages published: " + (j+1));
                        
            int tempInterval = (int) (this.intervalBetweenMessages * 0.1);
            int maxInterval = this.intervalBetweenMessages + tempInterval;
            Random rand = new Random();
            int randomNum = rand.nextInt((maxInterval - this.intervalBetweenMessages) + 1) + this.intervalBetweenMessages;
            logger.debug("Wait " + randomNum + " seconds until next publish");
            
            Thread.currentThread().sleep(randomNum);
            j++;
        }
    }
    
    public static void main(String args[])
    {

		MiddlewarePerformanceTests middlewarePerformanceTests = new MiddlewarePerformanceTests(args[0]);
        try
        {
		/*
			java.io.File dir = new java.io.File("./configs");System.out.println("path "+dir.getCanonicalFile()+"\n");
java.io.File[] filesList = dir.listFiles();
for (java.io.File file : filesList) {
    if (file.isFile()) {
        System.out.println(file.getName());
    }
}*/
            middlewarePerformanceTests.startApp();
        } catch (Exception ex)
        {
            Logger.getLogger(MiddlewarePerformanceTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
