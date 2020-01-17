package com.wizarpos.emvsample.db.detailed;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class VasTransactionDoa_Impl implements VasTransactionDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfVasTransactionResult;

  public VasTransactionDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVasTransactionResult = new EntityInsertionAdapter<VasTransactionResult>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `VasTransactionResult`(`id`,`card_id`,`amount`,`walletId`,`marchantAddress`,`marchantTid`,`marchantName`,`merchantId`,`product`,`transactionStatusMessage`,`vasTid`,`transactionRef`,`paymentmethod`,`logo`,`dateTime`,`error`,`vasType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, VasTransactionResult value) {
        stmt.bindLong(1, value.getId());
        if (value.getStan() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getStan());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAmount());
        }
        if (value.getWalletId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getWalletId());
        }
        if (value.getMarchantAddress() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMarchantAddress());
        }
        if (value.getMarchantTid() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMarchantTid());
        }
        if (value.getMarchantName() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMarchantName());
        }
        if (value.getMerchantId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getMerchantId());
        }
        if (value.getProduct() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getProduct());
        }
        if (value.getTransactionStatusMessage() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getTransactionStatusMessage());
        }
        if (value.getVasTid() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getVasTid());
        }
        if (value.getTransactionRef() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getTransactionRef());
        }
        if (value.getPaymentmethod() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getPaymentmethod());
        }
        stmt.bindLong(14, value.getLogo());
        if (value.getDateTime() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getDateTime());
        }
        final int _tmp;
        _tmp = value.getError() ? 1 : 0;
        stmt.bindLong(16, _tmp);
        if (value.getVasType() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getVasType());
        }
      }
    };
  }

  @Override
  public long saveVasTransData(VasTransactionResult vasTransactionResult) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfVasTransactionResult.insertAndReturnId(vasTransactionResult);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<VasTransactionResult> getAllVasTransactions() {
    final String _sql = "Select * From VasTransactionResult ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfStan = _cursor.getColumnIndexOrThrow("card_id");
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
      final List<VasTransactionResult> _result = new ArrayList<VasTransactionResult>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final VasTransactionResult _item;
        _item = new VasTransactionResult();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpStan;
        _tmpStan = _cursor.getString(_cursorIndexOfStan);
        _item.setStan(_tmpStan);
        final String _tmpAmount;
        _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        final String _tmpWalletId;
        _tmpWalletId = _cursor.getString(_cursorIndexOfWalletId);
        _item.setWalletId(_tmpWalletId);
        final String _tmpMarchantAddress;
        _tmpMarchantAddress = _cursor.getString(_cursorIndexOfMarchantAddress);
        _item.setMarchantAddress(_tmpMarchantAddress);
        final String _tmpMarchantTid;
        _tmpMarchantTid = _cursor.getString(_cursorIndexOfMarchantTid);
        _item.setMarchantTid(_tmpMarchantTid);
        final String _tmpMarchantName;
        _tmpMarchantName = _cursor.getString(_cursorIndexOfMarchantName);
        _item.setMarchantName(_tmpMarchantName);
        final String _tmpMerchantId;
        _tmpMerchantId = _cursor.getString(_cursorIndexOfMerchantId);
        _item.setMerchantId(_tmpMerchantId);
        final String _tmpProduct;
        _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
        _item.setProduct(_tmpProduct);
        final String _tmpTransactionStatusMessage;
        _tmpTransactionStatusMessage = _cursor.getString(_cursorIndexOfTransactionStatusMessage);
        _item.setTransactionStatusMessage(_tmpTransactionStatusMessage);
        final String _tmpVasTid;
        _tmpVasTid = _cursor.getString(_cursorIndexOfVasTid);
        _item.setVasTid(_tmpVasTid);
        final String _tmpTransactionRef;
        _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
        _item.setTransactionRef(_tmpTransactionRef);
        final String _tmpPaymentmethod;
        _tmpPaymentmethod = _cursor.getString(_cursorIndexOfPaymentmethod);
        _item.setPaymentmethod(_tmpPaymentmethod);
        final int _tmpLogo;
        _tmpLogo = _cursor.getInt(_cursorIndexOfLogo);
        _item.setLogo(_tmpLogo);
        final String _tmpDateTime;
        _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
        _item.setDateTime(_tmpDateTime);
        final boolean _tmpError;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfError);
        _tmpError = _tmp != 0;
        _item.setError(_tmpError);
        final String _tmpVasType;
        _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
        _item.setVasType(_tmpVasType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<VasTransactionResult> getVas(int id) {
    final String _sql = "SELECT * FROM VasTransactionResult WHERE card_id LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<VasTransactionResult>() {
      private Observer _observer;

      @Override
      protected VasTransactionResult compute() {
        if (_observer == null) {
          _observer = new Observer("VasTransactionResult") {
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
          final int _cursorIndexOfStan = _cursor.getColumnIndexOrThrow("card_id");
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
          final VasTransactionResult _result;
          if(_cursor.moveToFirst()) {
            _result = new VasTransactionResult();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result.setId(_tmpId);
            final String _tmpStan;
            _tmpStan = _cursor.getString(_cursorIndexOfStan);
            _result.setStan(_tmpStan);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            _result.setAmount(_tmpAmount);
            final String _tmpWalletId;
            _tmpWalletId = _cursor.getString(_cursorIndexOfWalletId);
            _result.setWalletId(_tmpWalletId);
            final String _tmpMarchantAddress;
            _tmpMarchantAddress = _cursor.getString(_cursorIndexOfMarchantAddress);
            _result.setMarchantAddress(_tmpMarchantAddress);
            final String _tmpMarchantTid;
            _tmpMarchantTid = _cursor.getString(_cursorIndexOfMarchantTid);
            _result.setMarchantTid(_tmpMarchantTid);
            final String _tmpMarchantName;
            _tmpMarchantName = _cursor.getString(_cursorIndexOfMarchantName);
            _result.setMarchantName(_tmpMarchantName);
            final String _tmpMerchantId;
            _tmpMerchantId = _cursor.getString(_cursorIndexOfMerchantId);
            _result.setMerchantId(_tmpMerchantId);
            final String _tmpProduct;
            _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
            _result.setProduct(_tmpProduct);
            final String _tmpTransactionStatusMessage;
            _tmpTransactionStatusMessage = _cursor.getString(_cursorIndexOfTransactionStatusMessage);
            _result.setTransactionStatusMessage(_tmpTransactionStatusMessage);
            final String _tmpVasTid;
            _tmpVasTid = _cursor.getString(_cursorIndexOfVasTid);
            _result.setVasTid(_tmpVasTid);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            _result.setTransactionRef(_tmpTransactionRef);
            final String _tmpPaymentmethod;
            _tmpPaymentmethod = _cursor.getString(_cursorIndexOfPaymentmethod);
            _result.setPaymentmethod(_tmpPaymentmethod);
            final int _tmpLogo;
            _tmpLogo = _cursor.getInt(_cursorIndexOfLogo);
            _result.setLogo(_tmpLogo);
            final String _tmpDateTime;
            _tmpDateTime = _cursor.getString(_cursorIndexOfDateTime);
            _result.setDateTime(_tmpDateTime);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            _result.setError(_tmpError);
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            _result.setVasType(_tmpVasType);
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
  public LiveData<List<VasCardResult>> getVasResult() {
    final String _sql = "Select CardTransactionResult.*, VasTransactionResult.* from  VasTransactionResult LEFT JOIN CardTransactionResult on   VasTransactionResult.card_id LIKE CardTransactionResult.id ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<VasCardResult>>() {
      private Observer _observer;

      @Override
      protected List<VasCardResult> compute() {
        if (_observer == null) {
          _observer = new Observer("VasTransactionResult","CardTransactionResult") {
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
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
          final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfAmount_1 = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final List<VasCardResult> _result = new ArrayList<VasCardResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final VasCardResult _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
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
            final Long _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
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
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            final int _tmpId_1;
            _tmpId_1 = _cursor.getInt(_cursorIndexOfId_1);
            final Long _tmpAmount_1;
            if (_cursor.isNull(_cursorIndexOfAmount_1)) {
              _tmpAmount_1 = null;
            } else {
              _tmpAmount_1 = _cursor.getLong(_cursorIndexOfAmount_1);
            }
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            _item = new VasCardResult(_tmpRRN,_tmpAuthID,_tmpSTAN,_tmpPAN,_tmpTransactionStatus,_tmpResponseCode,_tmpTransactionStatusReason,_tmpAccountType,_tmpBatchNumber,_tmpMerchantID,_tmpTerminalID,_tmpCardHolderName,_tmpCardExpiry,_tmpTSI,_tmpTVR,_tmpAID,_tmpAmount,_tmpAdditionalAmount,_tmpLongDateTime,null,_tmpId,null,null,null,null,null,null,null,null,null,null,null,null,_tmpDateTime,null,null,_tmpTransactionType);
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
  public LiveData<VasCardResult> getAirtimeResultwithrequestId(String transactionRef) {
    final String _sql = "SELECT * FROM  VasTransactionResult  INNER JOIN AirtimeEntity on VasTransactionResult.transactionRef = AirtimeEntity.transactionRef where VasTransactionResult.vasType = ?   ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (transactionRef == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, transactionRef);
    }
    return new ComputableLiveData<VasCardResult>() {
      private Observer _observer;

      @Override
      protected VasCardResult compute() {
        if (_observer == null) {
          _observer = new Observer("VasTransactionResult","AirtimeEntity") {
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
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
          final VasCardResult _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final Long _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            }
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            final int _tmpId_1;
            _tmpId_1 = _cursor.getInt(_cursorIndexOfId_1);
            _result = new VasCardResult(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,_tmpAmount,null,null,null,_tmpId,null,null,null,null,null,null,null,null,null,null,null,null,_tmpDateTime,null,null,null);
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
  public LiveData<VasCardResult> getAirtimeResultwithRRN(String RRN) {
    final String _sql = "SELECT * FROM  VasTransactionResult  INNER JOIN CardTransactionResult ON CardTransactionResult.id = VasTransactionResult.card_id  INNER JOIN AirtimeEntity on VasTransactionResult.transactionRef = AirtimeEntity.transactionRef Where CardTransactionResult.RRN = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (RRN == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, RRN);
    }
    return new ComputableLiveData<VasCardResult>() {
      private Observer _observer;

      @Override
      protected VasCardResult compute() {
        if (_observer == null) {
          _observer = new Observer("VasTransactionResult","CardTransactionResult","AirtimeEntity") {
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
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfId_1 = _cursor.getColumnIndexOrThrow("id");
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
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final int _cursorIndexOfId_2 = _cursor.getColumnIndexOrThrow("id");
          final VasCardResult _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final Long _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            }
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            final int _tmpId_1;
            _tmpId_1 = _cursor.getInt(_cursorIndexOfId_1);
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
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            final int _tmpId_2;
            _tmpId_2 = _cursor.getInt(_cursorIndexOfId_2);
            _result = new VasCardResult(_tmpRRN,_tmpAuthID,_tmpSTAN,_tmpPAN,_tmpTransactionStatus,_tmpResponseCode,_tmpTransactionStatusReason,_tmpAccountType,_tmpBatchNumber,_tmpMerchantID,_tmpTerminalID,_tmpCardHolderName,_tmpCardExpiry,_tmpTSI,_tmpTVR,_tmpAID,_tmpAmount,_tmpAdditionalAmount,_tmpLongDateTime,null,_tmpId,null,null,null,null,null,null,null,null,null,null,null,null,_tmpDateTime,null,null,_tmpTransactionType);
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
