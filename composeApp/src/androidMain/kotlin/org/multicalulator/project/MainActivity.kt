package org.multicalulator.project

import App
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //App()
            CalcView()
        }
    }


    @Composable
    fun CalcView() {
        val displayText = remember { mutableStateOf("0") }
        Column(
            modifier = Modifier
                .background(Color.LightGray)
        ) {
            Row { CalcDisplay(displayText) }
            Row {
                Column {
                    for (i in 7 downTo 1 step 3) {
                        CalcRow(displayText, i, 3)
                    }
                    Row {
                        CalcNumericButton(number = 0, display = displayText)
                        CalcEqualsButton(display = displayText)
                    }

                }
                Column {
                    CalcOperationButton(operation = "+", display = displayText)
                    CalcOperationButton(operation = "-", display = displayText)
                    CalcOperationButton(operation = "*", display = displayText)
                    CalcOperationButton(operation = "/", display = displayText)

                }
            }
        }

    }

    @Composable
    fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int) {
        val endNum = startNum + numButtons
        Row(modifier = Modifier.padding(0.dp)) {
            for (i in startNum..<endNum) {
                CalcNumericButton(i, display)
            }
        }

    }

    @Composable
    fun CalcDisplay(display: MutableState<String>) {
        Text(
            display.value, modifier = Modifier
                .height(50.dp)
                .padding(5.dp)
                .fillMaxWidth()
        )
    }

    @Composable
    fun CalcNumericButton(number: Int, display: MutableState<String>) {
        Button(
            onClick = { display.value += number },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = number.toString())
        }
    }

    @Composable
    fun CalcOperationButton(operation: String, display: MutableState<String>) {
        Button(
            onClick = { },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = operation)
        }

    }

    @Composable
    fun CalcEqualsButton(display: MutableState<String>) {

        Button(
            onClick = { display.value = "0" },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = "=")
        }
    }

    @Preview
    @Composable
    fun AppAndroidPreview() {
        CalcView()
    }
}


