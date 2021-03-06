package controllers

import application.Speaker._
import javax.inject._
import play.api.db.slick.{DbName, SlickApi}
import play.api.libs.json.Json
import play.api.mvc._
import infrastructure.user.{User, UserDao}

import scala.concurrent.{ExecutionContext, Future}


class HomeController @Inject() (
                                 // the @inject notation inect all these params, ALL of them, not only the binded in Module.scala.
                                 // For example, if I delete @inject and the binded one, the app still crashes, as it cannot inject the rest of parameters
                                 cc: ControllerComponents,
                                 injectedSpeaker: SpeakerInt, // DI Binded with "binding annotations"
                                 injectedWorker: WorkerInt,   // DI Binded with "pragmatic binding"
                                 englishSpeaker: EnglishSpeaker,
                                 slickApi: SlickApi,
                                 userDao: UserDao,
                                 executionContext: ExecutionContext
                               ) extends AbstractController(cc) {

    case class Animal(val age: Int, val name: String)
    val listUsers = List(
        Animal(32, "user1"),
        Animal(45, "user2")
    )

    val hello = Action {
        Ok("hello!!!!!!")
    }

    def detail = Action {
        Ok(Json.obj(
            "id" -> 2323,
            "name" -> "anyname"
        ))
    }

    def list = Action {
        Ok(Json.arr(
            listUsers.map(
                userItem => Json.obj(
                    "age" -> userItem.age,
                    "name" -> userItem.name
                )
            )
        ))
    }

    def speak_injectedspeaker = Action {
        Ok(injectedSpeaker.sayHello())
    }

    def speak_injectedworker = Action {
        Ok(injectedWorker.sayWork())
    }

    def sayenglish = Action {
        Ok(englishSpeaker.sayHello())
    }

    def insert = Action.async { implicit request =>
        userDao.insert(
            User("firstnameeee", "lastnameeeee")
        ).map(_ => Ok("done"))(executionContext)
    }

    def createdb = Action {
        userDao.createDatabase
        Ok("done")
    }

    def index() = Action { implicit request: Request[AnyContent] =>
        Ok(views.html.index())
    }
}
