package function_literal

/*
    #function-literal #function-value #anonymous-function
    函数文字＃函数值＃匿名函数
*/
object FunctionLiteral extends App {

  // Just reminding - this is general function
  //只是提醒 - 这是一般功能
  def foo(a: Int) = a + 1 // it has name 'foo'

  // So we can call it like this:
  //所以我们可以这样称
  //每次你调用这个函数时,它都会实例化Function1的对象
  val result1 = foo(1)  // each time when you call this function it will instantiate object of Function1



  // And this is #anonymous-function - there is no name of this function - you see, no name = anonymous:
  //这是＃匿名函数 - 没有这个函数的名字 - 你看,没有名字=匿名：
  //所以,是的 - 这是类Function1的一个实例
  (a: Int) => a + 1 // so, yes - this is an instance of class Function1



  // Sometimes though, we want to make use of #anonymous-function having a reference to it
   //有时候,我们想利用＃匿名函数来引用它,然后它不再是匿名的,我知道但是让我们说我们不知道它的名字,但只有昵称
     // then it is not anonymous anymore.. I know but let's say we don't know its name but only nick name
  // Here are we go.. when it comes to referencing #anonymous-function - #Function-Literal appears
  //这里是我们去..当涉及到＃匿名函数 - ＃函数文字出现
  // #Function-Literal is about syntax sugar, is an alternative form how we can define a function:
  val v = (a: Int) => a + 1 // v: Int => Int = <function1>
  def f = (a: Int) => a + 1 // f: Int => Int

    // This reference 'v' is - #Function-Value because it represents a value (instance) of Function1 instance
    //此引用“v”是 - ＃函数 - 值，因为它表示Function1实例的值(实例)
    // ( So, the notion of #Function-Literal
        // is somewhere in-between two notions: #Anonymous-Function & #Function-Value )

  // This will NOT create instance of Function1 - because it already exists. That's its advantage.
  //这不会创建Function1的实例 - 因为它已经存在,这是它的优势。
  val resultV = v(1)

  // This will CREATE Function1 instance each time when we call it (same as for normal function)
  //这将在我们每次调用它时创建Function1实例(与正常函数相同)
  val resultF = f(1)
  val resultF2 = f(1) // yes, each time - new object. Think about it !




  // Q: What about this? Is it Function Literal?
  //问：这个怎么样?它是函数文字吗?
  val v2 = new Function1[Int, Int] {
    println("test")                       // you will see that this will be printed only once when you will be using this function
    def apply(a: Int): Int = a + 1
  }

  def f2 = new Function1[Int, Int] {      // so, f(a:Int): Int just a syntax sugar for that one
    println("test")                       // you will see that this will be printed all the time when you invoke this function
    def apply(a: Int): Int = a + 1
  }

  // A: Not there are not - because, remember #Function-Literal is about syntax (sugar)
  // - lightweight syntax for defining #anonymous-functions

  // But technically v1 is the same as v2, and f1 is same as f2



  // Q: should we use #Function-Literal and #FunctionValue every time ?

  def myFunction(f: (Int)=>Int ) = {
    f(1)
  }

  // To avoid function-instance-creation for every call ?

  myFunction(v) // no function creation !
  myFunction(v) // no function creation !

  // A: Probably yes - if we can. But sometimes we can not :

  def myFunction2[A] = (a:A) => a

  // because we can not write in a #Function-Literal in the way of:

  // val goodTry[A] = (a:A) => a   // compilation error: "wrong value declaration"

  val resultF3 = myFunction2[Int](1) // but this works, of course
  println (resultF3) // 1

 // To remember as an example:
  // 4 - is integer literal
  // (2+2) - is not

  // in our case/example we operate by functions (but not integer) - so it is about #Function-Literal
}
