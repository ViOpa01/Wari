package com.wizarpos.emvsample.activity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.wizarpos.emvsample.models.Pfm

class DataModel {

    data class DataLookUpDetails(@Expose val service : String)

    data class DataLookUpFailedResponse(@Expose val error : String, @Expose val message : String, @Expose val date : String, @Expose val ref : String)

    data class DataLookUpSuccessResponse(@Expose val error : String, @Expose val data : List<DataResponseElements>)

    data class DataResponseElements(@Expose val type : String, @Expose val code : String, @Expose val description: String, @Expose val amount : String, @Expose val value : String, @Expose val duration : String ) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(type)
            parcel.writeString(code)
            parcel.writeString(description)
            parcel.writeString(amount)
            parcel.writeString(value)
            parcel.writeString(duration)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<DataResponseElements> {
            override fun createFromParcel(parcel: Parcel): DataResponseElements {
                return DataResponseElements(parcel)
            }

            override fun newArray(size: Int): Array<DataResponseElements?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class DataSubscriptionDetails( @Expose val clientReference:String,val terminal_id: String,  val user_id : String,  val password : String,  val pin : String, val phone : String,  val service : String,  val amount : String,  val description : String = "pay",  val code : String, val pfm:Pfm,@Expose var paymentMethod: String?)

    data class DataSubscriptionSuccessResponse(@Expose val error : Boolean, @Expose val message : String, @Expose val amount : String, @Expose val ref : String, @Expose val date : String,@Expose val transactionID:String)


    data class DataSubscriptionFailedResponse(@Expose val error : Boolean, @Expose val message : String, @Expose val date : String, @Expose val ref : String)

}