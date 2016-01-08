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

  def divisibleByThreeNotFive = prop ((n: Int) => (fizzbuzz(n) mustEqual "Fizz")).setGen(multiplesOfThreeButNotFive)
  def divisibleByFiveNotThree = prop ((n: Int) => (fizzbuzz(n) mustEqual "Buzz")).setGen(multiplesOfFiveButNotThree)
  def divisibleByFifteen = prop ((n: Int) => (fizzbuzz(n) mustEqual "FizzBuzz")).setGen(multiplesOfFifteen)
  def notDivisibleByThreeOrFive = prop ((n: Int) => (fizzbuzz(n) mustEqual n.toString)).setGen(excludeMultiplesOfThreeOrFive)

  val multiplesOfThreeButNotFive = for (n <- Gen.choose(-1000, 1000) if n % 5 != 0) yield 3 * n
  val multiplesOfFiveButNotThree = for (n <- Gen.choose(-1000, 1000) if n % 3 != 0) yield 5 * n
  val multiplesOfFifteen = for (n <- Gen.choose(-1000, 1000)) yield 15 * n
  val excludeMultiplesOfThreeOrFive = for (n <- Gen.choose(-1000, 1000) if n % 5 !=0 && n % 3 != 0) yield n
}