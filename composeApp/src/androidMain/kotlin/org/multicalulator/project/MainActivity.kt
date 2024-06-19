package org.multicalulator.project

import Calculator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalcView()
        }
    }


    @Composable
    fun CalcView() {
        val displayText = remember { mutableStateOf("0") }
        var leftNumber by rememberSaveable { mutableIntStateOf(0) }
        var rightNumber by rememberSaveable { mutableIntStateOf(0) }
        var operation by rememberSaveable { mutableStateOf("") }
        var complete by rememberSaveable { mutableStateOf(false) }

        if(operation.isNotEmpty() && complete){
            var answer by remember { mutableIntStateOf(0) }
              when(operation)  {
                "+" -> answer = Calculator().add(leftNumber , rightNumber)
                "-" -> answer = Calculator().subtract(leftNumber, rightNumber)
                "/" -> answer = Calculator().divide(leftNumber, rightNumber)
                "*" -> answer = Calculator().multiply(leftNumber, rightNumber)
            }
            displayText.value = answer.toString()

        } else if (operation.isNotEmpty() && !complete) {
            displayText.value = rightNumber.toString()

        } else {
            displayText.value = leftNumber.toString()
        }
        fun numberPress(btnNum : Int){
            if (complete) {
                leftNumber = 0
                rightNumber = 0
                operation = ""
                complete = false
            }
            if (operation.isNotEmpty() && !complete) {
                rightNumber = rightNumber * 10 + btnNum
            }
            else if  (operation.isEmpty() && !complete) {
                leftNumber = leftNumber * 10 + btnNum
            }

        }
        fun operationPress(op: String) {
            if (!complete)
                operation = op
        }
        fun equalsPress() {
            complete = true
        }
        Column(
            modifier = Modifier
                .background(Color.LightGray)
        ) {
            Row { CalcDisplay(displayText) }
            Row {
                Column {
                    for (i in 7 downTo 1 step 3) {
                        CalcRow(onPress = {number -> numberPress(number)}, startNum = i, numButtons = 3)
                    }
                    Row {
                        CalcNumericButton(number = 0, onPress = {number ->  numberPress(number)})
                        CalcEqualsButton(onPress = {equalsPress()})
                    }

                }
                Column {
                    CalcOperationButton(operation = "+", onPress = {operation -> operationPress(operation)})
                    CalcOperationButton(operation = "-", onPress = {operation -> operationPress(operation)})
                    CalcOperationButton(operation = "*", onPress = {operation -> operationPress(operation)})
                    CalcOperationButton(operation = "/", onPress = {operation -> operationPress(operation)})
                }
            }
        }

    }

    @Composable
    fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
        val endNum = startNum + numButtons
        Row(modifier = Modifier.padding(0.dp)) {
            for (i in startNum..<endNum) {
                CalcNumericButton(i, onPress)
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
    fun CalcNumericButton(number: Int, onPress: (number: Int) -> Unit) {
        Button(
            onClick = { onPress(number) },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = number.toString())
        }
    }

    @Composable
    fun CalcOperationButton(operation: String, onPress: (operation: String) -> Unit) {
        Button(
            onClick = {onPress(operation)},
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = operation)
        }

    }

    @Composable
    fun CalcEqualsButton(onPress: () -> Unit) {
        Button(
            onClick = onPress,
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


