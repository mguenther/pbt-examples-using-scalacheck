package com.mgu.max

import org.scalacheck.Prop
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

class MaxSpec extends Specification with ScalaCheck {

  "The Math.max(x,y) function" should {
    "always yield a result that is either x or y and larger than either x or y" in {
      Prop.forAll((x: Int, y: Int) => {
        val z = Math.max(x, y)
        ((z == x || z == y) && (z >= x || z >= y)) mustEqual true
      })
    }
  }
}
