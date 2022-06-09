package com.example.adiser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adiser.authentication.AuthenticationActivity
import com.example.adiser.databinding.ActivitySplashscreenBinding

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ss.alpha = 0f
        binding.imgSplash.alpha = 0f
        binding.ss.animate().setDuration(1500).alpha(1f).withEndAction {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.imgSplash.animate().setDuration(1500).alpha(1f).withEndAction {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
