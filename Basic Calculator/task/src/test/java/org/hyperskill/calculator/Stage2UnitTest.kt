package org.hyperskill.calculator

import android.widget.Button
import org.hyperskill.calculator.internals.BasicCalculatorUnitTest
import org.junit.Assert.assertEquals
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner

// Version 2.0
@RunWith(RobolectricTestRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class Stage2UnitTest : BasicCalculatorUnitTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun test00_checkButtonsWork() {
        testActivity {
            calculatorView.apply {

                button1.clickAndRun()
                button2.clickAndRun()
                button3.clickAndRun()
                button4.clickAndRun()
                button5.clickAndRun()
                button6.clickAndRun()
                button7.clickAndRun()
                button8.clickAndRun()
                button9.clickAndRun()
                button0.clickAndRun()

                val messageDisplayNumbersError = "Clicked all the buttons, but something went wrong while displaying numbers"
                val expectedNumberToDisplay = "1234567890"
                val actualNumberDisplayed = displayEditText.text.toString()
                assertEquals(messageDisplayNumbersError, expectedNumberToDisplay, actualNumberDisplayed)
            }
        }
    }

    @Test
    fun test01_checkClearButtonWorks() {
        testActivity {
            calculatorView.apply {

                forEachNumberButton { button: Button, idString: String ->
                    button.clickAndRun()
                    clearButton.clickAndRun()
                    this.assertClearDisplay(
                        expectedHint = "0",
                        testCaseDescription = "Clicked the clearButton after clicking $idString"
                    )
                }
            }
        }

    }

    @Test
    fun test02_checkZeroButtonLock() {
        testActivity {
            calculatorView.apply {

                clearButton.clickAndRun()
                button0.clickAndRun()
                button0.clickAndRun()
                button0.clickAndRun()
                button0.clickAndRun()
                button0.clickAndRun()

                val actualText = displayEditText.text.toString()
                val message = "Clicked the button0 multiple times, got more or less than one 0."
                assertEquals(message, "0", actualText)
            }
        }
    }

    @Test
    fun test03_checkDotButtonLock() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button0.clickAndRun()
                dotButton.clickAndRun()
                dotButton.clickAndRun()
                dotButton.clickAndRun()
                val actualText = displayEditText.text.toString()
                val message = "Clicked the dotButton multiple times, got more or less than one dot."
                assertEquals(message, "0.", actualText)
            }
        }

    }

    @Test
    fun test04_checkDotButtonLockAfterNumber() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button0.clickAndRun()
                dotButton.clickAndRun()
                button8.clickAndRun()
                dotButton.clickAndRun()
                val actualText = displayEditText.text.toString()
                val message = "Clicked the dotButton multiple times, got more or less than one dot."
                assertEquals(message, "0.8", actualText)
            }
        }

    }

    @Test
    fun test05_checkNoZeroesToTheLeft() {
        testActivity {
            calculatorView.apply {

                clearButton.clickAndRun()
                button0.clickAndRun()
                button1.clickAndRun()

                val actualText = displayEditText.text.toString()
                val message = "Clicked the zero and button1, got a wrong result."
                assertEquals(message, "1", actualText)
            }
        }

    }

    @Test
    fun test06_checkNoDotsWithoutInteger() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                dotButton.clickAndRun()
                button1.clickAndRun()
                val actualText = displayEditText.text.toString()
                val message = "Clicked the dotButton and button1, got a wrong result."
                assertEquals(message, "0.1", actualText)
            }
        }
    }
}