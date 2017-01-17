package dao

import scala.concurrent.Future
import javax.inject.Inject

import models.Student
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

/**
  * Created by limbo on 2017/1/17.
  */
class StudentDao @Inject()(dbConfProvider:DatabaseConfigProvider)extends HasDatabaseConfigProvider[JdbcProfile]{

  import dbConfig.driver.api._

  private val students = TableQuery[StudentTable]
  def all():Future[Seq[Student]] = db.run(students.result)
  def insert(student:Student) : Future[Unit] = db.run(students += student).map {_ => ()}

  private class StudentTable(tag: Tag) extends Table[Student](tag,"students"){
    def id = column[Int]("student_id",O.PrimaryKey)
    def name = column[String]("student_name")

    def * = (id,name) <> (Student.tupled,Student.unapply)
  }
}
