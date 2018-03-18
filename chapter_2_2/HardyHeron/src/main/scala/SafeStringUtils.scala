import scala.util.{Random, Try}

trait SafeStringUtils {
  def trimToNone(s: String): Option[String] = {
    Option(s) map(_.trim) filterNot(_ isEmpty)
  }

  def stringToInt(s: String): Option[Int] = {
    trimToNone(s) flatMap { x => Try{x.toInt}.toOption}
  }

  def stringToLong(s: String): Option[Long] = {
    trimToNone(s) flatMap { x => Try{x.toLong}.toOption}
  }

  def generateString(size: Int): String = {
    val validChars: Seq[Char] = ('A' to 'Z') ++ ('a' to 'z')
    (1 to size).map(_ => validChars(Random.nextInt(validChars.size))).mkString
  }
}

object SafeStringUtils extends SafeStringUtils
