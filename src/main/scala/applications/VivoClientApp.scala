package applications

import com.rabbitmq.client._
import integration.VivoClientInboundGateway

object VivoClientApp extends App {
  // Create a connection to the AMPQ node
  val factory = new ConnectionFactory()
  factory.setUri("amqp://dhalfxhj:TJ-8jR4Z9ikYuFVy2mULh75CjnisKSmL@bunny.cloudamqp.com/dhalfxhj")
  val conn = factory.newConnection()

  // Open a channel to the node
  val channel = conn.createChannel();

  // Define an Exchange and a Queue on that channel
  val exchangeName = "training-microservices"
  //  channel.exchangeDeclare(exchangeName, "direct", true)
  val queueName = "training-microservices-queue"
  val routingKey = "training-microservices-routing"
  channel.queueBind(queueName, exchangeName, routingKey)


  val consumer = new DefaultConsumer(channel) {
    def handleDelivery(consumerTag: String,
      envelope: Envelope,
      properties: AMQP.BasicProperties,
      body: Byte) {
//        val routingKey = envelope.getRoutingKey
//        val contentType = properties.getContentType
        val deliveryTag = envelope.getDeliveryTag

        val post = body.toString
        VivoClientInboundGateway.createPost(post)

        channel.basicAck(deliveryTag, false)
      }
  }

  val autoAck = false
  channel.basicConsume(queueName, autoAck, "training-microservices-consumer", consumer)
}
