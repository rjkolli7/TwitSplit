package com.demo.twitsplit

object CommonHelper {

    const val MAX_MESSAGE_CHARS = 50f
    const val MAX_WORD_CHARS = 46

    fun splitMessage(message: String): Array<String?>? {
        if (!message.contains(" ")) {
            return if (message.length <= MAX_MESSAGE_CHARS) {
                arrayOf(message)
            } else null
        }

        if (message.length <= MAX_MESSAGE_CHARS)
            return arrayOf(message)

        val totalParts = getFinalPartsCount(message, Math.ceil((message.length / MAX_MESSAGE_CHARS).toDouble()).toInt())

        val messageParts = getMessageParts(totalParts)
        val words = message.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        var wordPosition = 0
        var messageLength = 0

        var builder: StringBuilder
        for (i in messageParts.indices) {
            builder = StringBuilder()
            builder.append(messageParts[i])
            for (j in wordPosition until words.size) {
                if (!isWordInLimit(words[j])!!)
                    return null
                if (messageLength + words[j].length > MAX_MESSAGE_CHARS || j == words.size - 1) {
                    if (j == words.size - 1 && i == messageParts.size - 1) {
                        builder.append(" " + words[j])
                    }
                    messageParts[i] = builder.toString()
                    wordPosition = j
                    messageLength = 0
                    break
                } else {
                    builder.append(" " + words[j])
                    messageLength = builder.toString().length
                }
            }
        }

        return messageParts
    }

    fun getMessageParts(totalParts: Int): Array<String?> {
        val messageParts = arrayOfNulls<String>(totalParts)
        for (i in 0 until totalParts) {
            val messagePart = i + 1
            messageParts[i] = messagePart.toString() + "/" + totalParts
        }
        return messageParts
    }

    fun isWordInLimit(word: String): Boolean? {
        return word.length <= MAX_WORD_CHARS
    }

    fun getFinalPartsCount(message: String, totalParts: Int): Int {
        val builder = StringBuilder()
        builder.append(message)
        for (i in 0 until totalParts) {
            val messagePart = i + 1
            builder.append("$messagePart/$totalParts")
        }
        return Math.ceil((builder.toString().length / MAX_MESSAGE_CHARS).toDouble()).toInt()
    }


}
