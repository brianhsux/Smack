package com.example.brianhsu.smack.Controller

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.brianhsu.smack.R
import com.example.brianhsu.smack.Services.AuthService
import com.example.brianhsu.smack.Services.UserDataServices
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {


    var userAvatar = "profiledefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }


    fun generateUserAvatar(view: View) {
        val random = Random()
        val randomLD = random.nextInt(2)
        val randomNum = random.nextInt(28)

        if (randomLD == 0) {
            userAvatar = "light$randomNum"
        } else {
            userAvatar = "dark$randomNum"
        }

        val imageResId = resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImageView.setImageResource(imageResId)
    }

    fun generateColorClicked(view: View) {
        val random = Random()
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        createAvatarImageView.setBackgroundColor(Color.rgb(r, g, b))

        val saveR = r.toDouble() / 255
        val saveG = g.toDouble() / 255
        val saveB = b.toDouble() / 255

        avatarColor = "[$saveR, $saveG, $saveB, 1]"
    }

    fun createUserClicked(view: View) {

        val userName = createUserNameText.text.toString()
        val email = createUserEmailText.text.toString()
        val password = createUserPasswordText.text.toString()


        AuthService.registerUser(this, email, password) { registerSuccess ->
            if (registerSuccess) {
                AuthService.loginUser(this, email, password) { loginSuccess ->
                    if (loginSuccess) {
                        AuthService.createUser(this, userName, email, userAvatar, avatarColor) { createSuccess ->
                            if (createSuccess) {
                                println(UserDataServices.avatarName)
                                println(UserDataServices.avatarColor)
                                println(UserDataServices.name)
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }
}
