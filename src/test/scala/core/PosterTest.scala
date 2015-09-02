package core

import communication.{Listener, Event}
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, FlatSpec}
import persistance.MongoDao
import org.mockito.Mockito._

class PosterTest extends FlatSpec with Matchers with MockitoSugar {

  "receiveEvent" should "persist a post" in {
    val mockMongoDao = mock[MongoDao]
    val mockListener = mock[Listener]
    val poster = new Poster(mockMongoDao)

    poster.addListener(mockListener)
    poster.receiveEvent(Event("createPost", "body"))

    verify(mockMongoDao).write("body")
    verify(mockListener).receiveEvent(Event("postCreated", "body"))
  }
}
