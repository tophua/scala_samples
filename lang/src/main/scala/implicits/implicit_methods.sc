// #implicit #implicit-methods

case class A(val n: Int)

object A {
  // just grouping implicits here
  //只是在这里分组implicits
  //1只有标记为implicit的变量,函数或对象定义才能被编译器当做隐式操作目标
  implicit def aToString(a: A) : String = "A: %d" format a.n
}
//隐式解析的搜索范围
//-当前代码作用域,最直接的就是隐式定义和当前代码处在同一作用域中,
//-当第一种解析方式没有找到合适的隐式转换时,编译器会继续在隐式参数类型的隐式作用域里查找。一个类型的隐式作用域指的是与该类型相关联的所有的伴生对象。
//对于一个类型T它的隐式搜索区域包括如下：
//-假如T是这样定义的：T with A with B with C，那么A, B, C的伴生对象都是T的搜索区域。
//-如果T是类型参数，那么参数类型和基础类型都是T的搜索部分。比如对于类型List[Foo]，List和Foo都是搜索区域
//-如果T是一个单例类型p.T,那么p和T都是搜索区域。
//-如果T是类型注入p#T,那么p和T都是搜索区域。

val a = A(5)
//隐式转换
val s1: String = a            // s == "A: 2"
// same as:
//显示调用
val s2:String = A.aToString(a)

// "aToString" method is called in attempt to case A to String. 
// This method is called implicitly


