package com.iisysgroup.payvice.base.interactor

import com.itex.richard.payviceconnect.model.DstvModel
import io.reactivex.Single


interface MultichoiceInteractor {
    fun validateIucAndGetPlans(iuc: String, product: MultichoiceProduct): Single<DstvModel.DstvResponse>
    fun subscribe(iuc: String, plan: DstvModel.Data, product: MultichoiceProduct, authPin: String): Single<DstvModel.PayResponse>


    enum class MultichoiceProduct {
        DSTV, GOTV;

        override fun toString() = this.name.toLowerCase()
    }
}