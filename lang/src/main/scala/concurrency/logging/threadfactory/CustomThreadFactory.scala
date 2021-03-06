package concurrency.logging.threadfactory

import java.lang.Thread.UncaughtExceptionHandler
import java.util.concurrent.ThreadFactory

import scala.concurrent.{BlockContext, CanAwait}
import scala.concurrent.forkjoin.{ForkJoinPool, ForkJoinWorkerThread}

// The purpose if this factory is to modify tread name, putting a prefix before actual name.
//这个工厂的目的是修改花纹名称,在实际名称前加上前缀
// that facilitate debugging and logging in async env
//这有助于调试和登录异步环境
class CustomThreadFactory(prefix: String, uncaughtExceptionHandler:UncaughtExceptionHandler) extends ThreadFactory with ForkJoinPool.ForkJoinWorkerThreadFactory {
  def wire[T <: Thread](thread: T): T = {
    thread.setDaemon(true)
    thread.setUncaughtExceptionHandler(uncaughtExceptionHandler)
    thread.setName(prefix + "-" + thread.getName)  // prefix here !
    thread
  }

  def newThread(runnable: Runnable): Thread = wire(new Thread(runnable))

  def newThread(fjp: ForkJoinPool): ForkJoinWorkerThread = wire(new ForkJoinWorkerThread(fjp) with BlockContext {
    override def blockOn[T](thunk: =>T)(implicit permission: CanAwait): T = {
      var result: T = null.asInstanceOf[T]
      ForkJoinPool.managedBlock(new ForkJoinPool.ManagedBlocker {
        @volatile var isdone = false
        override def block(): Boolean = {
          result = try thunk finally { isdone = true }
          true
        }
        override def isReleasable = isdone
      })
      result
    }
  })
}

object CustomThreadFactory {

  def apply(prefix:String): CustomThreadFactory = {
    new CustomThreadFactory(prefix, uncaughtExceptionHandler)
  }

  val uncaughtExceptionHandler = new UncaughtExceptionHandler {
    override def uncaughtException(t: Thread, e: Throwable) = e.printStackTrace()
  }


}
