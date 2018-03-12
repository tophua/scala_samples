package lists

// #list #concatenation
//联合
object ListConcatenations extends App {

  val list1 = List(1,2,3)
  val list2 = List(4,5,6)
  //添加到list2的开头。 所以':::'是list2的方法。
  val result1 = list1 ::: list2 // add to the beginning of list2. So ':::' is method of list2.
  val result11 = list2.:::(list1) // same as above 同上
  //添加到list1的末尾。 '++'是list1的方法
  val result2 = list1 ++ list2  // add to the end of list1. '++' is method of list1
  //list1 ::: list2 : List(1, 2, 3, 4, 5, 6)
  println ("list1 ::: list2 : " + result1)
  //list2.:::(list1) : List(1, 2, 3, 4, 5, 6)
  println ("list2.:::(list1) : " + result11)
  //list1 ++ list2 : List(1, 2, 3, 4, 5, 6)
  println ("list1 ++ list2 : " + result2)

  // what about "::" ?
  //它为list2的开始添加新的元素,所以,::是list2的方法
  val result3 = list1 :: list2 // It add new ELEMENT to the BEGINNING of the list2. So, :: is method of list2
  //list1 :: list2 : List(List(1, 2, 3), 4, 5, 6)
  println ("list1 :: list2 : " + result3) // List(List(1, 2, 3), 4, 5, 6)

}
