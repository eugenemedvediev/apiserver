package nl.medvediev.apiserver

import org.scalatest.FunSuite
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws.ning.NingWSClient

/**
  * Created by ievgen on 26/05/16.
  */
class APIServerTest extends FunSuite {

  test("start") {
    // given
    val server: APIServer = new APIServer(9090)
    server.start

    val wsClient = NingWSClient()

    // when
    wsClient
      .url(s"http://localhost:${server.getPort}")
      .get()
      .map { response =>
        // then
        server.stop()
        assert(response.status === 200)
        assert(response.header("Content-Type") === "json")
        assert(response.body === """
                                   |{"message": "API Server Works"}
                                 """.stripMargin)
      }
  }

  test("reuse port") {
    // given
    val server1 = new APIServer(9090)
    val server2 = new APIServer(9090)

    // when
    server1.start
    server2.start

    // then
    assert(server1.getPort >= 9090)
    assert(server2.getPort > 9090)
    assert(server1.getPort != server2.getPort)

    // when
    server1.stop //port free
    server2.stop //port + 1 free

    // then
    assert(server1.getPort === 9090)
    assert(server2.getPort === 9090)
  }
}
