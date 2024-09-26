package org.hyperskill.calculator

import org.hyperskill.calculator.internals.BasicCalculatorUnitTest
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner

// Version 2.0
@RunWith(RobolectricTestRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class Stage4UnitTest : BasicCalculatorUnitTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun test00_checkCalculatorIntegerHint() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                addButton.clickAndRun()

                val testCaseDescription =
                    "After operation with integer number display hint as integer"
                assertClearDisplay(expectedHint = "1", testCaseDescription)
            }
        }
    }

    @Test
    fun test01_checkCalculatorIntegerHintTypedAsDouble() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                dotButton.clickAndRun()
                button0.clickAndRun()
                divideButton.clickAndRun()

                val testCaseDescription =
                    "After operation with integer number display hint as integer"
                assertClearDisplay(expectedHint = "1", testCaseDescription)
            }
        }
    }

    @Test
    fun test02_checkCalculatorIntegerResult() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                dotButton.clickAndRun()
                button5.clickAndRun()
                multiplyButton.clickAndRun()
                button2.clickAndRun()
                equalButton.clickAndRun()

                val testCaseDescription =
                    "If the result of an operation is an integer number display the result as integer"
                assertResult(expectedResult = "3", testCaseDescription)
            }
        }
    }

    @Test
    fun test03_checkCalculatorDoubleHint() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button3.clickAndRun()
                dotButton.clickAndRun()
                button3.clickAndRun()
                multiplyButton.clickAndRun()

                val testCaseDescription =
                    "After operation with double number display hint as double"
                assertClearDisplay(expectedHint = "3.3", testCaseDescription)
            }
        }
    }

    @Test
    fun test04_checkCalculatorDoubleResult() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button3.clickAndRun()
                dotButton.clickAndRun()
                button3.clickAndRun()
                subtractButton.clickAndRun()
                button1.clickAndRun()
                equalButton.clickAndRun()

                val testCaseDescription =
                    "After operation with double result number display result as double"
                assertResult(expectedResult = "2.3", testCaseDescription)
            }
        }
    }

    @Test
    fun test05_checkCalculatorRepeatsOperationWithEqualsAfterEquals() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button3.clickAndRun()
                multiplyButton.clickAndRun()
                button2.clickAndRun()
                var expectedResult = 3.0 * 2.0
                val testCaseDescription =
                    "Clicking equal after equal should repeat operation"

                repeat(4) {
                    equalButton.clickAndRun()
                    assertResult(expectedResult, testCaseDescription)
                    expectedResult *= 2.0
                }
            }
        }
    }

    @Test
    fun test06_checkEqualsAfterOperation() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button5.clickAndRun()
                multiplyButton.clickAndRun()
                equalButton.clickAndRun()

                val expectedResult = 5.0 * 5.0
                val testCaseDescription =
                    "If the second term of the operation is not set use the first term on both sides"
                assertResult(expectedResult, testCaseDescription)
            }
        }
    }

    @Test
    fun test07_checkEqualsAfterOperationWithResultOfLastOperation() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                button0.clickAndRun()
                divideButton.clickAndRun()
                button2.clickAndRun()
                equalButton.clickAndRun()
                multiplyButton.clickAndRun()
                equalButton.clickAndRun()

                val expectedResult = 5.0 * 5.0
                val testCaseDescription =
                    "If the second term is not set use the first term on both sides including the case in which the first term is the result of a previous operation"
                assertResult(expectedResult, testCaseDescription)
            }
        }
    }
}