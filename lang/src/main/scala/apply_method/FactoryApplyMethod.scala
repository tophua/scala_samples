package apply_method

/*
  #apply-method
  related: #factory-method #companion-object
*/
// sequence: #2 (look at #1 first)

// Shows general use case:how to use apply method as factory method
//显示一般用例：如何使用apply方法作为工厂方法

class A(x:Int)
//这个类没有伴生对象
class B(x:Int) // this class does not have companion object

// companion object
//伴生对象
object A { // why do we use "object" here, but not "class"? because we want to use "()" method as class' constructor has
  //为什么我们在这里使用“object”，而不是“class”呢？ 因为我们要使用“（)”方法作为类的构造函数
  def apply(x:Int) = new A(x) // #factory-method related

}

object FactoryApplyMethod extends App {
  //通过在对象上调用apply方法来创建类A的实例
  val a1 = A(1)     // create instance of class A by calling apply method on A object
    //这将无法编译,我们不能创建一个对象，而不使用“new”
  //val b1 = B(2)   // this will not able to compile, we can NOT create an object without using "new"
  //同样的结果通过调用构造函数
  val a2 = new A(1) // same result by calling constructor

  // why the difference ?
  //为什么区别？
  //  by using A(1) we do not use method "new" to create an object (it make code more precise/short). And an object looks like a function call
  //通过使用A(1)我们不使用方法“new”来创建对象(它使得代码更加精确/短),而一个对象看起来像一个函数调用

}
