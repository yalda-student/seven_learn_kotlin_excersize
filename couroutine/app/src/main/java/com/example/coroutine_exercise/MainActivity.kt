package com.example.coroutine_exercise

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.coroutine_exercise.databinding.ActivityMainBinding
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