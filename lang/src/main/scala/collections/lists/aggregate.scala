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
  // 1. compute partial results using the computation function
  // 2. and then combine all these partial results using the combination function.

  // in comparison to fold

  val result0 = ss.foldLeft( /*seed=*/0 ) ( compFn)           // 10
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
  //
  // Q. Why it is required to pass then?
  // A. The default implementation does Not create threads..

  // Let's go with parallelized implementation of it

  // The par-version, does use the second combine function

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