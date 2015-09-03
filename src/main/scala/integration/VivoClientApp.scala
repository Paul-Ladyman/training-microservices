package integration

import communication.Emitter

object VivoClientApp extends App with Emitter {
  val emitterQueueName = "create-post-requests"
  val emitterRoutingKey = "create-post-requests-queue"

  startEmitter

  val message = """{"type":"createPost", "body":"Hello World"}"""
  publishMessage(message)
}
