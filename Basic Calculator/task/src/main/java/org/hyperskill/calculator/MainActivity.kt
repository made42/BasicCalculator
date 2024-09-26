package org.hyperskill.calculator

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    class Expression(var left: String = "", var op: String = "", var right: String = "")

    private lateinit var displayEditText: EditText
    private var cont = false

    private var exp = Expression()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayEditText = findViewById(R.id.displayEditText)
        displayEditText.inputType = InputType.TYPE_NULL
        listOf(R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9)
            .forEach { findViewById<Button>(it).setOnClickListener { view ->
                    if (displayEditText.text.toString() == "0") displayEditText.text.clear()
                    displayEditText.append((view as Button).text)
                }
            }
        findViewById<Button>(R.id.button0).setOnClickListener(View.OnClickListener { _ ->
            val input = displayEditText.text
            if (input.toString() == "-") { return@OnClickListener }
            if (input.isEmpty() || !input.startsWith("0")) { input.append("0") }
            displayEditText.text = input
        })
        findViewById<Button>(R.id.dotButton).setOnClickListener { _ ->
            val input = displayEditText.text
            if (input.isEmpty() || input.toString() == "-") { input.append("0") }
            if (!input.contains(".")) { input.append(".") }
            displayEditText.text = input
        }
        listOf(R.id.addButton, R.id.subtractButton, R.id.multiplyButton, R.id.divideButton)
            .forEach { findViewById<Button>(it).setOnClickListener(View.OnClickListener { view ->
                exp.right = ""
                if (view.id == R.id.subtractButton) {
                    if (cont) {
                        exp.left = displayEditText.hint.toString()
                        exp.op = (view as Button).text.toString()
                        cont = false
                        return@OnClickListener
                    }
                    if (displayEditText.text.isEmpty()) {
                        displayEditText.text.append("-")
                        return@OnClickListener
                    }
                }
                exp.left = displayEditText.text.ifEmpty { displayEditText.hint }.toString()
                exp.op = (view as Button).text.toString()
                if (displayEditText.text.isNotEmpty()) {
                    val txt = displayEditText.text.toString().toDouble()
                    displayEditText.hint = if ((txt - txt.toInt()) == 0.0) txt.toInt().toString() else txt.toString()
                }
                displayEditText.text.clear()
            }) }
        findViewById<Button>(R.id.equalButton).setOnClickListener { _ ->
            var sndTrm = exp.right
            if (displayEditText.text.isNotEmpty()) {
                exp.right = displayEditText.text.toString()
                sndTrm = exp.right
            } else {
                exp.left = displayEditText.hint.toString()
                if (sndTrm == "") {
                    sndTrm = exp.left
                }
            }
            val result: Double = when (exp.op) {
                "+" -> exp.left.toDouble() + sndTrm.toDouble()
                "-" -> exp.left.toDouble() - sndTrm.toDouble()
                "*" -> exp.left.toDouble() * sndTrm.toDouble()
                "/" -> exp.left.toDouble() / sndTrm.toDouble()
                else -> 0.0
            }
            displayEditText.text.clear()
            displayEditText.hint = if ((result - result.toInt()) == 0.0) result.toInt().toString() else result.toString()
            cont = true
        }
        findViewById<Button>(R.id.clearButton).setOnClickListener { _ ->
            displayEditText.text.clear()
            displayEditText.hint = "0"
            cont = false
        }

        /*
            Tests for android can not guarantee the correctness of solutions that make use of
            mutation on "static" variables to keep state. You should avoid using those.
            Consider "static" as being anything on kotlin that is transpiled to java
            into a static variable. That includes global variables and variables inside
            singletons declared with keyword object, including companion object.
            This limitation is related to the use of JUnit on tests. JUnit re-instantiate all
            instance variable for each test method, but it does not re-instantiate static variables.
            The use of static variable to hold state can lead to state from one test to spill over
            to another test and cause unexpected results.
            Using mutation on static variables to keep state
            is considered a bad practice anyway and no measure
            attempting to give support to that pattern will be made.
        */
    }
}