/**
  * create by liush on 2018-3-15
  * list模式匹配
  */
object ListMatchDemo extends App{

  // 模式匹配
  val shuffledData = List(6, 3, 5, 6, 2, 9, 1)
  println(sortList(shuffledData))

  // 排序
  def sortList(dataSet: List[Int]): List[Int] = dataSet match {
    case List()       => List()
    case head :: tail => compute(head, sortList(tail))
  }

  def compute(data: Int, dataSet: List[Int]): List[Int] = dataSet match {
    case List() => List(data);
    // 如果集合第一个元素值小于data值，data放在第一个位置
    case head :: tail => if (data <= head) data :: dataSet
    // 如果不小于data值，进行下次比较
    else head :: compute(data, tail);
  }
  // list与list之间进行连接符:::
  println(List(1,2,3,4) ::: List(4,5,6,7,8) ::: List (10,11));
  println(List(1,2,3,4) :::(List(4,5,6,7,8):::List(10,11)));
  println(List(1,2,3,4) :::List(4,5,6,7,8):::List(10,11));

  // lenght方法特别慢
  println(List(1,2,3,4).length)

  var bigData = List("Hadoop","Spark","Kaffka")
  // 最后一个元素，执行结果：Kaffka
  println(bigData.last);
  // 除最后一个元素外的元素，执行结果：List(Hadoop, Spark)
  println(bigData.init);
  // 反转后的内容，执行结果：List(Kaffka, Spark, Hadoop)
  println(bigData.reverse)
  // 打印出自己本身，执行结果：List(Hadoop, Spark, Kaffka)
  println(bigData)
  // 获取前两个元素，执行结果：List(Hadoop, Spark)
  println(bigData.take(2))
  // 删除前两2个元素，执行结果：List(Kaffka)
  println(bigData.drop(2))
  // 折分为，前面两个为一组，后面元素为一组，执行结果：(List(Hadoop, Spark),List(Kaffka))
  println(bigData splitAt(2));
  // 通过索引获得元素：索引从0开始，执行结果：Kaffka
  println(bigData apply(2))
  println(bigData(2))

  // 声明list
  val data = List('a','b','c','d','e','f','g');
  // 获得所有的元素的索引，执行结果：Range(0, 1, 2, 3, 4, 5, 6)
  println(data.indices);
  // zip配对组合操作，执行结果：Vector((0,a), (1,b), (2,c), (3,d), (4,e), (5,f), (6,g))
  println(data.indices zip data);
  // zip配对组合操作，执行结果：List((a,0), (b,1), (c,2), (d,3), (e,4), (f,5), (g,6))
  println(data.zipWithIndex);
  // toString方法，执行结果：List(a, b, c, d, e, f, g)
  println(data.toString());
  // 返回两端以中括号开头、结束，中间以分号隔，执行结果：[a;b;c;d;e;f;g]
  println(data.mkString("[",";","]"));
  // 格式化输出字符串，执行结果：a    b    c    d    e    f    g
  println(data.mkString("    "));
  // 无任格式输出，执行结果：abcdefg
  println(data.mkString);

  // 将数据操作的结果赋值在buffer对象，执行结果:(a;;b;;c;;d;;e;;f;;g)c
  val buffer = new StringBuilder();
  data.addString(buffer,"(",";;",")");
  println(buffer);

  // list和数组相互转换
  val array =data
  println(array.toArray);
  println(array.toList);

  // copy data对象中的元素到newArray数组中，copy到3以后的位置
  val newArray = new Array[Char](10)
  data.copyToArray(newArray,3)
  // 执行结果：   abcdefg
  newArray.foreach(print)

  /*
   *  能过iterator集合中.next获取数组
   *  a
   *  b
   */
  val iterator = data.toIterator
  println(iterator.next())
  println(iterator.next())

  // list中的元素 * 2 操作，执行结果：List(2, 4, 6, 8, 10, 12)
  println(List(1,2,3,4,5,6).map(_*2))

  val datastr = List("Scala","Hadoop","Spark")

  // 获取每个元素的长度,执行结果：List(5, 6, 5)
  println(datastr.map(_.length()))

  // 转为List内容进行反转，执行结果：List(alacS, poodaH, krapS)
  println(datastr.map(_.toList.reverse.mkString))

  // 把元素中的每个元素转成List,执行结果：List(List(S, c, a, l, a), List(H, a, d, o, o, p), List(S, p, a, r, k))
  println(datastr.map(_.toList));

  // 将list元素分成多个list元素，再重新合成新的List元素,执行结果：
  println(datastr.flatMap(_.toList))

  /* 1到9不包含10，执行结果：
   * List((2,1),
   * (3,1), (3,2),
   * (4,1), (4,2), (4,3),
   * (5,1), (5,2), (5,3), (5,4),
   * (6,1), (6,2), (6,3), (6,4), (6,5),
   * (7,1), (7,2), (7,3), (7,4), (7,5), (7,6),
   * (8,1), (8,2), (8,3), (8,4), (8,5), (8,6), (8,7),
   * (9,1), (9,2), (9,3), (9,4), (9,5), (9,6), (9,7), (9,8))
   */
  println(List.range(1, 10).flatMap(i => List.range(1, i).map(j => (i,j))))

  // list中的元素，1到5连加，执行结果：sum=15
  var sum = 0;
  List(1,2,3,4,5).foreach(sum+=_);
  println("sum="+sum)

  // 生成元素list，从1到10，不包含11，过虑出偶数，执行结果：List(2, 4, 6, 8, 10)
  println(List.range(1, 11).filter { x => x % 2 == 0});
  // 过虑data元素长度为5的元素
  println(datastr.filter { str => str.length() ==5 })

  /*
 * 分区partition
 */
  // 按照是否为偶数，分成两个List，执行结果：(List(2, 4),List(1, 3, 5))
  println(List(1,2,3,4,5).partition(_ %2 == 0))

  // 返回Option,返回第一个符合的值,没有符合的值返回None
  println(List(1,2,3,4,5,6).find { x => x%2 == 0 })
  println(List(1,2,3,4,5).find { x => x <= 0 })

  // 根据条件获取小于4的元素值，并返回List，执行结果：List(1, 2, 3)
  println(List(1,2,3,4,5).takeWhile(_<4));

  // 最大程度剪切掉符合条件值
  println(List(1,2,3,4,5).dropWhile { x => x <4 })

  // 符合条件的一部分，不符条件为别外一部分,执行结果：(List(1, 2, 3),List(4, 5))
  println(List(1,2,3,4,5).span { x => x < 4 })

  def hastotallyZeroRow(m:List[List[Int]]) =
    m.exists{
      // forall：如果元素都等于0，那么返回true,否则返回false
      row => row forall{x => x == 0}
    }

  val m = List(List(1,0,0),List(0,1,0),List(0,0,0))
  println(hastotallyZeroRow(m));
  // foldLeft初始值为：0，从1加到100，执行结果：5050
  println((1.to(100).foldLeft(0)(_ + _)));

  // 初始值为：0，1-100组成元素的集合，使用相加的函数做为参数传递进行相加
  println((0 /: (1 to (100)))(_ + _));

  // 初始值为：100，每次都相减，计算过程，(1-(2-(3-(4-(5-100))))),执行结果：-97
  println((1.to(5)).foldRight(100)(_ - _));
  println(((1.to(5)) :\ 100)(_ - _))

  /*
         *  排序
         *  执行结果：List(-3, 1, 2, 4, 6, 8)
           *  List(8, 6, 4, 2, 1, -3)
         */
  println(List(1, 2, -3, 4, 6, 8).sortWith(_ < _));
  println(List(1, 2, -3, 4, 6, 8).sortWith(_ > _));


  import scala.collection.mutable.ListBuffer;
  val listBuffer = new ListBuffer[Int]();
  // 追加元素
  listBuffer += 1;
  listBuffer += 2;
  // 执行结果：ListBuffer(1, 2)
  println(listBuffer);

  import scala.collection.mutable.ArrayBuffer;
  val arrayBuffer = new ArrayBuffer[Int]();
  arrayBuffer += 1;
  arrayBuffer += 2;
  // 执行结果：ArrayBuffer(1, 2)
  println(arrayBuffer);


  /**
    * immutable包中的不可变Queue
    */
  import scala.collection.immutable.Queue
  val empty = Queue[Int]();
  // 加一个元素，Queue本身是不可变的，每次操作都会生成一个新的Queue
  val queue1 = empty.enqueue(1);
  val queue2 = queue1.enqueue(List(2,3,4,5))
  // 执行结果：Queue(1, 2, 3, 4, 5)
  println(queue2);

  // 将quenue2拆分成两部分：第一个元素，和剩下的一个元素组成Queue两部分
  val(element,left) = queue2.dequeue
  // 执行结果：1:Queue(2, 3, 4, 5)
  println(element +":"+left);

  /**
    * mutable包中的可变Queue
    */
  import scala.collection.mutable.Queue
  val queue = scala.collection.mutable.Queue[String]();
  queue += "a";
  // 追加List
  queue ++=List("b","c");
  // 执行结果：Queue(a, b, c)
  println(queue);
  // 与不可变的Queue不同，可变的Queue只返回第一个元素，并且把第一个元素从Queue中删除，执行结果：a
  println(queue.dequeue())
  // 执行结果：Queue(b, c)
  println(queue);


  /**
    *  mutable包中Stack
    */
  import scala.collection.mutable.Stack;
  val stack = new Stack[Int]();
  stack.push(1);
  stack.push(2);
  stack.push(3);
  stack.push(10);
  // 返回stack顶元素：10，执行结果：10
  println(stack.top);
  // 执行结果：Stack(10, 3, 2, 1)
  println(stack);
  // 返回stack顶元素，且将返stack顶元素进行删除，执行结果：10
  println(stack.pop);
  // 执行结果：Stack(3, 2, 1)
  println(stack);

  // 生成List对象，执行结果：List(1, 2, 3, 4, 5)
  println(List.apply(1, 2, 3, 4, 5))

  // 生成1-4List,包前，不包后，执行结果：List(1, 2, 3, 4)
  println(List.range(1, 5));

  // 生成9-1List,每次步长为-2，执行结果：List(9, 7, 5, 3)
  println(List.range(9, 1, -2));

  /*
   *  zip操作与反zip操作，执行结果：
   *  List((a,1), (b,2), (c,3), (d,4), (e,5), (f,6))
     *  (List(a, b, c, d, e, f),List(1, 2, 3, 4, 5, 6))
   */
  val zipped = "abcdef".toList.zip("123456789").toList
  println(zipped);
  println(zipped.unzip)

  // 将所有的集合中的元素，合成一个大集合，执行结果：List(a, b, c, d, e, f)
  println(List(List('a', 'b'), List('c', 'd'), List('e', 'f')).flatten);
  // 将集合内容合并，执行结果：List(b, c)
  println(List.concat(List(), List('b'), List('c')))
}
