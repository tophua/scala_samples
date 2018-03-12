package apply_method

case class Person (name:String)

class Person007 private( val name:String)

object Person007 {
  def apply(name:String) : Person007 = {
    //伴生对象可以访问私有构造函数
    new Person007(name)      // companion object has access to private constructor
  }
}

object PrivateConstructor extends App {

  val p1 = Person(name = "bob")
  //如果我们没有Person 007伴随对象,将不会编译
  val p2 = Person007(name = "007")    // will nto compile if we did not have Person007 companion object

  println("===="+p1.name)
  print(p2.name)
}
