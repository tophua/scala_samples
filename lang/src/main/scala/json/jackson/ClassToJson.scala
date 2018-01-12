package json.jackson

import java.io.StringWriter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * jackson-module-scala
 *
 * The Scala Module supports serialization and limited deserialization of:
 * Scala Case Classes, Sequences, Maps, Tuples, Options, and Enumerations.
 *
 * + some info: https://github.com/FasterXML/jackson-module-scala/wiki/FAQ
 */
object ClassToJson extends App {

  // 1. create a mapper
  // 创建一个映射器
  val mapper = new ObjectMapper
  mapper.registerModule(DefaultScalaModule)

  // 2. Given a class, case class
  //给定一个类，case类
  case class Person(name:String, age:Int)
  val person = Person("fred", 25)

  // 3. write it/person out
  //把它/person写出来
  val out = new StringWriter
  mapper.writeValue(out, person)
  
  // 4. get result as a string
  //以字符串形式获得结果
  val json = out.toString
  println(json)                   // {"name":"fred","age":25}


  //  from Map
  //
  {
    val map = Map("a" -> person, "b" -> "not person")
    val mapOut = new StringWriter
    mapper.writeValue(mapOut, map)
    val mapJson = mapOut.toString
    println("mapJson: " + mapJson) // {"a":{"name":"fred","age":25},"b":"not person"}

  }

  //  from Option
  val options = List(Option(1), None)
  val optionOut = new StringWriter
  mapper.writeValue(optionOut, options)
  val optionJson = optionOut.toString

  println("optionJson: " + optionJson)  // mapJson: [1,null]  well it is JS's null

}
