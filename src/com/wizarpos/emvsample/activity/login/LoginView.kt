package com.wizarpos.emvsample.activity.login

interface LoginView {

    fun showProgress()
    fun dismissProgress()

    fun setInvalidUser()
    fun setInvalidPassword()

    fun showMessage(message : String)

    fun setLoginError(throwable: Throwable)
    fun setLoginSuccessful()

    fun setDeviceChangedError()
    fun setShouldUpdatePin()

}