package com.example.adiser.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adiser.R

class AuthenticationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_authentication)
    }
}
