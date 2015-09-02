package communication

import com.rabbitmq.client.ConnectionFactory

abstract trait QueueConsumer {

  // Create a connection to the AMPQ node
  val factory = new ConnectionFactory()
  factory.setUri("amqp://dhalfxhj:TJ-8jR4Z9ikYuFVy2mULh75CjnisKSmL@bunny.cloudamqp.com/dhalfxhj")
  val conn = factory.newConnection()

  // Open a channel to the node
  val channel = conn.createChannel()

  // Define an Exchange and a Queue on that channel
  val exchangeName = "training-microservices"
  val queueName = "training-microservices-queue"
  val routingKey = "training-microservices-routing"
  channel.queueBind(queueName, exchangeName, routingKey)

  def consume: Unit = {
    Thread.sleep(100)
    val autoAck = false
    val response = channel.basicGet(queueName, autoAck)
    if (response == null) {
      consume
    } else {
      val message = new String(response.getBody, "utf8")

      if (!ignoreMessage(message)) {
        handleMessage(message)
        channel.basicAck(response.getEnvelope.getDeliveryTag, false)
      }
      consume
    }
  }

  def handleMessage(message: String)

  def ignoreMessage(message: String) = false

  consume
}
