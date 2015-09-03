package communication

import com.rabbitmq.client.ConnectionFactory

trait Emitter {
  // Create a connection to the AMPQ node
  val factory = new ConnectionFactory()
  factory.setUri("amqp://dhalfxhj:TJ-8jR4Z9ikYuFVy2mULh75CjnisKSmL@bunny.cloudamqp.com/dhalfxhj")
  val conn = factory.newConnection()

  // Open a channel to the node
  val channel = conn.createChannel()

  // Define an Exchange and a Queue on that channel
  val exchangeName = "training-microservices"
  val emitterQueueName: String
  val emitterRoutingKey: String


  def startEmitter = {
    channel.queueDeclare(emitterQueueName, true, false, false, null)
    channel.queueBind(emitterQueueName, exchangeName, emitterRoutingKey)
  }

  def publishMessage(message: String) = {
    val messageBodyBytes = message.getBytes
    channel.basicPublish(exchangeName, emitterRoutingKey, null, messageBodyBytes)
  }
}
