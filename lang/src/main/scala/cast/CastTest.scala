package cast

import scala.concurrent._
import scala.concurrent.duration._

import ExecutionContext.Implicits.global

trait Order {
  var aType:String = _
}

class OrderB extends Order {
  aType = "b"
}
class OrderC extends Order {
  aType = "c"
}

class Client {
  def send(payload:String): Future[String] = {
    Future { "bright future" }
  }
}
//强制类型转换
object CastTest extends App {

  // there is some 'interesting' stuff about casting and
  //有一些关于强制类型转换的“有趣”的东西
  val l: List[Int] = List[String]("a").asInstanceOf[List[Int]]

  println(l)      // List[Int] = List(a)

  println(l.head) // a

  // --

  val client = new Client()

  val fResult = fun(new OrderB())

  val result = Await.result(fResult, 10.seconds)

  println(result)

  //强制类型转换
  //当调用包含隐式参数的方法是,如果当前上下文中有合适的隐式值,则编译器会自动为改组参数填充合适的值。
  //如果没有编译器会抛出异常。当然,标记为隐式参数的我们也可以手动为该参数添加默认值
  def fun(a:Order)(implicit executionContext: ExecutionContext) : Future[String]  = {

    val x = a.asInstanceOf[OrderC] // case cast exception

    client.send("the payload")

  }
  

}
