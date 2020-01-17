package com.wizarpos.util

import com.iisysgroup.poslib.host.Host
import com.wizarpos.emvsample.services.helper.activity.util.Models
import java.io.Serializable

/**
 * Created by Agbede on 2/28/2018.
 */

data class TransactionModel(val cardDetails: Models.CardDetails,val VasDetails:Models.VasDetails) : Serializable
