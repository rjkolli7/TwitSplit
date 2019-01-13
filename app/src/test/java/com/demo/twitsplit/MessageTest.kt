package com.demo.twitsplit

import com.demo.twitsplit.CommonHelper.MAX_MESSAGE_CHARS
import org.junit.Test

import org.junit.Assert.*

class MessageTest {

    @Test
    fun message_nonWhiteSpace_50CharLess() {
        val message = "Ican'tbelieveTweeternowsupportschunkingmymessages."
        assertTrue(message, message.length <= MAX_MESSAGE_CHARS && !message.contains(" "))
    }

    @Test
    fun message_nonWhiteSpace_50CharMore() {
        val message = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thavetodoitmyself."
        assertTrue(message, message.length > MAX_MESSAGE_CHARS && !message.contains(" "))
    }

    @Test
    fun checkChunkMessages() {
        val message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val expectedArray = arrayOf(
            "1/2 I can't believe Tweeter now supports chunking",
            "2/2 my messages, so I don't have to do it myself."
        )
        assertArrayEquals(expectedArray, CommonHelper.splitMessage(message))
    }

    @Test
    fun checkSplitParts() {
        val message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val expectedArray = arrayOf("1/2", "2/2")
        assertArrayEquals(
            expectedArray,
            CommonHelper.getMessageParts(
                CommonHelper.getFinalPartsCount(
                    message,
                    Math.ceil((message.length / MAX_MESSAGE_CHARS).toDouble()).toInt()
                )
            )
        )
    }

    @Test
    fun check_finalSplitsCount() {
        val message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        assertEquals(
            2,
            CommonHelper.getFinalPartsCount(message, Math.ceil((message.length / MAX_MESSAGE_CHARS).toDouble()).toInt()).toLong()
        )
    }

    @Test
    fun check_WordCountInLimit() {
        val message = "0123456789012345678901234567890123456789012345"
        assertTrue(message, CommonHelper.isWordInLimit(message)!!)
    }

    @Test
    fun check_WordCountNotInLimit() {
        val message = "01234567890123456789012345678901234567890123456"
        assertFalse(message, CommonHelper.isWordInLimit(message)!!)
    }
}