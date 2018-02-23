package collections

// Interesting to know that Seq[T] is a function Function1[Int, T]

object SeqFun extends App {

  def first(f: Int => Int) = {
    //取出第一个值
   f(0)
  }

  def second(f: Int => Int) = {
    f(1) //取出第二个值
  }

  val s = Seq[Int](1,2,3)

  val restul_1 = first(s)
  val restul_2 = second(s)

  println(restul_1)
  println(restul_2)

  // actually it is partial function
  // 其实是部分功能
  println( "isDefined: " + s.isDefinedAt(2) ) // true
  println( "isDefined: " + s.isDefinedAt(3) ) // false
}
