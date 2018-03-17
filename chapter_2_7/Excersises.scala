
class Console(val make: String,
              val model: String,
              val debutDate: java.util.Date,
              val wifiType: String = "b/g",
              val physicalMediaFormat: List[String],
              val maxVideoResolution: (Int, Int)) {

  override def toString = s"${make}, ${model}, ${debutDate}, ${wifiType}, ${physicalMediaFormat}, ${maxVideoResolution}"
}

val ps = new Console("Sony", "PS4", null, physicalMediaFormat = List("No"), maxVideoResolution = (1080, 1920))
val xbox = new Console("Xbox", "1", null, physicalMediaFormat = List("YES"), maxVideoResolution = (1200, 1600))


class Game(val name: String, val maker: String, val supportedConsoles: List[Console]) {
  def isSupported(console: Console): Boolean = supportedConsoles.contains(console)
}

val fifa = new Game("Fifa18", "EA", List(ps, xbox))
val tlou = new Game("The last of us", "Bethesda", List(ps))
