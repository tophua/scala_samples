package ExceptionSample

import scala.util.Try
import scala.io.BufferedSource
import java.io.IOException

// #exceptions. #Try
//异常处理
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
  //scala.util.Tty - 似乎,检查这个ouy这是很容易
{

  val result = Try {scala.io.Source.fromFile("some-file.txt", "UTF-8")}.toOption
  //所以我们正在处理调用的错误情况,让我们调用来决定如何处理错误
  result match {                   // so we are handling error on Caller side. Let's caller decide how to go with error
    case None => println (result) //None
    case s:Some[BufferedSource] => println (s"bufferSource $s is here")
  }
}


}
