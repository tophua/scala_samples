package currying

/*
  #currying 柯里化
  related: #partially-applied-function
*/

/*
 * The difference between a Function that returns a function and Currying.
 * 返回函数的函数和Currying之间的区别。
 * Currying - is a function that expects parameters but if those are missed -
 * 柯里 - 是一个函数,期望参数,但如果这些都是错过的
 * returns another function where (rest of) those params are expected to be passed to than new function afterwards.
 * 返回另一个函数,其中(其余部分)这些参数预计将被传递给之后的新函数。
 * To be able to miss some of parameters we have to use "_".
 * 为了能够错过一些参数,我们必须使用“_”。
 */
object CurryingTest extends App {

{
  // 1. not Currying - it just returns a anonymous function with predefined body/algorithm
  //1.柯里化 - 它只是返回一个匿名函数与预定义的主体/算法
  //使用匿名函数,期望b:Int作为参数,并使用父函数“a”
  def add1(a:Int) = { b:Int => a + b } // use anonymous function, that expect b:Int as param and use "a" from parent-function
  // 1.1
  //1.1: 11
  println ("1.1: " + add1(5)(6))

  // 1.2
  //add1(5)_  // can not be treated as 'partially applied function',
  //不能被视为“部分应用函数”
  //            // because there is no 2nd parameter defined, so there is nothing to omit then
  //因为没有定义第二个参数,所以没有什么可以省略的
  //只使用一个参数,不需要把它看作“部分应用的函数”，
   val f = add1(5) // only one param is used. no need to treat this as 'partially applied function',
                    // no need to pass "_", does not require 2nd parameter passed (see next example)
  //1.2: 11
   println("1.2: " + f(6) )

}

  println()

{
  // 2. Currying - it does not return a function - it returns an Int result.
  // 2. Currying - 它不返回函数 - 它返回一个Int结果。
  // It expects 2nd parameter to be passed,
  //它期望第二个参数被传递,
  // but if that is not provided - it returns a function that expects this parameter
  //但如果没有提供 - 它会返回一个期望这个参数的函数
  def add2(a:Int)(b:Int) = {a + b}

  // 2.1
  //2.1: 11
  println ("2.1: " + add2(5)(6))
  //不会编译,期待第二个参数通过
  //val f =  add(5) // does not compile, expecting 2nd param passed
  val f =  add2(5)_  // can treat it as 'partially applied function' because 2nd parameter is defined,
                     //可以将它作为'部分应用函数',因为第2个参数是定义的,
                        // so we may mark it to be omitted  (#partially-applied-function related)
  //所以我们可以将其标记为省略(＃部分应用函数相关)
  println("2.2: " + f(6) )

}

}
