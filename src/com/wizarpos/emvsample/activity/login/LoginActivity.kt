package com.wizarpos.emvsample.activity.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.wizarpos.util.SharedPreferenceUtils
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncMenuActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast
import com.wizarpos.emvsample.activity.IdleActivity


class LoginActivity : AppCompatActivity(), LoginView {
    override fun showProgress() {
        try{
            loginProgressDialog.dismiss()
        }catch (e : Exception){

        }
    }

    override fun dismissProgress() {
        try{
            loginProgressDialog.dismiss()
        }catch (e : Exception){

        }

    }

    override fun setInvalidUser() {
        try{
            loginProgressDialog.dismiss()
        }catch (e : Exception){

        }
        toast("Your userName is invalid")
    }

    override fun setInvalidPassword() {
        try{
            loginProgressDialog.dismiss()
        }catch (e : Exception){

        }
        toast("Your password is invalid")
    }

    override fun showMessage(message: String) {
        try{
            loginProgressDialog.dismiss()
        }catch (e : Exception){

        }
        toast(message)
    }

    override fun setLoginError(throwable: Throwable) {
        try{
            loginProgressDialog.dismiss()
        }catch (e : Exception){

        }
        toast("Login Error")
    }

    override fun setLoginSuccessful() {
        try{
            loginProgressDialog.dismiss()
        }catch (e : Exception){

        }
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

                toast("Logging in").duration = Toast.LENGTH_LONG
                wallet_username = username.text.toString()
                wallet_password = password.text.toString()

                if (wallet_username.isEmpty()) {
                    username.error = "Please enter a valid userName"
                } else if (wallet_password.isEmpty()) {
                    password.error = "Please enter a vaid password"
                }

                try{
                    loginProgressDialog.show()
                }catch (e : Exception){

                }
                presenter.login(wallet_username, wallet_password)


            }

        }
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, IdleActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("EXIT", true)
        startActivity(intent)
        super.onBackPressed()
    }
}
