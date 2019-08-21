package controllers

import javax.inject.{Inject, Singleton}
import play.api.Logging
import play.api.libs.json.{Json, OFormat}
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

case class Paragraph(text : String)

@Singleton
class ResourceController @Inject()(cc: ControllerComponents)(implicit ec : ExecutionContext) extends AbstractController(cc) with Logging {

  implicit val paragraphFormat: OFormat[Paragraph] = Json.format[Paragraph]
  var paragraphList : List[Paragraph] = List()

  def addParagraph() = Action.async(parse.json) {
    implicit request => {
     request.body.validate[Paragraph].fold(
       errors =>
         Future {
           errors.foreach {
             case (jsPath, jsError) =>
               jsError.map(_.toString).foreach(logger.info(_))
           }
           BadRequest(errors.mkString)
         }
         ,
       paragraph =>
         Future {
           paragraphList = paragraphList ::: List(paragraph)
           Ok("")
         }
     )
    }
  }

  def paragraphs() = Action {
    Ok(Json.toJson(paragraphList))
  }

}