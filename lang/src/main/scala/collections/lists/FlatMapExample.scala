package collections.lists

// #flatmap (#flatmap-method)
// related: #map-method #flatten #product-trait (#product)

object FlatMapExample extends App {

  // trying to be simple.
  //试图变得简单
  //这里是:字符串的序列,或者:字符序列的序列
  val fruits = List("apple", "banana", "orange")  // here it is: sequence of strings. Or: sequence of sequences of chars

  // map

  // # 1
  {
    val bigFruits = fruits map (_.toUpperCase)

    println (bigFruits) // List(APPLE, BANANA, ORANGE)
  }

  // #2 flatten
  {
  //拼合 - 将每个项目(字符串[字符序列])转换为初始集合列表字符
    val result = fruits flatten // flatten - converts each item (String [sequence of chars]) to initial collection -
                                  // to List of Chars

    println ("fruits flatten: " + result) // List(a, p, p, l, e, b, a, n, a, n, a, o, r, a, n, g, e)

  }

  // flatMap = flatten + map

  // #3
  {
    val bigLetters = fruits flatMap(_.toUpperCase)

    println ("bigLetters:" + bigLetters) // List(A, P, P, L, E, B, A, N, A, N, A, O, R, A, N, G, E)

    // Note: bigLetters is: List[Char]  ! Sequence of chars were converted to initial collections to List
    //注意：bigLetters是：List [Char]!字符序列被转换为List的初始集合
    // so, this is sam as:
    val bigLetters2 = fruits.flatten map(_.toUpper) // 'toUpper' here is method of Char (not 'toUpperCase' now)

    println ("bigLetters2: " + bigLetters2)


  }

  // so flatMap is flatten + map


  // #4 closer to reality. some often uses case..

  // 2dArray - for vs flatMap

  {
    //matrix矩阵
    val matrix = Array.ofDim[Int](2,2)
    matrix(0)(0) = 1; matrix(0)(1) = 2
    matrix(1)(0) = 3; matrix(1)(1) = 4    // Array[Array[Int]] = Array(Array(1, 2), Array(3, 4))

    // for:
    {
      //matrix: Array[Array[Int]] = Array(Array(1, 2), Array(3, 4))
      //matrix矩阵赋值
      val elements = for (   // elements: Array[Int] = Array(1, 2, 3, 4)
        row <- matrix;       // row is Array 行是数组
        elem <- row
      ) yield elem

      println ("elements1: ")
      elements foreach ( print (_) ) // 1234

    }

    // flatMap:
    {
      //矩阵的迭代
      val elements:Array[Int] = matrix flatMap( row => for (elements <-row) yield elements )

      println ("\nelements2: ")
      elements foreach ( print (_) ) // 1234
    }

    // so, you decide what is better for you - to use 'for' or 'flatMap'. But at least, now you know what flatMap is
    //所以,你决定什么对你更好 - 使用'for'或'flatMap',但至少现在你知道flatMap是什么了

  }

  // #5 and of course example with Option(s)
  //
  val results = List(None, Some(1), Some(2), None) // List[Option[Int]]

  val flatResult1 = results flatMap(x=>x)           //  List[Int] = List(1, 2)  .
  // So it treats Option as possible Collection of: Some or None
  //所以它尽可能地将Option选项收集为：Some或None
  // Related to Product trait:
  // http://stackoverflow.com/questions/1301907/how-should-i-think-about-scalas-product-classes
  // http://en.wiktionary.org/wiki/Cartesian_product

  // same as (int this case)

  val flatResult2 = results flatten                 //  List[Int] = List(1, 2)


}
