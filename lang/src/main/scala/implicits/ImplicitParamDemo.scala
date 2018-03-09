package implicits

/**
  * create by liush on 2018-3-9
  * 隐式参数
  * 隐含参数有点类似缺省参数,如果在调用方法时没有提供某个参数,编译器会在当前作用域查找是否有符合条件的implicit 对象可以作为参数传入,
  * 不同于缺省参数,隐式参数的值可以在方法调用的前的上下文中指定,这是隐式参数更加灵活的地方。
  */
object ImplicitParamDemo {

  object Greeter{
    def greet(name:String)(implicit prompt: String) {
      println("Welcome, " + name + ". The System is ready.")
      println(prompt)
    }
  }

  def main(args: Array[String]) {

    implicit val prompt = ">"

    Greeter.greet("admin")
  }
}
