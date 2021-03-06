package collections.lists

// #list #zip #index

object ZipSamples extends App {

  val list = List("a", "b", "c")

  // #1
  val zipped1 = list zip List(0,1,2)      // List[(String, Int)] = List((a,0), (b,1), (c,2))
  val zipped2 = list zip List(0,1,2,3,4)  // List[(String, Int)] = List((a,0), (b,1), (c,2)) -- same as before !

  println("zipped1: " + zipped1)
  println("zipped2: " + zipped2)

  // #2 with Index
  //zipWithIndex 将列表元素与其索引进行拉链操作,组成一个pair
  val zippedIWithIndex = list.zipWithIndex // List((a,0), (b,1), (c,2))

  // same as:   list zip List(0,1,2,3,4)
  //zipWithIndex 将列表元素与其索引进行拉链操作,组成一个pair
  //zippedIWithIndex:List((a,0), (b,1), (c,2))
  println ("zippedIWithIndex:" + zippedIWithIndex)

}
