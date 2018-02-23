package calling_by_name

// #calling by name - arguments that are evaluating on the moment of use, but not on the moment of passing them to function.
// #partially applied function - use "_" to omit some arguments and return a new function instead that expects rest of not-yet-applied arguments to be passed to that new function
// #higher order function - take function as parameter

// Calling function By Name

/*
  #calling-by-name
  related: #calling-by-value #lazy-evaluation #anonymous-function #partially-applied-function #higher-order-function
*/

// sequence: #2 (look at #1 first)
object CallingFunctionByNameTest extends App {

  var var1 = 0
  var var2 = 0

  // 1.
  // the function that accepts arg-function with: two int params and returning String
  //接受arg-function的函数有：两个int params并返回String
  // the function passing v1 & v2 as parameters to arg-function, invoking arg-function 2 times, connecting the result to one string
  //该函数将v1＆v2作为参数传递给arg-function,调用arg-function 2次，将结果连接到一个字符串
  def takeFunction1(f: (Int, Int) => String, v1:Int, v2:Int ): String = {
    f(v1, v2) + f(v1, v2)
  }

  // 2. same as #1 but calling arg-function by-name
  //2,与＃1相同，但是按名称调用arg-function
  def takeFunction2(f: => ((Int, Int) => String), v1:Int, v2:Int): String = {
    f(v1, v2) + f(v1, v2)
  }


  def aFun(v1:Int, v2:Int) : String = {
    var1 += 1
    (v1 + v2).toString
  }


  /* nGen does not have any parameters defined (on first glance),
  nGen没有定义任何参数（乍一看），
   * but since fnGen returns 'partially applied function' which made by using "_" on aFun,
   * 但是由于fnGen返回在aFun上使用“_”生成的“部分应用函数”
   * then it makes fnGen returns the function that's able to apply parameters
   * 那么它使得fnGen返回能够应用参数的函数
  */
  def fnGen() = {

    var2 += 1

    aFun _

  }

  // --
  // first try - aFun will be evaluated immediately, once by passing it
  //首先尝试 - aFun将被立即评估，一次通过
  println( takeFunction1( aFun, 2, 2) )   // btw.: we can not use 'aFun()' with brackets here !
  println("var1 = " + var1)                  // "aFun" interpreted as: (Int, Int) => String; (as expected by 'takeFunction1')
                                             // "aFun()" interpreted as: String

  println( takeFunction2( aFun, 2, 2) )  // evaluated immediately anyhow, because passing a reference to aFun leads it its initialization (it can not exist without its params)
  println("var1 = " + var1 + "\n")


  // second try
  println( takeFunction1( fnGen(), 2, 2) )
  println("var2 = " + var2)

  println( takeFunction2( fnGen(), 2, 2) ) // fnGen() will be evaluated afterwards, each time when on the moment of calling it
  println("var2 = " + var2)                    // fGen() - a reference to partial function, that does not require params to be passed to create it

  /* Output:
  44
  var1 = 2
  44
  var1 = 4

  44
  var2 = 1
  44
  var2 = 3
  */


}


