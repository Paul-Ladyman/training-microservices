package integration

import communication.Event
import core.PosterAggregate
import org.mockito.Mockito._
import org.scalatest.{FlatSpec, _}
import org.scalatest.mock.MockitoSugar

class VivoClientInboundGatewayTest extends FlatSpec with Matchers with MockitoSugar {
  val mockPoster = mock[PosterAggregate]

  "createPost" should "fire a createPost event" in {
    VivoClientInboundGateway.addListener(mockPoster)
    VivoClientInboundGateway.createPost("test post")
    verify(mockPoster).receiveEvent(Event("createPost", "test post"))
  }
}
