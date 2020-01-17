package com.wizarpos.emvsample.db.detailed;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa_Impl;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa_Impl;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa_Impl;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class TransDataBase_Impl extends TransDataBase {
  private volatile TransactionDataDoa _transactionDataDoa;

  private volatile EodDoa _eodDoa;

  private volatile VasTransactionDoa _vasTransactionDoa;

  private volatile DiscoDoa _discoDoa;

  private volatile AirtimeDoa _airtimeDoa;

  private volatile CableTvDoa _cableTvDoa;

  private volatile TransferDoa _transferDoa;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `EodData` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `transactionRef` TEXT NOT NULL, `transactionType` TEXT NOT NULL, `type` TEXT NOT NULL, `specificTransaction` TEXT NOT NULL, `dateTime` INTEGER NOT NULL, `responseCode` TEXT NOT NULL, `amount` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `VasTransactionResult` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `card_id` TEXT, `amount` TEXT NOT NULL, `walletId` TEXT NOT NULL, `marchantAddress` TEXT NOT NULL, `marchantTid` TEXT NOT NULL, `marchantName` TEXT NOT NULL, `merchantId` TEXT NOT NULL, `product` TEXT NOT NULL, `transactionStatusMessage` TEXT NOT NULL, `vasTid` TEXT NOT NULL, `transactionRef` TEXT NOT NULL, `paymentmethod` TEXT NOT NULL, `logo` INTEGER NOT NULL, `dateTime` TEXT NOT NULL, `error` INTEGER NOT NULL, `vasType` TEXT NOT NULL, FOREIGN KEY(`card_id`) REFERENCES `CardTransactionResult`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE  INDEX `index_VasTransactionResult_card_id` ON `VasTransactionResult` (`card_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CardTransactionResult` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `RRN` TEXT NOT NULL, `authID` TEXT NOT NULL, `STAN` TEXT NOT NULL, `PAN` TEXT NOT NULL, `transactionStatus` TEXT NOT NULL, `responseCode` TEXT NOT NULL, `transactionStatusReason` TEXT NOT NULL, `accountType` TEXT NOT NULL, `batchNumber` TEXT NOT NULL, `merchantID` TEXT NOT NULL, `isoTransmissionDateTime` TEXT NOT NULL, `terminalID` TEXT NOT NULL, `originalForwardingInstitutionCode` TEXT NOT NULL, `cardHolderName` TEXT NOT NULL, `cardExpiry` TEXT NOT NULL, `TSI` TEXT NOT NULL, `TVR` TEXT NOT NULL, `AID` TEXT NOT NULL, `amount` INTEGER NOT NULL, `additionalAmount` INTEGER NOT NULL, `longDateTime` INTEGER NOT NULL, `transactionType` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AirtimeEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `card_id` INTEGER, `transactionRef` TEXT NOT NULL, `recepiant_phone` TEXT NOT NULL, FOREIGN KEY(`card_id`) REFERENCES `CardTransactionResult`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE  INDEX `index_AirtimeEntity_card_id` ON `AirtimeEntity` (`card_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CableTvEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `card_id` INTEGER, `stan` TEXT, `amount` TEXT NOT NULL, `walletId` TEXT NOT NULL, `marchantAddress` TEXT NOT NULL, `marchantTid` TEXT NOT NULL, `marchantName` TEXT NOT NULL, `merchantId` TEXT NOT NULL, `product` TEXT NOT NULL, `transactionStatusMessage` TEXT NOT NULL, `vasTid` TEXT NOT NULL, `transactionRef` TEXT NOT NULL, `paymentmethod` TEXT NOT NULL, `logo` INTEGER NOT NULL, `dateTime` TEXT NOT NULL, `vasType` TEXT NOT NULL, `error` INTEGER NOT NULL, `iuc` TEXT NOT NULL, FOREIGN KEY(`card_id`) REFERENCES `CardTransactionResult`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE  INDEX `index_CableTvEntity_card_id` ON `CableTvEntity` (`card_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DiscoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `card_id` INTEGER, `stan` TEXT, `amount` TEXT NOT NULL, `walletId` TEXT NOT NULL, `marchantAddress` TEXT NOT NULL, `marchantTid` TEXT NOT NULL, `marchantName` TEXT NOT NULL, `merchantId` TEXT NOT NULL, `product` TEXT NOT NULL, `transactionStatusMessage` TEXT NOT NULL, `vasTid` TEXT NOT NULL, `transactionRef` TEXT NOT NULL, `paymentmethod` TEXT NOT NULL, `logo` INTEGER NOT NULL, `dateTime` TEXT NOT NULL, `error` INTEGER NOT NULL, `vasType` TEXT NOT NULL, `recepiant_name` TEXT NOT NULL, `meterType` TEXT NOT NULL, `transactionId` TEXT NOT NULL, `unit` TEXT NOT NULL, `unit_value` TEXT NOT NULL, `vat` TEXT NOT NULL, `meterNumber` TEXT NOT NULL, `token` TEXT NOT NULL, `address` TEXT NOT NULL, `arras` TEXT NOT NULL, `tarrif` TEXT NOT NULL, FOREIGN KEY(`card_id`) REFERENCES `CardTransactionResult`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE  INDEX `index_DiscoEntity_card_id` ON `DiscoEntity` (`card_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TransferEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `stan` TEXT, `card_id` INTEGER, `amount` TEXT NOT NULL, `walletId` TEXT NOT NULL, `marchantAddress` TEXT NOT NULL, `marchantTid` TEXT NOT NULL, `marchantName` TEXT NOT NULL, `merchantId` TEXT NOT NULL, `product` TEXT NOT NULL, `transactionStatusMessage` TEXT NOT NULL, `vasTid` TEXT NOT NULL, `transactionRef` TEXT NOT NULL, `paymentmethod` TEXT NOT NULL, `logo` INTEGER NOT NULL, `dateTime` TEXT NOT NULL, `error` INTEGER NOT NULL, `vasType` TEXT NOT NULL, `recepiant` TEXT NOT NULL, `accountName` TEXT NOT NULL, `recivingBank` TEXT NOT NULL, FOREIGN KEY(`card_id`) REFERENCES `CardTransactionResult`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE  INDEX `index_TransferEntity_card_id` ON `TransferEntity` (`card_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `WithdrawalEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `card_id` INTEGER, `stan` TEXT, `amount` TEXT NOT NULL, `walletId` TEXT NOT NULL, `marchantAddress` TEXT NOT NULL, `marchantTid` TEXT NOT NULL, `marchantName` TEXT NOT NULL, `merchantId` TEXT NOT NULL, `product` TEXT NOT NULL, `transactionStatusMessage` TEXT NOT NULL, `vasTid` TEXT NOT NULL, `transactionRef` TEXT NOT NULL, `paymentmethod` TEXT NOT NULL, `logo` INTEGER NOT NULL, `dateTime` TEXT NOT NULL, `error` INTEGER NOT NULL, `vasType` TEXT NOT NULL, `wallet` TEXT NOT NULL, `accountName` TEXT NOT NULL, FOREIGN KEY(`card_id`) REFERENCES `CardTransactionResult`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE  INDEX `index_WithdrawalEntity_card_id` ON `WithdrawalEntity` (`card_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"497ba8bee2e1a439681992b3794735d7\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `EodData`");
        _db.execSQL("DROP TABLE IF EXISTS `VasTransactionResult`");
        _db.execSQL("DROP TABLE IF EXISTS `CardTransactionResult`");
        _db.execSQL("DROP TABLE IF EXISTS `AirtimeEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `CableTvEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `DiscoEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `TransferEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `WithdrawalEntity`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsEodData = new HashMap<String, TableInfo.Column>(8);
        _columnsEodData.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsEodData.put("transactionRef", new TableInfo.Column("transactionRef", "TEXT", true, 0));
        _columnsEodData.put("transactionType", new TableInfo.Column("transactionType", "TEXT", true, 0));
        _columnsEodData.put("type", new TableInfo.Column("type", "TEXT", true, 0));
        _columnsEodData.put("specificTransaction", new TableInfo.Column("specificTransaction", "TEXT", true, 0));
        _columnsEodData.put("dateTime", new TableInfo.Column("dateTime", "INTEGER", true, 0));
        _columnsEodData.put("responseCode", new TableInfo.Column("responseCode", "TEXT", true, 0));
        _columnsEodData.put("amount", new TableInfo.Column("amount", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEodData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEodData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEodData = new TableInfo("EodData", _columnsEodData, _foreignKeysEodData, _indicesEodData);
        final TableInfo _existingEodData = TableInfo.read(_db, "EodData");
        if (! _infoEodData.equals(_existingEodData)) {
          throw new IllegalStateException("Migration didn't properly handle EodData(com.wizarpos.emvsample.db.detailed.EodData).\n"
                  + " Expected:\n" + _infoEodData + "\n"
                  + " Found:\n" + _existingEodData);
        }
        final HashMap<String, TableInfo.Column> _columnsVasTransactionResult = new HashMap<String, TableInfo.Column>(17);
        _columnsVasTransactionResult.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsVasTransactionResult.put("card_id", new TableInfo.Column("card_id", "TEXT", false, 0));
        _columnsVasTransactionResult.put("amount", new TableInfo.Column("amount", "TEXT", true, 0));
        _columnsVasTransactionResult.put("walletId", new TableInfo.Column("walletId", "TEXT", true, 0));
        _columnsVasTransactionResult.put("marchantAddress", new TableInfo.Column("marchantAddress", "TEXT", true, 0));
        _columnsVasTransactionResult.put("marchantTid", new TableInfo.Column("marchantTid", "TEXT", true, 0));
        _columnsVasTransactionResult.put("marchantName", new TableInfo.Column("marchantName", "TEXT", true, 0));
        _columnsVasTransactionResult.put("merchantId", new TableInfo.Column("merchantId", "TEXT", true, 0));
        _columnsVasTransactionResult.put("product", new TableInfo.Column("product", "TEXT", true, 0));
        _columnsVasTransactionResult.put("transactionStatusMessage", new TableInfo.Column("transactionStatusMessage", "TEXT", true, 0));
        _columnsVasTransactionResult.put("vasTid", new TableInfo.Column("vasTid", "TEXT", true, 0));
        _columnsVasTransactionResult.put("transactionRef", new TableInfo.Column("transactionRef", "TEXT", true, 0));
        _columnsVasTransactionResult.put("paymentmethod", new TableInfo.Column("paymentmethod", "TEXT", true, 0));
        _columnsVasTransactionResult.put("logo", new TableInfo.Column("logo", "INTEGER", true, 0));
        _columnsVasTransactionResult.put("dateTime", new TableInfo.Column("dateTime", "TEXT", true, 0));
        _columnsVasTransactionResult.put("error", new TableInfo.Column("error", "INTEGER", true, 0));
        _columnsVasTransactionResult.put("vasType", new TableInfo.Column("vasType", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVasTransactionResult = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysVasTransactionResult.add(new TableInfo.ForeignKey("CardTransactionResult", "NO ACTION", "NO ACTION",Arrays.asList("card_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesVasTransactionResult = new HashSet<TableInfo.Index>(1);
        _indicesVasTransactionResult.add(new TableInfo.Index("index_VasTransactionResult_card_id", false, Arrays.asList("card_id")));
        final TableInfo _infoVasTransactionResult = new TableInfo("VasTransactionResult", _columnsVasTransactionResult, _foreignKeysVasTransactionResult, _indicesVasTransactionResult);
        final TableInfo _existingVasTransactionResult = TableInfo.read(_db, "VasTransactionResult");
        if (! _infoVasTransactionResult.equals(_existingVasTransactionResult)) {
          throw new IllegalStateException("Migration didn't properly handle VasTransactionResult(com.wizarpos.emvsample.db.detailed.VasTransactionResult).\n"
                  + " Expected:\n" + _infoVasTransactionResult + "\n"
                  + " Found:\n" + _existingVasTransactionResult);
        }
        final HashMap<String, TableInfo.Column> _columnsCardTransactionResult = new HashMap<String, TableInfo.Column>(23);
        _columnsCardTransactionResult.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsCardTransactionResult.put("RRN", new TableInfo.Column("RRN", "TEXT", true, 0));
        _columnsCardTransactionResult.put("authID", new TableInfo.Column("authID", "TEXT", true, 0));
        _columnsCardTransactionResult.put("STAN", new TableInfo.Column("STAN", "TEXT", true, 0));
        _columnsCardTransactionResult.put("PAN", new TableInfo.Column("PAN", "TEXT", true, 0));
        _columnsCardTransactionResult.put("transactionStatus", new TableInfo.Column("transactionStatus", "TEXT", true, 0));
        _columnsCardTransactionResult.put("responseCode", new TableInfo.Column("responseCode", "TEXT", true, 0));
        _columnsCardTransactionResult.put("transactionStatusReason", new TableInfo.Column("transactionStatusReason", "TEXT", true, 0));
        _columnsCardTransactionResult.put("accountType", new TableInfo.Column("accountType", "TEXT", true, 0));
        _columnsCardTransactionResult.put("batchNumber", new TableInfo.Column("batchNumber", "TEXT", true, 0));
        _columnsCardTransactionResult.put("merchantID", new TableInfo.Column("merchantID", "TEXT", true, 0));
        _columnsCardTransactionResult.put("isoTransmissionDateTime", new TableInfo.Column("isoTransmissionDateTime", "TEXT", true, 0));
        _columnsCardTransactionResult.put("terminalID", new TableInfo.Column("terminalID", "TEXT", true, 0));
        _columnsCardTransactionResult.put("originalForwardingInstitutionCode", new TableInfo.Column("originalForwardingInstitutionCode", "TEXT", true, 0));
        _columnsCardTransactionResult.put("cardHolderName", new TableInfo.Column("cardHolderName", "TEXT", true, 0));
        _columnsCardTransactionResult.put("cardExpiry", new TableInfo.Column("cardExpiry", "TEXT", true, 0));
        _columnsCardTransactionResult.put("TSI", new TableInfo.Column("TSI", "TEXT", true, 0));
        _columnsCardTransactionResult.put("TVR", new TableInfo.Column("TVR", "TEXT", true, 0));
        _columnsCardTransactionResult.put("AID", new TableInfo.Column("AID", "TEXT", true, 0));
        _columnsCardTransactionResult.put("amount", new TableInfo.Column("amount", "INTEGER", true, 0));
        _columnsCardTransactionResult.put("additionalAmount", new TableInfo.Column("additionalAmount", "INTEGER", true, 0));
        _columnsCardTransactionResult.put("longDateTime", new TableInfo.Column("longDateTime", "INTEGER", true, 0));
        _columnsCardTransactionResult.put("transactionType", new TableInfo.Column("transactionType", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCardTransactionResult = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCardTransactionResult = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCardTransactionResult = new TableInfo("CardTransactionResult", _columnsCardTransactionResult, _foreignKeysCardTransactionResult, _indicesCardTransactionResult);
        final TableInfo _existingCardTransactionResult = TableInfo.read(_db, "CardTransactionResult");
        if (! _infoCardTransactionResult.equals(_existingCardTransactionResult)) {
          throw new IllegalStateException("Migration didn't properly handle CardTransactionResult(com.wizarpos.emvsample.db.detailed.CardTransactionResult).\n"
                  + " Expected:\n" + _infoCardTransactionResult + "\n"
                  + " Found:\n" + _existingCardTransactionResult);
        }
        final HashMap<String, TableInfo.Column> _columnsAirtimeEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsAirtimeEntity.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsAirtimeEntity.put("card_id", new TableInfo.Column("card_id", "INTEGER", false, 0));
        _columnsAirtimeEntity.put("transactionRef", new TableInfo.Column("transactionRef", "TEXT", true, 0));
        _columnsAirtimeEntity.put("recepiant_phone", new TableInfo.Column("recepiant_phone", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAirtimeEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAirtimeEntity.add(new TableInfo.ForeignKey("CardTransactionResult", "NO ACTION", "NO ACTION",Arrays.asList("card_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesAirtimeEntity = new HashSet<TableInfo.Index>(1);
        _indicesAirtimeEntity.add(new TableInfo.Index("index_AirtimeEntity_card_id", false, Arrays.asList("card_id")));
        final TableInfo _infoAirtimeEntity = new TableInfo("AirtimeEntity", _columnsAirtimeEntity, _foreignKeysAirtimeEntity, _indicesAirtimeEntity);
        final TableInfo _existingAirtimeEntity = TableInfo.read(_db, "AirtimeEntity");
        if (! _infoAirtimeEntity.equals(_existingAirtimeEntity)) {
          throw new IllegalStateException("Migration didn't properly handle AirtimeEntity(com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity).\n"
                  + " Expected:\n" + _infoAirtimeEntity + "\n"
                  + " Found:\n" + _existingAirtimeEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsCableTvEntity = new HashMap<String, TableInfo.Column>(19);
        _columnsCableTvEntity.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsCableTvEntity.put("card_id", new TableInfo.Column("card_id", "INTEGER", false, 0));
        _columnsCableTvEntity.put("stan", new TableInfo.Column("stan", "TEXT", false, 0));
        _columnsCableTvEntity.put("amount", new TableInfo.Column("amount", "TEXT", true, 0));
        _columnsCableTvEntity.put("walletId", new TableInfo.Column("walletId", "TEXT", true, 0));
        _columnsCableTvEntity.put("marchantAddress", new TableInfo.Column("marchantAddress", "TEXT", true, 0));
        _columnsCableTvEntity.put("marchantTid", new TableInfo.Column("marchantTid", "TEXT", true, 0));
        _columnsCableTvEntity.put("marchantName", new TableInfo.Column("marchantName", "TEXT", true, 0));
        _columnsCableTvEntity.put("merchantId", new TableInfo.Column("merchantId", "TEXT", true, 0));
        _columnsCableTvEntity.put("product", new TableInfo.Column("product", "TEXT", true, 0));
        _columnsCableTvEntity.put("transactionStatusMessage", new TableInfo.Column("transactionStatusMessage", "TEXT", true, 0));
        _columnsCableTvEntity.put("vasTid", new TableInfo.Column("vasTid", "TEXT", true, 0));
        _columnsCableTvEntity.put("transactionRef", new TableInfo.Column("transactionRef", "TEXT", true, 0));
        _columnsCableTvEntity.put("paymentmethod", new TableInfo.Column("paymentmethod", "TEXT", true, 0));
        _columnsCableTvEntity.put("logo", new TableInfo.Column("logo", "INTEGER", true, 0));
        _columnsCableTvEntity.put("dateTime", new TableInfo.Column("dateTime", "TEXT", true, 0));
        _columnsCableTvEntity.put("vasType", new TableInfo.Column("vasType", "TEXT", true, 0));
        _columnsCableTvEntity.put("error", new TableInfo.Column("error", "INTEGER", true, 0));
        _columnsCableTvEntity.put("iuc", new TableInfo.Column("iuc", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCableTvEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysCableTvEntity.add(new TableInfo.ForeignKey("CardTransactionResult", "NO ACTION", "NO ACTION",Arrays.asList("card_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesCableTvEntity = new HashSet<TableInfo.Index>(1);
        _indicesCableTvEntity.add(new TableInfo.Index("index_CableTvEntity_card_id", false, Arrays.asList("card_id")));
        final TableInfo _infoCableTvEntity = new TableInfo("CableTvEntity", _columnsCableTvEntity, _foreignKeysCableTvEntity, _indicesCableTvEntity);
        final TableInfo _existingCableTvEntity = TableInfo.read(_db, "CableTvEntity");
        if (! _infoCableTvEntity.equals(_existingCableTvEntity)) {
          throw new IllegalStateException("Migration didn't properly handle CableTvEntity(com.wizarpos.emvsample.db.detailed.vas.vas_entity.CableTvEntity).\n"
                  + " Expected:\n" + _infoCableTvEntity + "\n"
                  + " Found:\n" + _existingCableTvEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsDiscoEntity = new HashMap<String, TableInfo.Column>(29);
        _columnsDiscoEntity.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsDiscoEntity.put("card_id", new TableInfo.Column("card_id", "INTEGER", false, 0));
        _columnsDiscoEntity.put("stan", new TableInfo.Column("stan", "TEXT", false, 0));
        _columnsDiscoEntity.put("amount", new TableInfo.Column("amount", "TEXT", true, 0));
        _columnsDiscoEntity.put("walletId", new TableInfo.Column("walletId", "TEXT", true, 0));
        _columnsDiscoEntity.put("marchantAddress", new TableInfo.Column("marchantAddress", "TEXT", true, 0));
        _columnsDiscoEntity.put("marchantTid", new TableInfo.Column("marchantTid", "TEXT", true, 0));
        _columnsDiscoEntity.put("marchantName", new TableInfo.Column("marchantName", "TEXT", true, 0));
        _columnsDiscoEntity.put("merchantId", new TableInfo.Column("merchantId", "TEXT", true, 0));
        _columnsDiscoEntity.put("product", new TableInfo.Column("product", "TEXT", true, 0));
        _columnsDiscoEntity.put("transactionStatusMessage", new TableInfo.Column("transactionStatusMessage", "TEXT", true, 0));
        _columnsDiscoEntity.put("vasTid", new TableInfo.Column("vasTid", "TEXT", true, 0));
        _columnsDiscoEntity.put("transactionRef", new TableInfo.Column("transactionRef", "TEXT", true, 0));
        _columnsDiscoEntity.put("paymentmethod", new TableInfo.Column("paymentmethod", "TEXT", true, 0));
        _columnsDiscoEntity.put("logo", new TableInfo.Column("logo", "INTEGER", true, 0));
        _columnsDiscoEntity.put("dateTime", new TableInfo.Column("dateTime", "TEXT", true, 0));
        _columnsDiscoEntity.put("error", new TableInfo.Column("error", "INTEGER", true, 0));
        _columnsDiscoEntity.put("vasType", new TableInfo.Column("vasType", "TEXT", true, 0));
        _columnsDiscoEntity.put("recepiant_name", new TableInfo.Column("recepiant_name", "TEXT", true, 0));
        _columnsDiscoEntity.put("meterType", new TableInfo.Column("meterType", "TEXT", true, 0));
        _columnsDiscoEntity.put("transactionId", new TableInfo.Column("transactionId", "TEXT", true, 0));
        _columnsDiscoEntity.put("unit", new TableInfo.Column("unit", "TEXT", true, 0));
        _columnsDiscoEntity.put("unit_value", new TableInfo.Column("unit_value", "TEXT", true, 0));
        _columnsDiscoEntity.put("vat", new TableInfo.Column("vat", "TEXT", true, 0));
        _columnsDiscoEntity.put("meterNumber", new TableInfo.Column("meterNumber", "TEXT", true, 0));
        _columnsDiscoEntity.put("token", new TableInfo.Column("token", "TEXT", true, 0));
        _columnsDiscoEntity.put("address", new TableInfo.Column("address", "TEXT", true, 0));
        _columnsDiscoEntity.put("arras", new TableInfo.Column("arras", "TEXT", true, 0));
        _columnsDiscoEntity.put("tarrif", new TableInfo.Column("tarrif", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDiscoEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysDiscoEntity.add(new TableInfo.ForeignKey("CardTransactionResult", "NO ACTION", "NO ACTION",Arrays.asList("card_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesDiscoEntity = new HashSet<TableInfo.Index>(1);
        _indicesDiscoEntity.add(new TableInfo.Index("index_DiscoEntity_card_id", false, Arrays.asList("card_id")));
        final TableInfo _infoDiscoEntity = new TableInfo("DiscoEntity", _columnsDiscoEntity, _foreignKeysDiscoEntity, _indicesDiscoEntity);
        final TableInfo _existingDiscoEntity = TableInfo.read(_db, "DiscoEntity");
        if (! _infoDiscoEntity.equals(_existingDiscoEntity)) {
          throw new IllegalStateException("Migration didn't properly handle DiscoEntity(com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity).\n"
                  + " Expected:\n" + _infoDiscoEntity + "\n"
                  + " Found:\n" + _existingDiscoEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsTransferEntity = new HashMap<String, TableInfo.Column>(21);
        _columnsTransferEntity.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsTransferEntity.put("stan", new TableInfo.Column("stan", "TEXT", false, 0));
        _columnsTransferEntity.put("card_id", new TableInfo.Column("card_id", "INTEGER", false, 0));
        _columnsTransferEntity.put("amount", new TableInfo.Column("amount", "TEXT", true, 0));
        _columnsTransferEntity.put("walletId", new TableInfo.Column("walletId", "TEXT", true, 0));
        _columnsTransferEntity.put("marchantAddress", new TableInfo.Column("marchantAddress", "TEXT", true, 0));
        _columnsTransferEntity.put("marchantTid", new TableInfo.Column("marchantTid", "TEXT", true, 0));
        _columnsTransferEntity.put("marchantName", new TableInfo.Column("marchantName", "TEXT", true, 0));
        _columnsTransferEntity.put("merchantId", new TableInfo.Column("merchantId", "TEXT", true, 0));
        _columnsTransferEntity.put("product", new TableInfo.Column("product", "TEXT", true, 0));
        _columnsTransferEntity.put("transactionStatusMessage", new TableInfo.Column("transactionStatusMessage", "TEXT", true, 0));
        _columnsTransferEntity.put("vasTid", new TableInfo.Column("vasTid", "TEXT", true, 0));
        _columnsTransferEntity.put("transactionRef", new TableInfo.Column("transactionRef", "TEXT", true, 0));
        _columnsTransferEntity.put("paymentmethod", new TableInfo.Column("paymentmethod", "TEXT", true, 0));
        _columnsTransferEntity.put("logo", new TableInfo.Column("logo", "INTEGER", true, 0));
        _columnsTransferEntity.put("dateTime", new TableInfo.Column("dateTime", "TEXT", true, 0));
        _columnsTransferEntity.put("error", new TableInfo.Column("error", "INTEGER", true, 0));
        _columnsTransferEntity.put("vasType", new TableInfo.Column("vasType", "TEXT", true, 0));
        _columnsTransferEntity.put("recepiant", new TableInfo.Column("recepiant", "TEXT", true, 0));
        _columnsTransferEntity.put("accountName", new TableInfo.Column("accountName", "TEXT", true, 0));
        _columnsTransferEntity.put("recivingBank", new TableInfo.Column("recivingBank", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransferEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTransferEntity.add(new TableInfo.ForeignKey("CardTransactionResult", "NO ACTION", "NO ACTION",Arrays.asList("card_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesTransferEntity = new HashSet<TableInfo.Index>(1);
        _indicesTransferEntity.add(new TableInfo.Index("index_TransferEntity_card_id", false, Arrays.asList("card_id")));
        final TableInfo _infoTransferEntity = new TableInfo("TransferEntity", _columnsTransferEntity, _foreignKeysTransferEntity, _indicesTransferEntity);
        final TableInfo _existingTransferEntity = TableInfo.read(_db, "TransferEntity");
        if (! _infoTransferEntity.equals(_existingTransferEntity)) {
          throw new IllegalStateException("Migration didn't properly handle TransferEntity(com.wizarpos.emvsample.db.detailed.vas.vas_entity.TransferEntity).\n"
                  + " Expected:\n" + _infoTransferEntity + "\n"
                  + " Found:\n" + _existingTransferEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsWithdrawalEntity = new HashMap<String, TableInfo.Column>(20);
        _columnsWithdrawalEntity.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsWithdrawalEntity.put("card_id", new TableInfo.Column("card_id", "INTEGER", false, 0));
        _columnsWithdrawalEntity.put("stan", new TableInfo.Column("stan", "TEXT", false, 0));
        _columnsWithdrawalEntity.put("amount", new TableInfo.Column("amount", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("walletId", new TableInfo.Column("walletId", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("marchantAddress", new TableInfo.Column("marchantAddress", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("marchantTid", new TableInfo.Column("marchantTid", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("marchantName", new TableInfo.Column("marchantName", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("merchantId", new TableInfo.Column("merchantId", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("product", new TableInfo.Column("product", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("transactionStatusMessage", new TableInfo.Column("transactionStatusMessage", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("vasTid", new TableInfo.Column("vasTid", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("transactionRef", new TableInfo.Column("transactionRef", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("paymentmethod", new TableInfo.Column("paymentmethod", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("logo", new TableInfo.Column("logo", "INTEGER", true, 0));
        _columnsWithdrawalEntity.put("dateTime", new TableInfo.Column("dateTime", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("error", new TableInfo.Column("error", "INTEGER", true, 0));
        _columnsWithdrawalEntity.put("vasType", new TableInfo.Column("vasType", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("wallet", new TableInfo.Column("wallet", "TEXT", true, 0));
        _columnsWithdrawalEntity.put("accountName", new TableInfo.Column("accountName", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWithdrawalEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWithdrawalEntity.add(new TableInfo.ForeignKey("CardTransactionResult", "NO ACTION", "NO ACTION",Arrays.asList("card_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWithdrawalEntity = new HashSet<TableInfo.Index>(1);
        _indicesWithdrawalEntity.add(new TableInfo.Index("index_WithdrawalEntity_card_id", false, Arrays.asList("card_id")));
        final TableInfo _infoWithdrawalEntity = new TableInfo("WithdrawalEntity", _columnsWithdrawalEntity, _foreignKeysWithdrawalEntity, _indicesWithdrawalEntity);
        final TableInfo _existingWithdrawalEntity = TableInfo.read(_db, "WithdrawalEntity");
        if (! _infoWithdrawalEntity.equals(_existingWithdrawalEntity)) {
          throw new IllegalStateException("Migration didn't properly handle WithdrawalEntity(com.wizarpos.emvsample.db.detailed.vas.vas_entity.WithdrawalEntity).\n"
                  + " Expected:\n" + _infoWithdrawalEntity + "\n"
                  + " Found:\n" + _existingWithdrawalEntity);
        }
      }
    }, "497ba8bee2e1a439681992b3794735d7", "4faaf58068214ae6cdd294c46e6d7dd0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "EodData","VasTransactionResult","CardTransactionResult","AirtimeEntity","CableTvEntity","DiscoEntity","TransferEntity","WithdrawalEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `EodData`");
      _db.execSQL("DELETE FROM `VasTransactionResult`");
      _db.execSQL("DELETE FROM `AirtimeEntity`");
      _db.execSQL("DELETE FROM `CableTvEntity`");
      _db.execSQL("DELETE FROM `DiscoEntity`");
      _db.execSQL("DELETE FROM `TransferEntity`");
      _db.execSQL("DELETE FROM `WithdrawalEntity`");
      _db.execSQL("DELETE FROM `CardTransactionResult`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TransactionDataDoa getTransactionDataDoa() {
    if (_transactionDataDoa != null) {
      return _transactionDataDoa;
    } else {
      synchronized(this) {
        if(_transactionDataDoa == null) {
          _transactionDataDoa = new TransactionDataDoa_Impl(this);
        }
        return _transactionDataDoa;
      }
    }
  }

  @Override
  public EodDoa getEodDataBase() {
    if (_eodDoa != null) {
      return _eodDoa;
    } else {
      synchronized(this) {
        if(_eodDoa == null) {
          _eodDoa = new EodDoa_Impl(this);
        }
        return _eodDoa;
      }
    }
  }

  @Override
  public VasTransactionDoa getVasDataBase() {
    if (_vasTransactionDoa != null) {
      return _vasTransactionDoa;
    } else {
      synchronized(this) {
        if(_vasTransactionDoa == null) {
          _vasTransactionDoa = new VasTransactionDoa_Impl(this);
        }
        return _vasTransactionDoa;
      }
    }
  }

  @Override
  public DiscoDoa getDiscoTable() {
    if (_discoDoa != null) {
      return _discoDoa;
    } else {
      synchronized(this) {
        if(_discoDoa == null) {
          _discoDoa = new DiscoDoa_Impl(this);
        }
        return _discoDoa;
      }
    }
  }

  @Override
  public AirtimeDoa getAirtimeTable() {
    if (_airtimeDoa != null) {
      return _airtimeDoa;
    } else {
      synchronized(this) {
        if(_airtimeDoa == null) {
          _airtimeDoa = new AirtimeDoa_Impl(this);
        }
        return _airtimeDoa;
      }
    }
  }

  @Override
  public CableTvDoa getCableTable() {
    if (_cableTvDoa != null) {
      return _cableTvDoa;
    } else {
      synchronized(this) {
        if(_cableTvDoa == null) {
          _cableTvDoa = new CableTvDoa_Impl(this);
        }
        return _cableTvDoa;
      }
    }
  }

  @Override
  public TransferDoa getTransferTable() {
    if (_transferDoa != null) {
      return _transferDoa;
    } else {
      synchronized(this) {
        if(_transferDoa == null) {
          _transferDoa = new TransferDoa_Impl(this);
        }
        return _transferDoa;
      }
    }
  }
}
