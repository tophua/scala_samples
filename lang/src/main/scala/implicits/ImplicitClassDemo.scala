package implicits

/**
  * create by liush on 2018-3-9
  * “隐式类”形式的隐式转换
  * 隐式类与旧的隐式转换的语法(implicit def)是有细微的不同的,
  * 隐式类的运作方式是：
  * 隐式类的主构造函数只能有一个参数(有两个以上并不会报错,但是这个隐式类永远不会被编译器作为隐式类在隐式转化中使用),
  * 且这个参数的类型就是将要被转换的目标类型,
  * 从语义上这很自然:这个隐式转换类将包裹目标类型,隐式类的所有方法都会自动“附加”到目标类型上。
  */
object ImplicitClassDemo {
  implicit class MyImplicitTypeConversion(val str: String){
    def strToInt = str.toInt
  }

  /**
    * 隐式解析的搜索范围:
    * 1)在当前作用域下查找,这种情形又分两种情况,一个是在当前作用域显示声明的implicit元素,另一个通过import导入的implicit元素
    * 2)如果第一种方式没有找到,则编译器会继续在隐式参数类型的隐式作用域里查找。
    * 3)隐式作用域？一个类型的隐式作用域指的是“与该类型相关联的类型”的所有的伴生对象
    * OK，那什么叫作“与一个类型相关联的类型”?定义如下：
    * 1,假如T是这样定义的：T with A with B with C,那么A, B, C的伴生对象都是T的搜索区域。
    * 2,如果T是类型参数,那么参数类型和基础类型都是T的搜索部分。比如对于类型List[Foo],List和Foo都是搜索区域
    * 3,如果T是一个单例类型p.T，那么p和T都是搜索区域
    * 4,如果T是类型注入p#T，那么p和T都是搜索区域。
    */
  def main(args: Array[String]) {
    //compile error!
    //val max = math.max("1", 2);
    import implicits.ImplicitDefDemo.MyImplicitTypeConversion._
    val max = math.max("1", 2);
    println(max)
  }
}
