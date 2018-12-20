package focus

/**
  *
  * focus Create in 14:34 2018/11/26
  */
trait Pet {
  val name: String
}

class Dog(val name: String) extends Pet {}
class Cat(val name: String) extends Pet {}

