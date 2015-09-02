package applications

import integration.VivoClientInboundGateway
import com.rabbitmq.client.ConnectionFactory

object UserApp extends App {
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
  channel.queueDeclare(queueName, true, false, false, null)
  channel.queueBind(queueName, exchangeName, routingKey)

  // Send a message
  val messageBodyBytes = "Hello, world!".getBytes()
  channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes)

  println("VivoClientApp Done")
}
