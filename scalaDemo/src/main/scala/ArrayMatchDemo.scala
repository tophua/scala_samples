/**
  * create by liush on 2018-3-15
  */
abstract class Item
case class Book(description: String, price: Double) extends Item
case class Bundle(description: String, price: Double, items: Item*) extends Item

object ArrayMatchDemo extends App{

  def match_array(arr:Any) =  arr match{
    case Array(0) => println("Array:0" )
    case Array(x,y) => println("Array:x="+x+",y="+y )
    case Array(0,_*) => println("Array:..." )
    case _ => println("something else" )
  }
  match_array(Array(0))
  match_array(Array(0,1))
  match_array(Array(0,1,2,3,4,5))
  match_array(Array("one","two","three"))
  /**
    *  输出结果：
        Array:0
        Array:x=0,y=1
        Array:...
        somethingelse

    */
  // 正则匹配
  val pattern = "([0-9]+) ([a-z]+)".r;
  "741258933 hadoop" match {
    case pattern(num,item) => println(num+":"+item)
  }
  //输出结果：741258933:hadoop
  def case_class_nested(person: Item) = person match {
    // 匹配Bundle类型，嵌套Book类型，Item类型可以0到N个参数
    case Bundle(_, _, art @ Book(_, _), rest @ _*) => println(art.description + " : " + art.price)
    case Bundle(_, _, Book(descr, _), _*) => println("The first description is : " + descr)
    case _ => println("Oops!!!")
  }

  // 输出结果：Scala for the Spark Developer : 69.95
  case_class_nested(Bundle("1111 Special's", 30.0,
    Book("Scala for the Spark Developer", 69.95),
    Book("Hadoop in Action", 69.95),
    Book("Hive", 79.95),
    Book("HBase", 32.86)))
  //输出结果：Scala forthe Spark Developer : 69.95
}
