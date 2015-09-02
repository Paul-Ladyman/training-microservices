package integration

import communication.{Emitter, Listener, Event}


object VivoClientInboundGateway extends Emitter {

  def createPost(body: String) = {
    listeners.foreach(listener => {
      println(s">>>>>> Creating post $body")
      listener.receiveEvent(Event("createPost", body))
    })
  }
}
