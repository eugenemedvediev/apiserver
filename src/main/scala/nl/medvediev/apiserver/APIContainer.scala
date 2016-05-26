package nl.medvediev.apiserver

import org.simpleframework.http.{Request, Response}
import org.simpleframework.http.core.Container

class APIContainer extends Container {
  override def handle(request: Request, response: Response) : Unit = {
    response.getPrintStream.println("API Server works")
    response.getPrintStream.close()
    response.close()
  }
}