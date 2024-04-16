  package com.example.diceroller
  import android.os.Bundle
  import android.widget.Toast
  import androidx.activity.ComponentActivity
  import androidx.activity.compose.setContent
  import androidx.compose.foundation.Image
  import androidx.compose.foundation.layout.Column
  import androidx.compose.foundation.layout.Spacer
  import androidx.compose.foundation.layout.fillMaxSize
  import androidx.compose.foundation.layout.height
  import androidx.compose.foundation.layout.padding
  import androidx.compose.foundation.layout.wrapContentSize
  import androidx.compose.foundation.text.KeyboardOptions
  import androidx.compose.material3.Button
  import androidx.compose.material3.MaterialTheme
  import androidx.compose.material3.OutlinedTextField
  import androidx.compose.material3.Surface
  import androidx.compose.material3.Text
  import androidx.compose.material3.TextField
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.getValue
  import androidx.compose.runtime.mutableIntStateOf
  import androidx.compose.runtime.mutableStateOf
  import androidx.compose.runtime.remember
  import androidx.compose.runtime.setValue
  import androidx.compose.ui.Alignment
  import androidx.compose.ui.Modifier
  import androidx.compose.ui.platform.LocalContext
  import androidx.compose.ui.res.painterResource
  import androidx.compose.ui.res.stringResource
  import androidx.compose.ui.text.input.ImeAction
  import androidx.compose.ui.text.input.KeyboardType
  import androidx.compose.ui.tooling.preview.Preview
  import androidx.compose.ui.unit.dp
  import com.example.diceroller.ui.theme.DiceRollerTheme


  class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                }
                DiceRollerApp()
            }
        }
    }
}

  @Composable
  fun SimpleOutlinedTextFieldSample(modifier: Modifier = Modifier
  ) {
      var text by remember { mutableStateOf("") }

      OutlinedTextField(
          value = text,
          onValueChange = { text = it },
          label = { Text("Label") }
      )
  }


  @Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )

}
  @Composable
  fun DiceWithButtonAndImage(modifier: Modifier = Modifier){
      var result by remember { mutableIntStateOf(1) }
      var userInput by remember { mutableStateOf("")}
      val imageResource = when (result) {
          1 -> R.drawable.dice_1
          2 -> R.drawable.dice_2
          3 -> R.drawable.dice_3
          4 -> R.drawable.dice_4
          5 -> R.drawable.dice_5
          else -> R.drawable.dice_6
      }
      var message by remember { mutableStateOf("") } // Corrigindo o nome da variável

      Column(
          modifier = modifier,
          horizontalAlignment = Alignment.CenterHorizontally
      ){
          Image(
              painter = painterResource(imageResource),
              contentDescription = result.toString()
          )
          Spacer(modifier = Modifier.height(16.dp))

          Button(
              onClick = {
                  val userNumber = userInput.toIntOrNull() ?: 0 // Convertendo para Int
                  result = (1..6).random()
                  message = if (userNumber == result) {
                      "Acertou, Boa!"
                  } else {
                      "Errou! O Número Era $result."
                  }
                  val contexto = LocalContext.current
                  Toast.makeText(contexto, message, Toast.LENGTH_SHORT).show() // Usando a variável 'message'
              }
          )
          {
              Text(stringResource(id = R.string.roll))
          }
          TextField(
              value = userInput,
              onValueChange = { userInput = it },
              label = { Text(stringResource(R.string.Bet)) },
              modifier = Modifier.padding(top = 16.dp),
              keyboardOptions = KeyboardOptions.Default.copy(
                  keyboardType = KeyboardType.Number,
                  imeAction = ImeAction.Done
              )
          )
          Spacer(modifier = Modifier.height(16.dp))
      }
  }
