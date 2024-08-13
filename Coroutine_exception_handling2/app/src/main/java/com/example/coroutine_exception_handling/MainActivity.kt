package com.example.coroutine_exception_handling

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)

        // شروع یک Coroutine
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // شبیه‌سازی یک درخواست شبکه‌ای
                textView.text = "Fetching data..."
                val data = fetchDataFromApi() // یک تابع شبیه‌سازی شده برای درخواست API
                textView.text = data
            } catch (e: Exception) {
                // مدیریت خطا
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                textView.text = "Failed to fetch data."
            }
        }
    }

    // شبیه‌سازی یک تابع برای دریافت داده از API
    private suspend fun fetchDataFromApi(): String {
        delay(2000) // شبیه‌سازی تأخیر در پاسخ API
        throw Exception("Network error") // شبیه‌سازی خطا
        return "Data from API"
    }
}
