package com.zoho.customkeyboard

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zoho.customkeyboard.ui.theme.CustomKeyboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomKeyboardTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var text by remember { mutableStateOf("") }

                    TextField(
                        value = text,
                        onValueChange = { text = it }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { openKeyboardSettings() }
                    ) { Text("open key board") }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { openKeyboardChooserSettings() }
                    ) { Text("choose key board") }
                }
            }
        }
    }

    private fun openKeyboardSettings() {
        val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
        startActivity(intent)
    }
    private fun openKeyboardChooserSettings() {
        val im = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        im.showInputMethodPicker()
    }
}

