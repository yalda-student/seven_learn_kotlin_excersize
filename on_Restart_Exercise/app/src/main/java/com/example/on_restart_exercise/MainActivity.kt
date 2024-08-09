package com.example.on_restart_exercise

import android.content.Intent
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
import com.example.on_restart_exercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var textViewStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewStatus = findViewById(R.id.textViewStatus)
        val buttonNextActivity: Button = findViewById(R.id.buttonNextActivity)

        buttonNextActivity.setOnClickListener {
            // انتقال به اکتیویتی جدید
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        // عملیاتی که هنگام بازگشت به اکتیویتی اصلی انجام می‌شود
        textViewStatus.text = "Activity Restarted"
    }
}
