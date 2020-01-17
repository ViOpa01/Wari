package com.wizarpos.emvsample.db.detailed

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.*

/**
 * Created by Olije Favour on 11/22/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.


@Database(entities = [EodData::class,VasTransactionResult::class,CardTransactionResult::class,AirtimeEntity::class,CableTvEntity::class,DiscoEntity::class,TransferEntity::class,WithdrawalEntity::class],version = 1)
abstract class TransDataBase : RoomDatabase() {

    abstract  fun getTransactionDataDoa():TransactionDataDoa
    abstract  fun getEodDataBase():EodDoa
    abstract  fun getVasDataBase():VasTransactionDoa
    abstract  fun getDiscoTable():DiscoDoa
    abstract  fun getAirtimeTable():AirtimeDoa
    abstract  fun getCableTable():CableTvDoa
    abstract  fun getTransferTable():TransferDoa


    companion object {
        @Volatile
        var INSTANCE: TransDataBase? =null

        fun getInstance(context: Context): TransDataBase {

            val tempInstance= INSTANCE

            if (tempInstance != null){

                return tempInstance
            }

            synchronized(this){

                val instance = Room.databaseBuilder(context.applicationContext,TransDataBase::class.java,"trans_db").build()

                INSTANCE=instance

                return instance
            }
        }


    }







}