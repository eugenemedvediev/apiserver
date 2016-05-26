package nl.medvediev.apiserver

import java.net.{BindException, InetSocketAddress}

import org.simpleframework.http.core.ContainerSocketProcessor
import org.simpleframework.transport.connect.SocketConnection


/**
  * Created by ievgen on 26/05/16.
  */
class APIServer(port: Int) {

  var socketConnection: SocketConnection = _

  def start: APIServer = {
    this.socketConnection = startServer
    this
  }

  private def startServer: SocketConnection = {
    val container = new APIContainer()
    val connection = new SocketConnection(new ContainerSocketProcessor(container))
    val socketAddress = new InetSocketAddress(port)
    try {
      connection.connect(socketAddress)
    } catch {
      case e: BindException => throw  new IllegalArgumentException(s"Port $port is in use")
    }
    connection
  }

  def stop() = if (socketConnection != null) socketConnection.close()
}
