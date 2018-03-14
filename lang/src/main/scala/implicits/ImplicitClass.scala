package implicits

/**
  * create by liush on 2018-3-14
  * 隐式转换调用类中本不存在的方法
  * 通过隐式转换,使对象能调用类中本不存在的方法
  */
class SwingType{
  def  wantLearned(sw : String) = println("兔子已经学会了:"+sw)
}
object swimming{
  implicit def learningType(s : AminalType) = new SwingType
}
class AminalType
object Stringutils {
  //隐式类
  implicit class StringImprovement(val s : String){   //隐式类
    def increment = s.map(x => (x +1).toChar)
  }
}

object ImplicitClass  extends  App{
  import swimming._
  val rabbit = new AminalType
  //例3：通过隐式转换,使对象能调用类中本不存在的方法
  //编译器在rabbit对象调用时发现对象上并没有wantLearning方法,此时编译器就会在作用域范围内查找能使其编译通过的隐式视图,
  //找到learningType方法后,编译器通过隐式转换将对象转换成具有这个方法的对象,之后调用wantLearning方法
  rabbit.wantLearned("breaststroke")         //蛙泳
  /**
    * 在scala2.10后提供了隐式类,可以使用implicit声明类,但是需要注意以下几点：
    *  1.其所带的构造参数有且只能有一个
    *  2.隐式类必须被定义在类,伴生对象和包对象里
    *  3.隐式类不能是case class(case class在定义会自动生成伴生对象与2矛盾）
    *  4.作用域内不能有与之相同名称的标示符
    */
  import Stringutils._
  //编译器在mobin对象调用increment时发现对象上并没有increment方法,此时编译器就会在作用域范围内搜索隐式实体,
  //发现有符合的隐式类可以用来转换成带有increment方法的StringImprovement类,最终调用increment方法。
  println("mobin".increment)
}
