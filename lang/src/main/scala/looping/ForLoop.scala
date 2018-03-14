package looping

/*
 #loop #foor-loop #list-comprehension
 related: #filtering #yield #tuple
 */
object ForLoop extends App {

  //1.
  //for (int i=0; i<10; i++) // no-no-no, even do not try

  //2.
  //2.这已经更好了
  for (i <- 0 to 3) print(i)  // that is already better

  //2
  //
  for (i <- 3 to 0) print(i) // nope.. but good try

  //2.1
  println()
  //你需要这样做才能达到这个效果
  for (i <- 3 to 0 by -1) print(i) // you need do that to achieve that effect

  // 2.2
  println()
  for (i <- 0 to 3 by 2) print(i) // yeah of course you can do that


  // what about loop in the loop?
  //循环中的循环是什么？
  // 3.1
  println()
  for (i <- 0 to 3; j <- 0 to 3) print(i, j)

  // 3.2 or maybe you prefer this syntax, without using ";"
  //3.2 或者你更喜欢这种语法，而不使用“;”
  println()
  for {
    i <- 0 to 3
    j <- 0 to 3
  } print(i, j)


  // 4. you can collect(yielding) all info while lopping into some list
  // 4.你可以收集(yield)所有信息,同时进入一些列表
  println()
  val list1 = for (i <- 0 to 3) yield i

  println (list1) // Vector(0, 1, 2, 3)

  // 5. you can filter while looping
  // 5.你可以循环过滤
  val list2 = for (i <- 0 to 3 if i%2==0 ) yield i      // list-comprehension

  println (list2) // Vector(0, 2)

  // 5.1 you also can filter like this.
  for (i <- 0 to 3)
    if (i%2==0)     // you may omit "{..}". But maybe it's obvious
      print (i)
      // println (i) // no you can not continue like that. "i" already is not visible from out of "for" scope

  // 6. let's use variable/values just right in the loop
  val result = for { i <- 1 to 3 ;
        j = i * 2; // same as val j
        k = j * 2; // same as val k
        k <- j to k } yield k

   println("\n6. result: " + result)

  // 7. how traverse the map
  val mapResult =  for { (key, value) <- Map(1->"One", 2->"two") } yield (key, value) // #tuple related

  println( "7. " + mapResult )
}
