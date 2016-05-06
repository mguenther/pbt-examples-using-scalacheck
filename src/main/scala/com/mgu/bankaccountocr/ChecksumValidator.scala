package com.mgu.bankaccountocr

import scala.util.Try

class ChecksumValidator {

  def isValid(accountNumber: String): Boolean = {

    def containsOnlyNumericalLetters(accountNumber: String): Boolean =
      Try(accountNumber.toDouble).isSuccess

    def hasValidLength(accountNumber: String): Boolean =
      return accountNumber.length == 9

    def hasValidChecksum(accountNumber: String): Boolean =
      return computeChecksum(accountNumber) == 0

    return containsOnlyNumericalLetters(accountNumber) && hasValidLength(accountNumber) && hasValidChecksum(accountNumber)
  }

  private def computeChecksum(accountNumber: String): Int =
    return accountNumber
      .split("")
      .map(_.toInt)
      .reverse
      .zip(Stream from 1)
      .foldLeft(0)((a, b) => a + (b._1 * b._2)) % 11
}

object ChecksumValidator extends App {
  val validator = new ChecksumValidator()
  println(validator.isValid("559682859"))
  println(validator.isValid("291865097"))
  println(validator.isValid("458381470"))
  println(validator.isValid("620965622"))
  println(validator.isValid("653289707"))
  println(validator.isValid("745989802"))
  println(validator.isValid("468497455"))
  println(validator.isValid("463062647"))
  println(validator.isValid("662558243"))
  println(validator.isValid("836385632"))
}
