package com.wizarpos.emvsample.db.detailed.vas.vas_doa;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import com.wizarpos.emvsample.db.detailed.vas.model.DiscoCardResult;
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class DiscoDoa_Impl implements DiscoDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDiscoEntity;

  public DiscoDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDiscoEntity = new EntityInsertionAdapter<DiscoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DiscoEntity`(`id`,`card_id`,`stan`,`amount`,`walletId`,`marchantAddress`,`marchantTid`,`marchantName`,`merchantId`,`product`,`transactionStatusMessage`,`vasTid`,`transactionRef`,`paymentmethod`,`logo`,`dateTime`,`error`,`vasType`,`recepiant_name`,`meterType`,`transactionId`,`unit`,`unit_value`,`vat`,`meterNumber`,`token`,`address`,`arras`,`tarrif`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DiscoEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getCardrowId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getCardrowId());
        }
        if (value.getStan() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStan());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAmount());
        }
        if (value.getWalletId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getWalletId());
        }
        if (value.getMarchantAddress() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMarchantAddress());
        }
        if (value.getMarchantTid() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMarchantTid());
        }
        if (value.getMarchantName() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getMarchantName());
        }
        if (value.getMerchantId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMerchantId());
        }
        if (value.getProduct() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getProduct());
        }
        if (value.getTransactionStatusMessage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getTransactionStatusMessage());
        }
        if (value.getVasTid() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getVasTid());
        }
        if (value.getTransactionRef() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getTransactionRef());
        }
        if (value.getPaymentmethod() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getPaymentmethod());
        }
        stmt.bindLong(15, value.getLogo());
        if (value.getDateTime() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getDateTime());
        }
        final int _tmp;
        _tmp = value.getError() ? 1 : 0;
        stmt.bindLong(17, _tmp);
        if (value.getVasType() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getVasType());
        }
        if (value.getRecepiant_name() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getRecepiant_name());
        }
        if (value.getMeterType() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getMeterType());
        }
        if (value.getTransactionId() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getTransactionId());
        }
        if (value.getUnit() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getUnit());
        }
        if (value.getUnit_value() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindString(23, value.getUnit_value());
        }
        if (value.getVat() == null) {
          stmt.bindNull(24);
        } else {
          stmt.bindString(24, value.getVat());
        }
        if (value.getMeterNumber() == null) {
          stmt.bindNull(25);
        } else {
          stmt.bindString(25, value.getMeterNumber());
        }
        if (value.getToken() == null) {
          stmt.bindNull(26);
        } else {
          stmt.bindString(26, value.getToken());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(27);
        } else {
          stmt.bindString(27, value.getAddress());
        }
        if (value.getArras() == null) {
          stmt.bindNull(28);
        } else {
          stmt.bindString(28, value.getArras());
        }
        if (value.getTarrif() == null) {
          stmt.bindNull(29);
        } else {
          stmt.bindString(29, value.getTarrif());
        }
      }
    };
  }

  @Override
  public long savDiscoData(DiscoEntity vasTransactionResult) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDiscoEntity.insertAndReturnId(vasTransactionResult);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<DiscoEntity> getDiscoData() {
    final String _sql = "Select * From DiscoEntity ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCardrowId = _cursor.getColumnIndexOrThrow("card_id");
      final int _cursorIndexOfStan = _cursor.getColumnIndexOrThrow("stan");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      final int _cursorIndexOfWalletId = _cursor.getColumnIndexOrThrow("walletId");
      final int _cursorIndexOfMarchantAddress = _cursor.getColumnIndexOrThrow("marchantAddress");
      final int _cursorIndexOfMarchantTid = _cursor.getColumnIndexOrThrow("marchantTid");
      final int _cursorIndexOfMarchantName = _cursor.getColumnIndexOrThrow("marchantName");
      final int _cursorIndexOfMerchantId = _cursor.getColumnIndexOrThrow("merchantId");
      final int _cursorIndexOfProduct = _cursor.getColumnIndexOrThrow("product");
      final int _cursorIndexOfTransactionStatusMessage = _cursor.getColumnIndexOrThrow("transactionStatusMessage");
      final int _cursorIndexOfVasTid = _cursor.getColumnIndexOrThrow("vasTid");
      final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
      final int _cursorIndexOfPaymentmethod = _cursor.getColumnIndexOrThrow("paymentmethod");
      final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
      final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
      final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
      final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
      final int _cursorIndexOfRecepiantName = _cursor.getColumnIndexOrThrow("recepiant_name");
      final int _cursorIndexOfMeterType = _cursor.getColumnIndexOrThrow("meterType");
      final int _cursorIndexOfTransactionId = _cursor.getColumnIndexOrThrow("transactionId");
      final int _cursorIndexOfUnit = _cursor.getColumnIndexOrThrow("unit");
      final int _cursorIndexOfUnitValue = _cursor.getColumnIndexOrThrow("unit_value");
      final int _cursorIndexOfVat = _cursor.getColumnIndexOrThrow("vat");
      final int _cursorIndexOfMeterNumber = _cursor.getColumnIndexOrThrow("meterNumber");
      final int _cursorIndexOfToken = _cursor.getColumnIndexOrThrow("token");
      final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
      final int _cursorIndexOfArras = _cursor.getColumnIndexOrThrow("arras");
      final int _cursorIndexOfTarrif = _cursor.getColumnIndexOrThrow("tarrif");
      final List<DiscoEntity> _result = new ArrayList<DiscoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DiscoEntity _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final Integer _tmpCardrowId;
        if (_cursor.isNull(_cursorIndexOfCardrowId)) {
          _tmpCardrowId = null;
        } else {
          _tmpCardrowId = _cursor.getInt(_cursorIndexOfCardrowId);
        }
        final String _tmpStan;
        _tmpStan = _cursor.getString(_cursorIndexOfStan);
        final String _tmpAmount;
        _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
        final String _tmpWalletId;
        _tmpWalletId = _cursor.getString(_cursorIndexOfWalletId);
        final String _tmpMarchantAddress;
        _tmpMarchantAddress = _cursor.getString(_cursorIndexOfMarchantAddress);
        final String _tmpMarchantTid;
        _tmpMarchantTid = _cursor.getString(_cursorIndexOfMarchantTid);
        final String _tmpMarchantName;
        _tmpMarchantName = _cursor.getString(_cursorIndexOfMarchantName);
        final String _tmpMerchantId;
        _tmpMerchantId = _cursor.getString(_cursorIndexOfMerchantId);
        final String _tmpProduct;
        _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
        final String _tmpTransactionStatusMessage;
        _tmpTransactionStatusMessage = _cursor.getString(_cursorIndexOfTransactionStatusMessage);
        final String _tmpVasTid;
        _tmpVasTid = _cursor.getString(_cursorIndexOfVasTid);
        final String _tmpTransactionRef;
        _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
        final String _tmpPaymentmethod;
        _tmpPaymentmethod = _cursor.getString(_cursorIndexOfPaymentmethod);
        final int _tmpLogo;
        _tmpLogo = _cursor.getInt(_cursorIndexOfLogo);
        final String _tmpDateTime;
        _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
        final boolean _tmpError;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfError);
        _tmpError = _tmp != 0;
        final String _tmpVasType;
        _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
        final String _tmpRecepiant_name;
        _tmpRecepiant_name = _cursor.getString(_cursorIndexOfRecepiantName);
        final String _tmpMeterType;
        _tmpMeterType = _cursor.getString(_cursorIndexOfMeterType);
        final String _tmpTransactionId;
        _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
        final String _tmpUnit;
        _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
        final String _tmpUnit_value;
        _tmpUnit_value = _cursor.getString(_cursorIndexOfUnitValue);
        final String _tmpVat;
        _tmpVat = _cursor.getString(_cursorIndexOfVat);
        final String _tmpMeterNumber;
        _tmpMeterNumber = _cursor.getString(_cursorIndexOfMeterNumber);
        final String _tmpToken;
        _tmpToken = _cursor.getString(_cursorIndexOfToken);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        final String _tmpArras;
        _tmpArras = _cursor.getString(_cursorIndexOfArras);
        final String _tmpTarrif;
        _tmpTarrif = _cursor.getString(_cursorIndexOfTarrif);
        _item = new DiscoEntity(_tmpId,_tmpCardrowId,_tmpStan,_tmpAmount,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpError,_tmpVasType,_tmpRecepiant_name,_tmpMeterType,_tmpTransactionId,_tmpUnit,_tmpUnit_value,_tmpVat,_tmpMeterNumber,_tmpToken,_tmpAddress,_tmpArras,_tmpTarrif);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<DiscoEntity> getAirtimePurchased(int id) {
    final String _sql = "SELECT * FROM DiscoEntity WHERE card_id LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<DiscoEntity>() {
      private Observer _observer;

      @Override
      protected DiscoEntity compute() {
        if (_observer == null) {
          _observer = new Observer("DiscoEntity") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfCardrowId = _cursor.getColumnIndexOrThrow("card_id");
          final int _cursorIndexOfStan = _cursor.getColumnIndexOrThrow("stan");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfWalletId = _cursor.getColumnIndexOrThrow("walletId");
          final int _cursorIndexOfMarchantAddress = _cursor.getColumnIndexOrThrow("marchantAddress");
          final int _cursorIndexOfMarchantTid = _cursor.getColumnIndexOrThrow("marchantTid");
          final int _cursorIndexOfMarchantName = _cursor.getColumnIndexOrThrow("marchantName");
          final int _cursorIndexOfMerchantId = _cursor.getColumnIndexOrThrow("merchantId");
          final int _cursorIndexOfProduct = _cursor.getColumnIndexOrThrow("product");
          final int _cursorIndexOfTransactionStatusMessage = _cursor.getColumnIndexOrThrow("transactionStatusMessage");
          final int _cursorIndexOfVasTid = _cursor.getColumnIndexOrThrow("vasTid");
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfPaymentmethod = _cursor.getColumnIndexOrThrow("paymentmethod");
          final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
          final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
          final int _cursorIndexOfRecepiantName = _cursor.getColumnIndexOrThrow("recepiant_name");
          final int _cursorIndexOfMeterType = _cursor.getColumnIndexOrThrow("meterType");
          final int _cursorIndexOfTransactionId = _cursor.getColumnIndexOrThrow("transactionId");
          final int _cursorIndexOfUnit = _cursor.getColumnIndexOrThrow("unit");
          final int _cursorIndexOfUnitValue = _cursor.getColumnIndexOrThrow("unit_value");
          final int _cursorIndexOfVat = _cursor.getColumnIndexOrThrow("vat");
          final int _cursorIndexOfMeterNumber = _cursor.getColumnIndexOrThrow("meterNumber");
          final int _cursorIndexOfToken = _cursor.getColumnIndexOrThrow("token");
          final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
          final int _cursorIndexOfArras = _cursor.getColumnIndexOrThrow("arras");
          final int _cursorIndexOfTarrif = _cursor.getColumnIndexOrThrow("tarrif");
          final DiscoEntity _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final Integer _tmpCardrowId;
            if (_cursor.isNull(_cursorIndexOfCardrowId)) {
              _tmpCardrowId = null;
            } else {
              _tmpCardrowId = _cursor.getInt(_cursorIndexOfCardrowId);
            }
            final String _tmpStan;
            _tmpStan = _cursor.getString(_cursorIndexOfStan);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            final String _tmpWalletId;
            _tmpWalletId = _cursor.getString(_cursorIndexOfWalletId);
            final String _tmpMarchantAddress;
            _tmpMarchantAddress = _cursor.getString(_cursorIndexOfMarchantAddress);
            final String _tmpMarchantTid;
            _tmpMarchantTid = _cursor.getString(_cursorIndexOfMarchantTid);
            final String _tmpMarchantName;
            _tmpMarchantName = _cursor.getString(_cursorIndexOfMarchantName);
            final String _tmpMerchantId;
            _tmpMerchantId = _cursor.getString(_cursorIndexOfMerchantId);
            final String _tmpProduct;
            _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
            final String _tmpTransactionStatusMessage;
            _tmpTransactionStatusMessage = _cursor.getString(_cursorIndexOfTransactionStatusMessage);
            final String _tmpVasTid;
            _tmpVasTid = _cursor.getString(_cursorIndexOfVasTid);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            final String _tmpPaymentmethod;
            _tmpPaymentmethod = _cursor.getString(_cursorIndexOfPaymentmethod);
            final int _tmpLogo;
            _tmpLogo = _cursor.getInt(_cursorIndexOfLogo);
            final String _tmpDateTime;
            _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            final String _tmpRecepiant_name;
            _tmpRecepiant_name = _cursor.getString(_cursorIndexOfRecepiantName);
            final String _tmpMeterType;
            _tmpMeterType = _cursor.getString(_cursorIndexOfMeterType);
            final String _tmpTransactionId;
            _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            final String _tmpUnit;
            _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            final String _tmpUnit_value;
            _tmpUnit_value = _cursor.getString(_cursorIndexOfUnitValue);
            final String _tmpVat;
            _tmpVat = _cursor.getString(_cursorIndexOfVat);
            final String _tmpMeterNumber;
            _tmpMeterNumber = _cursor.getString(_cursorIndexOfMeterNumber);
            final String _tmpToken;
            _tmpToken = _cursor.getString(_cursorIndexOfToken);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpArras;
            _tmpArras = _cursor.getString(_cursorIndexOfArras);
            final String _tmpTarrif;
            _tmpTarrif = _cursor.getString(_cursorIndexOfTarrif);
            _result = new DiscoEntity(_tmpId,_tmpCardrowId,_tmpStan,_tmpAmount,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpError,_tmpVasType,_tmpRecepiant_name,_tmpMeterType,_tmpTransactionId,_tmpUnit,_tmpUnit_value,_tmpVat,_tmpMeterNumber,_tmpToken,_tmpAddress,_tmpArras,_tmpTarrif);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<DiscoEntity>> getDiscoResult() {
    final String _sql = "Select CardTransactionResult.*, DiscoEntity.* from  DiscoEntity LEFT JOIN CardTransactionResult on   DiscoEntity.card_id LIKE CardTransactionResult.id ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<DiscoEntity>>() {
      private Observer _observer;

      @Override
      protected List<DiscoEntity> compute() {
        if (_observer == null) {
          _observer = new Observer("DiscoEntity","CardTransactionResult") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfCardrowId = _cursor.getColumnIndexOrThrow("card_id");
          final int _cursorIndexOfStan = _cursor.getColumnIndexOrThrow("stan");
          final int _cursorIndexOfAmount_1 = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfWalletId = _cursor.getColumnIndexOrThrow("walletId");
          final int _cursorIndexOfMarchantAddress = _cursor.getColumnIndexOrThrow("marchantAddress");
          final int _cursorIndexOfMarchantTid = _cursor.getColumnIndexOrThrow("marchantTid");
          final int _cursorIndexOfMarchantName = _cursor.getColumnIndexOrThrow("marchantName");
          final int _cursorIndexOfMerchantId = _cursor.getColumnIndexOrThrow("merchantId");
          final int _cursorIndexOfProduct = _cursor.getColumnIndexOrThrow("product");
          final int _cursorIndexOfTransactionStatusMessage = _cursor.getColumnIndexOrThrow("transactionStatusMessage");
          final int _cursorIndexOfVasTid = _cursor.getColumnIndexOrThrow("vasTid");
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfPaymentmethod = _cursor.getColumnIndexOrThrow("paymentmethod");
          final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
          final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
          final int _cursorIndexOfRecepiantName = _cursor.getColumnIndexOrThrow("recepiant_name");
          final int _cursorIndexOfMeterType = _cursor.getColumnIndexOrThrow("meterType");
          final int _cursorIndexOfTransactionId = _cursor.getColumnIndexOrThrow("transactionId");
          final int _cursorIndexOfUnit = _cursor.getColumnIndexOrThrow("unit");
          final int _cursorIndexOfUnitValue = _cursor.getColumnIndexOrThrow("unit_value");
          final int _cursorIndexOfVat = _cursor.getColumnIndexOrThrow("vat");
          final int _cursorIndexOfMeterNumber = _cursor.getColumnIndexOrThrow("meterNumber");
          final int _cursorIndexOfToken = _cursor.getColumnIndexOrThrow("token");
          final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
          final int _cursorIndexOfArras = _cursor.getColumnIndexOrThrow("arras");
          final int _cursorIndexOfTarrif = _cursor.getColumnIndexOrThrow("tarrif");
          final List<DiscoEntity> _result = new ArrayList<DiscoEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DiscoEntity _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            final Integer _tmpId_1;
            if (_cursor.isNull(_cursorIndexOfId_1)) {
              _tmpId_1 = null;
            } else {
              _tmpId_1 = _cursor.getInt(_cursorIndexOfId_1);
            }
            final Integer _tmpCardrowId;
            if (_cursor.isNull(_cursorIndexOfCardrowId)) {
              _tmpCardrowId = null;
            } else {
              _tmpCardrowId = _cursor.getInt(_cursorIndexOfCardrowId);
            }
            final String _tmpStan;
            _tmpStan = _cursor.getString(_cursorIndexOfStan);
            final String _tmpAmount_1;
            _tmpAmount_1 = _cursor.getString(_cursorIndexOfAmount_1);
            final String _tmpWalletId;
            _tmpWalletId = _cursor.getString(_cursorIndexOfWalletId);
            final String _tmpMarchantAddress;
            _tmpMarchantAddress = _cursor.getString(_cursorIndexOfMarchantAddress);
            final String _tmpMarchantTid;
            _tmpMarchantTid = _cursor.getString(_cursorIndexOfMarchantTid);
            final String _tmpMarchantName;
            _tmpMarchantName = _cursor.getString(_cursorIndexOfMarchantName);
            final String _tmpMerchantId;
            _tmpMerchantId = _cursor.getString(_cursorIndexOfMerchantId);
            final String _tmpProduct;
            _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
            final String _tmpTransactionStatusMessage;
            _tmpTransactionStatusMessage = _cursor.getString(_cursorIndexOfTransactionStatusMessage);
            final String _tmpVasTid;
            _tmpVasTid = _cursor.getString(_cursorIndexOfVasTid);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            final String _tmpPaymentmethod;
            _tmpPaymentmethod = _cursor.getString(_cursorIndexOfPaymentmethod);
            final int _tmpLogo;
            _tmpLogo = _cursor.getInt(_cursorIndexOfLogo);
            final String _tmpDateTime;
            _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            final String _tmpRecepiant_name;
            _tmpRecepiant_name = _cursor.getString(_cursorIndexOfRecepiantName);
            final String _tmpMeterType;
            _tmpMeterType = _cursor.getString(_cursorIndexOfMeterType);
            final String _tmpTransactionId;
            _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            final String _tmpUnit;
            _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            final String _tmpUnit_value;
            _tmpUnit_value = _cursor.getString(_cursorIndexOfUnitValue);
            final String _tmpVat;
            _tmpVat = _cursor.getString(_cursorIndexOfVat);
            final String _tmpMeterNumber;
            _tmpMeterNumber = _cursor.getString(_cursorIndexOfMeterNumber);
            final String _tmpToken;
            _tmpToken = _cursor.getString(_cursorIndexOfToken);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpArras;
            _tmpArras = _cursor.getString(_cursorIndexOfArras);
            final String _tmpTarrif;
            _tmpTarrif = _cursor.getString(_cursorIndexOfTarrif);
            _item = new DiscoEntity(_tmpId,_tmpCardrowId,_tmpStan,_tmpAmount,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpError,_tmpVasType,_tmpRecepiant_name,_tmpMeterType,_tmpTransactionId,_tmpUnit,_tmpUnit_value,_tmpVat,_tmpMeterNumber,_tmpToken,_tmpAddress,_tmpArras,_tmpTarrif);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<DiscoEntity> getDiscowithrequestId(String transactionId) {
    final String _sql = "SELECT * FROM  DiscoEntity  WHERE  DiscoEntity.transactionId LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (transactionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, transactionId);
    }
    return new ComputableLiveData<DiscoEntity>() {
      private Observer _observer;

      @Override
      protected DiscoEntity compute() {
        if (_observer == null) {
          _observer = new Observer("DiscoEntity") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfCardrowId = _cursor.getColumnIndexOrThrow("card_id");
          final int _cursorIndexOfStan = _cursor.getColumnIndexOrThrow("stan");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfWalletId = _cursor.getColumnIndexOrThrow("walletId");
          final int _cursorIndexOfMarchantAddress = _cursor.getColumnIndexOrThrow("marchantAddress");
          final int _cursorIndexOfMarchantTid = _cursor.getColumnIndexOrThrow("marchantTid");
          final int _cursorIndexOfMarchantName = _cursor.getColumnIndexOrThrow("marchantName");
          final int _cursorIndexOfMerchantId = _cursor.getColumnIndexOrThrow("merchantId");
          final int _cursorIndexOfProduct = _cursor.getColumnIndexOrThrow("product");
          final int _cursorIndexOfTransactionStatusMessage = _cursor.getColumnIndexOrThrow("transactionStatusMessage");
          final int _cursorIndexOfVasTid = _cursor.getColumnIndexOrThrow("vasTid");
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfPaymentmethod = _cursor.getColumnIndexOrThrow("paymentmethod");
          final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
          final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
          final int _cursorIndexOfRecepiantName = _cursor.getColumnIndexOrThrow("recepiant_name");
          final int _cursorIndexOfMeterType = _cursor.getColumnIndexOrThrow("meterType");
          final int _cursorIndexOfTransactionId = _cursor.getColumnIndexOrThrow("transactionId");
          final int _cursorIndexOfUnit = _cursor.getColumnIndexOrThrow("unit");
          final int _cursorIndexOfUnitValue = _cursor.getColumnIndexOrThrow("unit_value");
          final int _cursorIndexOfVat = _cursor.getColumnIndexOrThrow("vat");
          final int _cursorIndexOfMeterNumber = _cursor.getColumnIndexOrThrow("meterNumber");
          final int _cursorIndexOfToken = _cursor.getColumnIndexOrThrow("token");
          final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
          final int _cursorIndexOfArras = _cursor.getColumnIndexOrThrow("arras");
          final int _cursorIndexOfTarrif = _cursor.getColumnIndexOrThrow("tarrif");
          final DiscoEntity _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final Integer _tmpCardrowId;
            if (_cursor.isNull(_cursorIndexOfCardrowId)) {
              _tmpCardrowId = null;
            } else {
              _tmpCardrowId = _cursor.getInt(_cursorIndexOfCardrowId);
            }
            final String _tmpStan;
            _tmpStan = _cursor.getString(_cursorIndexOfStan);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            final String _tmpWalletId;
            _tmpWalletId = _cursor.getString(_cursorIndexOfWalletId);
            final String _tmpMarchantAddress;
            _tmpMarchantAddress = _cursor.getString(_cursorIndexOfMarchantAddress);
            final String _tmpMarchantTid;
            _tmpMarchantTid = _cursor.getString(_cursorIndexOfMarchantTid);
            final String _tmpMarchantName;
            _tmpMarchantName = _cursor.getString(_cursorIndexOfMarchantName);
            final String _tmpMerchantId;
            _tmpMerchantId = _cursor.getString(_cursorIndexOfMerchantId);
            final String _tmpProduct;
            _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
            final String _tmpTransactionStatusMessage;
            _tmpTransactionStatusMessage = _cursor.getString(_cursorIndexOfTransactionStatusMessage);
            final String _tmpVasTid;
            _tmpVasTid = _cursor.getString(_cursorIndexOfVasTid);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            final String _tmpPaymentmethod;
            _tmpPaymentmethod = _cursor.getString(_cursorIndexOfPaymentmethod);
            final int _tmpLogo;
            _tmpLogo = _cursor.getInt(_cursorIndexOfLogo);
            final String _tmpDateTime;
            _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            final String _tmpRecepiant_name;
            _tmpRecepiant_name = _cursor.getString(_cursorIndexOfRecepiantName);
            final String _tmpMeterType;
            _tmpMeterType = _cursor.getString(_cursorIndexOfMeterType);
            final String _tmpTransactionId;
            _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            final String _tmpUnit;
            _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            final String _tmpUnit_value;
            _tmpUnit_value = _cursor.getString(_cursorIndexOfUnitValue);
            final String _tmpVat;
            _tmpVat = _cursor.getString(_cursorIndexOfVat);
            final String _tmpMeterNumber;
            _tmpMeterNumber = _cursor.getString(_cursorIndexOfMeterNumber);
            final String _tmpToken;
            _tmpToken = _cursor.getString(_cursorIndexOfToken);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpArras;
            _tmpArras = _cursor.getString(_cursorIndexOfArras);
            final String _tmpTarrif;
            _tmpTarrif = _cursor.getString(_cursorIndexOfTarrif);
            _result = new DiscoEntity(_tmpId,_tmpCardrowId,_tmpStan,_tmpAmount,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpError,_tmpVasType,_tmpRecepiant_name,_tmpMeterType,_tmpTransactionId,_tmpUnit,_tmpUnit_value,_tmpVat,_tmpMeterNumber,_tmpToken,_tmpAddress,_tmpArras,_tmpTarrif);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<DiscoCardResult> getDiscoResultwithRRN(String RRN) {
    final String _sql = "SELECT * FROM  DiscoEntity  INNER JOIN CardTransactionResult ON DiscoEntity.id = DiscoEntity.card_id Where CardTransactionResult.RRN = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (RRN == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, RRN);
    }
    return new ComputableLiveData<DiscoCardResult>() {
      private Observer _observer;

      @Override
      protected DiscoCardResult compute() {
        if (_observer == null) {
          _observer = new Observer("DiscoEntity","CardTransactionResult") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfStan = _cursor.getColumnIndexOrThrow("stan");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfWalletId = _cursor.getColumnIndexOrThrow("walletId");
          final int _cursorIndexOfMarchantAddress = _cursor.getColumnIndexOrThrow("marchantAddress");
          final int _cursorIndexOfMarchantTid = _cursor.getColumnIndexOrThrow("marchantTid");
          final int _cursorIndexOfMarchantName = _cursor.getColumnIndexOrThrow("marchantName");
          final int _cursorIndexOfMerchantId = _cursor.getColumnIndexOrThrow("merchantId");
          final int _cursorIndexOfProduct = _cursor.getColumnIndexOrThrow("product");
          final int _cursorIndexOfTransactionStatusMessage = _cursor.getColumnIndexOrThrow("transactionStatusMessage");
          final int _cursorIndexOfVasTid = _cursor.getColumnIndexOrThrow("vasTid");
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfPaymentmethod = _cursor.getColumnIndexOrThrow("paymentmethod");
          final int _cursorIndexOfLogo = _cursor.getColumnIndexOrThrow("logo");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
          final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
          final int _cursorIndexOfRecepiantName = _cursor.getColumnIndexOrThrow("recepiant_name");
          final int _cursorIndexOfMeterType = _cursor.getColumnIndexOrThrow("meterType");
          final int _cursorIndexOfTransactionId = _cursor.getColumnIndexOrThrow("transactionId");
          final int _cursorIndexOfUnit = _cursor.getColumnIndexOrThrow("unit");
          final int _cursorIndexOfUnitValue = _cursor.getColumnIndexOrThrow("unit_value");
          final int _cursorIndexOfVat = _cursor.getColumnIndexOrThrow("vat");
          final int _cursorIndexOfMeterNumber = _cursor.getColumnIndexOrThrow("meterNumber");
          final int _cursorIndexOfToken = _cursor.getColumnIndexOrThrow("token");
          final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
          final int _cursorIndexOfArras = _cursor.getColumnIndexOrThrow("arras");
          final int _cursorIndexOfTarrif = _cursor.getColumnIndexOrThrow("tarrif");
          final int _cursorIndexOfRRN = _cursor.getColumnIndexOrThrow("RRN");
          final int _cursorIndexOfAuthID = _cursor.getColumnIndexOrThrow("authID");
          final int _cursorIndexOfSTAN = _cursor.getColumnIndexOrThrow("STAN");
          final int _cursorIndexOfPAN = _cursor.getColumnIndexOrThrow("PAN");
          final int _cursorIndexOfTransactionStatus = _cursor.getColumnIndexOrThrow("transactionStatus");
          final int _cursorIndexOfResponseCode = _cursor.getColumnIndexOrThrow("responseCode");
          final int _cursorIndexOfTransactionStatusReason = _cursor.getColumnIndexOrThrow("transactionStatusReason");
          final int _cursorIndexOfAccountType = _cursor.getColumnIndexOrThrow("accountType");
          final int _cursorIndexOfBatchNumber = _cursor.getColumnIndexOrThrow("batchNumber");
          final int _cursorIndexOfMerchantID = _cursor.getColumnIndexOrThrow("merchantID");
          final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
          final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
          final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
          final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
          final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
          final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
          final int _cursorIndexOfAmount_1 = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
          final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
          final DiscoCardResult _result;
          if(_cursor.moveToFirst()) {
            final String _tmpStan;
            _tmpStan = _cursor.getString(_cursorIndexOfStan);
            final Long _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            }
            final String _tmpWalletId;
            _tmpWalletId = _cursor.getString(_cursorIndexOfWalletId);
            final String _tmpMarchantAddress;
            _tmpMarchantAddress = _cursor.getString(_cursorIndexOfMarchantAddress);
            final String _tmpMarchantTid;
            _tmpMarchantTid = _cursor.getString(_cursorIndexOfMarchantTid);
            final String _tmpMarchantName;
            _tmpMarchantName = _cursor.getString(_cursorIndexOfMarchantName);
            final String _tmpMerchantId;
            _tmpMerchantId = _cursor.getString(_cursorIndexOfMerchantId);
            final String _tmpProduct;
            _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
            final String _tmpTransactionStatusMessage;
            _tmpTransactionStatusMessage = _cursor.getString(_cursorIndexOfTransactionStatusMessage);
            final String _tmpVasTid;
            _tmpVasTid = _cursor.getString(_cursorIndexOfVasTid);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            final String _tmpPaymentmethod;
            _tmpPaymentmethod = _cursor.getString(_cursorIndexOfPaymentmethod);
            final int _tmpLogo;
            _tmpLogo = _cursor.getInt(_cursorIndexOfLogo);
            final String _tmpDateTime;
            _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            final String _tmpRecepiant_name;
            _tmpRecepiant_name = _cursor.getString(_cursorIndexOfRecepiantName);
            final String _tmpMeterType;
            _tmpMeterType = _cursor.getString(_cursorIndexOfMeterType);
            final String _tmpTransactionId;
            _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            final String _tmpUnit;
            _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            final String _tmpUnit_value;
            _tmpUnit_value = _cursor.getString(_cursorIndexOfUnitValue);
            final String _tmpVat;
            _tmpVat = _cursor.getString(_cursorIndexOfVat);
            final String _tmpMeterNumber;
            _tmpMeterNumber = _cursor.getString(_cursorIndexOfMeterNumber);
            final String _tmpToken;
            _tmpToken = _cursor.getString(_cursorIndexOfToken);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpArras;
            _tmpArras = _cursor.getString(_cursorIndexOfArras);
            final String _tmpTarrif;
            _tmpTarrif = _cursor.getString(_cursorIndexOfTarrif);
            final String _tmpRRN;
            _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
            final String _tmpAuthID;
            _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
            final String _tmpSTAN;
            _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
            final String _tmpPAN;
            _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
            final String _tmpTransactionStatus;
            _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            final String _tmpTransactionStatusReason;
            _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
            final String _tmpAccountType;
            _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
            final String _tmpBatchNumber;
            _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
            final String _tmpMerchantID;
            _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
            final String _tmpTerminalID;
            _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
            final String _tmpCardHolderName;
            _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
            final String _tmpCardExpiry;
            _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
            final String _tmpTSI;
            _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
            final String _tmpTVR;
            _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
            final String _tmpAID;
            _tmpAID = _cursor.getString(_cursorIndexOfAID);
            final Long _tmpAmount_1;
            if (_cursor.isNull(_cursorIndexOfAmount_1)) {
              _tmpAmount_1 = null;
            } else {
              _tmpAmount_1 = _cursor.getLong(_cursorIndexOfAmount_1);
            }
            final Long _tmpAdditionalAmount;
            if (_cursor.isNull(_cursorIndexOfAdditionalAmount)) {
              _tmpAdditionalAmount = null;
            } else {
              _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
            }
            final Long _tmpLongDateTime;
            if (_cursor.isNull(_cursorIndexOfLongDateTime)) {
              _tmpLongDateTime = null;
            } else {
              _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
            }
            _result = new DiscoCardResult(_tmpRRN,_tmpAuthID,_tmpSTAN,_tmpPAN,_tmpTransactionStatus,_tmpResponseCode,_tmpTransactionStatusReason,_tmpAccountType,_tmpBatchNumber,_tmpMerchantID,_tmpTerminalID,_tmpCardHolderName,_tmpCardExpiry,_tmpTSI,_tmpTVR,_tmpAID,_tmpAmount,_tmpAdditionalAmount,_tmpLongDateTime,null,_tmpStan,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpError,_tmpVasType,_tmpRecepiant_name,_tmpMeterType,_tmpTransactionId,_tmpUnit,_tmpUnit_value,_tmpVat,_tmpMeterNumber,_tmpToken,_tmpAddress,_tmpArras,_tmpTarrif);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
