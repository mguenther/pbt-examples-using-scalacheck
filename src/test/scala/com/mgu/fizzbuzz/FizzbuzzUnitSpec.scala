package com.mgu.fizzbuzz

import org.scalatest.{Matchers, FlatSpec}

class FizzbuzzUnitSpec extends FlatSpec with Matchers {

  "Fizzbuzz" should "yield 'Fizz' for a number that is not divisible by 5 and multiplied by 3" in {
      Fizzbuzz.fizzbuzz(3) shouldBe "Fizz"
  }

  "Fizzbuzz" should "yield 'Buzz' for a number that is not divisible by 3 and multiplied by 5" in {
    Fizzbuzz.fizzbuzz(5) shouldBe "Buzz"
  }

  "Fizzbuzz" should "yield 'FizzBuzz' for a number that is divisible by 15" in {
    Fizzbuzz.fizzbuzz(15) shouldBe "FizzBuzz"
  }

  "Fizzbuzz" should "yield identity for a number that is not divisible by either 3 or 5" in {
    Fizzbuzz.fizzbuzz(1) shouldBe "1"
  }
}
