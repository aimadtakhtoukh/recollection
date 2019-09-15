package model

import play.api.libs.json.JsValue

case class Scene(id : Option[Long], info : JsValue)
