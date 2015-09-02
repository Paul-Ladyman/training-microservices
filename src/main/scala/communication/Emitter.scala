package communication

import scala.collection.mutable.ListBuffer


trait Emitter {
  val listeners = new ListBuffer[Listener]

  def addListener(listener: Listener) = {
    listeners += listener
  }
}
