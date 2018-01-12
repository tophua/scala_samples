package collections.lists

object aggregate extends App {

  val ss = Seq(1,2,3,4) // 0 + 1 + 2 + 3 + 4 = 10

  // aggregate takes 3 parameters: 聚合需要3个参数：
  // 1. a seed value, 种子值，
  // 2. a computation function 一个计算功能
  // 3. a combination function 一个组合功能

  val compFn = (x:Int, y:Int) => {
    println(s"compFn: x+y=$x+$y")
    x + y
  }
  val combFn1 = (x:Int, y:Int) => {
    println(s"combFn1: x+y=$x+$y")
    x + y
  }


  // it splits the collection in a number of threads,
  //它在多个线程中分割集合，
  // 1. compute partial results using the computation function
  // 1.使用计算函数计算部分结果
  // 2. and then combine all these partial results using the combination function.
  // 2. 然后使用组合函数组合所有这些部分结果

  // in comparison to fold
  // 与折叠相比
  val result0 = ss.foldLeft( /*seed=*/0 ) ( compFn)           // 10
  //聚合
  val result1 = ss.aggregate( /*seed=*/0 ) ( compFn, combFn1)  // 10 - same !

  println("result0: " + result0) // 10
  println("result1: " + result1) // 10

  // 8 points:
//  compFn: x+y=0+1
//  compFn: x+y=1+2
//  compFn: x+y=3+3
//  compFn: x+y=6+4
//  compFn: x+y=0+1
//  compFn: x+y=1+2
//  compFn: x+y=3+3
//  compFn: x+y=6+4

  // The the combFn is not being used inside the aggregate() ? And compFn is applied twice for the whole list?
  //combFn没有在aggregate（）中使用？ 而compFn是整个列表申请两次？
  // Q. Why it is required to pass then?
  // 为什么需要通过呢？
  // A. The default implementation does Not create threads..
  //默认实现不创建线程..
  // Let's go with parallelized implementation of it
  //我们来并行执行它
  // The par-version, does use the second combine function
  //par-version，使用第二个组合功能
  println("par:")

  val resultPar1 = ss.par.aggregate( /*seed=*/0 ) ( compFn, combFn1)

  println("resultPar1: " + resultPar1) // 10 - same result, but in par

  // One of possible outputs (7 points):
    //  compFn: x+y=0+1
    //  compFn: x+y=0+2
    //  combFn1: x+y=1+2
    //  compFn: x+y=0+3
    //  compFn: x+y=0+4
    //  combFn1: x+y=3+4
    //  combFn1: x+y=3+7

  // --

  val combFn2 = (x:Int, y:Int) => {
    println(s"combFn2: x,y=$x,$y")
    x * 2
  }

  println("par:")

  val resultPar2 = ss.par.aggregate( /*seed=*/0 ) ( compFn, combFn2)

  // One of possible outputs (7 points):
    //  compFn: x+y=0+2
    //  compFn: x+y=0+3
    //  compFn: x+y=0+1
    //  compFn: x+y=0+4
    //  combFn2: x,y=1,2
    //  combFn2: x,y=3,4
    //  combFn2: x,y=2,6

  println("resultPar2: " + resultPar2) // 4

}