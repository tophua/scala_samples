import scala.collection.mutable.ArrayBuffer

/**
  * create by liush on 2018-3-16
  */
object ArrayOperator extends App{

  val numbers = Array(1, 2, 3, 4) //声明一个数组对象
  val first = numbers(0) // 读取第一个元素
  numbers(3) = 100 // 替换第四个元素为100
  val biggerNumbers = numbers.map(_ * 2) // 所有元素乘2
  val a = Array(1,2)
  val b = Array(3,4)
  //合并集合，并返回一个新的数组，新数组包含左右两个集合对象的内容。
  val c = a ++ b
  //c中的内容是(1,2,3,4)
 // val a = List(1,2)
  val bc = scala.collection.mutable.LinkedList(3,4)
  val cc = a ++: bc
  //这个方法同上一个方法类似,两个加号后面多了一个冒号,但是不同的是右边操纵数的类型决定着返回结果的类型
  println(cc.getClass().getName())// c的类型是：scala.collection.mutable.LinkedList
  //在数组前面添加一个元素，并返回新的对象，下面添加一个元素 0

  val cd = 0 +: a // c中的内容是 （0,1,2）
  //同上面的方法想法,在数组末尾添加一个元素,并返回新对象
  val cdd = a :+ 0
  //对数组中所有的元素进行相同的操作,foldLeft的简写
  val ad = List(1,2,3,4)
  val cddd = (10 /: a)(_+_)   // 1+2+3+4+10
  val dd = (10 /: a)(_*_)   // 1*2*3*4*10
  println("c:"+cddd)   // c:20
  println("d:"+dd)   // d:240
  //将数组中的元素逐个添加到b中
  val a1 = List(1,2,3,4)
  val b1 = new StringBuilder()
  val c1 = a1.addString(b1)   // c中的内容是  1234
  //将数组中的元素逐个添加到b中,每个元素用sep分隔符分开
  val c2 = a1.addString(b1,",")
  println("c:  "+c2)  // c:  1,2,3,4
  //在首尾各加一个字符串，并指定sep分隔符
  val c3 = a1.addString(b1,"{",",","}")
  println("c:  "+c3)  // c:  {1,2,3,4}
  val a4 = List(1,2,3,4)
  //聚合计算,aggregate是柯里化方法,参数是两个方法
  val c4 = a4.par.aggregate(5)(seqno,combine)
  println("c:"+c)
  def seqno(m:Int,n:Int): Int ={
    val s = "seq_exp=%d+%d"
    println(s.format(m,n))
    return m+n
  }
  def combine(m:Int,n:Int): Int ={
    val s = "com_exp=%d+%d"
    println(s.format(m,n))
    return m+n
  }
  /**
  seq_exp=5+3
    seq_exp=5+2
    seq_exp=5+4
    seq_exp=5+1
    com_exp=6+7
    com_exp=8+9
    com_exp=13+17
    c:30
    */
  val first1 = numbers(0) // 读取第一个元素
  //获取index索引处的字符,这个方法会执行一个隐式的转换
  val chars = Array('a','b','c')
  println("c:"+chars.charAt(0))   //结果 a
  //创建一个副本
  val newchars = chars.clone()
  //通过执行一个并行计算(偏函数),得到一个新的数组对象
  //我们通过下面的偏函数，把chars数组的小写a转换为大写的A
  val fun:PartialFunction[Char,Char] = {
    case 'a' => 'A'
    case x => x
  }
  /**输出结果是 newchars:A,b,c */

  val arr = Array(1,'a',"b")
  //定义一个偏函数，要求当被执行对象为Int类型时，进行乘100的操作，对于上面定义的对象arr来说，只有第一个元素符合要求
  val fun1:PartialFunction[Any,Int] = {
    case x:Int => x*100
  }
  //计算
  //在序列中查找第一个符合偏函数定义的元素,并执行偏函数计算
  val value1 = arr.collectFirst(fun1)
  println("value:"+value1)
  //另一种写法
  val value2 = arr.collectFirst({case x:Int => x*100})
  println("value:"+value2)

  val arr1 = Array("a","b","c")
  //排列组合,这个排列组合会选出所有包含字符不一样的组合,对于 “abc”、“cba”,只选择一个,参数n表示序列长度,就是几个字符为一组
  val newarr = arr1.combinations(2)
  newarr.foreach((item) => println(item.mkString(",")))
  /**
  a,b
    a,c
    b,c
    */
  val ac = List(1,2,3,4)
  val bcc = List(2,3)
  //判断当前序列中是否包含另一个序列
  println(ac.containsSlice(bcc))  //true

  val abb = Array('a', 'b', 'c')
  val bbb : Array[Char] = new Array(5)
  abb.copyToArray(bbb)    /**b中元素 ['a','b','c',0,0]*/
  abb.copyToArray(bbb,1)  /**b中元素 [0,'a',0,0,0]*/
  abb.copyToArray(bbb,1,2)    /**b中元素 [0,'a','b',0,0]*/

  val abbb = Array('a', 'b', 'c')
  val bbbbb:ArrayBuffer[Char]  = ArrayBuffer()
  //将数组中的内容拷贝到Buffer中
  abbb.copyToBuffer(bbbbb)
  println(bbbbb.mkString(","))

  val ay = Array(1, 2, 3)
  val by = Array(4, 5,6)
  //判断两个序列长度以及对应位置元素是否符合某个条件,如果两个序列具有相同的元素数量并且p(x, y)=true,返回结果为true
  //下面代码检查a和b长度是否相等,并且a中元素是否小于b中对应位置的元素
  println(ay.corresponds(by)(_<_))  //true
  val au = Array(1, 2, 3)
  //统计符合条件的元素个数，下面统计大于 2 的元素个数
  println(au.count({x:Int => x > 2}))  // count = 1
  val ai = Array(1, 2, 3,4)
  val bi = Array(4, 5,6,7)
  //计算当前数组与另一个数组的不同。将当前数组中没有在另一个数组中出现的元素返回
  val ci = ai.diff(bi)
  println(ci.mkString) //1,2,3

  //去除当前集合中重复的元素，只保留一个
  val ao = Array(1, 2, 3,4,4,5,6,6)
  val co = ao.distinct
  println(co.mkString(","))    // 1,2,3,4,5,6

  val ap = Array(1, 2, 3,4)
  val cp = ap.drop(2)
  //将当前序列中前 n 个元素去除后，作为一个新序列返回
  println(cp.mkString(","))    // 3,4

  //下面去除大于2的,第一个元素 3 满足,它后面的元素 2 不满足,所以返回 2,3,4
  val aq = Array(3, 2, 3,4)
  val cq = aq.dropWhile( {x:Int => x > 2} )
  println(cq.mkString(","))
  //如果数组 a 是下面这样,第一个元素就不满足,所以返回整个数组 1, 2, 3,4
  val aw = Array(1, 2, 3,4)
  //判断是否以某个序列结尾
  val ae = Array(3, 2, 3,4)
  val be = Array(3,4)
  println(ae.endsWith(be))  //true
  //判断当前数组是否包含符合条件的元素
  val ar = Array(3, 2, 3,4)
  println(ar.exists( {x:Int => x==3} ))   //true
  println(ar.exists( {x:Int => x==30} ))  //false

  //取得当前数组中符合条件的元素，组成新的数组返回
  val at = Array(3, 2, 3,4)
  val bt = at.filter( {x:Int => x> 2} )
  println(bt.mkString(","))    //3,3,4
  //与上面的 filter 作用相反
  val bto = at.filterNot( {x:Int => x> 2} )

  //查找第一个符合条件的元素
  val aa = Array(1, 2, 3,4)
  val ba = aa.find( {x:Int => x>2} )
  println(ba)  // Some(3)

  //对当前序列的每个元素进行操作,结果放入新序列返回,参数要求是GenTraversableOnce及其子类
  val as = Array(1, 2, 3,4)
  val bs = as.flatMap(x=>1 to x)
  println(bs.mkString(","))
  /**
  1,1,2,1,2,3,1,2,3,4
  从1开始，分别于集合a的每个元素生成一个递增序列，过程如下
  1
  1,2
  1,2,3
  1,2,3,4*/
//将二维数组的所有元素联合在一起,形成一个一维数组返回
val dArr = Array(Array(1,2,3),Array(4,5,6))
  val cdf = dArr.flatten
  println(cdf.mkString(","))    //1,2,3,4,5,6
  //从左到右计算
  def seqno1(m:Int,n:Int): Int ={
    val s = "seq_exp=%d+%d"
    println(s.format(m,n))
    return m+n
  }
  val abb1 = Array(1, 2, 3,4)
  val bw = abb1.foldLeft(5)(seqno1)
  /** 运算过程
    seq_exp=5+1
    seq_exp=6+2
    seq_exp=8+3
    seq_exp=11+4
    */
  //从右到左计算
  val bbe = abb1.foldRight(5)(seqno1)
  /** 运算过程
    seq_exp=4+5
    seq_exp=3+9
    seq_exp=2+12
    seq_exp=1+14
    */
  //检测序列中的元素是否都满足条件p,如果序列为空,返回true
  val b7 = abb1.forall( {x:Int => x>0})   //true
  val b8 = abb1.forall( {x:Int => x>2})   //false

//检测序列中的元素是否都满足条件 p,如果序列为空,返回true
  val b6= a.forall( {x:Int => x>0})   //true
  val b9 = a.forall( {x:Int => x>2})   //false
  //按条件分组,条件由f匹配,返回值是Map类型,每个key对应一个序列,
  // 下面代码实现的是,把小于3的数字放到一组,大于3的放到一组,返回Map[String,Array[Int]]
  val b67 = a.groupBy( x => x match {
    case x if (x < 3) => "small"
    case _ => "big"
  })
//按指定数量分组，每组有 size 数量个元素，返回一个集合
val b11 = a.grouped(3).toList
  b11.foreach((x) => println("第"+(b.indexOf(x)+1)+"组:"+x.mkString(",")))
  /**
  第1组:1,2,3
  第2组:4,5 */
  //检测序列是否存在有限的长度,对应Stream这样的流数据,返回false
  println(a.hasDefiniteSize)  //true
  //返回序列的第一个元素，如果序列为空，将引发错误
  println(a.head) //1
  //返回Option类型对象，就是scala.Some 或者 None，如果序列是空，返回None
  println(a.headOption)   //Some(1)
  //返回elem在序列中的索引，找到第一个就返回
  //val a = Array(1, 3, 2, 3, 4)
  println(a.indexOf(3))   // return 1
  //返回elem在序列中的索引，可以指定从某个索引处（from）开始查找，找到第一个就返回
  println(a.indexOf(3,2)) // return 3
  //检测当前序列中是否包含另一个序列（that），并返回第一个匹配出现的元素的索引
  val apppp = Array(1, 3, 2, 3, 4)
  val bpo = Array(2,3)
  println(apppp.indexOfSlice(bpo))  // return 2
  //检测当前序列中是否包含另一个序列（that），并返回第一个匹配出现的元素的索引，指定从 from 索引处开始
  println(apppp.indexOfSlice(bpo,3))    // return 4
  //返回当前序列中第一个满足 p 条件的元素的索引
  //val a = Array(1, 2, 3, 4)
  println(apppp.indexWhere( {x:Int => x>3}))  // return 3
  //返回当前序列中第一个满足 p 条件的元素的索引，可以指定从 from 索引处开始
  println(apppp.indexWhere( {x:Int => x>3},4))    // return 4
  //返回当前序列索引集合
  // val a = Array(10, 2, 3, 40, 5)
  val bpp = a.indices
  println(bpp.mkString(","))    // 0,1,2,3,4
  //返回当前序列中不包含最后一个元素的序列
  val arb = Array(10, 2, 3, 40, 5)
  val brb = arb.init
  println(brb.mkString(","))    // 10, 2, 3, 40
  //取两个集合的交集
  val aw1 = Array(1, 2, 3, 4, 5)
  val bw1 = Array(3, 4, 6)
  val cw1 = aw1.intersect(bw1)
  println(cw1.mkString(","))    //return 3,4

  //判断序列中是否存在指定索引
  val ax = Array(1, 2, 3, 4, 5)
  println(ax.isDefinedAt(1))   // true
  println(ax.isDefinedAt(10))  // false
  //判断当前序列是否为空
  println(ax.isEmpty)
  //对序列中的每个元素产生一个 iterator
  val bx = a.iterator  //此时就可以通过迭代器访问 b
  //取得序列中最后一个元素
  println(a.last) // return  5
  //取得序列中最后一个等于 elem 的元素的位置
  println(a.lastIndexOf(4))   // return  4
  //取得序列中最后一个等于 elem 的元素的位置，可以指定在 end 之前（包括）的元素中查找
  println(a.lastIndexOf(4,3)) // return  1
  //判断当前序列中是否包含序列 that，并返回最后一次出现该序列的位置处的索引
  val a22 = Array(1, 4, 2, 3, 4, 5, 1, 4)
  val b22 = Array(1, 4)
  println(a22.lastIndexOfSlice(b22))  // return  6
//判断当前序列中是否包含序列 that，并返回最后一次出现该序列的位置处的索引，可以指定在 end 之前（包括）的元素中查找
  println(a22.lastIndexOfSlice(b,4))    // return  0
  //返回当前序列中最后一个满足条件 p 的元素的索引
  println(a22.lastIndexWhere( {x:Int => x<2}))  // return  6
  //返回当前序列中最后一个满足条件 p 的元素的索引，可以指定在 end 之前（包括）的元素中查找
  println(a22.lastIndexWhere( {x:Int => x<2},2))    // return  0
  //返回当前序列中最后一个对象
  val add = Array(1, 2, 3, 4, 5)
  println(add.lastOption)   // return  Some(5)
  //返回当前序列中元素个数
  println(a.length)   // return  5
  //对序列中的元素进行 f 操作
  val bbb1 = a.map( {x:Int => x*10})
  println(bbb1.mkString(","))    // 10,20,30,40,50
  //返回序列中最大的元素
  println(a.max)  // return  5
  //返回序列中第一个符合条件的最大的元素
  println(a.maxBy( {x:Int => x > 2})) // return  3
  //将所有元素组合成一个字符串
  println(a.mkString) // return  12345
  //将所有元素组合成一个字符串，以 sep 作为元素间的分隔符
  println(a.mkString(","))    // return  1,2,3,4,5
  //将所有元素组合成一个字符串，以 start 开头，以 sep 作为元素间的分隔符，以 end 结尾
  println(a.mkString("{",",","}"))    // return  {1,2,3,4,5}
  //判断序列不是空
  println(a.nonEmpty)
  //后补齐序列，如果当前序列长度小于 len，那么新产生的序列长度是 len，多出的几个位值填充 elem，如果当前序列大于等于 len ，则返回当前序列
  val buu = a.padTo(7,9)    //需要一个长度为 7  的新序列，空出的填充 9
  println(buu.mkString(","))    // return  1,2,3,4,5,9,9
  //返回一个并行实现，产生的并行序列，不能被修改
  val brr = a.par   //  "ParArray" size = 5
  //按条件将序列拆分成两个新的序列，满足条件的放到第一个序列中，其余的放到第二个序列，下面以序列元素是否是 2 的倍数来拆分
  val bnn:(Array[Int],Array[Int]) = a.partition( {x:Int => x % 2 == 0})
  println(bnn._1.mkString(","))     // return  2,4
  println(bnn._2.mkString(","))     // return  1,3,5
  //批量替换，从原序列的 from 处开始，后面的 replaced 数量个元素，将被替换成序列 that
  //val a = Array(1, 2, 3, 4, 5)
  val byy = Array(3, 4, 6)
  val cyy = a.patch(1,byy,2)
  println(c.mkString(","))    // return 1,3,4,6,4,5
  /**从 a 的第二个元素开始，取两个元素，即 2和3 ，这两个元素被替换为 b的内容*/

  val pa = Array(1,2,3,4,1,2,3,4)
  //给定一个条件 p，返回一个前置数列的长度，这个数列中的元素都满足 p
  val bp = pa.prefixLength( {x:Int => x<3}) // b = 2
  //返回所有元素乘积的值
  val btbb = a.product       // b = 120  （1*2*3*4*5）
  //反转序列
  val bssss = a.reverse
  println(bssss.mkString(","))    //5,4,3,2,1
  //反向生成迭代
  //reverseIterator
  //同 map 方向相反
  //val a = Array(1,2,3,4,5)
  val bn = a.reverseMap( {x:Int => x*10} )
  println(bn.mkString(","))    // 50,40,30,20,10
  //判断两个序列是否顺序和对应位置上的元素都一样
  val ah = Array(1,2,3,4,5)
  val bh = Array(1,2,3,4,5)
  println(ah.sameElements(bh))  // true

  val ch = Array(1,2,3,5,4)
  println(ah.sameElements(ch))  // false

  //用法同 fold，scan会把每一步的计算结果放到一个新的集合中返回，而 fold 返回的是单一的值
  val bk = a.scan(5)(seqno)
  println(bk.mkString(","))    // 5,6,8,11,15,20
  //从左向右计算 scanLeft
  //从右向左计算 scanRight
  //从序列的 from 处开始向后查找，所有满足 p 的连续元素的长度
  val akn = Array(1,2,3,1,1,1,1,1,4,5)
  val bkn = akn.segmentLength( {x:Int => x < 3},3)        // 5
  // size: Int  序列元素个数，同 length
  //取出当前序列中，from 到 until 之间的片段
  val bq = a.slice(1,3)
  println(bq.mkString(","))    // 2,3
  //从第一个元素开始，每个元素和它后面的 size - 1 个元素组成一个数组，最终组成一个新的集合返回，当剩余元素不够 size 数，则停止
  val bca = a.sliding(3).toList
  //val b = a.sliding(3,2).toList   //第一个从1开始,第二个从3开始,因为步进是 2
  for(i<-0 to bca.length - 1){
    val s = "第%d个：%s"
    println(s.format(i,bca(i).mkString(",")))
  }
  /**
  第0个：1,2,3
    第1个：2,3,4
    第2个：3,4,5
    */

//按指定的排序规则排序
// val a = Array(3,2,1,4,5)
val byab = a.sortBy( {x:Int => x})
  println(byab.mkString(","))    // 1,2,3,4,5
//分割序列为两个集合，从第一个元素开始，直到找到第一个不满足条件的元素止，之前的元素放到第一个集合，其它的放到第二个集合
 val axab = Array(3,2,1,4,5)
  val bxab = axab.span( {x:Int => x > 2})
  println(bxab._1.mkString(","))     //  3
  println(bxab._2.mkString(","))     //  2,1,4,5
  //从指定位置开始,把序列拆分成两个集合
  val asplit = Array(3,2,1,4,5)
  val bsplit = asplit.splitAt(2)
  println(bsplit._1.mkString(",")) //  3,2
  println(bsplit._2.mkString(",")) //  1,4,5
  val astarts = Array(0,1,2,3,4,5)
  val bstarts = Array(1,2)
  //从指定偏移处,是否以某个序列开始
  println(astarts.startsWith(bstarts,1))      //  true
  //是否以某个序列开始
  println(astarts.startsWith(bstarts))        //  true
  //返回 start 和 end 间的字符序列
  val charsqq = Array('a','b','c','d')
  val bbbmm = charsqq.subSequence(1,3)
  println(bbbmm.toString)     //  bc
  //序列求和，元素需为Numeric[T]类型
  val bsum = a.sum       //  15
  //返回除了当前序列第一个元素的其它元素组成的序列
  val btail = a.tail      //  2,3,4,5
  //返回当前序列中前 n 个元素组成的序列
  val btake = a.take(3)       //  1,2,3
//返回当前序列中，从右边开始，选择 n 个元素组成的序列
  val btakeRight = a.takeRight(3)      //  3,4,5
  //返回当前序列中，从第一个元素开始，满足条件的连续元素组成的序列
  val btakeWhile = a.takeWhile( {x:Int => x < 3})      //  1,2
  //同 Map 类型，需要被转化序列中包含的元素时 Tuple2 类型数据
  val charsArray = Array(("a","b"),("c","d"),("e","f"))
  val bcharsArray = charsArray.toMap
  println(bcharsArray)      //Map(a -> b, c -> d, e -> f)
  //矩阵转换，二维数组行列转换
  val btranspose = charsArray.transpose
  println(btranspose.mkString(","))
  //联合两个序列，同操作符 ++
  //val a = Array(1,2,3,4,5)
  //val b = Array(6,7)
  val cunion = a.union(b)
  println(c.mkString(","))        // 1,2,3,4,5,6,7

  //将含有两个元素的数组，第一个元素取出组成一个序列，第二个元素组成一个序列
  val charsArray1 = Array(("a","b"),("c","d"))
  val bchars = charsArray1.unzip
  println(bchars._1.mkString(","))     //a,c
  println(bchars._2.mkString(","))     //b,d
//将序列中 i 索引处的元素更新为 x
  a.update(1,9)
  println(a.mkString(","))        //1,9,3,4,5
  //将序列中 i 索引处的元素更新为 x ,并返回替换后的数组
  val bupdated = a.updated(1,9)
  println(bupdated.mkString(","))        //1,9,3,4,5
  //返回 from 到 until 间的序列，不包括 until 处的元素
  val bview = a.view(1,3)
  println(bview.mkString(","))        //2,3
  //根据条件 p 过滤元素
  //  val a = Array(1,2,3,4,5)
  val bwithFilter = a.withFilter( {x:Int => x>3}).map(x=>x)
  println(bwithFilter.mkString(","))        //4,5

  //将两个序列对应位置上的元素组成一个pair序列
  val azip = Array(1,2,3,4,5)
  val bzip = Array(5,4,3,2,1)
  val czip = azip.zip(bzip)
  println(czip.mkString(","))        //(1,5),(2,4),(3,3),(4,2),(5,1)
  //序列中的每个元素和它的索引组成一个序列
  //val a = Array(10,20,30,40)
  val bzipWithIndex = a.zipWithIndex
  println(bzipWithIndex.mkString(","))        //(10,0),(20,1),(30,2),(40,3)
}
