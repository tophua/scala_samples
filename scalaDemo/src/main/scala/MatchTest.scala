/**
  * create by liush on 2018-1-26
  */
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object MatchTest extends App{

  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list" //单例对象Nil匹配空列表
    case _ => "something else"
  }
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e //UnOp构造器匹配(匹配类型和参数), "-"常量匹配(使用==), e变量匹配(任意值,并方便后面引用)
    case BinOp("+", e, Number(0)) => e
    case BinOp("*", e, Number(1)) => e // Multiplying by one
    case _ => expr  //-通配符匹配, 匹配任意值,但无法引用
  }
  //Variable patterns, 变量模式

  //变量模式, 和通配符一样是catch all, 但是区别就在于, 后面可以通过变量引用
  def exprDemo(expr: Any) =expr match {
    case BinOp(_, _, _) => println(expr +"is a binary operation")
    case _ => println("It's something else")
  }

  def expr1Demo(expr: Any) = expr match {
    case 0 => "zero"
    case somethingElse => "not zero: "+ somethingElse
  }

  //Sequence patterns, 序列模式

  def list1Demo(expr: Any) = expr match {
    case List(0, _, _) => println("found it") //第一个为0的length为3的List
    case _ =>
  }
  def listDemo(expr: Any) = expr match {
    case List(0, _*) => println("found it") //第一个为0,可变长的List
    case _ =>
  }
  //Tuple patterns, 元组模式
  //特别之处在于, 可以用tuple给多个变量赋值

  def tupleDemo(expr: Any) =
    expr match {
      case (a, b, c) => println("matched "+ a + b + c)
      case _ =>
    }
  //需要注意的是, 在类型模式下用s替换x
 //原因在于, x的类型是Any, 所以你无法调用x.length, 所以必须使用string s来替换x
  def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size //匹配任意Map
    case _ => -1
  }
  //可以看到运行时, 编译器是无法check Map中数据的类型的, 因为Scala和Java一样使用了erasure model of generics(泛型的擦除模式),
  // 参数类型信息没有保留到运行期
  def isIntIntMap(x: Any) = x match {
    case m: Map[Int, Int] => true //会报unchecked warning, 因为无法check Map中是否为Int
    case _ => false
  }
  //isIntIntMap(Map(1 >1))
  //isIntIntMap(Map("abc" >"abc"))
  def isStringArray(x: Any) = x match {
    case a: Array[String] => "yes"
    case _ => "no"
  }
  val ai = Array(1, 2, 3)
  isStringArray(ai)
  //java.lang.String = no //对于数组可以识别出
  //Variable binding, 变量绑定
  def variableBinding(expr: Any) = expr match {
      //通过@将UnOp("abs", _)绑定到变量e上
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case _ =>
  }
  //其实就是在模式后面加上一定的条件判断, 通过了才会执行后面的语句
  //比如下面的例子, 判断x是否等于y,
  def simplifyAdd(e: Expr) = e match {
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) //加上条件判断, 称为pattern guard
    case _ => e
  }
  //Scala支持多个Pattern重叠,比如下面的例子,匹配第一个的,一定也会匹配第二个, 第三个
  //但顺序不能反,必须先具体的,后general的, 否则编译器会报错
  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-",UnOp("-",e)) => simplifyAll(e) // ‘-’is its own inverse
    case UnOp(op, e) => UnOp(op, simplifyAll(e))
    case _ => expr
  }

  /**
    Sealed意味着只能在这个文件定义子类, 这样就不能随便在其他地方增加case类
    这样做的原因是, scala要求模式匹配时需要考虑到所有的情况, 所以程序员都需要知道到底有哪些case class
    通过Sealed class, 不但程序员可以在该文件中找到所有的case class, 而且scala编译器也会找到, 并在编译时做检查,
    */
  sealed abstract class Expr  //sealed
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  def describe(e: Expr): String = (e: @unchecked) match { //@unchecked告诉编译器不用check这个match的case
    case Number(_) => "a number"
    case Var(_) => "a variable"
  }

}
