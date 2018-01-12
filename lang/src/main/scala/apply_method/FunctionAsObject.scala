package apply_method

/*
  #apply-method
  related: #lazy-evaluation #anonymous-function
  相关：＃懒惰评估＃匿名函数
*/
// sequence: #3 (look at #1, #2 first)

object FunctionAsObject extends App {
  //＃匿名函数相关
  def sum = (x:Int, y:Int) => x + y // #anonymous-function related
  //延迟评估相关
  println(sum) // <function2>   // #lazy-evaluation related
  //演示函数“sum”是Function2的一个实例,它具有方法apply（_，_）
  val v1 = sum.apply(1, 2) // demonstrates that function "sum" is an instance of Function2 that has method apply(_,_)

  println(v1)  // 3


  // only because Function<N> has apply() method we can do this:
  //因为Function <N>有apply（）方法，所以我们可以这样做：
  val v2 = sum(2,2) // this is apply method !
  println(v2)  // 4

  // That means that in Scala everything tends to be a function (apply methods helps to get this feeling), but all functions are objects.
  //这意味着在一切都倾向于一个函数（应用方法有助于得到这种感觉），但所有的功能都是对象

}
