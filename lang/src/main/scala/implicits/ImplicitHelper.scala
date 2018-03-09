package implicits

/**
  * create by liush on 2018-3-9
  * 隐式转换就是：当Scala编译器进行类型匹配时,如果找不到合适的候选,那么隐式转化提供了另外一种途径来告诉编译器如何将当前的类型转换成预期类型
  */
class ImplicitHelper {

  object ImplVal {
    implicit val name: String = "reynold"
  }

  /**
    * 定义成trait的话，可以让ScalaDemo继承，这样就可以自动引入了
    */
  trait ImplVal {
    implicit val name: String = "reynold"
  }

}

  object ImplicitHelper {
    /**
      * 隐式参数
      * 隐式参数一般和柯里化进行结合,使用该函数不用给出implicit的值
      *
      * @param param
      * @param impl
      */
    def echo(param: String)(implicit impl: String) {
      println(param + "," + impl)
    }

    /**
      * 隐式转换函数是指在同一个作用域下面，一个给定输入类型并自动转换为指定返回类型的函数，
      * 这个函数和函数名字无关，和入参名字无关，只和入参类型以及返回类型有关
      *
      * @param str
      * @return
      */
    implicit def strToInt(str: String) = str.toInt

    /**
      * 隐式类有如下几个限制:
      * They must be defined inside of another trait/class/object.
      * They may only take one non-implicit argument in their constructor.
      * There may not be any method, member or object in scope with the same name as the implicit class.
      * Note: This means an implicit class cannot be a case class.
      * 隐式类的运作方式：
      * 隐式类的主构造函数只能有一个参数（有两个以上并不会报错，但是这个隐式类永远不会被编译器作为隐式类在隐式转化中使用）
      * 且这个参数的类型就是将要被转换的目标类型
      * 隐式转换类将包裹目标类型，隐式类的所有方法都会自动"附加"到目标类型上
      *
      * @param origin 隐式类构造函数参数
      */
    implicit class ImpAdd(origin: Int) {
      def add(param: Int) = origin + param
    }

}
