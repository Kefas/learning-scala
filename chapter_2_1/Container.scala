class Container[A] (items: A*) {
  val item: Option[A] = items.headOption

  val next: Option[Container[A]] = {
    if (item.isDefined) Some(new Container(items.tail: _*)) else None
  }

  def foreach(f: A => Unit): Unit = {
    for {i <- item; n <- next} {
      f(i)
      n.foreach(f)
    }
  }

  def apply(index: Int): Option[A] = {
    if(index == 0) item else next.flatMap(_(index-1))
  }
}