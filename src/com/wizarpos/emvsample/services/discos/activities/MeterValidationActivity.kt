package com.wizarpos.emvsample.services.discos.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
import com.itex.richard.payviceconnect.model.*
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.services.discos.viewmodels.MeterValidationViewModel
import com.wizarpos.util.Service
import com.wizarpos.util.SingleImageTitleObject
import com.wizarpos.util.StringUtil.getClientRef
import com.wizarpos.util.VasServices.*
import kotlinx.android.synthetic.main._disco.*
import org.jetbrains.anko.*
import java.util.*

class MeterValidationActivity:AppCompatActivity(),View.OnClickListener{
//    GetMasterKey

    lateinit var productCode: String
    lateinit var meterNumber: String
    lateinit var  meterName: String
    lateinit var  address: String




    lateinit var password:String

    lateinit var channel:String
    lateinit var username:String
    lateinit var wallet:String
    lateinit var authPin:String
    lateinit var requestType:String
    lateinit var meterType:String
    lateinit var terminalID:String
    lateinit var electricMeterType:String
    lateinit var clientReference:String

    private var ref : String = ""


    private val disco by lazy{
        intent.getStringExtra(DiscosActivity.SERVICE)
    }

     val validationProgressDialogue by lazy {
        indeterminateProgressDialog("Validating Meter number", "Wari- Pos")
    }


    private var mMeterValidationViewModel: MeterValidationViewModel? = null

    private var isBeneficiary: Boolean= false


    lateinit var service :Service
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electricity)
        service=SERVICES[disco]!!
        subTitleText.text = service.name
        serviceImage.setImageResource(service.icon)

        mMeterValidationViewModel = ViewModelProviders.of(this).get(MeterValidationViewModel::class.java)



        clientReference = getClientRef(this, "")

        selectProductBtn.setOnClickListener(this)

        proceedBtn.setOnClickListener(this)


        mMeterValidationViewModel!!.lLookRes.observe(this, Observer {
//            if(it != null && mMeterValidationViewModel!!.mIsOnline ){
            Log.i("electricMeterType merer validation", electricMeterType)
            var message:String=""
            var error:Boolean =false
            when (electricMeterType){

                ABUJA_ELECTRICITY_POSTPAID,ABUJA_ELECTRICITY_PREPAID ->{
                   val  response =  it as AbujaModel.LookUpResponse
                    meterName = response!!.name!!
                    meterNumber =response!!.customerMeterNo!!
                    productCode =response!!.productCode!!
                    address=""
                    requestType = "0"
                    meterType ="2"
                    if(ABUJA_ELECTRICITY_PREPAID ==electricMeterType){
                        meterType ="0"
                    }

                    message=response.message!!
                    error=response.error
//                    displayResponse(_message =response!!.message!!,error = response!!.error,_meterName = meterName,_meterNumber =meterNumber ,_productCode =productCode,requestType = requestType,meterType = meterType,clientReference =clientReference,terminalId = terminalID,electricMeterType = electricMeterType )
                }

                ENUGU_ELECTRICITY_POSTPAID ,ENUGU_ELECTRICITY_PREPAID ->{
                   val  response =   it as EnuguModel.LookupResponse

                    meterName = response!!.name!!
                    meterNumber =response!!.account!!
                    productCode =response!!.productCode!!
                    address=""
                    requestType = ""
                    meterType=response.type
                    message=response.message!!
                    error=response.error


//                    displayResponse(_message =response!!.message!!,error = response!!.error,_meterName = meterName,_meterNumber =meterNumber ,_productCode =productCode,requestType = requestType,meterType = meterType,clientReference =clientReference,terminalId = terminalID,electricMeterType = electricMeterType )


                }

                EKO_ELECTRICITY_POSTPAID,EKO_ELECTRICITY_PREPAID ->{

                    val response =   it as EkoModel.EkoLookUpResponse

                    meterName = response!!.name!!
                    meterNumber =response!!.meterNumber!!
                    productCode =""
                    requestType =""
                    address=response.address!!
                    meterType=response.account_type!!
                    message=response.message!!
                    error=response.error

//                    displayResponse(_message =response!!.message!!,error = response!!.error,_meterName = meterName,_meterNumber =meterNumber ,_productCode =meterNumber,requestType = requestType,meterType = meterType,clientReference =clientReference,terminalId = terminalID,electricMeterType = electricMeterType  )

                }

                IBADAN_ELECTRICITY_POSTPAID ,IBADAN_ELECTRICITY_PREPAID ->{
                    val response =   it as IbadanModel.IbLookupResponse

                    meterName = response!!.name!!
                    meterNumber =response!!.account
                    productCode =response!!.productCode!!
                    requestType = ""
                    meterType=response.type!!
                    message=response.message!!
                    address=""
                    error=response.error

//                    displayResponse(_message =response!!.message!!,error = response!!.error,_meterName = meterName,_meterNumber =meterNumber ,_productCode =meterNumber,requestType = requestType,meterType = meterType,clientReference =clientReference,terminalId = terminalID,electricMeterType = electricMeterType  )

                }

                IKEJA_ELECTRICITY_POSTPAID,IKEJA_ELECTRICITY_PREPAID ->{
                    val response =   it as IkejaModel.IkejaLookupResponse

                    meterName = response!!.name!!
                    productCode =""
                    requestType =""
                    meterType=meterType
                    address=response.address!!
                    meterNumber =meterNumber
                    message=response.message!!
                    error=response.error

//                    displayResponse(_message =response!!.message!!,error = response!!.error,_meterName = meterName,_meterNumber =meterNumber ,_productCode =meterNumber,requestType = requestType,meterType = meterType,clientReference =clientReference,terminalId = terminalID,electricMeterType = electricMeterType  )


                }

                PORTHARCOURT_ELECTRICITY_POSTPAID,PORTHARCOURT_ELECTRICITY_PREPAID ->{

                    val response =   it as PortharcourtModel.lookUpResponse

                    meterName = response!!.name!!
                    meterNumber =response.meterNumber
                    productCode =response!!.productCode!!
                    requestType = ""
                    meterType=response.type!!
                    message=response.message!!
                    address=response.address!!
                    error=response.error

//                    displayResponse(_message =response!!.message!!,error = response!!.error,_meterName = meterName,_meterNumber =meterNumber ,_productCode =meterNumber,requestType = requestType,meterType = meterType,clientReference =clientReference,terminalId = terminalID,electricMeterType = electricMeterType  )


                }

                else ->longToast("Noting")
            }

//            }
            displayResponse(_message =message,error = error,_meterName = meterName,_meterNumber =meterNumber ,_productCode =productCode,requestType = requestType,meterType = meterType,clientReference =clientReference,terminalId = terminalID,electricMeterType = electricMeterType )

        })

    }


    private fun displayResponse(error: Boolean,_message:String,_meterName:String,_meterNumber:String,_productCode:String,requestType:String,meterType:String,clientReference:String,terminalId:String,electricMeterType:String){
        try {
            alert {

                if (error){

                    title = "Verification Failed "
                    message = _message
                    okButton { }
                }else {

                    title =  _message ?:  "Validation Successful"
                    message = "\n Customer Name - ${_meterName}" +
                            "\n \nMeter Number - ${_meterNumber}"

                    okButton {
                        Log.i("electricMeterType merer validation about sending >>", electricMeterType)
                        Log.d("requestType >>",requestType)

                        var intent =Intent(this@MeterValidationActivity, ElectricityPaymentActivity::class.java)
                        intent.putExtra(Helper.PASSWORD,password)
                        intent.putExtra(Helper.USERNAME,username)
                        intent.putExtra(Helper.WALLET,wallet)
                        intent.putExtra(Helper.CHANNEL,channel)
                        intent.putExtra(METER_NAME,_meterName)
                        intent.putExtra(METER_NUMBER,_meterNumber)
                        intent.putExtra(METER_TYPE,meterType)
                        intent.putExtra(PRODUCT_CODE,_productCode)

                        intent.putExtra(REQUEST_TYPE,requestType)
                        intent.putExtra(CLIENT_REFERENCE,clientReference)
                        intent.putExtra(TERMINAL_ID,terminalID)
                        intent.putExtra(ELECTRIC_METER_TYPE,electricMeterType)
                        intent.putExtra(ADDRESS,address)
//                        intent.putExtra(ADDRESS,electricMeterType)





                        startActivity(intent)
                    }
                }
            }.show()
        }
        catch (e:Throwable){
            alert {
                isCancelable =false
                title = "Verification Failed "
                message = _message
                okButton { }

            }.show()
        }
    }



    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.selectProductBtn ->{
             selectProduct(this,this,service)
            }

            R.id.proceedBtn ->{
                password = SecureStorage.retrieve(Helper.STORED_PASSWORD,"")
                channel = "ANDROID"
                username =SecureStorage.retrieve(Helper.USER_ID,"")
                wallet = SecureStorage.retrieve(Helper.TERMINAL_ID,"")
                authPin = SecureStorage.retrieve(Helper.PIN,"")
                terminalID = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
                requestType ="0"

                if(areFormFieldsFilled()) {
                    meterType=mMeterValidationViewModel!!.mProduct!!.requestCode
                    electricMeterType=mMeterValidationViewModel!!.mProduct!!.name
                    meterNumber =edtTxtMeterNumber.text.toString()
                    validateMeterNumber(activity = this,electricMeterType = electricMeterType,channel = channel,wallet =wallet,username = username,requestType = requestType,meterNo = meterNumber,password=password,terminalId = terminalID)
                }
            }
        }

    }



    private fun validateMeterNumber(activity: Activity, electricMeterType:String,channel: String, wallet: String, username: String, requestType: String, meterNo: String,password:String,terminalId:String){

           var meterType= ""
            when (electricMeterType){


                ABUJA_ELECTRICITY_POSTPAID ->{
                    meterType= ABUJA_POSTPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                 ABUJA_ELECTRICITY_PREPAID->{
                    meterType=ABUJA_PREPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                ENUGU_ELECTRICITY_POSTPAID ->{
                    meterType= ENUGU_POSTPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                ENUGU_ELECTRICITY_PREPAID ->{
                    meterType= ENUGU_PREPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                EKO_ELECTRICITY_POSTPAID ->{
                    meterType= EKO_POSTPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                EKO_ELECTRICITY_PREPAID ->{
                    meterType= EKO_PREPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                IBADAN_ELECTRICITY_POSTPAID ->{
                    meterType= IBADAN_POSTPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                IBADAN_ELECTRICITY_PREPAID ->{
                    meterType= IBADAN_PREPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                IKEJA_ELECTRICITY_POSTPAID ->{
                    meterType= IKEJA_POSTPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                IKEJA_ELECTRICITY_PREPAID ->{
                    meterType= IKEJA_PREPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                PORTHARCOURT_ELECTRICITY_POSTPAID ->{
                    meterType=  PORTHARCOURT_POSTPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }

                PORTHARCOURT_ELECTRICITY_PREPAID ->{
                    meterType=PORTHARCOURT_PREPAID
                    mMeterValidationViewModel!!.validateMeterNumber(activity = activity, electricMeterType = electricMeterType, channel = channel, wallet = wallet, username = username, requestType = requestType, meterNo =meterNo, meterType = meterType, password =password, terminalId =terminalId)

                }
            }


    }




     private fun areFormFieldsFilled():Boolean {

         if( mMeterValidationViewModel!!.mProduct==null){

             longToast("Select a Product")
             return false
         }

         if(edtTxtMeterNumber.text.toString().isNullOrEmpty()){
             edtTxtMeterNumber.error="Meter number cannot be Empty"
             return false
         }
         return true
     }




     private fun selectProduct(context: Context, activity: Activity, service: Service):Service.Product? {
         var  product :Service.Product?= null
         val networkDialog = BottomSheetDialog(context)

        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_list_layout, null, false)
        networkDialog.setContentView(view)

        val titleView = view.findViewById<TextView>(R.id.titleText)
        titleView.text = String.format("Select %s Product", service)

        val listView:ListView = view.findViewById(android.R.id.list)
        val itemList = ArrayList<SingleImageTitleObject>()

        for (product in service.products) {
            itemList.add(SingleImageTitleObject(product.name, service.icon))
        }

        listView.adapter = SingleImageTitleObject.SingleImageTitleAdapter(itemList, activity,
                R.layout.bottom_sheet_list_item)
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            product = service.products[position]
            mMeterValidationViewModel!!.mProduct=product
            Log.d("product >>>", Gson().toJson(product))
            productText.text = product!!.name
            networkDialog.dismiss()
        }

        networkDialog.show()

         Log.d("product >>>2", Gson().toJson(product))
         return product
    }


    companion object{
        const val METER_NAME = "meterName"
        const val METER_NUMBER = "meterNumber"
        const val PRODUCT_CODE = "productCode"
        const val REQUEST_TYPE = "requestType"
        const val METER_TYPE = "meterType"
        const val CLIENT_REFERENCE ="clientReference"
        const val TERMINAL_ID ="terminalId"
        const val ELECTRIC_METER_TYPE ="electricMeterType"
        const val ADDRESS ="address"


    }
}
