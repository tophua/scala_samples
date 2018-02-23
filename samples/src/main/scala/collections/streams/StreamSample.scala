package collections.streams

import scala.collection.immutable.Stream.cons

// #stream
// related: #cons

// In nutshell: Streams are similar to list but a tail is evaluated only on demand
//简而言之：流类似于列表，但尾部仅在需求时评估

// Inspired by : http://daily-scala.blogspot.ca/2010/01/introducing-streams.html

object StreamSample extends App {

  // #1
  {
    val stream1 = Stream(1,2,3,4,5)

    // same as:
    //任何集合都可以转换成流
    val stream2 = (1 to 5).toStream  // any collection could be transformed to stream
    //因为你可以看到尾巴尚未评估
    println (stream1) // Stream(1, ?)  - as you can see the tail is not evaluated yet
    //因为你可以看到尾巴尚未评估
    println (stream2) // Stream(1, ?)  - as you can see the tail is not evaluated yet
  }

  // #2
  {

    val stream1 = Stream(1,2,100,4,5)


    val first3Values= stream1.take(3)

    println ("first3Values: " + first3Values)                 // Stream(1, ?)         Not evaluated !
    println ("first3Values.toList: " + first3Values.toList)   //  List(1, 2, 100)     Evaluated !

  }

  println ("\n#3 \n")

  // #3 cons method  - is the same is :: in lists. Same as: #:: operator (see below)
  //cons方法 - 在列表中是一样的。 相同：＃::运营商（见下文）
  {

    // cons takes :
    // 1. the value at the start point and 在起始点的值和
    // 2. a function to return the rest of the  stream NOT another stream.
    //一个函数返回流的其余部分而不是另一个流。

    val stream = cons(0, cons(1, Stream.empty ))


    println ("stream: " + stream)  // stream: Stream(0, ?)

//  The accessing the second element will run the function.
    //访问第二个元素将运行该功能。
// Notice it is not evaluated until request !
//请注意，它不被评估，直到请求!
    val stream2 = cons (0, {
                              println("getting second element")
                              cons(1, Stream.empty)
                           }
                        )
    //stream2：Stream（0，？） - NO“获得第二个元素”出现！
    println ("stream2: " + stream2) // stream2: Stream(0, ?) -- NO "getting second element" appearing !
    //没有“获得第二元素”出现！
    stream2(0)  // NO "getting second element" appeared !
    //这将导致“获得第二元素”出现
    stream2(1)  // this will lead to "getting second element" appearing

    // Function is only evaluated once:
    //函数只评估一次：
    stream2(1)  // NO "getting second element" appeared !



  }

  // #4  - force method, + Stream.from method
  //#4  -强制方法，+ Stream.from方法
  {

    // Create an infinite stream starting at `start` and incrementing by `1`.

    // Stream.from(start = 100).force  // leads to OutOfMemoryException

  }

  println ("\n#3 \n")

  // #5 "#::" method  - same as "Stream.cons", same as "::" in List
  //方法 - 与“Stream.cons”相同，与List中的“::”相同
  {
    val stream5 = 0 #:: { println("hi"); 1 } #:: Stream.empty

    println ("stream5:" + stream5)

    stream5(1) // hi
  }

}
