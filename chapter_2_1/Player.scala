class Player(val volume: Int = 80) {
  val duration: Long = 250L

  private lazy val synth = javax.sound.midi.MidiSystem.getSynthesizer

  def play(notes: List[Int]): Unit = {
    synth.open()
    val channel = synth.getChannels.head
    notes.foreach({
        note =>
          channel.noteOn(note, volume)
          Thread.sleep(duration)
          channel.noteOff(note)
    })
    synth.close()
  }
}

val player = new Player()
player.play((30 to 70 by 5).toList ++ (70 to 30 by -5).toList)

