package com.mgu.add

import com.mgu.add.Adder.add
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object AddSpec extends Properties("Adder") {

  property("adding two numbers should not depend on parameter order") = forAll {
    (a: Int, b: Int) => {
      add(a, b) == add(b, a)
    }
  }

  property("adding 1 twice is the same as adding 2 once") = forAll {
    (a: Int) => {
      add(add(a, 1), 1) == add(a, 2)
    }
  }

  property("adding zero is the same as doing nothing") = forAll {
    (a: Int) => {
      add(a, 0) == a
    }
  }
}
