package com.mgu.bankaccountocr

import org.scalacheck.Gen
import org.specs2.{Specification, ScalaCheck}

class ChecksumValidatorSpec extends Specification with ScalaCheck { def is = s2"""

  ChecksumValidator should
    yield 'false' for an account number that contains non-digits    $invalidatesAccountNumberWithAlphaNumericalLetters
    yield 'false' for an account number that is not 9-digits wide   $invalidatesAccountNumberWithIncorrectLength
    yield 'false' for an account number with a non-zero checksum    $invalidatesAccountNumberWithWrongChecksum
    be idempotent on a valid account number                         $isIdempotentOnAnyAccountNumber
    yield 'true' for an account number with a checksum of zero      $validatesAccountNumberWithCorrectChecksum
  """

  val checksumValidator = new ChecksumValidator()

  def invalidatesAccountNumberWithAlphaNumericalLetters =
    prop((accountNumber: String) => (checksumValidator.isValid(accountNumber) mustEqual false)).setGen(genAccountNumberWithAlphaCharacters)

  def invalidatesAccountNumberWithIncorrectLength =
    prop((accountNumber: String) => (checksumValidator.isValid(accountNumber) mustEqual false)).setGen(genWrongSizedAccountNumber)

  def invalidatesAccountNumberWithWrongChecksum =
    prop((accountNumber: String) => (checksumValidator.isValid(accountNumber) mustEqual false)).setGen(genInvalidAccountNumber)

  def isIdempotentOnAnyAccountNumber =
    prop((accountNumber: String) => (checksumValidator.isValid(accountNumber) mustEqual checksumValidator.isValid(accountNumber))).setGen(genInvalidAccountNumber)

  def validatesAccountNumberWithCorrectChecksum =
    prop((accountNumber: String) => (checksumValidator.isValid(accountNumber) mustEqual true)).setGen(genValidAccountNumber)

  val genWrongSizedAccountNumber = Gen.listOf(Gen.choose(0, 9)).suchThat(_.size != 9).map(_.mkString)

  val genAccountNumberWithAlphaCharacters = Gen.listOfN(9, Gen.alphaChar).map(_.mkString)

  val genValidAccountNumber = for {
    d9 <- Gen.choose(0, 9)
    d8 <- Gen.choose(0, 9)
    d7 <- Gen.choose(0, 9)
    d6 <- Gen.choose(0, 9)
    d5 <- Gen.choose(0, 9)
    d4 <- Gen.choose(0, 9)
    d3 <- Gen.choose(0, 9)
    d2 <- Gen.choose(0, 9)
    d1 <- Gen.choose(0, 9) if (d1 + 2 * d2 + 3 * d3 + 4 * d4 + 5 * d5 + 6 * d6 + 7 * d7 + 8 * d8 + 9 * d9) % 11 == 0
  } yield List(d9, d8, d7, d6, d5, d4, d3, d2, d1).mkString

  val genInvalidAccountNumber = for {
    d9 <- Gen.choose(0, 9)
    d8 <- Gen.choose(0, 9)
    d7 <- Gen.choose(0, 9)
    d6 <- Gen.choose(0, 9)
    d5 <- Gen.choose(0, 9)
    d4 <- Gen.choose(0, 9)
    d3 <- Gen.choose(0, 9)
    d2 <- Gen.choose(0, 9)
    d1 <- Gen.choose(0, 9) if (d1 + 2 * d2 + 3 * d3 + 4 * d4 + 5 * d5 + 6 * d6 + 7 * d7 + 8 * d8 + 9 * d9) % 11 != 0
  } yield List(d9, d8, d7, d6, d5, d4, d3, d2, d1).mkString
}
