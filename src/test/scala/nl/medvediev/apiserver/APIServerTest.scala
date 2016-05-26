package nl.medvediev.apiserver

import org.scalatest.FunSuite
import play.api.libs.ws.ning.NingWSClient
import play.api.libs.concurrent.Execution.Implicits._

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
      .url("http://localhost:9090")
      .get()
      .map { response =>
        // then
        assert(response.status === 200)
        assert(response.body === "API Server works")
        println(s"Response body is: '${response.body}'")

      }
  }
}
