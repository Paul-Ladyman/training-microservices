package integration

import communication.Emitter

object VivoClientApp extends App {

  val message = """{"type":"createPost", "body":"Hello World"}"""
  Emitter.publishMessage(message)
}
