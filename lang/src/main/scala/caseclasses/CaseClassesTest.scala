package caseclasses

/*
 #case-classes
 related: #apply-method #unapply-method #extractor
*/
object CaseClassesTest extends App {

  case class A(a:Int, b:Int)

  // 1. may omit using "new"
  //1.可以省略使用“新”
  val a1 = A(1,2)  // same as A.apply(1,2)


  // 2. toString() is defined by default
  //2. toString（）是默认定义的
  println(a1) // prints: A(1,2)


  // 3. public read-only(getters) properties by default
  //3.默认情况下公共只读（getters）属性
  //所以不需要在类似于一般类的参数中使用“val a：int”
  println(a1.a) // so no need to put "val a:int" in arguments like for general classes

  //a1.a = 1 // can no do it (read only)


  // 4. equals() defined by default
  //4. equals（）默认定义
  val a2 = A(1,2)
  //方法'=='使用默认的内置'equals（）'
  if (a1 == a2) println ("equal!")  // method '==' uses default built-in 'equals()'

  // 5. you we want setters to be defined
  //5.你们想让定义者定义
  //我们需要把'var'（和一般类相同）
  case class B(var a:Int, var b:Int) // we need to put 'var' (same as for general classes)
  val b1 = B(1,2)
  //没关系，因为它被定义为VAR
  b1.a=2  // that's ok, because it was defined as VAR

  // --

  // 6. works with "pattern matching" - #unapply-method #extractor related
  //6.与“模式匹配” - ＃unapply-method #extractor相关
  val b2 = B(1,2)
  var str = b2 match {
      ////它的工作原因是unapply-method（提取器）方法是默认定义的
    case B(1,_) => "yes, first param is '1'"  // it works because of unapply-method (extractor) method is defined by default
  }
  println(str)

  // 7. inheritance
  //7.继承
  {
    case class A(a:Int)
    //确实允许我们扩展，要求“val / val”和“override”
    //case class A2(a:Int) extends A(1)               // does allow us to extend, asks for 'val/val' and 'override'
    ////但它仍然不允许 禁止使用继承！
    //case class A2(override val a:Int) extends A(1)  // but it still does not allow. It is prohibited to use inheritance !


    // but
    ////如果我们有一般类（不是case类）
    class GeneralClass(val a:Int) // if we have general class (not case class)
    ////然后我们可以从它扩展我们的case类
    case class AA(override val a:Int) extends GeneralClass(1) // then we can extend our case class from it

    val g:GeneralClass = AA(2)

    println ("inherited, and overridden: " + g.a) // 2
  }

  // 8. a case class that accepts a function (what would happen ? )
  //8.接受函数的case类（会发生什么？）
  {
    //预期功能为参数的case class t
    case class F( f: Int => Int) // the case class that expects a function as parameter
    //函数返回两次以上
    def f(a:Int) = {a + a} // function that returns back in two time more than it gets

    val obj = F(f)
    //其实..它是一个返回一个函数的getter。 像预期一样工作。
    val f_ref = obj.f // actually.. it is a getter that returns a function. works like expected.
    println("result:"  + f_ref(2))

    // and how patter-matching will work with it?
    //以及模式匹配将如何与它一起工作？
    obj match {
      case F( f:(Int=>Int) ) => println(" f:(Int=>Int) matched")  // works as expected
    }
    obj match {
      case F( f:(Any=>Any) ) => println(" f:(Any=>Any) matched")  // works as expected
    }
    obj match {
      case F( _ ) => println("_ matched")                         // works as expected
    }
    /*
    obj match {
      case F( 4 ) => println("will not work") // it expects a function, no a Int value (or function result)
    }*/

  }

}
