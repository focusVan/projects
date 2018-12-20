package focus

/**
  *
  * focus Create in 10:32 2018/11/26
  */
class Point(var x: Int, var y: Int) {
  private var _x = 0
  private var _y = 0

  def x1 = _x
  def y1 = _y

  def x1_= (newValue: Int): Unit = {
    _x = newValue
  }
  def y1_= (newValue: Int): Unit = {
    _y = newValue
  }

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }

  override def toString: String = s"($x, $y)"
}
