package communication

import com.rabbitmq.client.ConnectionFactory

trait QueueConsumer {

  // Create a connection to the AMPQ node
  private val factory = new ConnectionFactory()
  factory.setUri("amqp://dhalfxhj:TJ-8jR4Z9ikYuFVy2mULh75CjnisKSmL@bunny.cloudamqp.com/dhalfxhj")
  private val conn = factory.newConnection()

  // Open a channel to the node
  private val channel = conn.createChannel()

  // Define an Exchange and a Queue on that channel
  private val exchangeName = "training-microservices"
  val consumerQueueName: String
  val consumerRoutingKey: String

  def startConsumer = {
    channel.queueDeclare(consumerQueueName, true, false, false, null)
    channel.queueBind(consumerQueueName, exchangeName, consumerRoutingKey)
    consume()
  }

  def consume(): Unit = {
    Thread.sleep(100)
    val autoAck = false
    val response = channel.basicGet(consumerQueueName, autoAck)
    if (response == null) {
      consume()
    } else {
      val message = new String(response.getBody, "utf8")

      if (handleMessage(message)) {
        messageHandler(message)
        channel.basicAck(response.getEnvelope.getDeliveryTag, false)
      }
      consume()
    }
  }

  def messageHandler(message: String)

  def handleMessage(message: String) = false
}
