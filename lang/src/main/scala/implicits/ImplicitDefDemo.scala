package implicits

/**
  * create by liush on 2018-3-9
  */
object ImplicitDefDemo {
  //它们都实现了：将一个String类型的变量隐式转换为Int类型
  //旧的定义方法指是的“implict def”形式
  object MyImplicitTypeConversion {
    implicit def strToInt(str: String) = str.toInt
  }

  def main(args: Array[String]) {
    //compile error!
    //val max = math.max("1", 2);
    import MyImplicitTypeConversion.strToInt
    val max = math.max("1", 2);
    println(max)
  }
}
