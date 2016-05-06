package com.mgu.bankaccountocr

import org.scalatest.{Matchers, FlatSpec}

class ChecksumValidatorFlatSpec extends FlatSpec with Matchers {

  "ChecksumValidator" should "yield true for valid account numbers" in {
    val validator = new ChecksumValidator()
    validator.isValid("000000000") shouldBe true
    validator.isValid("000000051") shouldBe true
    validator.isValid("123456789") shouldBe true
    validator.isValid("457508000") shouldBe true
  }

  "ChecksumValidator" should "yield false for invalid account numbers" in {
    val validator = new ChecksumValidator()
    validator.isValid("664371495") shouldBe false
  }
}
