package currying

/*
  #currying 柯里化
  related: #partially-applied-function
*/

/*
 * The difference between a Function that returns a function and Currying.
 * 返回函数的函数和Currying之间的区别。
 * Currying - is a function that expects parameters but if those are missed -
 * 柯里 - 是一个函数，期望参数，但如果这些都是错过的
 * returns another function where (rest of) those params are expected to be passed to than new function afterwards.
 * 返回另一个函数，其中（其余部分）这些参数预计将被传递给之后的新函数。
 * To be able to miss some of parameters we have to use "_".
 * 为了能够错过一些参数，我们必须使用“_”。
 */
object CurryingTest extends App {

{
  // 1. not Currying - it just returns a anonymous function with predefined body/algorithm
  //1.柯里化 - 它只是返回一个匿名函数与预定义的主体/算法
  //使用匿名函数，期望b：Int作为参数，并使用父函数“a”
  def add1(a:Int) = { b:Int => a + b } // use anonymous function, that expect b:Int as param and use "a" from parent-function
  // 1.1
  println ("1.1: " + add1(5)(6))

  // 1.2
  //add1(5)_  // can not be treated as 'partially applied function',
  //            // because there is no 2nd parameter defined, so there is nothing to omit then

   val f = add1(5) // only one param is used. no need to treat this as 'partially applied function',
                    // no need to pass "_", does not require 2nd parameter passed (see next example)
   println("1.2: " + f(6) )

}

  println()

{ // 2. Currying - it does not return a function - it returns an Int result.
  // It expects 2nd parameter to be passed,
  // but if that is not provided - it returns a function that expects this parameter
  def add2(a:Int)(b:Int) = {a + b}

  // 2.1
  println ("2.1: " + add2(5)(6))

  //val f =  add(5) // does not compile, expecting 2nd param passed
  val f =  add2(5)_  // can treat it as 'partially applied function' because 2nd parameter is defined,
                        // so we may mark it to be omitted  (#partially-applied-function related)
  println("2.2: " + f(6) )

}

}
