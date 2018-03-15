import scala.collection.mutable.ArrayBuffer

/**
  * create by liush on 2018-3-15
  */
object ArrayDemo  extends App{
  // 声明Int类型、String类型数组
  val number = new Array[Int](10);                //> number  : Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
  val stringArray = new Array[String](10);        //> stringArray  : Array[String] = Array(null, null, null, null, null,
  //| null,null, null, null, null)
  // 声明并赋值
  val strArray = Array("Hello","world")           //> strArray  : Array[String] = Array(Hello, world)
  strArray(0) = "One"
  strArray                                        //> res0: Array[String] = Array(One, world)
  // 动态数组
  var buffer = new ArrayBuffer[Int]()             //> buffer  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  // 用+=在尾端添加元素
  buffer +=1                                      //> res1: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1)
  // 在尾端添加多个元素
  buffer +=(1,2,3,5)                              //> res2: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 1,
  //> 2, 3, 5)
  // 可以用 ++=操作符追加任何集合
  buffer ++=Array(8,13,21)                        //> res3: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 1, 2,
  // 在第2个元素后添加元素6
  buffer.insert(2, 6)
  buffer                                          //> res5: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 1,
  //  | 6, 2)


  // 在第2个元素后添加多个元素
  buffer.insert(2, 7,8,9)
  buffer
  // 删除位置是第2个元素
  buffer.remove(2)                                //> res7: Int = 7
  buffer                                          //> res8: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 1, 8,
  //|  9, 6,2)
  // 从第3个元素开始移除3个元素
  buffer.remove(2, 3)
  buffer
  // 将数组缓冲转换为Array
  buffer.toArray
  // 声明元素
  val nums = Array(2,3,5,7,11)                   //> nums  : Array[Int] = Array(2, 3, 5, 7, 11)
  // nums中的元素都*2，并返回新的集合
  var result = for(elem <-nums) yield 2 * elem   //> result  : Array[Int] = Array(4, 6, 10, 14, 22)

  // 生成nums集合中值对2取余为0的数，乘2，返回新的集合
  for(elem <-nums if elem %2  == 0) yield elem *2//> res11: Array[Int] = Array(4)
  nums.filter(_ % 2 == 0).map { 2*_}             //> res12: Array[Int] = Array(4)

  val multiArr = Array.ofDim[Double](3,4);        //> multiArr  : Array[Array[Double]] = Array(Array(0.0, 0.0, 0.0, 0.0), Array(0
  //| .0, 0.0, 0.0, 0.0), Array(0.0, 0.0, 0.0, 0.0))
  multiArr(2)(1)=45
  multiArr
}
