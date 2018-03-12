package pattern_matching


/*
 *  #pattern-matching #anonymous-function #partial-function
 * related:
*/
object PatternMatching_AnonymousFunction extends App {

  // 1. use pattern-matching as anonymous function
  //1.使用模式匹配作为匿名函数
  // working with map, where type of Key and Value is defined
  //使用map来定义Key和Value的类型
  val map = Map[Int,String](1->"A", 2->"B")

  map foreach {  case(k,v) => println("k="+k+";v="+v) }  // pattern-matching in action !

  // just reminding that foreach() is defined as: "def foreach[U](f: A => U):Unit "
  //只是提醒foreach（）被定义为：“def foreach [U]（f：A => U）：Unit”
  // If we did not use use pattern matching, then your code would look like this
  //如果我们没有使用使用模式匹配，那么你的代码将如下所示
  map.foreach(x => {
    if (x._1 == 1)  println("k=" + x._1 + ";v=" + x._2)
    else           println("k=" + x._1  + ";v=" + x._2)
  })

  // so, foreach expects a function
  //所以，foreach期待一个函数
  // But what kind of function we have provided with " case(k,v) => println("k="+k+";v="+v) "  ?
  //但是，我们提供了什么样的函数“case（k，v）=> println（”k =“+ k +”; v =“+ v）”？
  // It is:
  // (Int,String) => Unit i.e. Tuple2[Int, String] => Unit  ?
  // TODO: http://stackoverflow.com/questions/18807890/to-see-anonymous-function-declaration


  // 2.
  //这里我们在一行/列表中使用字符串和整数
  val list = List("a", "b", "c", 1, 2, 3) // here we use strings and integers in one line/list

  // will not compile :
  //不会编译：
//  list map { case (x:Int) => println(x+1) }  // try to increase all Int values

  // it is because our anonymous incrementing function can not be applied for String values that this list has
  // I.e. the data we have (integers and strings) deprives our anonymous function sense.

  // but this will work 但是这将工作
  //将工作，因为它接受PartialFunction
  list collect{ case (x:Int) => print(x+1) }  // "234" - will work, because it accept PartialFunction

  // @see PartialFunctionTest ! to get explanation

}
