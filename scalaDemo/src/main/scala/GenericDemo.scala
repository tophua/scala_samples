import scala.reflect.ClassTag

/**
  * create by liush on 2018-1-12
  * 泛型函数
  */

object GenericDemo {
  //泛型函数
  /*
    *  泛型[]，中括号F、S、T都表示运行时参数类型，
    * ClassTag[T]保存了泛型擦除后的原始类型T,提供给被运行时的。
    */
  def main(args: Array[String]): Unit = {

    // 运行执行代码：val triple: Triple[String, Int, Double]
    val triple = new Triple("Spark", 3, 3.1415)

    // 运行执行代码：val bigData: Triple[String, String, Char]
    val bigData = new Triple[String, String, Char]("Spark", "Hadoop", 'R');

    // getData函数传入泛型为T的运行时List类型参数，返回list.length / 2的整数。
    def getData[T](list: List[T]) = list(list.length / 2)

    // List索引从0开始,执行结果：Hadoop
    println(getData(List("Spark", "Hadoop", 'R')));

    // 获得getData函数引用
    val f = getData[Int] _
    // 调用getData函数,执行结果：4
    println(f(List(1, 2, 3, 4, 5, 6)));


    /*
         * ClassTag:在运行时指定,在编译时无法确定的
         * ClassTag[T]保存了泛型擦除后的原始类型T,提供给被运行时的。
         */
    def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)

    /*
     *  执行结果：
     *  42
     *  13
     */
    mkArray(42, 13).foreach(println)

    /*
     * 执行结果：
     * Japan
     * Brazil
     * Germany
     */
    mkArray("Japan", "Brazil", "Germany").foreach(println)
  }

}

/*
    * <:泛型类型限定符，表示只限定Comparable子类
    * Comparable[T]:为T下界，T:为Comparable[T]上界
    */
class Pair[T <: Comparable[T]](val first: T, val second: T) {
  // compareTo方法进行比较，如果大于0返回first
  def bigger = if (first.compareTo(second) > 0) first else second
}


// 声明带T泛型参数的类
class Pair_Lower_Bound[T](val first: T, val second: T) {
  // 传入的参数泛型T 必须为 R的父类(超类),返回构造Pair_Lower_Bound对象
  // R:为T的上界，T:为R下界
  def replaceFirst[R >: T](newFirst: R) = new Pair_Lower_Bound[R](newFirst, second)
}

object TypeVariablesBounds {
  def main(args: Array[String]): Unit = {
    // 函数调用
    var pair = new Pair("Spark", "Hadoop")
    // 执行结果：Spark
    println(pair.bigger)
  }
}

/*
   * <%泛型视图限定符，表示把传入不是Comparable[T]类型的 隐式传换 为Comparable[T]类型
   * Comparable[T]:为T下界，T:为Comparable[T]上界
   */
class PairNotPerfect[T <% Comparable[T]](val first: T, val second: T) {
  // compareTo方法进行比较，如果大于0返回first
  def bigger = if (first.compareTo(second) > 0) first else second
}

/*
 * <%泛型视图限定符，表示把传入不是Ordered[T]类型的 隐式传换 为Ordered[T]类型
 * Ordered[T]:为T下界，T:为Ordered[T]上界
 * Ordered继承： extends Any with java.lang.Comparable[A]
 */
class PairBetter[T <% Ordered[T]](val first: T, val second: T) {
  def bigger = if (first.compareTo(second) > 0) first else second
}

object ViewVariablesBounds {
  def main(args: Array[String]): Unit = {
    // 函数调用
    var pair = new PairNotPerfect("Spark", "Hadoop");
    // 执行结果：Spark
    println(pair.bigger)

    // 函数调用,Int类型进行隐式转换，将Int -> RichInt,RichInt实现了Comparable接口
    var pairInt = new PairNotPerfect(3, 5)
    // 执行结果：5
    println(pairInt.bigger);

    // 函数调用,Int类型进行隐式转换，将String -> RichString,RichString实现了Comparable接口
    var pairBetterStr = new PairBetter("Java", "Scala");
    println(pairBetterStr.bigger);

    // 函数调用
    var pairBetterInt = new PairBetter(20, 12);
    // 执行结果：Spark
    println(pairBetterInt.bigger)
  }
}

//上下文界定：上下文界定是隐式参数的语法糖。如：Ordering：可以进行隐式转化的T类型。
object ViewVariablesBoundsObject {
  def main(args: Array[String]): Unit = {
    // 函数调用
    var pair = new PairNotPerfect("Spark", "Hadoop");
    // 执行结果：Spark
    println(pair.bigger)

    // 函数调用,Int类型进行隐式转换，将Int -> RichInt,RichInt实现了Comparable接口
    var pairInt = new PairNotPerfect(3, 5)
    // 执行结果：5
    println(pairInt.bigger);

    // 函数调用,Int类型进行隐式转换，将String -> RichString,RichString实现了Comparable接口
    var pairBetterStr = new PairBetter("Java", "Scala");
    println(pairBetterStr.bigger);

    // 函数调用
    var pairBetterInt = new PairBetter(20, 12);
    // 执行结果：Spark
    println(pairBetterInt.bigger)
  }
}

