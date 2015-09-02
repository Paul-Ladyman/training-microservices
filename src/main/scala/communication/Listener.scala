package communication


abstract class Listener {
  def receiveEvent(event: Event)
}
