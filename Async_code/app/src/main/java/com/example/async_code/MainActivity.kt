package com.example.async_code

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            // بررسی ترتیب اجرای کدهای async
            lifecycleScope.launch {
                val timeStart = System.currentTimeMillis()

                // اجرای دو عملیات به صورت async
                val deferred1 = async { doTask1() }
                val deferred2 = async { doTask2() }

                // جمع کردن نتایج دو عملیات async
                val result = deferred1.await() + deferred2.await()

                val timeEnd = System.currentTimeMillis()

                // نمایش نتیجه در UI
                val timeTaken = timeEnd - timeStart
                resultTextView.text = "Result: $result\nTime Taken: $timeTaken ms"
            }
        }
    }

    // یک عملیات زمان‌بر
    private suspend fun doTask1(): Int {
        delay(2000) // شبیه‌سازی یک کار 2 ثانیه‌ای
        return 10
    }

    // یک عملیات زمان‌بر دیگر
    private suspend fun doTask2(): Int {
        delay(3000) // شبیه‌سازی یک کار 3 ثانیه‌ای
        return 20
    }
}