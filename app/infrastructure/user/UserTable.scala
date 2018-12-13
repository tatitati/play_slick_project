package infrastructure.user

import App.Domain._
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

class UserTable(tag: Tag) extends Table[User](tag, "user") {

  def id = column[String]("id", O.PrimaryKey)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")

  override def * =
    (id, firstName, lastName) <> (User.tupled, User.unapply)
}