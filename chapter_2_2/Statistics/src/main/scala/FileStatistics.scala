import scala.io.Source

object FileStatistics extends FileStatsBuilder with FileStatsFormatting {
  def main(args: Array[String]): Unit = {
    val filePath = args(0)
    summarize(filePath)
  }

  def summarize(filePath: String): Unit = {
    val stats = buildFileStats(filePath)
    val formatted = formatStats(stats)
    println(formatted)
  }
}

case class Stats(fileName: String, charsCount: Int, wordsCount: Int,  paragraphsCount: Int, topWords: List[String])

trait FileStatsBuilder {
  def buildFileStats(filePath: String): Stats = {
    def getContent(path: String): String = {
      Source.fromFile(path).getLines.mkString.trim
    }

    val content: String = getContent(filePath)
    val words = content.split("""\W+""")
    val paragraphs = content.split("""\w+\W*\n\n""")
    val topWords: List[String] = words
      .map(_.toLowerCase())
      .groupBy(w => w).toList
      .sortBy(_._2.length)
      .reverse.map(_._1)
      .take(20)

    Stats(filePath, content.length, words.size, paragraphs.size, topWords)
  }
}

trait FileStatsFormatting {
  def formatStats(stats: Stats): String = {
    val formatted = s"""File "${stats.fileName}" has ${stats.charsCount} chars, ${stats.wordsCount} words and ${stats.paragraphsCount} paragraphs.
The top 20 words were: ${stats.topWords.mkString(", ")}."""

    formatted
      .replaceAll("\n", " ")
      .replaceAll(", ([^,]*)$", ", and $1")
  }
}
