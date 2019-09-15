package services

import javax.inject.{Inject, Singleton}
import model.Scene
import play.api.Logging
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json.{JsValue, Json}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SceneService @Inject()
()
(implicit ec : ExecutionContext, protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with Logging {
  import profile.api._

  private implicit val jsValueMappedColumnType: BaseColumnType[JsValue] =
    MappedColumnType.base[JsValue, String](Json.stringify, Json.parse)

  class Scenes(tag : Tag) extends Table[Scene](tag, "scene") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def info = column[JsValue]("info")

    override def * : ProvenShape[Scene] =
      (id.?, info) <> (Scene.tupled, Scene.unapply)
  }

  val scenes = TableQuery[Scenes]

  def all() : Future[Seq[Scene]] =
    db.run(scenes.result)

  def byId(id : Long) : Future[Option[Scene]] =
    db.run(scenes.filter(_.id === id).result).map(_.headOption)

  def save(scene: Scene) : Future[Int] =
    db.run(scenes += scene)
}
