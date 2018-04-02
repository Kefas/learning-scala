import java.io.File

import org.scalatest._

import scala.util.Random

class FileReplacerSpec extends FlatSpec with Matchers {
  import FileReplacer._
  val content = "Monika is a very funny person"

  "The File Replacer Object" should "replace content" in {
    val testFile = newFile(content)
    main(Array("f.*y", "smart", testFile.getName))
    getContent(testFile.getAbsolutePath) should equal("Monika is a very smart person")
  }

  it should "not replace anything" in {
    val testFile = newFile(content)
    main(Array("P.*r", "That guy", testFile.getName))
    getContent(testFile.getAbsolutePath) should equal(content)
  }

  it should "create a backup file before replacing text" in {
    val testFile = newFile(content)
    main(Array("f.*y", "smart", testFile.getName))
    getContent(testFile.getAbsolutePath + ".bak") should equal(content)
    getContent(testFile.getAbsolutePath) should equal("Monika is a very smart person")
  }

  private def newFile(content: String): File = {
    val testFile = new File(s"testy_${(Random.alphanumeric take 20).mkString}.txt")
    write(content, testFile)
    testFile
  }
}