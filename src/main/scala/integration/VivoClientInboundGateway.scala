package integration

import communication.{Emitter, Listener, Event}


object VivoClientInboundGateway extends Emitter {

  def createPost = {
    listeners.foreach(listener => {
      listener.receiveEvent(Event("createPost", ""))
    })
  }
}
