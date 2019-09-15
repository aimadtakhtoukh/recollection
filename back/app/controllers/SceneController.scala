package controllers

import javax.inject.{Inject, Singleton}
import model.Scene
import play.api.Logging
import play.api.libs.json.{Json, OFormat}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.SceneService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SceneController @Inject()(sceneService : SceneService, cc: ControllerComponents)(implicit ec : ExecutionContext) extends AbstractController(cc) with Logging {

  implicit val sceneFormat: OFormat[Scene] = Json.format[Scene]

  def add() = Action.async(parse.json) {
    implicit request => {
     request.body.validate[Scene].fold(
       errors =>
         Future {
           errors.foreach {
             case (jsPath, jsError) =>
               jsError.map(_.toString).foreach(logger.info(_))
           }
           BadRequest(errors.mkString)
         }
         ,
       scene => sceneService.save(scene).map(_ => Ok(""))
     )
    }
  }

  def scenes() = Action.async {
    sceneService.all()
        .map(Json.toJson(_))
        .map(Ok(_))
  }

}