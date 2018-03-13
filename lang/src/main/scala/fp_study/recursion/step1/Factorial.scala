package fp_study.recursion.step1

// 1. Understanding recursion

// "Head recursion"
//头递归
object Factorial extends App {

  def factorial(i:Int) : BigInt = {      // let's take a look if on the call-flow if i = 5
    if (i  == 0) 1
    else i * factorial(i-1)           // f(5): 5 * f(4)  // 5 * (4 * ( 3 * (2*1) )
                                      // f(4): 4* f(3)   // 4 * (3 * (2*1)
                                      // f(3): 3 * f(2)  // 3 * (2*1)
                                      // f(2): 2 * f(1)  // 2 * f(1)
                                      // f(1):  = 1 * f(0) // 1 * 1   // <- here we got "real"/evaluated value "1"
  }

  // note: "i * " - this is "head" of recursion call
  //注意：“i*” - 这是递归调用的“head”
  println ( factorial(1) )
  println ( factorial(2) )
  println ( factorial(3) )
  println ( factorial(4) )
  println ( factorial(5) )

  // The important thing to understand is:  that function calls are saving into the Stack
  // and it all these calls are starting evaluating it gets real value.
  //要理解的重要事情是：函数调用正在保存到堆栈中,并且所有这些调用都开始评估它获得实际价值
  // Then it gets back to from the very end stack call to the beginning, evaluating one after another.
  // (In our case real value is = 1).


  // as you understand, sometimes stack might be overflowed, let's try this:
  //如你所知，有时栈可能会溢出，让我们试试这个：
  println( "factorial(1000): " + factorial(1000) ) // ve...ee...ery big Int

  // but if uncomment this:

  // println( "factorial(9999): " + factorial(9999) )  // java.lang.StackOverflowError


  // See "Factorial2" example to see how to use "tail recursion"

}
