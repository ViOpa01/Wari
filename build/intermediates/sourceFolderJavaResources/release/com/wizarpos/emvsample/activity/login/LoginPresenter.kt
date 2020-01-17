package com.wizarpos.emvsample.activity.login

interface LoginPresenter {

    fun login(userID: String, password: String)
}