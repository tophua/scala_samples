package lists

// This file contains very simple examples of most interesting method in List class
//这个文件包含List类中最有趣的方法的非常简单的例子
// The full list of methods is defined here:
//这里定义了完整的方法列表：
// http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.List

// #list #genaral
//list 一般操作
object ListMethods extends App {

  val list = List("a","b","c")

  // 1. take
  //取N个元素(N不是索引！),输出： List(a)
  println("take(1): " + list.take(1) ) // Takes N elements (N is not index!). Output:  List(a)

  // 2. drop
  //删除前N个元素, 输出：List(c)
  println ("drop(2): " + list.drop(2) ) // Drops first N elements. Output: List(c)

  // 3. reverse 逆转
  //list.reverse: List(c, b, a)
  println ("list.reverse: " + list.reverse) // Output: list.reverse: List(c, b, a)

  // 4. head
  println ("list.head: " + list.head) // Output:a

  // 5. tail
  //除去第一个元素之外
  println ("list.tail: " + list.tail) // Output:  List(b, c)

  // 5.1
  //除去最后一个元素之外
  println ("list.init: " + list.init)  // Output: List(a, b)

  // 6. sum
  //管用,因为它不知道如何总结Numeric [Strings]
  //println ("list.sum: " + list.sum)  // will not work. Because it does not know how to sum Numeric[Strings]
  // TODO: add implicit param to make it go


val listWithDuplications = List("a", "a", "b", "c")  // here we have

  // 7. group by
  // grouping by unique values:
  //按独特的价值分组：
  //listWithDuplications.groupBy: Map(b -> List(b), a -> List(a, a), c -> List(c))
  println ("listWithDuplications.groupBy: " + listWithDuplications.groupBy( x=>x ) )  // Map(b -> List(b), a -> List(a, a), c -> List(c))
  // which is the same as:
  //listWithDuplications.groupBy: Map(b -> List(b), a -> List(a, a), c -> List(c))
  println ("listWithDuplications.groupBy: " + listWithDuplications.groupBy(identity) )
  // 8. distinct - list without any duplicate elements.
  //distinct - 列表中没有任何重复的元素
  println ("listWithDuplications.distinct: " + listWithDuplications.distinct)  // Output: List(a, b, c)

  // 9. diff
  //差集
  {
    val list1 = List(1,2)
    val list2 = List(1,2,3)

    val result1 = list1.diff(list2)
    val result2 = list2.diff(list1)

    println ("diff: " + result1) // Output: List()  !!!
    println ("diff: " + result2) // Output: List(3)  // so I guess you should start from biggest list to go over it

  }

  // 10. ::: - list concantination
  //连接
  {

    val list1 = List(1,2)
    val list2 = List(3,4)

    val result = list1 ::: list2

    println ("list1 ::: list2 :" + result)  // Output: List(1, 2, 3, 4)

  }

  // 11  "::" - add to the beginning of list
  // 添加到列表的开头
  {

    val list1 = List(1,2)

    //val result = list1 :: 3  // will not work. Because, this is the same as "3::list1" and '3' doesn't have '::' method in it.

    // but this will work:

    val result = 3 :: list1
    val p=7 +: list1
    println("list1 +: 7:"+p) //list1 +: 7:List(7, 1, 2)
    println("list1 :: 3: " + result)  // Output: List(3, 1, 2)

  }

  // 12 ":+" - adding to the and of the list
  //在列表的尾部添加一个元素
  {
    val list1 = List(1,2)
    val result = list1 :+ 3               // Note that this operation has a complexity of O(n).
                                          // If you need this operation frequently, or for long lists,
                                          //如果您经常需要此操作,或者需要长列表，
                                          //考虑使用另一种数据类型(例如ListBuffer)
                                           // consider using another data type (e.g. a ListBuffer).
    //list1 :+ 3: List(1, 2, 3)
    println ("list1 :+ 3: " + result)

  }

  // 13 filter
  {
    val list1 = List(1,2,3)

    val result = list1.filter(x => x <2)

    println ("filter: " + result)  // Output: List(1)
  }

  // 14 find
  {
    val list1 = List(1,2,3)

    val result1 = list1.find(x => x < 3)
    val result2 = list1.find(x => x > 100)

    println ("find1: " + result1)  // Output: Some(1) // ONY ONE (FIRST) IS RETURNED
    println ("find2: " + result2)  // Output:None
  }

  // TODO: ...

// HigherOrderFunctions


  /// 15. map
  {

    val list1 = List(1,2,3)

    val result = list1.map(x=> x*2)

    println("map: " + result) // Output: List(2, 4, 6)


  }

  // 16. mkString
  {

    val result1 = List("a", "b", "c").mkString

    println ("mkString: " + result1)  // Output: abc

  }

  // 17. sortBy  (http://stackoverflow.com/questions/10602730/scala-ordered-by-multiple-fields)
  {

    case class Person (name:String, age: Int)

    val list = List( Person("Bob", 25), Person("Jon", 31), Person("Peter", 15) )
    //排序
    val result1 = list.sortBy(_.age)
    //自定义排序
    val result2 = list.sortBy {
      case Person(name, age) => (name, age)  // first 'name', then 'age'
    }

    println ("sortBy1: " + result1) // List(Person(Peter,15), Person(Bob,25), Person(Jon,31))
    println ("sortBy2: " + result2) // List(Person(Bob,25), Person(Jon,31), Person(Peter,15))

  }


}
