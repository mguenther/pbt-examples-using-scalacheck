package com.mgu.add

import com.mgu.add.Adder.add1
import org.scalatest.{Matchers, FlatSpec}

class AddUnitTest extends FlatSpec with Matchers {

  "Adder" should "yield 4 when I add 1 + 3" in {
    add1(1, 3) shouldBe 4
  }

  "Adder" should "yield 4 when I add 2 + 2" in {
    add1(2, 2) shouldBe 4
  }

  "Adder" should "yield 2 when I add -1 + 3" in {
    add1(-1, 3) shouldBe 2
  }
}
