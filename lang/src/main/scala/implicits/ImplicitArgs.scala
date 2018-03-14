// #implicitly #implicit

package implicits

object ImplicitArgs extends App {

  // version without implicit
  //版本没有隐含的
  def multiply2(f: Int => Int) = f(2)  // apply 2

  // version with implicit
  //版本与隐含
  def multiply2_v2(f: Int => Int) = f(implicitly[Int])
  //implicitly = "inject Int value from the implicit scope"
  //隐式=“从隐式范围注入Int值”

  val result1 = multiply2( x => x * 2) // 2 is 'hardcoded' value here
  //在隐式范围内注册整数值
  implicit val integer = 3          // registering integer value in the implicit scope
  // implicit val integer2 = 4      // error: ambiguous implicit values
  //隐含的val integer2 = 4 //错误：模棱两可的隐式值

  val result2 = multiply2_v2(x => x * 2)

  println (result1) // output: 2 * 2 = 4
  println (result2) // output: 2 * 3 = 6

   // so, the advantage of using 'implicitly'
   // - we are expecting that somewhere in the implicit scope integer value is defined
   // so it works like a configuration by Type
   //我们期望在隐式范围整数值中的某个地方被定义,所以它就像Type中的配置一样工作
   // --

  def function3(f: Int => Int) (implicit x:Int) = {
    f(x) // x=3
  }
  //我们可以使用默认注入的第二个参数
  var result3_1 = function3(x => x * 2)  // we may use second parameter it is injected by default
  var result3_2 = function3(x => x * 2)(4)

  println( result3_1 ) // 3 * 2 = 6
  println( result3_2 ) // 3 * 4 = 8

  // and you can not do this
  //你不能这样做
  //这作为默认变量（ALMOST）
  def function4(implicit i:Int) = i   // this works as default variable (ALMOST)
  // same as this
  def function4_1(i:Int = implicitly[Int]) = i   // implicit scope has Int = 3

  val result4 = function4         // should be without ()
  val result4_1 = function4_1()   // requires ()

  println("result4: " +result4)     // 3
  println("result4_1: " +result4_1) // 3


}
