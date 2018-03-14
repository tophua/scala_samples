package pattern_matching

/**
 * #pattern-matching
 * related: #pattern-overlaps #pattern-guards #variable-binding
 */
object PatternMatchingTest extends App {

  // 1.
  val x1 = 1

  val str1 = x1 match {
    case 1 => "one"
    case 2 => "two"
  }
  println (str1) // "one"

  // 2.
  val x2 = 2

  val str2 = x2 match {
      //是的，你可以把这样的条件，这是好的（＃模式卫士相关）
    case x2 if (x2>1) => "more than one"  // yes you can put condition like this, and this is good  (#pattern-guards related)
      //如果在x2 <= 1时错过了这个部分，将会在RUNTIME“scala.MatchError”失败！
    case _ => "default"                 // wil fail in RUNTIME "scala.MatchError" if miss this part when x2 <= 1  !!!
      //所以，规则是所有的情况下都应该被覆盖
  }                                     // so, the rule is all cases should be covered !
  //如果x = 2,则打印：“more than one”,当x2 = 1时打印:“default”
  println (str2) // prints: "more than one" if x=2. And prints: "default" when x2=1

  // 3. Nested cases. !!
  {
    val x1 = 2
    val str = x1 match {   // scala.MatchError: 1 (of class java.lang.Integer)
      case x if(x > 1) => "x"+x match {case "x2" => "yes"}
      case _ => "nope"
    }
    println (str)
  }

  // 4. how it works with lists 它如何与列表一起工作
  val list = List(1,2,3)
  list match {
    case List(_,_,3) => println("yes, there is '3' as a last element")
  }
  list match {
      //将不被打印,因为它匹配2长参数列表
    case List(_,_) => println("yes, there are two element defined") // will NOT be printed because it match to 2 long args list
      //将不会被打印,因为它匹配1长参数列表
    case List(_) => println("could not find what to match 1") // will NOT be printed because it match to 1 long args list
      //将被打印,因为“* _”表示任何参数
    case List(_*) => println("could not find what to match 2") // will be printed because '*_' means any args
  }


  // 5. how it matches by type
  //它是如何按类型匹配的
  def function(x:Any):Unit = x match {

    case _:String => println("yes, this is string")

      case x:Number => println("no, this is NOT string, this is number..")

      // #pattern-overlaps related
      // this will not happen, because Number-match is first in the 'match'. A developer should care about this by himself
      //这不会发生，因为数字匹配是在'比赛'中第一次。 开发者应该自己关心这个
      case _:Int => println("no, this is NOT string, this is Int..")

      case _ => println("who knows..")
  }
  function("123")
  function(123)

  // 6. you can define a variable binding inside 'case' ( #variable-binding related )
  //你可以在'case'中定义一个变量绑定（＃variable-binding related）
  {
    case class AA(a:Int, b:Int)
    val a = AA(1,2)
    a match {
        //myX绑定到x（在这种情况下，myX是x的别名）
      case AA(x @ myX, y) => println("myX: " + myX)  // myX is binding to x (in this case myX is alias to x)
    }
  }

  // 6.1 but '@' sign is more than just aliasing. It is binding .. in wider sense
  //但“@”符号不仅仅是别名。 从更广泛的意义上说，
  // TODO:  See: ExtractorsTest.scala that explains it

}
