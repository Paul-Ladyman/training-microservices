package core

import communication.{Emitter, QueueConsumer}
import persistance.MongoDao
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats

object PosterAggregate extends App with QueueConsumer {
  def handleMessage(message: String) = {
    val mongoDao = new MongoDao
    mongoDao.write(message)

    val postCreatedMessage = """{"type":"postCreated", "body":"Hello World"}"""

    Emitter.publishMessage(postCreatedMessage)
  }

  override def ignoreMessage(message: String) = {
    implicit val formats = DefaultFormats

    val json = parse(message)
    val postType = (json \ "type").extract[String]
    !postType.equals("createPost")
  }
}
