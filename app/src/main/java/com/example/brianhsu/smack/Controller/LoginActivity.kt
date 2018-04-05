package com.example.brianhsu.smack.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.brianhsu.smack.R
import com.example.brianhsu.smack.Services.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        enableSpinner(false)
    }

    fun loginLoginBtnClicked(view: View) {
        enableSpinner(true)

        val email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.loginUser(this, email, password) { loginSuccess ->
                if (loginSuccess) {
                    AuthService.findUserByEmail(this) { findSuccess ->
                        if (findSuccess) {
                            enableSpinner(false)
                            finish()
                        } else {
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }
            }
        } else {
            Toast.makeText(this, "Make sure email, and password are filled in.",
                    Toast.LENGTH_LONG).show()
            enableSpinner(false)
        }
    }

    fun errorToast() {
        Toast.makeText(this, "Something went wrong, please try again.",
                Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }

    fun loginCreateUserBtnClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
        finish()
    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            loginSpinner.visibility = View.VISIBLE
        } else {
            loginSpinner.visibility = View.INVISIBLE
        }

        loginEmailText.isEnabled = !enable
        loginPasswordText.isEnabled = !enable
        loginLoginBtn.isEnabled = !enable
        loginCreateUserBtn.isEnabled = !enable
    }
}
