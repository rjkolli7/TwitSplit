package com.demo.twitsplit.helper

/***
 *
 */
object CommonHelper {

    const val MAX_MESSAGE_CHARS = 50f // Max message length
    const val MAX_WORD_CHARS = 46 // Max word length if message is more than 1 part,


    /**
     * This method used to check if message is <= MAX_MESSAGE_CHARS or > MAX_MESSAGE_CHARS.
     * If message is > MAX_MESSAGE_CHARS it will split the messages in to chunks and return message with parts.
     *
     *
     * If message is < MAX_MESSAGE_CHARS it will check message contains whitespace or not.
     * If message not contains whitespace it will return null
     * If message contains whitespace it will return message as it is
     *
     * @param message
     * @return arrayOf(String) || null
     */
    fun splitMessage(message: String): Array<String?>? {
        if (!message.contains(" ")) { // check space
            return if (message.length <= MAX_MESSAGE_CHARS) { // check message length true or false
                arrayOf(message)
            } else null
        }

        if (message.length <= MAX_MESSAGE_CHARS) // check message length true or false
            return arrayOf(message)

        val totalParts = getFinalPartsCount(
            message,
            Math.ceil((message.length / MAX_MESSAGE_CHARS).toDouble()).toInt()
        ) // get totalParts count

        val messageParts = getMessageParts(totalParts) // get message parts array like 1/5..5/5
        val words = message.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() // split message into words by space.

        var wordPosition = 0 // changes wordPosition while running loop
        var messageLength = 0 // changes messageLength after adding to StringBuilder

        var builder: StringBuilder // add message to builder
        for (i in messageParts.indices) { // get message parts
            builder = StringBuilder()
            builder.append(messageParts[i]) // append part
            for (j in wordPosition until words.size) { // get word
                if (!isWordInLimit(words[j])!!) // check word length return null if it exceeded
                    return null
                if (messageLength + words[j].length > MAX_MESSAGE_CHARS || j == words.size - 1) { // true only if messageLength and word length in range or j equals words last position
                    if (j == words.size - 1 && i == messageParts.size - 1) { // true only if j equals words lastPosition or i equals messageParts lastPosition
                        builder.append(" " + words[j]) // append last word with space
                    }
                    messageParts[i] = builder.toString() // replace builder string to messageParts by position(i)
                    wordPosition = j // assign wordPosition = j
                    messageLength = 0 // assign 0 to messageLength
                    break // break loop
                } else {
                    builder.append(" " + words[j]) // append words to builder with space
                    messageLength = builder.toString().length // assign messageLength
                }
            }
        }

        return messageParts // return final message array with parts
    }

    /**
     * This method used to add Parts to chunk messages like 1/5...5/5
     *
     *
     * @param totalParts
     * @return arrayOf(String)
     */
    fun getMessageParts(totalParts: Int): Array<String?> {
        val messageParts = arrayOfNulls<String>(totalParts)
        for (i in 0 until totalParts) {
            val messagePart = i + 1
            messageParts[i] = messagePart.toString() + "/" + totalParts
        }
        return messageParts
    }

    /**
     * This method used to check MAX_WORD_CHARS length with word
     *
     *
     * @param word
     * @return Boolean
     */
    fun isWordInLimit(word: String): Boolean? {
        return word.length <= MAX_WORD_CHARS
    }

    /**
     * This method append parts to message then calculate no of
     * parts required
     *
     * Ex: message.length/Math.celi(MAX_MESSAGE_CHARS)
     *
     * @param message, totalParts
     * @return Int
     */
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
