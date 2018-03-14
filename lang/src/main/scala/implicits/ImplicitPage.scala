package implicits

/**
  * create by liush on 2018-3-14
  * 可以将隐式转换函数定义在伴生对象中,在使用时导入隐式视图到作用域中即可
  */
  class SwingTypeP{
  def  wantLearned(sw : String) = println("兔子已经学会了"+sw)
}

package swimmingPage{
  object swimming{
    implicit def learningType(s : AminalTyep) = new SwingTypeP  //将转换函数定义在包中
  }
}
 class AminalTyep

object ImplicitPage extends App{
//还可以将隐式转换函数定义在包对象中,同样在使用时导入作用域即可,如例4
  import swimmingPage.swimming._  //使用时显示的导入
  val rabbit = new AminalTyep
  rabbit.wantLearned("breaststroke")         //蛙泳
}
