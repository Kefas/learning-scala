import java.io._
import scala.io.Source

object FileReplacer{
  def main(args: Array[String]) {
    val pattern = args(0)
    val replacement = args(1)
    val filePath = args(2)

    println(s"FILE REPLACER $pattern, $replacement, $filePath")
    replacePattern(pattern, replacement, filePath)
  }

  def replacePattern(pattern: String, replacement: String, filePath: String): Unit = {
    val content =  getContent(filePath)
    backup(content, filePath + ".bak")

    val file = new File(filePath)
    write(content.replaceAll(pattern, replacement), file)
  }

  def backup(content: String, filePath: String): Unit = {
    write(content, new File(filePath))
  }

  def getContent(filePath: String): String = {
    Source.fromFile(filePath).getLines.mkString
  }

  def write(content: String, file: File): Unit = {
    val pw = new PrintWriter(file)
    pw.write(content)
    pw.close()
  }
}