package focus

/**
  *
  * focus Create in 18:16 2018/11/26
  */
class Factorial {
  def factorial(x: Int): Int = {
    def fact(x: Int, accumlator: Int): Int = {
      if (x <= 1) {
        accumlator
      } else {
        fact(x - 1, x * accumlator)
      }
    }
    fact(x, 1)
  }
}
