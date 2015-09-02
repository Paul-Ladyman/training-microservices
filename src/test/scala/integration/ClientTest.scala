package integration

import org.mockito.Mockito._
import org.scalatest.{FlatSpec, _}
import org.scalatest.mock.MockitoSugar

class ClientTest extends FlatSpec with Matchers with MockitoSugar {
  val mockPoster = mock[Poster]

  "createPost" should "fire a createPost event" in {
    VivoClient.addListener(mockPoster)
    VivoClient.createPost
    verify(mockPoster).receiveEvent(Event("createPost", ""))
  }
}
