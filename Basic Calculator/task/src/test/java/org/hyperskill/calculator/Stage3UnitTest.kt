package org.hyperskill.calculator

import org.hyperskill.calculator.internals.BasicCalculatorUnitTest
import org.junit.Assert.assertEquals
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner

// Version 2.0.1
@RunWith(RobolectricTestRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class Stage3UnitTest : BasicCalculatorUnitTest<MainActivity>(MainActivity::class.java) {


    @Test
    fun test00_checkCalculatorWaitsForTheNextValue() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                addButton.clickAndRun()
                this.assertClearDisplay(
                    expectedHint = 1.0,
                    testCaseDescription = "Clicking addButton after button1"
                )
            }
        }
    }

    @Test
    fun test01_checkCalculatorAdds() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                button1.clickAndRun()
                button1.clickAndRun()
                addButton.clickAndRun()
                this.assertClearDisplay(
                    expectedHint = 111.0,
                    testCaseDescription = "Clicking addButton with 111 on display"
                )
                button2.clickAndRun()
                button2.clickAndRun()
                button2.clickAndRun()
                equalButton.clickAndRun()

                val expectedResult = 333.0
                this.assertResult(expectedResult, "After add operation between 111 and 222")
            }
        }
    }

    @Test
    fun test02_checkCalculatorSubtracts() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button3.clickAndRun()
                button4.clickAndRun()
                button5.clickAndRun()
                subtractButton.clickAndRun()
                this.assertClearDisplay(
                    expectedHint = 345.0,
                    testCaseDescription = "Clicking subtractButton with 345 on display"
                )
                button6.clickAndRun()
                button7.clickAndRun()
                button8.clickAndRun()
                equalButton.clickAndRun()

                val expectedResult = 345.0 - 678.0
                val testCaseDescription = "After subtract operation between 345 and 678"
                this.assertResult(expectedResult, testCaseDescription)
            }
        }
    }

    @Test
    fun test03_checkCalculatorMultiplies() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                button0.clickAndRun()
                button1.clickAndRun()
                multiplyButton.clickAndRun()
                this.assertClearDisplay(
                    expectedHint = 101.0,
                    testCaseDescription = "Clicking multiplyButton with 101 on display"
                )

                button8.clickAndRun()
                button9.clickAndRun()
                equalButton.clickAndRun()

                val expectedResult = 101.0 * 89.0
                val testCaseDescription = "After multiply operation between 101 and 89"
                this.assertResult(expectedResult, testCaseDescription)

            }
        }
    }

    @Test
    fun test04_checkCalculatorDivides() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button5.clickAndRun()
                divideButton.clickAndRun()

                this.assertClearDisplay(
                    expectedHint = 5.0,
                    testCaseDescription = "Clicking divideButton with 5 on display"
                )

                button2.clickAndRun()
                equalButton.clickAndRun()


                val expectedResult = 5.0/2.0
                val testCaseDescription = "After divide operation between 5 and 2"
                this.assertResult(expectedResult, testCaseDescription)
            }
        }
    }

    @Test
    fun test05_checkCalculatorSupportsNegativeNumbersOnFirstTerm() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                subtractButton.clickAndRun()
                button1.clickAndRun()

                val actualText = displayEditText.text.toString()


                val message = "Looks like your calculator does not support negative numbers on first term."
                assertEquals(message, "-1", actualText)
            }
        }
    }

    @Test
    fun test06_checkCalculatorSupportsNegativeNumbersOnSecondTerm() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button5.clickAndRun()
                multiplyButton.clickAndRun()
                subtractButton.clickAndRun()
                button3.clickAndRun()
                equalButton.clickAndRun()

                val message = "Looks like your calculator does not support negative numbers on second term."
                assertResult(-15.0, message)
            }
        }
    }

    @Test
    fun test07_checkCalculatorCanDoMultipleActions() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button1.clickAndRun()
                button0.clickAndRun()
                button0.clickAndRun()
                addButton.clickAndRun()
                button2.clickAndRun()
                button5.clickAndRun()
                equalButton.clickAndRun()
                divideButton.clickAndRun()
                button5.clickAndRun()
                equalButton.clickAndRun()
                multiplyButton.clickAndRun()
                button1.clickAndRun()
                button0.clickAndRun()
                equalButton.clickAndRun()
                subtractButton.clickAndRun()
                button5.clickAndRun()
                button0.clickAndRun()
                equalButton.clickAndRun()

                val message = "Looks like your calculator can't handle doing action after action."
                val expectedResult = 200.0
                assertResult(expectedResult, message)
            }
        }
    }

    @Test
    fun test08_checkNoNegativeZeroesToTheLeft() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                subtractButton.clickAndRun()
                button0.clickAndRun()
                button1.clickAndRun()
                val actualText = displayEditText.text.toString()
                val message = "Clicked the subtract, zero and button1, got a wrong result."
                assertEquals(message, "-1", actualText)
            }
        }
    }

    @Test
    fun test09_checkNoNegativeDotsWithoutZero() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                subtractButton.clickAndRun()
                dotButton.clickAndRun()
                button1.clickAndRun()
                val actualText = displayEditText.text.toString()
                val message = "Clicked the subtract, dotButton and button1, got a wrong result."
                assertEquals(message, "-0.1", actualText)
            }
        }
    }

    @Test
    fun test10_checkMinusOperationAfterZeroAsResult() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button5.clickAndRun()
                subtractButton.clickAndRun()
                button5.clickAndRun()
                equalButton.clickAndRun()
                subtractButton.clickAndRun()
                button5.clickAndRun()
                equalButton.clickAndRun()
                val message = "It should be possible to make a minus operation " +
                        "after an operation resulting in zero"
                assertResult(-5.0, message)
            }
        }
    }

    @Test
    fun test11_checkMinusOperationWithNegativeSecondTermAfterZeroAsResult() {
        testActivity {
            calculatorView.apply {
                clearButton.clickAndRun()
                button5.clickAndRun()
                subtractButton.clickAndRun()
                button5.clickAndRun()
                equalButton.clickAndRun()
                subtractButton.clickAndRun()
                subtractButton.clickAndRun()
                button5.clickAndRun()
                equalButton.clickAndRun()
                val message = "It should be possible to make a minus operation " +
                        "with negative second term after an operation resulting in zero"
                assertResult(5.0, message)
            }
        }
    }
}