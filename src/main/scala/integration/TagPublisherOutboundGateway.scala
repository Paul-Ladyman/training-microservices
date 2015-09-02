package integration

import communication.{Listener, Event}

class TagPublisherOutboundGateway extends Listener {
  val postCreatedEvent = "postCreated"

  def receiveEvent(event: Event) = {
    val eventType = event.eventType
    if (eventType.equals(postCreatedEvent)) {
      val cwork = event.body
      println(s">>>> Writing post: $cwork to Nexus")
    }
    else {
      println(s">>>> TagPublisherOutboundGateway cannot handle event $eventType")
    }
  }
}
