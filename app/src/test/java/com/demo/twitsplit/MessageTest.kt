package com.demo.twitsplit

import com.demo.twitsplit.helper.CommonHelper
import com.demo.twitsplit.helper.CommonHelper.MAX_MESSAGE_CHARS

import org.junit.Test

import org.junit.Assert.*

/**
 * Local unit test, which will execute on the development machine (host).
 */
class MessageTest {


    /**
     * checks message has white space or not and length is in range of MAX_MESSAGE_CHARS.
     * */
    @Test
    fun message_nonWhiteSpace_50CharLess() {
        val message = "Ican'tbelieveTweeternowsupportschunkingmymessages."
        assertTrue(message, message.length <= MAX_MESSAGE_CHARS && !message.contains(" "))
    }

    /**
     * checks message has white space or not and length is not in range of MAX_MESSAGE_CHARS.
     * */
    @Test
    fun message_nonWhiteSpace_50CharMore() {
        val message = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thavetodoitmyself."
        assertTrue(message, message.length > MAX_MESSAGE_CHARS && !message.contains(" "))
    }

    /**
     * Split messages into chunks with parts.
     * And check chunk messages matches with expectedArray
     * */
    @Test
    fun checkChunkMessages() {
        val message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val expectedArray = arrayOf(
            "1/2 I can't believe Tweeter now supports chunking",
            "2/2 my messages, so I don't have to do it myself."
        )
        assertArrayEquals(expectedArray, CommonHelper.splitMessage(message))
    }

    /**
     * Split messages into chunks with parts.
     * And check parts matches with expectedArray
     * */
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

    /**
     * Split messages into chunks with parts.
     * And check parts count matches with expectedValue 2
     * */
    @Test
    fun check_finalSplitsCount() {
        val message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        assertEquals(
            2,
            CommonHelper.getFinalPartsCount(message, Math.ceil((message.length / MAX_MESSAGE_CHARS).toDouble()).toInt()).toLong()
        )
    }

    /**
     * Check word limit is in limit of MAX_WORD_CHARS
     * */
    @Test
    fun check_WordCountInLimit() {
        val message = "0123456789012345678901234567890123456789012345"
        assertTrue(message, CommonHelper.isWordInLimit(message)!!)
    }

    /**
     * Check word limit is not in limit of MAX_WORD_CHARS
     * */
    @Test
    fun check_WordCountNotInLimit() {
        val message = "01234567890123456789012345678901234567890123456"
        assertFalse(message, CommonHelper.isWordInLimit(message)!!)
    }
}