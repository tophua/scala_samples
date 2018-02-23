package closure

//# Closure
//闭包
object ClosureSample extends App {

// Closure - is all about encapsulation
  //关闭 - 关于封装
  object scope { // defining a scope to hide/close our data
  //定义一个范围来隐藏/关闭我们的数据
    private var data = 1 // here we 'close' data in a scope
  //这里我们关闭一个范围内的数据

    def setData(value:Int) = { // so that setScope has access to it
      //以便setScope可以访问它
      data = value                // using it, by setting new value to it ..
      data
    }

  }
// 把函数作为函数直接使用（只是为了方便）
  val setData : (Int=>Int) = scope.setData  // tacking ref to the function as Function Literal (just of convenience)
  //所以当我们通过这个函数作为参数
  val result = myFunction( setData, 2 ) // so when we pass this function as an argument

  def myFunction(f: Int=>Int, p:Int) = { 
    f(p)                                 // it will able to have access to 'data' and change its value
    //它将能够访问“数据”并更改其值
                                // so 'f' ('setData') functions keeps access to 'data'
                                // even though 'data' is hidden/closed - it closes it
  }
  //以便我们看到变化的结果
  println ( result  ) // so that we see change result

// -- In Java and other ancient languages you would usually do this (but that is not closure)
  //在Java和其他古代语言中，你通常会这样做（但不是封闭的）

  //定义接口 - 我们必须定义它
  abstract class ScopeSetter {  // define interface - we have to define it
    def setData(value:Int):Int      // since we want expose setData 因为我们要暴露setData
  }                                   // since we are not going to pass function as an parameter
  //因为我们不会将函数作为参数传递
  class ScopeSetterImpl extends ScopeSetter { // one of possible implementations 可能的实现之一
    private var data = 1       // our private data 我们的私人数据
    override def setData(value: Int) = {
      data = value             // changing that data 改变这些数据
      data
    }
  }

  val obj:ScopeSetter = new ScopeSetterImpl // ref to base interface 参考基础接口
  //在这里我们传递对象的引用，但不是函数
  val result2 = myFunction(obj) // here we passing reference to the object, but not to the function
  //期待一个ScopeSetter的实例
  def myFunction(setter: ScopeSetter) = { // expecting an instance of ScopeSetter
    obj.setData(2) // so we can call setData() method on it 所以我们可以调用setData（）方法
  }

  println( result2 )


// --- But just a reminding, in Scala function is an instance of Function class which has apply method
//但是提醒一下，在Scala中函数是一个Function类的实例，它具有apply方法
  object scope2 {

    var data = 1

    def setData = new Function1[Int, Int] { // function - is an object ! that has
      def apply(value: Int): Int = { // an interface with apply() method in it
        data = value
        data
      }
    }

  }

  // so...the same
  //仅供参考：“setData”是一个对象（带有已知接口）的函数
  val result3 = myFunction(scope2.setData, 2) // FYI: 'setData' is a function an object (with known interface) at the same time
  //就是这样 - 功能已经是“接口”，所以不需要创建一个
  def myFunction3(f:(Int=>Int), p:Int) = { // That's it - function is already 'interface', so no need to create one
    f(p)
  }

  println (result3)

  /*
   So.. The closure is ability of function to keep/remembering
   all stuff (val, vars..) from the scope where this function was defined
   and keep remember this in the scope where this function is executing.

   Same as it is done in OOP word - with encapsulation and polymorphic access.

   That's being said, it makes possible to implement design patterns like:
   Strategy, Visitor, Command, Functor, Callback/Listener  etc.
   in light way manner - with no interfaces and classes function way.
 */

}
