package calling_by_name

// Calling parameter "By Name"
// See also: CallingFunctionByName

/*
  #calling-by-name
  related: #calling-by-value #lazy-evaluation
*/
// sequence: #1 (look at #2 after)
//Scala按名称调用函数
object CallingByNameTest extends App {

  def fByValue(x:Int, y:Int) = {
    //如果x <0，我们不打算使用“y”
    if (x < 0) y else x // if x < 0 we are not going to use "y"

  }

  def fByName(x:Int, y: => Int) = {
    //如果我们不打算使用“y”，则不需要触发它进行评估
    if (x < 0) y else x   // if we are not going to use "y", not need to trigger it to evaluate

  }
  //将通过调用打印文本
  def y() = {println ("y is calling"); 1} // will print the text by calling
  //y（）通过传递来评估（＃值相关的值），但没有人会使用它的值
  val result1 = fByValue( x=0, y=y() ) // y() is evaluating by passing .. (#calling-by-value related) but nobody is going to use its value though
  //y（）不会被评估（＃懒惰评估相关）
  val result2 = fByName ( x=0, y=y() ) // y() will not be evaluated (#lazy-evaluation related)

  println (result1)
  println (result2)
  //
  def time() = {
    println("Getting time in nano seconds")
    System.nanoTime
  }
  //函数的参数是按值参数; 也就是说，参数的值在传递给函数之前确定
  //按名称机制将代码块传递给调用,并且每次调用访问该参数时,代码块被执行并且该值被计算
  def delayed( t: => Long ) = {
    println("In delayed method")
    println("Param: " + t)
  }
  delayed(time())
  /*
  * Output:
  * y is calling
  * 0
  * 0
  * */

}
