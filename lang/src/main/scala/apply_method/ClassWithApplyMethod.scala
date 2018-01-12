package apply_method

/*
  #apply-method
*/

// sequence: #1 (look at #1, #2 after)

class ClassWithApplyMethod(x:Int) {

  def apply(): Int = {println("apply method is calling with no arguments"); 1}
  def apply(x: Int): Unit = {println("apply method is calling with '" + x + "' argument")}

}

object Starter extends App {
  //不会调用任何apply（）方法,因为此时还没有创建对象
  val obj = new ClassWithApplyMethod(1) // no any apply() method will be invoked, because the object is not yet created at this moment


  println("-1-")
  //相当于x.apply（2）
  val y = obj(2) // equivalent to x.apply(2)


  println("-2-")
  //这不会导致apply（）评估，因为我们不使用“（）”来评估它
  //val z:Int = x  // this will not lead to apply() evaluation, because we do not use "()" to evaluate it
  //相当于x.apply（）
  val z:Int = obj() // equivalent to x.apply()

  // in this sense apply() method is just default 'empty-name' function which we could call when we applying "()" on a object
  //在这个意义上，apply（）方法就是默认的'empty-name'函数，当我们在对象上应用“（）”时，我们可以调用它
  // so apply is default function if we want to evaluate an object as a function
  //所以如果我们想要将一个对象作为一个函数来计算，那么apply就是默认函数

}

/* Output:
-1-
apply method is calling with '2' argument
-2-
apply method is calling with no arguments
 */
