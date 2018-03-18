object HtmlUtils {
  def removeMarkup(input: String) = {
    input
      .replaceAll("""<script>.*</script>""", "")
      .replaceAll("""</?\w[^>]*>""", "")
      .replaceAll("<.*>", "")
  }
}
