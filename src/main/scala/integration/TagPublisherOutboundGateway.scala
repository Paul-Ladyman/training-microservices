package integration

import communication.QueueConsumer
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._

object TagPublisherOutboundGateway extends App with QueueConsumer {
  val postCreatedEvent = "postCreated"
  val consumerQueueName = "posts-created"
  val consumerRoutingKey = "posts-created-queue"

  startConsumer

  def messageHandler(message: String) = {
    println(s">>>> Writing post: $message to Nexus")
  }

  override def handleMessage(message: String) = {
    implicit val formats = DefaultFormats

    val json = parse(message)
    val postType = (json \ "type").extract[String]
    postType.equals(postCreatedEvent)
  }
}
