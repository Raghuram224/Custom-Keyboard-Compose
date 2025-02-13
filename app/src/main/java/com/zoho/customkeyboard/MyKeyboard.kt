package com.zoho.customkeyboard
import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.inputmethod.InputConnection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MyKeyboard : InputMethodService() {
    @Composable
    fun KeyboardView(inputConnection: InputConnection?) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val keys = listOf(
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "X", "C", "V", "B", "N", "M", ",", "."
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)) {
            keys.chunked(10).forEach { row ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    row.forEach { key ->
                        KeyButton(key) {
                            inputConnection?.commitText(key, 1)
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                SpecialKeyButton("Space") {
                    inputConnection?.commitText(" ", 1)
                }
                SpecialKeyButton("Enter") {
                    inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
                }
                SpecialKeyButton("Backspace") {
                    inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                }
            }
        }
    }

    @Composable
    fun KeyButton(text: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text, fontSize = 16.sp)
        }
    }

    @Composable
    fun SpecialKeyButton(text: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(4.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text, fontSize = 14.sp)
        }
    }
}

