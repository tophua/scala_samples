package collections.streams

// please at "StreamSample" first.

object StreamCreations extends App {

  // A very common way to construct a Stream is to define a recursive method.
  //构建Stream的一个非常常见的方法是定义一个递归方法。
  // Each recursive call constructs a new element in the stream.
  //每个递归调用在流中构造一个新的元素。

  // #1
  {
    //永不结束循环
    def makeStream : Stream[Int] = Stream.cons(util.Random.nextInt(10), makeStream)  // never ending loop

    val infinate = makeStream

    println (infinate)  // Stream(4, ?) <!-- random values !!!
                            // "?" means that the values are not evaluated yet

    val val0 = infinate(0)
    val val1 = infinate(1)
    val val2 = infinate(2)
    // ...

    val val100 = infinate(100)

    println (val0, val1, val2, val100)  // (3,8,6,1) <!-- random values !!!

    // infinate.force    // it leads to OutOfMemoryException exception !!!! That's normal for infinite streams

    //val size = infinate.size // also: it leads to OutOfMemoryException
    //println (size)

  }


  // #2 - let's create limited stream for now
  //现在让我们创建有限的流
  {

    def makeStream(size:Int) : Stream[String] = size match {
      case 0 => Stream.empty
      case size => Stream.cons(size toString, makeStream(size-1))
    }

    val stream = makeStream(10)

    println ("stream.size: " + stream.size)     // stream.size: 10
    println ("stream content: " + stream.force) // Stream(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)

  }

}
