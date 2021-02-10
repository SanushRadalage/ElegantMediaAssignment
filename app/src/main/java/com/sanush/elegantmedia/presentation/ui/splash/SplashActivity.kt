package com.sanush.elegantmedia.presentation.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.sanush.elegantmedia.R
import com.sanush.elegantmedia.presentation.ui.MainActivity
import com.sanush.elegantmedia.presentation.ui.auth.AuthActivity
import com.sanush.elegantmedia.util.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = this
            .getSharedPreferences("AuthState", Context.MODE_PRIVATE)

        CoroutineScope(Main).launch {

            if (!sharedPreferences.get("fbSignStatus", false)) {
                delay(1000)
                val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                delay(1000)
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}