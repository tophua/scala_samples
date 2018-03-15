/**
  * create by liush on 2018-1-26
  */
//隐式转换
object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt) {//声明2个参数,,第二个是可以隐式的,当然你也可以显式的写
    println("Welcome, "+ name +". The system is ready.")
    println(prompt.preference)
  }
}
case class PreferredPrompt(val preference:String)
object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master> ") //声明成implicit,可用作补充
}

import JoesPrefs._
object greeter extends  App{
  Greeter.greet("Joe") //编译器会自动补充成,greet("Joe")(prompt)

}