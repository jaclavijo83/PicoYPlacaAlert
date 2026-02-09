package com.jaccs3000.picoyplacaalert

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                PicoPlacaScreen(
                    requestPermission = { requestNotificationPermissionIfNeeded() }
                )
            }
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    100
                )
            }
        }
    }
}

@Composable
fun PicoPlacaScreen(
    requestPermission: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }

    var plate by remember { mutableStateOf(prefs.getPlate()) }

    var morningRange by remember { mutableStateOf(prefs.getMorningRange()) }
    var eveningRange by remember { mutableStateOf(prefs.getEveningRange()) }

    var lunes by remember { mutableStateOf(prefs.getDigitsForDay("L")) }
    var martes by remember { mutableStateOf(prefs.getDigitsForDay("M")) }
    var miercoles by remember { mutableStateOf(prefs.getDigitsForDay("X")) }
    var jueves by remember { mutableStateOf(prefs.getDigitsForDay("J")) }
    var viernes by remember { mutableStateOf(prefs.getDigitsForDay("V")) }

    var result by remember { mutableStateOf("") }

    var showDaysConfig by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Placa registrada", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = plate,
            onValueChange = {
                plate = it
                prefs.savePlate(it)
            },
            label = { Text("Placa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Franjas horarias", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = morningRange,
            onValueChange = {
                morningRange = it
                prefs.saveMorningRange(it)
            },
            label = { Text("Horario mañana") },
            supportingText = { Text("Ejemplo: 7-9") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = eveningRange,
            onValueChange = {
                eveningRange = it
                prefs.saveEveningRange(it)
            },
            label = { Text("Horario tarde") },
            supportingText = { Text("Ejemplo: 18-20") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showDaysConfig = !showDaysConfig },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (showDaysConfig)
                    "Ocultar configuración de días"
                else
                    "⚙ Configurar dígitos por día"
            )
        }

        if (showDaysConfig) {

            DayField("Lunes", lunes) {
                lunes = it
                prefs.saveDigitsForDay("L", it)
            }

            DayField("Martes", martes) {
                martes = it
                prefs.saveDigitsForDay("M", it)
            }

            DayField("Miércoles", miercoles) {
                miercoles = it
                prefs.saveDigitsForDay("X", it)
            }

            DayField("Jueves", jueves) {
                jueves = it
                prefs.saveDigitsForDay("J", it)
            }

            DayField("Viernes", viernes) {
                viernes = it
                prefs.saveDigitsForDay("V", it)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val morning = morningRange.split("-")
                val evening = eveningRange.split("-")

                val startMorning = morning.getOrNull(0)?.toIntOrNull() ?: 0
                val endMorning = morning.getOrNull(1)?.toIntOrNull() ?: 0

                val startEvening = evening.getOrNull(0)?.toIntOrNull() ?: 0
                val endEvening = evening.getOrNull(1)?.toIntOrNull() ?: 0

                val hasRestriction = PicoPlacaLogic.hasPicoYPlaca(
                    plate = plate,
                    digitsToday = prefs.getDigitsForToday(),
                    startMorning = startMorning,
                    endMorning = endMorning,
                    startEvening = startEvening,
                    endEvening = endEvening
                )

                result = if (hasRestriction) {
                    "🚫 Hoy tienes pico y placa"
                } else {
                    "✅ Hoy puedes circular"
                }

                requestPermission()

                NotificationHelper.showNotification(
                    context,
                    "Pico y Placa",
                    result
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Validar ahora")
        }

        if (result.isNotEmpty()) {
            Text(result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun DayField(label: String, value: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        supportingText = { Text("Ejemplo: 5,6") },
        modifier = Modifier.fillMaxWidth()
    )
}
