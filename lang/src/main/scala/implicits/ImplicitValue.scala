package implicits

/**
  * create by liush on 2018-3-14
  * 隐式值：用于给方法提供参数
  */

object ImplicitValue  extends App{
  def person(implicit name : String) = name   //name为隐式参数
  //直接调用person方法,报错！编译器说无法为参数name找到一个隐式值
  //定义一个隐式值后再调用person方法
  implicit val p = "mobin"   //p被称为隐式值
  //implicit val p1 = "mobin1"
  //因为将p变量标记为implicit，所以编译器会在方法省略隐式参数的情况下去搜索作用域内的隐式值作为缺少参数。
  //但是如果此时你又定义一个隐式变量,再次调用方法时就会报错,
   println(person)
  //隐式转换为目标类型：把一种类型自动转换到另一种类型
  def foo(msg : String) = println(msg)
  //将整数转换成字符串类型：
  //报错,显然不能转换成功,解决办法就是定义一个转换函数给编译器将int自动转换成String
  //foo(10)
  implicit def intToString(x : Int) = x.toString
  foo(10)
  //隐式转换调用类中本不存在的方法



}
