package nl.medvediev.apiserver

import java.io.IOException
import java.net.InetSocketAddress

import org.simpleframework.http.core.ContainerSocketProcessor
import org.simpleframework.transport.connect.SocketConnection

/**
  * Created by ievgen.medvediev on 26/05/16.
  */
class APIServer(initPort: Int) {

  private var socketConnection: SocketConnection = _
  private var port = initPort

  def getPort: Int = port

  def start: APIServer = {
    this.socketConnection = startServer()
    this
  }

  private def startServer(): SocketConnection = {
    def connect(connection: SocketConnection, port: Int): SocketConnection = {
      val socketAddress = new InetSocketAddress(port)
      try {
        connection.connect(socketAddress)
        this.port = socketAddress.getPort
        connection
      } catch {
        case e: IOException => connect(connection, port + 1)
      }
    }

    val container = new APIContainer()
    val connection = new SocketConnection(new ContainerSocketProcessor(container))
    connect(connection, port)
  }

  def stop() = {
    if (socketConnection != null) socketConnection.close()
    port = initPort
  }
}
