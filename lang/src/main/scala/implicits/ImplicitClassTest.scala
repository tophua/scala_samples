package implicits

/*
  #implicit
  related: #implicit-class #implicit-parameters
 */
/*
 * Add new method to exiting Int class, That works in particular scope.
 * 将新方法添加到现有的Int类中,这在特定范围内起作用。
*/
object ImplicitsScope {
  // defines implicits method for Int class
  //定义Int类的蕴涵方法
  implicit class ExtendedInt(x: Int) {  // the method of class is not really matter, it is jus a scope
    //类的方法并不重要,它是一个范围
    //new方法times()将被添加到Int类中
    def times[A]( f: => A ): Unit = {    // new method 'times()' will be added to Int class
      for(i <- 1 to x) {
        f
      }
    }

  }

}


object Starter extends App {

  foo1

  def foo1 = {
    import implicits.ImplicitsScope._
    //你看!在Int类中添加了一个新的方法times()(仅限于此上下文!)
    5 times println("hello")  // you see ! there is a new method times() added in Int class (for this context only!)
  }

  // this will not work, because scope where method "times()" was defined is not imported
  //这将不起作用，因为方法“times（）”的定义范围不被导入
  /*
  def foo2 = {
    5 times println("this should not work")
  }*/

}
