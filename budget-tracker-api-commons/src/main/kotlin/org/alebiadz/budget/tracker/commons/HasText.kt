package org.alebiadz.budget.tracker.commons

import org.alebiadz.budget.tracker.commons.exception.EnumerationException

interface HasText {

    val text: String

    companion object {

        inline fun <reified T> byText(text: String): T where T : Enum<T>, T : HasText {

            return enumValues<T>().find { it.text == text }
                ?: throw EnumerationException(
                    text,
                    enumValues<T>().map { it.text },
                    enumValues<T>().first().javaClass.kotlin
                )
        }
    }
}