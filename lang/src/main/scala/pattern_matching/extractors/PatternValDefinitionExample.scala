package main.scala.pattern_matching.extractors

import scala.util.matching.Regex
import scala.collection.generic.SeqFactory


// #pattern-definition, #PatVarDef  #pattern-matching  #extractor #unapply
//模式匹配,抽取
object PatternValDefinitionExample extends App {

  // #1
  {
    val someone = Some(1)
    //模式定义
    val Some(one) = someone // a pattern definition

    println(one) // 1
  }

  // #2
  {

    case class Example(a:String, b: Int)
    val x = Example("hello", 42)
    val Example(s, i) = x         // extractor/unapply is invoking

    println(s"$s, $i") // hello, 42

  }

  // #3
  {
    val foo = List(1,2,3,4)
    //extractor / unapplySeq正在调用
    val List(one, two, three, four) = foo // extractor/unapplySeq is invoking

    println(s"$one, $two, $three, $four") // 1, 2, 3, 4
  }

  // #3.1 even like that: 即使如此：
  {
    val Seq(x, y, _, xx @ _* )= 1 to 10

    println(s"Seq(x, y, _, xx @ _* ): $x, $y, $xx"   ) // 1, 2, Range(4, 5, 6, 7, 8, 9, 10)
  }

  // # 4
  {
    //正则表达式
    val Pattern = "([ 0-9]+) ([ A-Za-z]+)". r // RegEx

    // 4.1
    val Pattern(count0, fruit0) = "100 Cars"

    // same as
    // 4.2
    val res = Pattern.unapplySeq("100 Cars")
    val count1 = res.get(0) // 100
    val fruit1 = res.get(1) // Cars

    // same as

    // 4.3
    val (count2, fruit2) = "100 Cars"  match {
      case Pattern( count, fruit) => (count, fruit)
    }

    // So, 4.1 is just sugared for short. That is just pattern matching.
    //所以,4.1只是简单的短,这只是模式匹配,
    println (s"count0, fruit0: $count0, $fruit0") // .. 100, Cars
    println (s"count1, fruit1: $count1, $fruit1") // .. 100, Cars
    println (s"count1, fruit2: $count2, $fruit2") // .. 100, Cars

  }

}
