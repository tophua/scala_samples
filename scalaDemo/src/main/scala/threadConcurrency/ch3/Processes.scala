
package ch3

import org.learningconcurrency._
import ch3._


object ProcessRun extends App {
  import scala.sys.process._

  val command = "ls"
  val exitcode = command.!

  log(s"command exited with status $exitcode")
  
}


object ProcessLineCount extends App {
  import scala.sys.process._

  def lineCount(filename: String): Int = {
    val output = s"wc $filename".!!

    output.trim.split(" ").head.toInt
  }

  val lc = lineCount("build.sbt")
  log(s"File build.sbt has $lc lines.")
}


object ProcessAsync extends App {
  import scala.sys.process._
  val lsProcess = "ls -R /".run()
  Thread.sleep(1000)
  log("Timeout - killing ls!")
  //立即终止
  lsProcess.destroy()
}


object ProcessList extends App {
  import scala.sys.process._

  def processes(): Stream[(Int, String)] = {
    val proclines = "ps -A".lines_!
    proclines.tail map { line =>
      val parts = line.trim.split(" ")
      (parts.head.toInt, parts.last)
    }
  }

  val currprocs = processes()
  val jvms = currprocs.filter(_._2.contains("java"))
  log(s"listing currently running JVM instances...")
  for (jvm <- jvms) {
    log(s"${jvm._1} ${jvm._2}")
  }

}


object ProcessFiles extends App {
  import scala.sys.process._

  def files(pattern: String): Stream[String] = s"find /home/ -name $pattern".lines_!

  for (file <- files("scala")) log(s"found - $file")
}


object ProcessPipelining extends App {
  import scala.sys.process._

  case class ProcessInfo(pid: Int, name: String)

  def processes(pattern: String): Stream[ProcessInfo] = {
    val proclines = "ps -A" #| s"grep $pattern" lines_!;
    proclines map { line =>
      val parts = line.trim.split(" ")
      ProcessInfo(parts.head.toInt, parts.last)
    }
  }

  val jvms = processes("java")
  for (jvm <- jvms) {
    log(s"Found a running `java` process, pid = ${jvm.pid}")
  }

}


