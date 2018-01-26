import java.io.{File, PrintWriter}

/**
  * create by liush on 2018-1-26
  */
object withPrintWriter  extends App{
  //比如下面,每次打开文件,操作,关闭文件,固定模式,所以实现withPrintWriter,
  //每次传入不同的op就可以进行不同的操作,而不用考虑文件开关
  //如果是oo实现,就需要传入基类对象,利用多态实现,明显使用函数更轻量级一些
  def withPrintWriter(file: File, op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
  //以调用方法的方式使用
  withPrintWriter(
    new File("date.txt"),
    writera => writera.println(new java.util.Date)
  )
  //currying版本
  def withPrintWriter1(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }} //currying版本
  val file = new File("date1.txt")
  withPrintWriter1(file) { writer => writer.println(new java.util.Date) } //将函数值放在{}, 很像built-in
}
