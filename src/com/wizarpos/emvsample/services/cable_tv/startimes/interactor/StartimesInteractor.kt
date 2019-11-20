package com.iisysgroup.payvice.startimes.interactor

import com.itex.richard.payviceconnect.model.StartimesModel
import io.reactivex.Single

/**
 * Created by Olije Favour on 6/24/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */


interface StartimesInteractor {
    fun validateNumber(smartCardCode: String): Single<StartimesModel.lookupResponse>
    fun subscribe(authPin: String, password: String, customerName: String, phone: String, productCode: String, bouquet: String, paymentMethod: String, smartCardCode: String, amount: Int): Single<StartimesModel.payResponse>


    enum class StartimesProduct {
        STARTIMES;

        override fun toString() = this.name.toLowerCase()
    }
}