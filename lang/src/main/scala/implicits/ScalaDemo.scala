package implicits

/**
  * create by liush on 2018-3-9
  */
object ScalaDemo {
  /**
    隐式解析机制
    即编译器是如何查找到缺失信息的,解析具有以下两种规则：
    1.首先会在当前代码作用域下查找隐式实体(隐式方法,隐式类,隐式对象)
    2.如果第一条规则查找隐式实体失败,会继续在隐式参数的类型的作用域里查找(如上面代码中的extends部分)
    类型的作用域是指与该类型相关联的全部伴生模块,一个隐式实体的类型T它的查找范围如下：
        （1）如果T被定义为T with A with B with C,那么A,B,C都是T的部分，在T的隐式解析过程中，它们的伴生对象都会被搜索
        （2）如果T是参数化类型，那么类型参数和与类型参数相关联的部分都算作T的部分，比如List[String]的隐式搜索会搜索List的
    伴生对象和String的伴生对象
        （3） 如果T是一个单例类型p.T，即T是属于某个p对象内，那么这个p对象也会被搜索
        （4） 如果T是个类型注入S#T，那么S和T都会被搜索
    */
  import implicits.ImplicitHelper.{ImpAdd, echo, strToInt}
  def main(args: Array[String]): Unit = {
    //隐式类
    println(1.add(2))
    //3
    //import com.donews.localspark.ImplicitHelper.strToInt 源码一般放在上面
    //隐式转换函数是指在同一个作用域下面,一个给定输入类型并自动转换为指定返回类型的函数
    println(strToInt("1"))
    //1
    println(math.max("1", 2))
    //2
    //隐式参数一般和柯里化进行结合,使用该函数不用给出implicit的值
    echo("hello")("word")
    //hello,word
    //echo("hello")
    //或者像下面这样
    implicit val impl = "implicit"
    echo("hello")
    //hello,implicit
  }
}
