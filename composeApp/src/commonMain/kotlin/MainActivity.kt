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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity {

    @Composable
    fun CalcView(){

    }

    @Composable
    fun CalcRow(){

    }

    @Composable
    fun CalcDisplay(display: MutableState<String>){
        Text(display.value, modifier = Modifier.height(50.dp).padding(5.dp).fillMaxWidth())
    }
    @Composable
    fun CalcNumericButton(number : Int, display: MutableState<String>){
        Button(
            onClick = { display.value += number } ,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = display.value)
        }
    }

    @Composable
    fun CalcOperationButton(display: MutableState<String>){
        Button(
            onClick = {  },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = display.value)
        }

    }
    @Composable
    fun CalcEqualsButton(display: MutableState<String>){
        val displayState = remember { mutableStateOf("=") }
        Button(
            onClick = { displayState.value = "0" },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = displayState.value)
        }
    }


}