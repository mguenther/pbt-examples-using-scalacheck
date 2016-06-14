package com.mgu.shrinking

import com.mgu.shrinking.LowerThan.isLowerThan80
import org.scalacheck.Prop
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

class LowerThanSpec extends Specification with ScalaCheck {

  "isLowerThan80" should {
    "yield true for all integers < 80" in {
      Prop.forAll((x: Int) => {
        isLowerThan80(x) mustEqual true
      })
    }
  }
}
