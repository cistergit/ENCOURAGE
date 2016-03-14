/**
 * Consumer.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic
 * Institute of Porto This work was supported by National Funds through FCT
 * (Portuguese Foundation for Science and Technology) and by the EU ARTEMIS JU
 * funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr.
 * 269354.
 */
package encourager;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public abstract class Consumer extends DefaultConsumer {

    String name;
    long sleep;
    Channel channel;
    String exchangeName;
    String routingKey;
    String queue;
    int processed;
    boolean consumersAck;
    ExecutorService executorService;

    public Consumer(int prefetch, ExecutorService threadExecutor,
            long s, Channel channel, String exchangeName, String queueName, String routingKey, boolean consumersAck) throws Exception {
        super(channel);
        this.sleep = s;
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.consumersAck = consumersAck;
        this.executorService = threadExecutor;

        /*
         queue - the name of the queue
         durable - true if we are declaring a durable queue (the queue will survive a server restart)
         exclusive - true if we are declaring an exclusive queue (restricted to this connection)
         autoDelete - true if we are declaring an autodelete queue (server will delete it when no longer in use)
         arguments - other properties (construction arguments) for the queue
         */
        
        if (queueName.equals("")) {
            this.queue = this.channel.queueDeclare().getQueue();
        }
        else
        {
            this.queue = this.channel.queueDeclare(queueName, true, false, false, null).getQueue();
        }
        
        if (this.exchangeName != null && this.routingKey != null)
        {
            this.channel.queueBind(this.queue, this.exchangeName, this.routingKey);
        }

        channel.basicQos(prefetch);
        channel.basicConsume(this.queue, false, this);
    }

    public Consumer(int prefetch, ExecutorService threadExecutor,
            long s, Channel channel, String exchangeName, String queueName, String routingKey, 
            boolean consumersAck, HashMap<String, Boolean> queueProperties) throws Exception {
        super(channel);
        this.sleep = s;
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.consumersAck = consumersAck;
        this.executorService = threadExecutor;

        /*
         queue - the name of the queue
         durable - true if we are declaring a durable queue (the queue will survive a server restart)
         exclusive - true if we are declaring an exclusive queue (restricted to this connection)
         autoDelete - true if we are declaring an autodelete queue (server will delete it when no longer in use)
         arguments - other properties (construction arguments) for the queue
         */
        
        if (queueName.equals("")) {
            this.queue = this.channel.queueDeclare().getQueue();
        } else {
            if (queueProperties != null) {
                this.queue = this.channel.queueDeclare(queueName, queueProperties.get("durable"), queueProperties.get("exclusive"), queueProperties.get("autodelete"), null).getQueue();
            } else {
                this.queue = this.channel.queueDeclare(queueName, true, false, false, null).getQueue();
            }
        }
        
        if (this.exchangeName != null && this.routingKey != null)
        {
            this.channel.queueBind(this.queue, this.exchangeName, this.routingKey);
        }

        channel.basicQos(prefetch);
        channel.basicConsume(this.queue, false, this);
    }

    @Override
    public void handleDelivery(String consumerTag,
            Envelope envelope,
            AMQP.BasicProperties properties,
            byte[] body) throws IOException {
        Delivery dlv = new Delivery(envelope, properties, body);
        Runnable task = new VariableLengthTask(this,
         envelope.getDeliveryTag(),
         channel, sleep, dlv, this.consumersAck);

        executorService.submit(task);
    }

    public abstract Runnable handleConsumerMessage(Delivery delivery);

    static class VariableLengthTask implements Runnable {

        long tag;
        long sleep;
        Channel chan;
        Consumer worker;
        Delivery delivery;
        boolean consumersAck;

        VariableLengthTask(Consumer w, long t, Channel c, long s, Delivery delivery, boolean consumersAck) {
            worker = w;
            tag = t;
            chan = c;
            sleep = s;
            this.delivery = delivery;
            this.consumersAck = consumersAck;
        }

        @Override
        public void run() {
            worker.handleConsumerMessage(delivery);

            if (chan.isOpen() && this.consumersAck) {
                try {
                    chan.basicAck(tag, false);
                } catch (IOException e) {
                }
            }
        }
    }
}
