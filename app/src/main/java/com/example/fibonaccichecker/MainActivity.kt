package com.example.fibonaccichecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FibonacciChecker()
        }
    }
}

@Composable
fun FibonacciChecker() {
    var numberText by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Digite um número para verificar se está na sequência de Fibonacci:")

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = numberText,
            onValueChange = { numberText = it },
            label = { Text("Número") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val number = numberText.text.toIntOrNull()
            result = if (number != null && pertenceFibonacci(number)) {
                "O número $number pertence à sequência de Fibonacci."
            } else {
                "O número $number não pertence à sequência de Fibonacci."
            }
        }) {
            Text("Verificar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(result)
    }
}

fun pertenceFibonacci(numero: Int): Boolean {
    var a = 0
    var b = 1
    var c: Int

    if (numero == 0 || numero == 1) return true

    while (b < numero) {
        c = a + b
        a = b
        b = c
    }

    return b == numero
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FibonacciChecker()
}
