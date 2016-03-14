/**
 * Publisher.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic
 * Institute of Porto This work was supported by National Funds through FCT
 * (Portuguese Foundation for Science and Technology) and by the EU ARTEMIS JU
 * funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr.
 * 269354.
 */
package encourager;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Publisher implements Runnable {

    Channel channel;
    String routingKey;
    ExecutorService executorService;
    byte[] messageContent;
    String exchangeName;
    String correlationId;
    boolean replyRequired;
    boolean publishersAck;
    boolean consumersAck;
    int messageDeliveryMode;
    String replyToQueue;
    HashMap messageHeaders;

    public Publisher(ExecutorService executorService, Channel channel, String exchangeName,
            String routingKey, Object message, String corrId, boolean replyRequired, int messageDeliveryMode, boolean publishersAck, boolean consumersAck, String replyToQueue, HashMap<String, Object> messageHeaders) {
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.messageContent = message.toString().getBytes();
        this.correlationId = corrId;
        this.replyRequired = replyRequired;
        this.publishersAck = publishersAck;
        this.consumersAck = consumersAck;
        this.messageDeliveryMode = messageDeliveryMode;
        this.executorService = executorService;
        this.replyToQueue = replyToQueue;
        this.messageHeaders = messageHeaders;
    }

    @Override
    public void run() {
        QueueingConsumer replyConsumer = null;
        BasicProperties amqProperties = null;
        
        if (!this.replyRequired)
        {
            this.replyToQueue = "";
        }

        // add confirm listener for broker acks
        if (this.publishersAck) {
            addConfirmListener();
            try {
                this.channel.confirmSelect();
            } catch (IOException ex) {
                Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // build message properties
        amqProperties = new BasicProperties.Builder()
                .headers(this.messageHeaders)
                .replyTo(this.replyToQueue)
                .correlationId(this.correlationId)
                .deliveryMode(this.messageDeliveryMode)
                .build();

        try {
            // Publish the received message into specific exchange in middleware
            this.channel.basicPublish(this.exchangeName, this.routingKey, amqProperties, this.messageContent);
        } catch (IOException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addConfirmListener() {
        this.channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long seqNo, boolean multiple) throws IOException {
                System.err.println("Publish ACK received");
            }

            @Override
            public void handleNack(long seqNo, boolean multiple) throws IOException {
                System.err.println("Publish NACK received");
            }
        });
    }

    public abstract Runnable handlePublisherMessage(QueueingConsumer.Delivery delivery);
}