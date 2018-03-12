package fp_deep


// #functor #higher-kinded-type #type-constructor #pattern-matching #first-order-parametric-polymorphism

// special flag
//特殊标志,泛型
import scala.language.higherKinds  // if not set/imported - the warning about "higher-kinded type' will be generated (see this paper: http://adriaanm.github.io/files/higher.pdf)

object FunctorDemo extends App {
  //使用一个类型参数的类型构造函数。 （“更高类型”）
  trait Functor[T[_]] {  // T[_] - #type-constructor that takes one type parameter. ("higher-kinded type" )
    //从A => B取得函数并返回包装T [A] => T [B]
    def fmap[A, B](f: A => B): T[A] => T[B] // takes function from A=>B and returns wrapped T[A] => T[B]
  }

  /*
     Everything you need to know about #higher-kinded-type is that type may apply another type as argument (#type-constructor), and this type-argument also may have another type argument etc...
     你需要知道的关于＃高级别类型的一切都可以用另一种类型作为参数,而这个类型参数也可能有另一个类型的参数等
     Similarly to functions - function can takes values as arguments, where argument maybe another function that takes argument .. etc.
     类似于函数 - 函数可以将值作为参数,其中参数可能是另一个需要参数的函数。
     Type behaves as a function.
     类型表现为一个功能
     Usual "generics" in Java (f.e.) is "first-order parametric polymorphism" that has its limitations (because it is "first" but not _higher_ than "first")
     OOP does not exist - just in case if did not know/realize.
  */

  // let's define/implement two Functors then
  //那么我们来定义/实现两个Functor
  val listFunctor = new Functor[List] {      // List - type constructor 列表类型的构造函数
    //[A]，[B] - 该类型构造函数的类型参数
    def fmap[A, B](f: A => B): List[A] => List[B] = {   // [A], [B] - type parameters for that type constructor
      case Nil     => Nil
      case a :: as => f(a) :: fmap(f)(as)   // List[B]
    }
  }

  // same as 'map' but 'map' works only with List-like types (not as abstract as Functor's fmap where we pass a type)
  //和'map'一样，但'map'只能和List类似的类型（不像Functor的fmap那样抽象，我们通过一个类型）
  val optionFunctor = new Functor[Option] {  // Option - type constructor 选项 - 类型构造函数
    //[A]，[B] - 该类型构造函数的类型参数
    def fmap[A, B](f: A => B): Option[A] => Option[B] = { // [A], [B] - type parameters for that type constructor
      case None    => None
      case Some(a) => Some(f(a))
    }
  }

  // just reminding:
  // case x => y    is same as (f: X => Y) - the function that takes value of type X and returns value of type Y
  //情况x => y与（f：X => Y）相同 - 获取类型X的值并返回类型Y的值的函数
  val result1 = listFunctor.fmap[Int,Int]  (_ + 1) ( List(1,2,3) )    // have to specify [Int,Int] - scala/jvm limitation (comparing to Haskell)
  val result2 = optionFunctor.fmap[Int,Int](_ + 1) ( Some(1) )

  println (result1)    // List(2,3,4)
  println (result2)    // List(2)

// Eventually we may make this work (but not in scope of current sample):
  // List(1,2,3).fmap(_ + 1)
  // Same(1).fmap(_ + 1)
// see: 'scalaz' samples/folders

}

// NOTES:
// - " First-order parametric polymorphism like (List[T]), although it allows to abstract over types "
// - " "higher-kinded types" is generalisation to types that abstract over types that abstract over types "
