package hu.bme.aut.f1calendar.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.f1calendar.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}