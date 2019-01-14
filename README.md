# TwitSplit
The product Tweeter allows users to post short messages limited to 50 characters each. Sometimes, users get excited and write messages longer than 50 characters. Instead of rejecting these messages, we would like to add a new feature that will split the message into parts and send multiple messages on the user's behalf, all of them meeting the 50 character requirement.

Android application versions:

Min SDK Version : 15
Target SDK Version: 28
Compile SDK Version: 28
Kotlin Version: 1.3.11
Gradle Version: 3.2.1

This Demo includes
1. Espresso Instrumental Test.
2. Business logic Unit Test.

# CommonHelper.kt

- splitMessage(message) method used for

1. To check if message is <= MAX_MESSAGE_CHARS or > MAX_MESSAGE_CHARS.
2. If message is > MAX_MESSAGE_CHARS it will split the messages in to chunks and return message with parts.
3. If message is < MAX_MESSAGE_CHARS it will check message contains whitespace or not.
4. If message not contains whitespace it will return null
5. If message contains whitespace it will return message as it is
6. This method will return either null or arrayOf(String)

- getMessageParts(totalParts)

1. This method used to add Parts to chunk messages like 1/5...5/5
2. To add parts to messages, should pas totalParts. Ex: 5

- getFinalPartsCount(message, totalParts)

1. This method used to get no of parts count required to get message parts.
2. To get final parts count should pass message and totalParts of message values.
3. This method will append parts to chunk messages based on totalParts then it will calculate final total parts count.

- isWordInLimit(word)

1. This method used to check MAX_WORD_CHARS length with word



