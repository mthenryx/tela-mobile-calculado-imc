package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    var textFieldAltura by remember {
        mutableStateOf("")
    }

    var textFieldPeso by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf(0.0)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(id = R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "Logo App",
                modifier = Modifier
                    .size(80.dp)
                    .padding(vertical = 16.dp)
            )

            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .fillMaxHeight(1.0f)
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Seus dados",
                        color = Color(0xff46ACD5),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = textFieldAltura,
                        onValueChange = { novoTexto -> textFieldAltura = novoTexto },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text("Altura") },
                        placeholder = { Text("Em cm (ex: 175)") },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color(0xff46ACD5),
                            unfocusedBorderColor = Color(0xff46ACD5),
                            cursorColor = Color(0xFF525252),
                            focusedLabelColor = Color(0xFF525252)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = textFieldPeso,
                        onValueChange = { novoTexto -> textFieldPeso = novoTexto },
                        label = { Text("Peso") },
                        placeholder = { Text("Em kg (ex: 70)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color(0xff46ACD5),
                            unfocusedBorderColor = Color(0xff46ACD5),
                            cursorColor = Color(0xFF525252),
                            focusedLabelColor = Color(0xFF525252)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // Converte com segurança para evitar Crash
                            val peso = textFieldPeso.toDoubleOrNull() ?: 0.0
                            val alturaCm = textFieldAltura.toDoubleOrNull() ?: 0.0

                            if (peso > 0 && alturaCm > 0) {
                                // Converte altura de cm para metros
                                val alturaMetros = alturaCm / 100.0
                                result = peso / (alturaMetros * alturaMetros)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff46ACD5)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .height(50.dp)
                    ) {
                        Text("CALCULAR")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            textFieldPeso = ""
                            textFieldAltura = ""
                            result = 0.0
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        )
                    ) {
                        Text("Limpar campos")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Card do Resultado
        if (result > 5 && result < 240) {
            var corCard by remember {
                mutableStateOf(Color.White)
            }

            var tipoClassificação by remember {
                mutableStateOf("")
            }

            if (result < 18.5) {
                corCard = Color.Red
                tipoClassificação = "Abaixo do peso"
            } else if (result >= 18.5 && result < 25) {
                corCard = Color.Green
                tipoClassificação = "Peso ideal"
            } else if (result >= 25 && result < 30) {
                corCard = Color(0xFFFF9800)
                tipoClassificação = "Levemente acima do peso"
            } else if (result >= 30 && result < 35) {
                corCard = Color.Red
                tipoClassificação = "Obesidade grau I"
            } else if (result >= 35 && result < 40) {
                corCard = Color.Red
                tipoClassificação = "Obesidade grau II"
            } else if (result >= 40) {
                corCard = Color.Red
                tipoClassificação = "Obesidade grau III"
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(65.dp),
                colors = CardDefaults.cardColors(
                    containerColor = corCard
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "%.2f    $tipoClassificação".format(result),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}