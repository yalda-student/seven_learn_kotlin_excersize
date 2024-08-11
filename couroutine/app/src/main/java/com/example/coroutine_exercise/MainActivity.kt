package com.example.coroutine_exercise

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            // راه‌اندازی Coroutine برای انجام یک عملیات زمان‌بر
            lifecycleScope.launch {
                // شبیه‌سازی یک عملیات زمان‌بر
                val result = withContext(Dispatchers.IO) {
                    performLongRunningTask()
                }
                // نمایش نتیجه در UI
                resultTextView.text = result
            }
        }
    }

    // یک عملیات فرضی که چند ثانیه طول می‌کشد تا کامل شود
    private suspend fun performLongRunningTask(): String {
        delay(3000) // شبیه‌سازی تأخیر
        return "Task Completed"
    }
}