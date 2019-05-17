package com.linhtruong.sample.functional

import com.linhtruong.sample.core.functional.Either
import com.linhtruong.sample.UnitTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Test


/**
 * @author linhtruong
 */
class EitherTest : UnitTest() {
    @Test
    fun `Either Right should return correct type`() {
        val result = Either.Right("right")
        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe true
        result.isLeft shouldBe false
        result.either({}, {
            right ->
            right shouldBeInstanceOf String::class.java
            right shouldEqual "right"
        })
    }

     @Test
    fun `Either Left should return correct type`() {
        val result = Either.Left("left")
        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe false
        result.isLeft shouldBe true
        result.either({
            left ->
            left shouldBeInstanceOf String::class.java
            left shouldEqual "left"
        },{})
    }
}
