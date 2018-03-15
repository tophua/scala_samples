/**
  * create by liush on 2018-3-15
  */

import scala.collection.JavaConversions._

//Java Map与Scala Map的隐式转换
object javaMapToScalaMap {
  def main(args: Array[String]): Unit = {
    //创建Java的Map
    val javaMap=new java.util.HashMap[String,Int]()
    javaMap.put("a", 1)
    javaMap.put("b", 2)
    javaMap.put("c", 3)

    //将JAVA 的map转换成Scala的Map
    val scalaMap: scala.collection.mutable.Map[String, Int] = javaMap
    for((k,v)<-scalaMap) println(k+" "+v)
   import  collection.JavaConverters._

    val map:java.util.Map[String, Int] = scalaMap.map(p => (p._1, p._2)).toMap.asJava

    val jmap = new java.util.HashMap[String, String]()
    jmap.put("fname", "Alvin")
    jmap.put("lname", "Alexander")
    for ((k,v) <- jmap)
      printf("key: %s, value: %s\n", k, v)
    val smap =mapAsScalaMap(jmap)

  }
}
