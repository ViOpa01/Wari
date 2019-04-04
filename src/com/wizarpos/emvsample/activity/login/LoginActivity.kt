package com.wizarpos.emvsample.activity.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.wizarpos.util.SharedPreferenceUtils
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncMenuActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), LoginView {
    override fun showProgress() {
        loginProgressDialog.show()
    }

    override fun dismissProgress() {
        loginProgressDialog.dismiss()

    }

    override fun setInvalidUser() {
        loginProgressDialog.dismiss()
        toast("Your username is invalid")
    }

    override fun setInvalidPassword() {
        loginProgressDialog.dismiss()
        toast("Your password is invalid")
    }

    override fun showMessage(message: String) {
        loginProgressDialog.dismiss()
        toast(message)
    }

    override fun setLoginError(throwable: Throwable) {
        loginProgressDialog.dismiss()
        toast("Login Error")
    }

    override fun setLoginSuccessful() {
        loginProgressDialog.dismiss()
        SharedPreferenceUtils.setUserLoggedIn(this@LoginActivity, true)
        val intent = Intent(this@LoginActivity, FuncMenuActivity::class.java)
        startActivity(intent)
        Log.d("okh", "logged in")
        finish()
    }

    override fun setDeviceChangedError() {

    }

    override fun setShouldUpdatePin() {
        alert {
            title = "Change pin"
            message = "You have requested to recover your password, please change the pin using our payvice application - payvice.com"
            okButton {

            }
        }

    }

    private val loginProgressDialog by lazy {
        indeterminateProgressDialog(message = "Logging in")
    }

    private val presenter: LoginPresenter by lazy {
        LoginPresenterImpl(LoginInteractorImpl(applicationContext), this)
    }

    private lateinit var wallet_username : String
    private lateinit var wallet_password : String
    lateinit var sign_in_button : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        sign_in_button = findViewById(R.id.sign_in_button)

        var payviceUsername = ""
        try {
            payviceUsername = SharedPreferenceUtils.getPayviceUsername(this)

        } catch (e: Exception) {

        }

        if (payviceUsername.isNotEmpty()) {
              val intent = Intent(this, FuncMenuActivity::class.java)
             startActivity(intent)
            Log.d("okh", "signed in")
            finish()
        } else {

            sign_in_button.setOnClickListener {
                wallet_username = username.text.toString()
                wallet_password = password.text.toString()

                if (wallet_username.isEmpty()) {
                    username.error = "Please enter a valid username"
                } else if (wallet_password.isEmpty()) {
                    password.error = "Please enter a vaid password"
                }

                loginProgressDialog.show()
                presenter.login(wallet_username, wallet_password)


            }

        }
    }
}
