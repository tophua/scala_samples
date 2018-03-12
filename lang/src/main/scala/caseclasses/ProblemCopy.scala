// #case-class #copy-method #shapless
package caseclasses


sealed trait Thing {
  val id : String
}
case class User(id : String, name : String) extends Thing
case class Item(id : String, description : String) extends Thing

object ProblemCopy extends App {

  // summoning the shapeless gods
  //注意不成形
  import copySyntax._

  val thing1 : Thing = User("unknown", "dave")

  // the following would not compile as copy method does not exist on the Thing trait
  //以下不会编译,因为复制方法不存在于事物特性上
  // but because of "copySyntax._" and shapless lib it works
  //但由于“copysyntax._”和不成形的自由工作
  val copy1 = thing1.copy(id = "1")

  println(copy1) // User(1, dave)


}
