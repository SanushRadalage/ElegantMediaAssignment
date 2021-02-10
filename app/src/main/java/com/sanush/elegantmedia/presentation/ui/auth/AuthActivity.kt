package com.sanush.elegantmedia.presentation.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.sanush.elegantmedia.R
import com.sanush.elegantmedia.presentation.ui.MainActivity
import com.sanush.elegantmedia.util.put
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var callbackManager: CallbackManager

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val EMAIL = "email"

        sharedPreferences = this
            .getSharedPreferences("AuthState", Context.MODE_PRIVATE)

        val loginButton = findViewById<View>(R.id.login_button) as LoginButton
        callbackManager = CallbackManager.Factory.create()

        loginButton.setPermissions(listOf(EMAIL))

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                sharedPreferences.put("fbSignStatus", true)
                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onCancel() {
                Toast.makeText(this@AuthActivity, "SignIn cancelled", Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(this@AuthActivity, exception.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}