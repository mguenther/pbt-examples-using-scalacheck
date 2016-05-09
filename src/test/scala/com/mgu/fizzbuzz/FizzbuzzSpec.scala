package com.mgu.fizzbuzz

import com.mgu.fizzbuzz.Fizzbuzz.fizzbuzz
import org.scalacheck.Gen
import org.specs2.ScalaCheck
import org.specs2._

class FizzbuzzSpec extends Specification with ScalaCheck { def is = s2"""

  FizzBuzz should
    yield 'Fizz' for a number that is not divisible by 5 and multiplied by 3        $divisibleByThreeNotFive
    yield 'Buzz' for a number that is not divisible by 3 and multiplied by 5        $divisibleByFiveNotThree
    yield 'FizzBuzz' for a number that is divisible by 15                           $divisibleByFifteen
    yield the identity of a number if not divisible by either 3 or 5                $notDivisibleByThreeOrFive
                                                                         """

  def divisibleByThreeNotFive = prop ((n: Int) => (fizzbuzz(n) mustEqual "Fizz")).setGen(ofThree)
  def divisibleByFiveNotThree = prop ((n: Int) => (fizzbuzz(n) mustEqual "Buzz")).setGen(ofFive)
  def divisibleByFifteen = prop ((n: Int) => (fizzbuzz(n) mustEqual "FizzBuzz")).setGen(ofFifteen)
  def notDivisibleByThreeOrFive = prop ((n: Int) => (fizzbuzz(n) mustEqual n.toString)).setGen(notMultiple)

  val numberGen = Gen.choose(Int.MinValue / 15, Int.MaxValue / 15)
  val ofThree = numberGen.suchThat(_ % 5 != 0).map(_ * 3)
  val ofFive = numberGen.suchThat(_ % 3 != 0).map(_ * 5)
  val ofFifteen = numberGen.map(_ * 15)
  val notMultiple = numberGen.suchThat(_ % 3 != 0).suchThat(_ % 5 != 0)

  val multiplesOfThreeButNotFive = for (n <- Gen.choose(-1000, 1000) if n % 5 != 0) yield 3 * n
  val multiplesOfFiveButNotThree = for (n <- Gen.choose(-1000, 1000) if n % 3 != 0) yield 5 * n
  val multiplesOfFifteen = for (n <- Gen.choose(-1000, 1000)) yield 15 * n
  val excludeMultiplesOfThreeOrFive = for (n <- Gen.choose(-1000, 1000) if n % 5 !=0 && n % 3 != 0) yield n
}