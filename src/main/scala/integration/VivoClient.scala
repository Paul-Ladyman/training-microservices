package integration

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object VivoClient {

  val listeners = new ListBuffer[Listener]

  def addListener(listener: Listener) = {
    listeners += listener
  }

  def createPost = {
    listeners.foreach(listener => {
      listener.receiveEvent(Event("createPost", ""))
    })
  }
}
