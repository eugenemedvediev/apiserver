package nl.medvediev.apiserver

import org.simpleframework.http.core.Container
import org.simpleframework.http.{Request, Response}

/**
  * Created by ievgen.medvediev on 26/05/16.
  */
class APIContainer extends Container {
  override def handle(request: Request, response: Response): Unit = {
    response.getPrintStream.println("API Server works")
    response.getPrintStream.close()
    response.close()
  }
}