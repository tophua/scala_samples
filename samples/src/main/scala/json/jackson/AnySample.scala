package json.jackson

// # json jackson any

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/*
class Model {
  var name: String = _
  var anyObject: Any = _   // anything  (will be transformed to Map type)
} */

case class Model(
  name: String,
  anyObject: Any           // anything
)

object AnySample extends App {

  // 1. create a mapper 创建一个映射器
  val mapper = new ObjectMapper
  mapper.registerModule(DefaultScalaModule)

  // 2. giving json string 给json字符串
  val json =
    """
      | {
      | 	"name": "fred",
      | 	"anyObject": {
      | 		"prop": "prop-value"
      | 	}
      | }
    """.stripMargin


  // 3.
  // convert to object
  //转换为对象
  val model = mapper.readValue(json, classOf[Model])

  print(model.anyObject.getClass.getSimpleName)  // Map1
}
