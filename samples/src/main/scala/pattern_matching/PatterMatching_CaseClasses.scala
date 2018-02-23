package pattern_matching

/*
 * #pattern-matching #case-classes
 * related: #unapply-method #extractor #companion-object
*/
object PatterMatching_CaseClasses extends App {

  // 1.
  case class A(a:Int, b:Int)

  val a = A(1,2)

  a match {
      //这是可能的,因为case类已经内置了“unapply”定义
    case A(_,_) => println("_,_") // this is possible because case classes have built-in "unapply" defined
  }


  // 2. To understand how pattern-matching works.. 了解模式匹配如何工作
  // Let's define our own unapply method for general class. Kind of reinventing what case-classe provide for us by default
  //让我们来定义我们自己的普通类的不适用方法,重新创建case-classe分类为我们默认提供的一种
  object MyA { // #companion-object related

    def apply(a:Int, b:Int) = new MyA(a, b)

    def unapply(myA:MyA) : Option[(Int, Int)] = {  // here it is. it will be invoked every time when 'case' invoking against it
      //这里是。 每当“情况”调用时都会被调用
      // the body of unapply tells if the argument has matched or not
      //不适用的主体告诉参数是否匹配
      Some(myA.a, myA.b) // in our case it match all the time 在我们的情况下，它始终匹配
    }
  }
  //二个就能够参与模式匹配
  class MyA(val a:Int, val b:Int) // was born to be able to participate in pattern-matching

  val myA = MyA(1, 2) // same as MyA.apply(1,2)

  myA match {
      //例MyA（1,2）'将导致MyA.unapply（1,2）调用,有道理记住这一点
    case MyA(1, 2) => println ("got (1,2)") // 'case MyA(1,2)' will lead to MyA.unapply(1,2) invocation. Make sense to remember this !
  }

  // (that says that if a class does not have unapply method, it can not be used in pattern-matching)
  //（也就是说如果一个类没有不适用的方法，就不能用于模式匹配）
  // let's check whether we can still use pattern magic like "_" having our general class
  //让我们来检查一下,我们是否仍然可以使用像“_”这样的模式魔法
  myA match {
    case MyA(_, _) => println ("got it (_,_)") // all "_"-magic still here even for general(not case) classes
      //所有的“_” - 即使对于一般的（不是大小写的）类，这里仍然有魔力,只要他们有不适用的方法定义
                                                  // provided that they have unapply method defined
  }

  // 3. a case class that accepts a function as a parameter (what would happen ? )
  //3.接受函数作为参数的case类（将会发生什么？）
  {
    //预期功能为参数的case class
    case class F( f: Int => Int) // the case class that expects a function as parameter
    //函数返回两次以上
    def f(a:Int) = {a + a} // function that returns back in two time more than it gets

    val obj = F(f)
    //其实..它是一个返回一个函数的getter。 像预期一样工作。
    val f_ref = obj.f // actually.. it is a getter that returns a function. works like expected.
    println("result:"  + f_ref(2))

    // and how patter-matching will work with it?
    //以及模式匹配将如何与它一起工作
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
