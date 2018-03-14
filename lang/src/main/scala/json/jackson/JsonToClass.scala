package json.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * jackson-module-scala
 *
 * The Scala Module supports serialization and limited deserialization of:
  * 他Scala模块支持序列化和有限的反序列化：
 * Scala Case Classes, Sequences, Maps, Tuples, Options, and Enumerations.
 *
 * + some info: https://github.com/FasterXML/jackson-module-scala/wiki/FAQ
 */
object JsonToClass extends App {

  // 1. create a mapper
  // 1,创建一个映射器创建一个映射器
  val mapper = new ObjectMapper
  mapper.registerModule(DefaultScalaModule)

  // 2. giving json string
  //2.给json字符串
  val json = """{"name":"fred","age":"25"}"""

  // 3. having class, case class
  // 3.
  case class Person(name:String, age:Int)

  // convert to object
  //转换为对象
  val person = mapper.readValue(json, classOf[Person])

  println(person)               // Person(fred,25)

  println ("person's age type: " + person.age.getClass.getName ) // int .. hmmm not Int (?)
  println ("person's age type package: " + person.age.getClass.getPackage ) // null

  val intAge:Int = 25
  assert(person.age == intAge)

}
