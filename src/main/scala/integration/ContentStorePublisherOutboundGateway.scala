package integration

import communication.Event

class ContentStorePublisherOutboundGateway {
  val postCreatedEvent = "postCreated"

  def receiveEvent(event: Event) = {
    val eventType = event.eventType
    if (eventType.equals(postCreatedEvent)) {
      val post = event.body
      println(s">>>> Persisting post: $post to Mongo")
    }
    else {
      println(s">>>> TagPublisherOutboundGateway cannot handle event $eventType")
    }
  }
}
