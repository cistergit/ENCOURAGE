/**
RabbitManager.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class RabbitManager
{
    private final String CONFIGURATION_FILE = "configs/rabbitmq_config.properties";
    private final String CONFIGURATION_FILE_COMPATIBILITY_ONLY = "configs/virtual_devices_module.properties";
    private final String REPLY_QUEUE = "VDReply_Queue";
    private final int DEFAULT_PREFETCH_COUNT = 20;
    private final boolean DEFAULT_CONSUMERS_ACK = true;
    private final boolean DEFAULT_PUBLISHERS_ACK = false;
    //private final int DEFAULT_MESSAGE_DELIVERY_MODE = MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode();
    private final int DEFAULT_MESSAGE_DELIVERY_MODE = MessageProperties.TEXT_PLAIN.getDeliveryMode();
    
    private String username;
    private String password;
    private String host;
    private String vHost;
    private String replyQueue;
    private int prefetchCount;
    private boolean consumersAck;
    private boolean publishersAck;
    private int messageDeliveryMode;
    
    ExecutorService threadExecutor;
    
    private Connection consumersConnection;
    private Connection publishersConnection;
    
    private Channel publishersChannel;
    
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    
    public RabbitManager() throws Exception
    {        
        File configurationFile = new File(CONFIGURATION_FILE);
        File configurationFileCompatibilityOnly = new File(CONFIGURATION_FILE_COMPATIBILITY_ONLY);
        
        if (configurationFile.exists())
        {
            Properties properties = RabbitUtils.loadPropertiesFile(CONFIGURATION_FILE);

            this.username = properties.getProperty("RabbitUser");
            this.password = properties.getProperty("RabbitPassword");
            this.host = properties.getProperty("RabbitHost");
            this.vHost = properties.getProperty("RabbitVHost");
            this.replyQueue = properties.getProperty("ReplyQueue", REPLY_QUEUE);
            this.prefetchCount = Integer.parseInt(properties.getProperty("PrefetchCount", Integer.toString(DEFAULT_PREFETCH_COUNT)));
            this.consumersAck = Boolean.parseBoolean(properties.getProperty("ConsumersAck", Boolean.toString(DEFAULT_CONSUMERS_ACK)));
            this.publishersAck = Boolean.parseBoolean(properties.getProperty("PublishersAck", Boolean.toString(DEFAULT_PUBLISHERS_ACK)));
            this.messageDeliveryMode = Integer.parseInt(properties.getProperty("MessageDeliveryMode", Integer.toString(DEFAULT_MESSAGE_DELIVERY_MODE)));
            logger.debug("RabbitManager Configurations");
            logger.debug("PrefetchCount --> " + this.prefetchCount);
//            System.out.println("PrefetchCount --> " + this.prefetchCount);
            logger.debug("ConsumersAck --> " + this.consumersAck);
            logger.debug("PublishersAck --> " + this.publishersAck);
            logger.debug("MessageDeliveryMode --> " + this.messageDeliveryMode);
            logger.debug("ReplyQueue --> " + this.replyQueue);
        }
        else if (configurationFileCompatibilityOnly.exists())
        {
            Properties properties = RabbitUtils.loadPropertiesFile(CONFIGURATION_FILE);

            this.username = properties.getProperty("RabbitUser");
            this.password = properties.getProperty("RabbitPassword");
            this.host = properties.getProperty("RabbitHost");
            this.vHost = properties.getProperty("RabbitVHost");
            this.replyQueue = properties.getProperty("ReplyQueue", REPLY_QUEUE);
            this.prefetchCount = Integer.parseInt(properties.getProperty("PrefetchCount", Integer.toString(DEFAULT_PREFETCH_COUNT)));
//            System.out.println("PrefetchCount --> " + this.prefetchCount);
            this.consumersAck = Boolean.parseBoolean(properties.getProperty("ConsumersAck", Boolean.toString(DEFAULT_CONSUMERS_ACK)));
            this.publishersAck = Boolean.parseBoolean(properties.getProperty("PublishersAck", Boolean.toString(DEFAULT_PUBLISHERS_ACK)));
            this.messageDeliveryMode = Integer.parseInt(properties.getProperty("MessageDeliveryMode", Integer.toString(DEFAULT_MESSAGE_DELIVERY_MODE)));
            logger.debug("RabbitManager Configurations");
            logger.debug("PrefetchCount --> " + this.prefetchCount);
            logger.debug("ConsumersAck --> " + this.consumersAck);
            logger.debug("PublishersAck --> " + this.publishersAck);
            logger.debug("MessageDeliveryMode --> " + this.messageDeliveryMode);
            logger.debug("ReplyQueue --> " + this.replyQueue);
        }
        else
        {
            throw new Exception("Cannot instantiate RabbitManager object. "
                    + "Missing configuration file name under 'configs/' folder.");
        }
        
        this.validateConnectionParameters();
            
        this.consumersConnection = this.getConnection();
        this.publishersConnection = this.getConnection();
        this.publishersChannel = this.publishersConnection.createChannel();
        
        this.publishersConnection.addShutdownListener(new ShutdownListener()
        {

            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                logger.fatal("BUG - publishers connection just closed - addShutdownListener");
                logger.fatal("Reason: " + cause.getReason());
                logger.fatal("Reference: " + cause.getReference());
            }
        });
        
        
        this.threadExecutor = Executors.newFixedThreadPool(20);
    }
    
    public RabbitManager(String username, String password, String host, String vHost) throws Exception
    {
        this.username = username;
        this.password = password;
        this.host = host;
        this.vHost = vHost;
        this.prefetchCount = DEFAULT_PREFETCH_COUNT;
        this.consumersAck = DEFAULT_CONSUMERS_ACK;
        this.publishersAck = DEFAULT_PUBLISHERS_ACK;
        this.messageDeliveryMode = DEFAULT_MESSAGE_DELIVERY_MODE;
        this.replyQueue = REPLY_QUEUE;
        logger.debug("RabbitManager Configurations");
        logger.debug("PrefetchCount --> " + this.prefetchCount);
//		System.out.println("PrefetchCount --> " + this.prefetchCount);
        logger.debug("ConsumersAck --> " + this.consumersAck);
        logger.debug("PublishersAck --> " + this.publishersAck);
        logger.debug("MessageDeliveryMode --> " + this.messageDeliveryMode);
        
        this.validateConnectionParameters();
        
        this.consumersConnection = this.getConnection();
        this.publishersConnection = this.getConnection();
        this.publishersChannel = this.publishersConnection.createChannel();
        
        this.publishersConnection.addShutdownListener(new ShutdownListener()
        {

            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                logger.fatal("BUG - publishers connection just closed - addShutdownListener");
                logger.fatal("Reason: " + cause.getReason());
                logger.fatal("Reference: " + cause.getReference());
            }
        });
        
        this.threadExecutor = Executors.newFixedThreadPool(20);
    }
    
    private Connection getConnection() throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        Connection conn = null;

        factory.setHost(host);
        factory.setVirtualHost(vHost);

        factory.setUsername(username);
        factory.setPassword(password);
        try
        {
            conn = factory.newConnection();
        }
        catch (IOException e)
        {
            throw new Exception("Cannot create connection on:\nHost - " + host + "\nVirtualHost - " + vHost + 
                    "\nUsername - " + username + "\nPassword - " + password);
        }

        return conn;
    }
    
    public void subscribe(String queueName, String exchange, String routingKey) throws Exception
    {
        Consumer newConsumer = new Consumer(this.prefetchCount, this.threadExecutor, 1, 
                this.consumersConnection.createChannel(), exchange, queueName, routingKey, this.consumersAck) {

            @Override
            public Runnable handleConsumerMessage(QueueingConsumer.Delivery delivery) {
                handleRabbitMessage(delivery);
                return null;
            }
        };
    }
    
    public void subscribe(String queueName, String exchange, String routingKey, 
            HashMap<String, Boolean> queueProperties) throws Exception
    {
        Consumer newConsumer = new Consumer(this.prefetchCount, this.threadExecutor, 1, 
                this.consumersConnection.createChannel(), exchange, queueName, routingKey, this.consumersAck, queueProperties) {

            @Override
            public Runnable handleConsumerMessage(QueueingConsumer.Delivery delivery) {
                logger.debug("Consumed new message with correlation id " + delivery.getProperties().getCorrelationId());
                handleRabbitMessage(delivery);
                return null;
            }
        };
    }
    
    public synchronized void publish(String exchange, String routingKey, String message, String corrId, boolean receiveReply, boolean brokerAck) throws Exception
    {
        if (this.publishersConnection == null || !this.publishersConnection.isOpen())
        {
            this.publishersConnection = getConnection();
            this.publishersChannel = this.publishersConnection.createChannel();
            logger.warn("New Publishers connection needed...");
        }
        
        String replyQueueLocal = "";
        
        if (receiveReply)
        {
            replyQueueLocal = this.replyQueue;
        }
        
        Publisher publisher = new Publisher(this.threadExecutor, this.publishersChannel, exchange, routingKey, message, corrId, receiveReply, this.messageDeliveryMode, this.publishersAck, this.consumersAck, replyQueueLocal, null) {

            @Override
            public Runnable handlePublisherMessage(QueueingConsumer.Delivery delivery) {
                logger.debug("Received reply message with correlation id " + delivery.getProperties().getCorrelationId());
                handleRabbitMessage(delivery);
                return null;
            }
        };
        threadExecutor.execute(publisher);
    }
    
    public synchronized void publish(String exchange, String routingKey, String message, String corrId, boolean receiveReply, boolean brokerAck, HashMap messageHeaders) throws Exception
    {
        if (this.publishersConnection == null || !this.publishersConnection.isOpen())
        {
            this.publishersConnection = getConnection();
            this.publishersChannel = this.publishersConnection.createChannel();
            logger.warn("New Publishers connection needed...");
        }
        
        String replyQueueLocal = "";
        
        if (receiveReply)
        {
            replyQueueLocal = this.replyQueue;
        }
        
        Publisher publisher = new Publisher(this.threadExecutor, this.publishersChannel, exchange, routingKey, message, corrId, receiveReply, this.messageDeliveryMode, this.publishersAck, this.consumersAck, replyQueueLocal, messageHeaders) {

            @Override
            public Runnable handlePublisherMessage(QueueingConsumer.Delivery delivery) {
                logger.debug("Received reply message with correlation id " + delivery.getProperties().getCorrelationId());
                handleRabbitMessage(delivery);
                return null;
            }
        };
        threadExecutor.execute(publisher);
    }
    
    @Deprecated
    public void startConsuming()
    {
        
    }
    
    public static void main (String[] args) throws Exception
    {
        RabbitManager rabbitManager = null;
        try
        {
            rabbitManager = new RabbitManager("guest", "guest", "192.168.90.23", "/") {

                @Override
                public Runnable handleRabbitMessage(QueueingConsumer.Delivery delivery) {

                    System.out.println("Message: " + delivery.getProperties().getCorrelationId());
                    
                    return null;
                }
            };
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        rabbitManager.subscribe("VDToApp_Queue", "VDToApp", "#");
        int i = 0;
        while (i < 5)
        {
            HashMap<String, Object> messageHeaders = new HashMap();
            messageHeaders.put("roomId", "EZMON_Room");
            messageHeaders.put("additionalInfo", "EZMON_AdditionalInfo");
            
            rabbitManager.publish("VDToApp", "", "ola", Integer.toString(i), true, false);
            i++;
        }
        System.err.println("Finished");
    }
    
    private void validateConnectionParameters() throws Exception
    {
        if (this.username == null)
        {
            throw new Exception("Missing RabbitMQ Username on configuration file.");
        }
        
        if (this.password == null)
        {
            throw new Exception("Missing RabbitMQ Password on configuration file.");
        }
        
        if (this.host == null)
        {
            throw new Exception("Missing RabbitMQ Host on configuration file.");
        }
        
        if (this.vHost == null)
        {
            throw new Exception("Missing RabbitMQ VirtualHost on configuration file.");
        }
    }
    
    public abstract Runnable handleRabbitMessage(QueueingConsumer.Delivery delivery);
}