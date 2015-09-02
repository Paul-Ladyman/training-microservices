package integration


abstract class Listener {
  def receiveEvent(event: Event)
}
