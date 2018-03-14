package partially_applied_functions

/*
 #partially-applied-function
 related: #currying
*/

object PartiallyAppliedFunction extends App {

  // #1
  def f1(a:Int, b:Int) = a + b  // was not born to be partial (there is no any "_" in its definition)

   //def partFun = f1(2)_  // can not do it..  (not so easy.. eh? )

  // #1.1
  // but we can reuse this - to turn it into partially applied function:
  //但我们可以重复使用 - 将其转化为部分应用的函数：
  val x = f1(1, _:Int)  // first argument is defined, but since we use "_" then 'x' is partially applied function with ine argument
  println("x: " + x ) // <function1>
  println("x(2): " + x(2))

  // #2 "currying",柯里化
  //这是“科里化”,我们可以使用部分应用函数（#currying相关）
  def f2(a:Int)(b:Int) = a + b  // this is "currying". we can use partially applied function with it (#currying related)

  def partFun = f2(2)_

  println("partFun: " + partFun)
  println("partFun: " + partFun(2))

  // thus, to convert "currying"-function to partial one is easier than do the same from general function..
  //此,将“currying”功能转换为部分功能比从一般功能中转换更容易
 /*
 Output:
  x: <function1>
  x(2): 3
  partFun: <function1>
  partFun: 4
 */
}
