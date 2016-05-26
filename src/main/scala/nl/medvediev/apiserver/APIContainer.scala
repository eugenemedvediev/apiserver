package nl.medvediev.apiserver

import org.simpleframework.http.core.Container
import org.simpleframework.http.{Request, Response, Status}

/**
  * Created by ievgen.medvediev on 26/05/16.
  */
class APIContainer extends Container {

  override def handle(request: Request, response: Response): Unit = {
    response.setCode(Status.OK.getCode)
    response.setValue("Content-Type", "application/json")
    response.getPrintStream.println(
      """
        |{"message": "API Server Works"}
      """.stripMargin)
    response.getPrintStream.close()
    response.close()
  }
}