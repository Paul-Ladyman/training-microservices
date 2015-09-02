package core

import communication.{Emitter, Listener, Event}
import persistance.MongoDao

class PosterAggregate(mongoDao: MongoDao) extends Listener with Emitter {
  def receiveEvent(event: Event) = {
    val post = event.body
    mongoDao.write(post)
    listeners.foreach(listener => {
      listener.receiveEvent(Event("postCreated", post))
    })
  }
}
