package org.hyperskill.calculator.internals

import android.app.Activity

open class BasicCalculatorUnitTest<T : Activity>(clazz: Class<T>): AbstractUnitTest<T>(clazz) {

    val calculatorView by lazy {
        CalculatorView(this)
    }

}