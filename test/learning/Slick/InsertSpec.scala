package learning.Slick

import infrastructure.user.{User, UserSchema}
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.guice._
import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import play.api.test._
import slick.jdbc.{JdbcProfile, MySQLProfile}
import slick.jdbc.MySQLProfile.api._

class InsertSpec extends FunSuite with GuiceOneAppPerTest with Injecting with MockitoSugar with Exec {
  val userTable = TableQuery[UserSchema]


  test("can insert a single row") {
    // clean db
    implicit val db = DatabaseConfigProvider.get[JdbcProfile]("mydb")(Play.current).db
    exec(userTable.delete)

    val rows = exec(userTable += User("eeeeee", "ffffff"))

    assert(rows == 1)
    assert(exec(userTable.result).size === 1)
  }

  test("can insert two rows") {
    // clean db
    implicit val db = DatabaseConfigProvider.get[JdbcProfile]("mydb")(Play.current).db
    exec(userTable.delete)


    val rows = exec(userTable ++= Seq(User("aaaaaa", "bbbbb"), User("cccccc", "ddddd")))

    assert(rows == Some(2))
    assert(exec(userTable.result).size === 2)
  }

  test("can return the id of the new inser") {
    implicit val db = DatabaseConfigProvider.get[JdbcProfile]("mydb")(Play.current).db
    exec(
        userTable.schema.drop andThen
        userTable.schema.create
    )


    exec(userTable.delete)

    var rows = exec(userTable returning userTable.map(_.id) += User("eeeeee1", "ffffff1"))
    assert(rows == 1)

    rows = exec(userTable returning userTable.map(_.id) += User("eeeeee2", "ffffff2"))
    assert(rows == 2)

    rows = exec(userTable returning userTable.map(_.id) += User("eeeeee2", "ffffff2"))
    assert(rows == 3)
  }
}
