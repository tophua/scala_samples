package looping

// #for #map-method #filter #flatMap #2d-array
// for循环
object ForAsMap extends App {

  // #1 for() and map()

  // for:
  {
    val list = for (x <- List(1,2,3)) yield x+1

    println (list) // 2,3,4
  }

  // same as using map:
  //与使用Map相同：
  {
   val list = List(1,2,3).map(_+1)

    println (list) // List(2,3,4)
  }

  // so, it seems map() method fits better in this case.
  //所以,在这种情况下,似乎map()方法更合适
    // Even more - compiler will convert 'for()' to 'map()'
  //更多 - 编译器会将'for()'转换为'map()'

  // #2 for and filter()
  //#2 for和过滤器()
  {

   val list = for (x <- List(1,2,3) if x<3 ) yield x
    println ("list1: " + list)   // List(1,2)
  }

  {

    val list = for (x <- List(1,2,3).withFilter(_<3) ) yield x

    println ("list2: " + list)

  }


  // #3 2dArray - for vs flatMap
  //
  {

    val matrix = Array.ofDim[Int](2,2)
    // the way we can fill 2D array is:
    //我们可以填充二维数组的方式是：
    matrix(0)(0) = 1; matrix(0)(1) = 2
    matrix(1)(0) = 3; matrix(1)(1) = 4  // Array[Array[Int]] = Array(Array(1, 2), Array(3, 4))

    // for:
    {
      //matrix: Array[Array[Int]] = Array(Array(1, 2), Array(3, 4))

      // the way how we can traverse 2D array is:
      //我们如何遍历二维数组的方式是：
      val elements = for (   // elements: Array[Int] = Array(1, 2, 3, 4)
        row <- matrix;
        elem <- row
      ) yield elem

      println ("elements1: ")
      elements foreach ( print (_) ) // 1234

    }

    // flatMap:
    //
    {
      //val elements = matrix flatMap( row => for (elements <-row) yield elements )  // Array[Int]
      // simpler:
      val elements = matrix flatMap( for (elements <-_) yield elements )  // Array[Int]

      println ("\nelements2: ")
      elements foreach ( print (_) ) // 1234
    }

    // so, you decide what is better for you - to use 'for' or 'flatMap'

  }



}
