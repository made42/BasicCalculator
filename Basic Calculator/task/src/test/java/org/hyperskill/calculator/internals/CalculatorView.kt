package org.hyperskill.calculator.internals

import android.app.Activity
import android.text.InputType
import android.widget.Button
import android.widget.EditText

import org.junit.Assert.assertEquals

class CalculatorView<T : Activity>(test: BasicCalculatorUnitTest<T>) {

    val displayEditText = with(test) {
        activity.findViewByString<EditText>("displayEditText").also { displayEditText ->
            displayEditText.assertInitialDisplayEditText()
        }
    }
    val button0 = with(test) {
        val idString = "button0"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "0")
        }
    }
    val button1 = with(test) {
        val idString = "button1"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "1")
        }
    }
    val button2 = with(test) {
        val idString = "button2"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "2")
        }
    }
    val button3 = with(test) {
        val idString = "button3"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "3")
        }
    }
    val button4 = with(test) {
        val idString = "button4"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "4")
        }
    }
    val button5 = with(test) {
        val idString = "button5"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "5")
        }
    }
    val button6 = with(test) {
        val idString = "button6"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "6")
        }
    }
    val button7 = with(test) {
        val idString = "button7"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "7")
        }
    }
    val button8 = with(test) {
        val idString = "button8"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "8")
        }
    }
    val button9 = with(test) {
        val idString = "button9"
        activity.findViewByString<Button>(idString).also {
            it.assertText(idString, "9")
        }
    }
    val dotButton = with(test) {
        activity.findViewByString<Button>("dotButton")
    }
    val equalButton = with(test) {
        activity.findViewByString<Button>("equalButton")
    }
    val clearButton = with(test) {
        activity.findViewByString<Button>("clearButton")
    }
    val addButton = with(test) {
        activity.findViewByString<Button>("addButton")
    }
    val subtractButton = with(test) {
        activity.findViewByString<Button>("subtractButton")
    }
    val multiplyButton = with(test) {
        activity.findViewByString<Button>("multiplyButton")
    }
    val divideButton = with(test) {
        activity.findViewByString<Button>("divideButton")
    }
    private val numberButtons = listOf(
        button0, button1, button2, button3, button4,
        button5, button6, button7, button8, button9
    )

    private fun EditText.assertInitialDisplayEditText() {

        // initial text should be empty string
        val messageTextError = "The EditText with id \"displayEditText\" should initially contain \"\" in the property text."
        val expectedText = ""
        val actualText = this.text.toString()
        assertEquals(messageTextError, expectedText, actualText)

        // initial hint should be 0
        val messageHintError = "Check whether EditText with id \"displayEditText\" has \"0\" set as a hint"
        val expectedHint = "0"
        val actualHint = this.hint.toString()
        assertEquals(messageHintError, expectedHint, actualHint)

        // inputType = InputType.Type_NULL
        val messageDisabledError =
            "The EditText with id \"displayEditText\" should have inputType = InputType.Type_NULL"
        val expectedInputType = InputType.TYPE_NULL
        val actualIsEnabled = this.inputType
        assertEquals(messageDisabledError, expectedInputType, actualIsEnabled)
    }

    fun forEachNumberButton(action: (button: Button, idString: String) -> Unit) {
        numberButtons.forEachIndexed { index, button ->
            val idString = "button$index"
            action(button, idString)
        }
    }

    private fun Button.assertText(idString: String, expectedText: String) {
        val messageTextError = "The Button with id \"$idString\" has wrong initial text"
        val actualText = this.text.toString()
        assertEquals(messageTextError, expectedText, actualText)
    }

    fun assertClearDisplay(expectedHint: String, testCaseDescription: String) {
        val messageClearError = "$testCaseDescription, displayEditText text was not cleared properly"
        val actualText = displayEditText.text.toString()
        val expectedText = ""
        assertEquals(messageClearError, expectedText, actualText)

        val messageHintAfterClearError = "$testCaseDescription, displayEditText hint was not set properly"
        val actualHint = displayEditText.hint.toString()
        assertEquals(messageHintAfterClearError, expectedHint, actualHint)
    }

    fun assertClearDisplay(expectedHint: Double, testCaseDescription: String) {
        val messageClearError = "$testCaseDescription, displayEditText text was not cleared properly"
        val actualText = displayEditText.text.toString()
        val expectedText = ""
        assertEquals(messageClearError, expectedText, actualText)

        val messageHintAfterClearError = "$testCaseDescription, displayEditText hint was not set properly"
        val actualHint = displayEditText.hint.toString().toDouble()
        assertEquals(messageHintAfterClearError, expectedHint, actualHint, 0.0)
    }

    fun assertResult(expectedResult: String, testCaseDescription: String) {
        val messageClearError = "$testCaseDescription, displayEditText text was not cleared properly"
        val actualText = displayEditText.text.toString()
        val expectedText = ""
        assertEquals(messageClearError, expectedText, actualText)

        val messageHintAfterClearError = "$testCaseDescription, displayEditText hint was not set properly"
        val actualHint = displayEditText.hint.toString()
        assertEquals(messageHintAfterClearError, expectedResult, actualHint)
    }

    fun assertResult(expectedResult: Double, testCaseDescription: String) {
        val messageClearError = "$testCaseDescription, displayEditText text was not cleared properly"
        val actualText = displayEditText.text.toString()
        val expectedText = ""
        assertEquals(messageClearError, expectedText, actualText)

        val messageHintResultError = "$testCaseDescription, displayEditText hint was not set properly"
        val actualResult = displayEditText.hint.toString().toDouble()
        assertEquals(messageHintResultError, expectedResult, actualResult, 0.0)
    }
}