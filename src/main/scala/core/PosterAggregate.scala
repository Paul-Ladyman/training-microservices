package core

import communication.{Emitter, QueueConsumer}
import persistance.MongoDao
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats

object PosterAggregate extends App with QueueConsumer with Emitter {
  val emitterQueueName = "posts-created"
  val emitterRoutingKey = "posts-created-queue"
  val consumerQueueName = "create-post-requests"
  val consumerRoutingKey = "create-post-requests-queue"

  startEmitter
  startConsumer

  def messageHandler(message: String) = {
    val mongoDao = new MongoDao
    mongoDao.write(message)

    val postCreatedMessage = """{"type":"postCreated", "body":"Hello World"}"""

    publishMessage(postCreatedMessage)
  }

  override def handleMessage(message: String) = {
    implicit val formats = DefaultFormats

    val json = parse(message)
    val postType = (json \ "type").extract[String]
    postType.equals("createPost")
  }
}
