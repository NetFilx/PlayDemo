package controllers

import javax.inject.Inject

import dao.StudentDao
import models.Student
import slick.driver.JdbcProfile
import play.api.mvc._
import play.api.db._
import play.api.db.slick.DatabaseConfigProvider


class Application @Inject()(db:Database,dbConfProvider:DatabaseConfigProvider) extends Controller {
  val dbConf = dbConfProvider.get[JdbcProfile]
  import dbConf.driver.api._
  def index = Action {
    //Ok(views.html.index("Your new application is ready."))
    var outString = "Number is "
   /**************常规的JDBC连接方式*******************/
//    val conn = db.getConnection()
//    try{
//      val stmt = conn.createStatement
//      val rs = stmt.executeQuery("SELECT student_name FROM students")
//      while(rs.next){
//        outString += "name: " + rs.getString("student_name")
//      }
//    }finally {
//      conn.close()
//    }
    /**************常规的JDBC连接方式*******************/

    /**************数据库交给play管理*******************/
    db.withConnection { conn =>
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT * FROM students")
      while(rs.next) {
        outString += "name==: " + rs.getString("student_id")
      }
    }

    //db.withConnection("database")对于其他非默认的数据库
    /**************数据库交给play管理*******************/

    /**************slick连接数据库*******************/
    val studentDao = new StudentDao()
    studentDao.insert(new Student(2,"limbo"))

    /**************slick连接数据库*******************/

    Ok(outString)
  }

}