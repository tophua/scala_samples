package collections.lists

// #fold #list
// related: #reduce (see: ReduceExample) #template-method-pattern #strategy-pattern #decorator-pattern

// In nutshell: Fold is sophisticated version of 'reduce'
//简而言之：折叠是“reduce”的复杂版本
object FoldExample extends App {

  println("#1")

  // # 1 - simple
  {
  //foldleft是从左开始计算,然后往右遍历
    val result1 = List(1,2,3).foldLeft(0)(_ + _)

    // empty list will work, because initial value is set to 0 ('reduceLeft' would not work [#reduce related] )
    //空列表将工作,因为初始值设置为0
    //foldleft是从左开始计算,然后往右遍历
    val result2 = List[Int]().foldLeft(0)(_ + _)

    println (result1) // 6
    println (result2) // 0

  }

  // traversing the list by foldRight
  //通过foldRight遍历列表
  {
    val list = List(1,2,3)
    //foldright是从右开始算,然后往左遍历
      list.foldRight(List[Int]()) { (right, result) =>
        right :: result
      }
    //List(1, 2, 3)
    println ("traversing by foldRight: " + list)
  }

  // traversing the list by foldLeft
  //通过foldLeft遍历列表
  {
    val list = List(1,2,3)
    //foldleft是从左开始计算,然后往右遍历
    list.foldLeft(List[Int]()) { (result, left) =>
      left :: result
    }
    println ("traversing by foldLeft: " + list)
  }

  // #2  similar to:  #template-method-pattern #strategy-pattern #decorator-pattern
  println("#2")

  // there are two operations ..
  def upperCaseOP (str:String) : String = {
    println(s"upperCaseOp($str)")
    str.toUpperCase
  }

  def addBarOP (str:String) : String = {
    println(s"addBarOP($str)")
    str + "bar"
  }

  def applyTransformations(initial: String, ops: Seq[String => String]) : String =
  //foldleft是从左开始计算,然后往右遍历
    ops.foldLeft(initial) {
                  //为每一对应用操作
                  //因为你可以看到有不同的类型：
      (currentResult, op) => op(currentResult) // applying an operation for each pair.
                                                // as you can see the pair has different types:
                                                    // 1. currentResult: String
                                                    // 2. op: String => String
    }

  // sequence of operations to be applied is important fo us. Like it is in #decorator-pattern
  //要应用的操作顺序对我们很重要,就像它在＃decorator-pattern中一样
  val result = applyTransformations("hello", Seq( upperCaseOP, addBarOP))
  //result: HELLObar
  println ("result: " + result) // HELLObar

}

/*
Full output is:

 #1
 6
 0
 #2
 upperCaseOp(hello)
 addBarOP(HELLO)
 result: HELLObar

*/
