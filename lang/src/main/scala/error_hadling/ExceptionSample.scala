package ExceptionSample

import scala.util.Try
import scala.io.BufferedSource
import java.io.IOException

// #exceptions. #Try
//
object ExceptionSample extends App {

 // # 1 syntax: "catch { case ..."
  //1 语法：“catch {case ...”
 {

    def loadFile(filename: String, encoding: String = "utf-8"): Option[String] = {
      try {
        val source = scala.io.Source.fromFile(filename, encoding)
        val contents = source.mkString
        source.close()
        Some(contents)
      } catch {  // catch expect PartialFunction
        //异常处理部分
        case x: IOException =>  return None
        case x => errorHandler(x) // if any other exception
      }
    }

    def errorHandler(value: Any) = None

    val result = loadFile("")

    println( result )  // None

}

 // #2 scala.util.Tty   -- check this ouy it is much easier, seems
  //scala.util.Tty - 检查这个ouy这是很容易，似乎
{


  val result = Try {scala.io.Source.fromFile("some-file.txt", "UTF-8")}.toOption
  //所以我们正在处理来电方的错误。 让我们打电话来决定如何去与错误
  result match {                   // so we are handling error on Caller side. Let's caller decide how to go with error
    case None => println (result)
    case s:Some[BufferedSource] => println (s"bufferSource $s is here")
  }
}


}
