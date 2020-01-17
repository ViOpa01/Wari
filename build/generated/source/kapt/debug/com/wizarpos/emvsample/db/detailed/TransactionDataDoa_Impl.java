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
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class TransactionDataDoa_Impl implements TransactionDataDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCardTransactionResult;

  public TransactionDataDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCardTransactionResult = new EntityInsertionAdapter<CardTransactionResult>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CardTransactionResult`(`id`,`RRN`,`authID`,`STAN`,`PAN`,`transactionStatus`,`responseCode`,`transactionStatusReason`,`accountType`,`batchNumber`,`merchantID`,`isoTransmissionDateTime`,`terminalID`,`originalForwardingInstitutionCode`,`cardHolderName`,`cardExpiry`,`TSI`,`TVR`,`AID`,`amount`,`additionalAmount`,`longDateTime`,`transactionType`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CardTransactionResult value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getRRN() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRRN());
        }
        if (value.getAuthID() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthID());
        }
        if (value.getSTAN() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSTAN());
        }
        if (value.getPAN() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPAN());
        }
        if (value.getTransactionStatus() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTransactionStatus());
        }
        if (value.getResponseCode() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getResponseCode());
        }
        if (value.getTransactionStatusReason() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTransactionStatusReason());
        }
        if (value.getAccountType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getAccountType());
        }
        if (value.getBatchNumber() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getBatchNumber());
        }
        if (value.getMerchantID() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getMerchantID());
        }
        if (value.getIsoTransmissionDateTime() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getIsoTransmissionDateTime());
        }
        if (value.getTerminalID() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getTerminalID());
        }
        if (value.getOriginalForwardingInstitutionCode() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getOriginalForwardingInstitutionCode());
        }
        if (value.getCardHolderName() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCardHolderName());
        }
        if (value.getCardExpiry() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getCardExpiry());
        }
        if (value.getTSI() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getTSI());
        }
        if (value.getTVR() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getTVR());
        }
        if (value.getAID() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getAID());
        }
        stmt.bindLong(20, value.getAmount());
        stmt.bindLong(21, value.getAdditionalAmount());
        stmt.bindLong(22, value.getLongDateTime());
        if (value.getTransactionType() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindString(23, value.getTransactionType());
        }
      }
    };
  }

  @Override
  public long saveTransData(CardTransactionResult cardTransactionResult) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCardTransactionResult.insertAndReturnId(cardTransactionResult);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CardTransactionResult> getAllTransactions() {
    final String _sql = "Select * From CardTransactionResult ORDER BY LongDateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
      final int _cursorIndexOfIsoTransmissionDateTime = _cursor.getColumnIndexOrThrow("isoTransmissionDateTime");
      final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
      final int _cursorIndexOfOriginalForwardingInstitutionCode = _cursor.getColumnIndexOrThrow("originalForwardingInstitutionCode");
      final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
      final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
      final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
      final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
      final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
      final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
      final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
      final List<CardTransactionResult> _result = new ArrayList<CardTransactionResult>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CardTransactionResult _item;
        _item = new CardTransactionResult();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpRRN;
        _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
        _item.setRRN(_tmpRRN);
        final String _tmpAuthID;
        _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
        _item.setAuthID(_tmpAuthID);
        final String _tmpSTAN;
        _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
        _item.setSTAN(_tmpSTAN);
        final String _tmpPAN;
        _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
        _item.setPAN(_tmpPAN);
        final String _tmpTransactionStatus;
        _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
        _item.setTransactionStatus(_tmpTransactionStatus);
        final String _tmpResponseCode;
        _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
        _item.setResponseCode(_tmpResponseCode);
        final String _tmpTransactionStatusReason;
        _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
        _item.setTransactionStatusReason(_tmpTransactionStatusReason);
        final String _tmpAccountType;
        _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
        _item.setAccountType(_tmpAccountType);
        final String _tmpBatchNumber;
        _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
        _item.setBatchNumber(_tmpBatchNumber);
        final String _tmpMerchantID;
        _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
        _item.setMerchantID(_tmpMerchantID);
        final String _tmpIsoTransmissionDateTime;
        _tmpIsoTransmissionDateTime = _cursor.getString(_cursorIndexOfIsoTransmissionDateTime);
        _item.setIsoTransmissionDateTime(_tmpIsoTransmissionDateTime);
        final String _tmpTerminalID;
        _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
        _item.setTerminalID(_tmpTerminalID);
        final String _tmpOriginalForwardingInstitutionCode;
        _tmpOriginalForwardingInstitutionCode = _cursor.getString(_cursorIndexOfOriginalForwardingInstitutionCode);
        _item.setOriginalForwardingInstitutionCode(_tmpOriginalForwardingInstitutionCode);
        final String _tmpCardHolderName;
        _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
        _item.setCardHolderName(_tmpCardHolderName);
        final String _tmpCardExpiry;
        _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
        _item.setCardExpiry(_tmpCardExpiry);
        final String _tmpTSI;
        _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
        _item.setTSI(_tmpTSI);
        final String _tmpTVR;
        _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
        _item.setTVR(_tmpTVR);
        final String _tmpAID;
        _tmpAID = _cursor.getString(_cursorIndexOfAID);
        _item.setAID(_tmpAID);
        final long _tmpAmount;
        _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        final long _tmpAdditionalAmount;
        _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
        _item.setAdditionalAmount(_tmpAdditionalAmount);
        final long _tmpLongDateTime;
        _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
        _item.setLongDateTime(_tmpLongDateTime);
        final String _tmpTransactionType;
        _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
        _item.setTransactionType(_tmpTransactionType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<CardTransactionResult> getTransactionByID(int id) {
    final String _sql = "SELECT * FROM CardTransactionResult WHERE id LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<CardTransactionResult>() {
      private Observer _observer;

      @Override
      protected CardTransactionResult compute() {
        if (_observer == null) {
          _observer = new Observer("CardTransactionResult") {
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
          final int _cursorIndexOfIsoTransmissionDateTime = _cursor.getColumnIndexOrThrow("isoTransmissionDateTime");
          final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
          final int _cursorIndexOfOriginalForwardingInstitutionCode = _cursor.getColumnIndexOrThrow("originalForwardingInstitutionCode");
          final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
          final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
          final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
          final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
          final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
          final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final CardTransactionResult _result;
          if(_cursor.moveToFirst()) {
            _result = new CardTransactionResult();
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _result.setId(_tmpId);
            final String _tmpRRN;
            _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
            _result.setRRN(_tmpRRN);
            final String _tmpAuthID;
            _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
            _result.setAuthID(_tmpAuthID);
            final String _tmpSTAN;
            _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
            _result.setSTAN(_tmpSTAN);
            final String _tmpPAN;
            _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
            _result.setPAN(_tmpPAN);
            final String _tmpTransactionStatus;
            _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
            _result.setTransactionStatus(_tmpTransactionStatus);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _result.setResponseCode(_tmpResponseCode);
            final String _tmpTransactionStatusReason;
            _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
            _result.setTransactionStatusReason(_tmpTransactionStatusReason);
            final String _tmpAccountType;
            _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
            _result.setAccountType(_tmpAccountType);
            final String _tmpBatchNumber;
            _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
            _result.setBatchNumber(_tmpBatchNumber);
            final String _tmpMerchantID;
            _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
            _result.setMerchantID(_tmpMerchantID);
            final String _tmpIsoTransmissionDateTime;
            _tmpIsoTransmissionDateTime = _cursor.getString(_cursorIndexOfIsoTransmissionDateTime);
            _result.setIsoTransmissionDateTime(_tmpIsoTransmissionDateTime);
            final String _tmpTerminalID;
            _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
            _result.setTerminalID(_tmpTerminalID);
            final String _tmpOriginalForwardingInstitutionCode;
            _tmpOriginalForwardingInstitutionCode = _cursor.getString(_cursorIndexOfOriginalForwardingInstitutionCode);
            _result.setOriginalForwardingInstitutionCode(_tmpOriginalForwardingInstitutionCode);
            final String _tmpCardHolderName;
            _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
            _result.setCardHolderName(_tmpCardHolderName);
            final String _tmpCardExpiry;
            _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
            _result.setCardExpiry(_tmpCardExpiry);
            final String _tmpTSI;
            _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
            _result.setTSI(_tmpTSI);
            final String _tmpTVR;
            _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
            _result.setTVR(_tmpTVR);
            final String _tmpAID;
            _tmpAID = _cursor.getString(_cursorIndexOfAID);
            _result.setAID(_tmpAID);
            final long _tmpAmount;
            _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            _result.setAmount(_tmpAmount);
            final long _tmpAdditionalAmount;
            _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
            _result.setAdditionalAmount(_tmpAdditionalAmount);
            final long _tmpLongDateTime;
            _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
            _result.setLongDateTime(_tmpLongDateTime);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _result.setTransactionType(_tmpTransactionType);
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
  public LiveData<CardTransactionResult> getTransactionByRRN(String rrn) {
    final String _sql = "SELECT * FROM CardTransactionResult WHERE RRN LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (rrn == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, rrn);
    }
    return new ComputableLiveData<CardTransactionResult>() {
      private Observer _observer;

      @Override
      protected CardTransactionResult compute() {
        if (_observer == null) {
          _observer = new Observer("CardTransactionResult") {
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
          final int _cursorIndexOfIsoTransmissionDateTime = _cursor.getColumnIndexOrThrow("isoTransmissionDateTime");
          final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
          final int _cursorIndexOfOriginalForwardingInstitutionCode = _cursor.getColumnIndexOrThrow("originalForwardingInstitutionCode");
          final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
          final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
          final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
          final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
          final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
          final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final CardTransactionResult _result;
          if(_cursor.moveToFirst()) {
            _result = new CardTransactionResult();
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _result.setId(_tmpId);
            final String _tmpRRN;
            _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
            _result.setRRN(_tmpRRN);
            final String _tmpAuthID;
            _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
            _result.setAuthID(_tmpAuthID);
            final String _tmpSTAN;
            _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
            _result.setSTAN(_tmpSTAN);
            final String _tmpPAN;
            _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
            _result.setPAN(_tmpPAN);
            final String _tmpTransactionStatus;
            _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
            _result.setTransactionStatus(_tmpTransactionStatus);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _result.setResponseCode(_tmpResponseCode);
            final String _tmpTransactionStatusReason;
            _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
            _result.setTransactionStatusReason(_tmpTransactionStatusReason);
            final String _tmpAccountType;
            _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
            _result.setAccountType(_tmpAccountType);
            final String _tmpBatchNumber;
            _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
            _result.setBatchNumber(_tmpBatchNumber);
            final String _tmpMerchantID;
            _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
            _result.setMerchantID(_tmpMerchantID);
            final String _tmpIsoTransmissionDateTime;
            _tmpIsoTransmissionDateTime = _cursor.getString(_cursorIndexOfIsoTransmissionDateTime);
            _result.setIsoTransmissionDateTime(_tmpIsoTransmissionDateTime);
            final String _tmpTerminalID;
            _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
            _result.setTerminalID(_tmpTerminalID);
            final String _tmpOriginalForwardingInstitutionCode;
            _tmpOriginalForwardingInstitutionCode = _cursor.getString(_cursorIndexOfOriginalForwardingInstitutionCode);
            _result.setOriginalForwardingInstitutionCode(_tmpOriginalForwardingInstitutionCode);
            final String _tmpCardHolderName;
            _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
            _result.setCardHolderName(_tmpCardHolderName);
            final String _tmpCardExpiry;
            _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
            _result.setCardExpiry(_tmpCardExpiry);
            final String _tmpTSI;
            _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
            _result.setTSI(_tmpTSI);
            final String _tmpTVR;
            _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
            _result.setTVR(_tmpTVR);
            final String _tmpAID;
            _tmpAID = _cursor.getString(_cursorIndexOfAID);
            _result.setAID(_tmpAID);
            final long _tmpAmount;
            _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            _result.setAmount(_tmpAmount);
            final long _tmpAdditionalAmount;
            _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
            _result.setAdditionalAmount(_tmpAdditionalAmount);
            final long _tmpLongDateTime;
            _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
            _result.setLongDateTime(_tmpLongDateTime);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _result.setTransactionType(_tmpTransactionType);
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
  public CardTransactionResult getImmediately(int id) {
    final String _sql = "SELECT * FROM CardTransactionResult WHERE id LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
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
      final int _cursorIndexOfIsoTransmissionDateTime = _cursor.getColumnIndexOrThrow("isoTransmissionDateTime");
      final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
      final int _cursorIndexOfOriginalForwardingInstitutionCode = _cursor.getColumnIndexOrThrow("originalForwardingInstitutionCode");
      final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
      final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
      final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
      final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
      final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
      final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
      final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
      final CardTransactionResult _result;
      if(_cursor.moveToFirst()) {
        _result = new CardTransactionResult();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _result.setId(_tmpId);
        final String _tmpRRN;
        _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
        _result.setRRN(_tmpRRN);
        final String _tmpAuthID;
        _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
        _result.setAuthID(_tmpAuthID);
        final String _tmpSTAN;
        _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
        _result.setSTAN(_tmpSTAN);
        final String _tmpPAN;
        _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
        _result.setPAN(_tmpPAN);
        final String _tmpTransactionStatus;
        _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
        _result.setTransactionStatus(_tmpTransactionStatus);
        final String _tmpResponseCode;
        _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
        _result.setResponseCode(_tmpResponseCode);
        final String _tmpTransactionStatusReason;
        _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
        _result.setTransactionStatusReason(_tmpTransactionStatusReason);
        final String _tmpAccountType;
        _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
        _result.setAccountType(_tmpAccountType);
        final String _tmpBatchNumber;
        _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
        _result.setBatchNumber(_tmpBatchNumber);
        final String _tmpMerchantID;
        _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
        _result.setMerchantID(_tmpMerchantID);
        final String _tmpIsoTransmissionDateTime;
        _tmpIsoTransmissionDateTime = _cursor.getString(_cursorIndexOfIsoTransmissionDateTime);
        _result.setIsoTransmissionDateTime(_tmpIsoTransmissionDateTime);
        final String _tmpTerminalID;
        _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
        _result.setTerminalID(_tmpTerminalID);
        final String _tmpOriginalForwardingInstitutionCode;
        _tmpOriginalForwardingInstitutionCode = _cursor.getString(_cursorIndexOfOriginalForwardingInstitutionCode);
        _result.setOriginalForwardingInstitutionCode(_tmpOriginalForwardingInstitutionCode);
        final String _tmpCardHolderName;
        _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
        _result.setCardHolderName(_tmpCardHolderName);
        final String _tmpCardExpiry;
        _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
        _result.setCardExpiry(_tmpCardExpiry);
        final String _tmpTSI;
        _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
        _result.setTSI(_tmpTSI);
        final String _tmpTVR;
        _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
        _result.setTVR(_tmpTVR);
        final String _tmpAID;
        _tmpAID = _cursor.getString(_cursorIndexOfAID);
        _result.setAID(_tmpAID);
        final long _tmpAmount;
        _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
        _result.setAmount(_tmpAmount);
        final long _tmpAdditionalAmount;
        _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
        _result.setAdditionalAmount(_tmpAdditionalAmount);
        final long _tmpLongDateTime;
        _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
        _result.setLongDateTime(_tmpLongDateTime);
        final String _tmpTransactionType;
        _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
        _result.setTransactionType(_tmpTransactionType);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<CardTransactionResult>> findAll() {
    final String _sql = "SELECT * FROM CardTransactionResult ORDER BY LongDateTime DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<CardTransactionResult>>() {
      private Observer _observer;

      @Override
      protected List<CardTransactionResult> compute() {
        if (_observer == null) {
          _observer = new Observer("CardTransactionResult") {
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
          final int _cursorIndexOfIsoTransmissionDateTime = _cursor.getColumnIndexOrThrow("isoTransmissionDateTime");
          final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
          final int _cursorIndexOfOriginalForwardingInstitutionCode = _cursor.getColumnIndexOrThrow("originalForwardingInstitutionCode");
          final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
          final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
          final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
          final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
          final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
          final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final List<CardTransactionResult> _result = new ArrayList<CardTransactionResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CardTransactionResult _item;
            _item = new CardTransactionResult();
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpRRN;
            _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
            _item.setRRN(_tmpRRN);
            final String _tmpAuthID;
            _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
            _item.setAuthID(_tmpAuthID);
            final String _tmpSTAN;
            _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
            _item.setSTAN(_tmpSTAN);
            final String _tmpPAN;
            _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
            _item.setPAN(_tmpPAN);
            final String _tmpTransactionStatus;
            _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
            _item.setTransactionStatus(_tmpTransactionStatus);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _item.setResponseCode(_tmpResponseCode);
            final String _tmpTransactionStatusReason;
            _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
            _item.setTransactionStatusReason(_tmpTransactionStatusReason);
            final String _tmpAccountType;
            _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
            _item.setAccountType(_tmpAccountType);
            final String _tmpBatchNumber;
            _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
            _item.setBatchNumber(_tmpBatchNumber);
            final String _tmpMerchantID;
            _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
            _item.setMerchantID(_tmpMerchantID);
            final String _tmpIsoTransmissionDateTime;
            _tmpIsoTransmissionDateTime = _cursor.getString(_cursorIndexOfIsoTransmissionDateTime);
            _item.setIsoTransmissionDateTime(_tmpIsoTransmissionDateTime);
            final String _tmpTerminalID;
            _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
            _item.setTerminalID(_tmpTerminalID);
            final String _tmpOriginalForwardingInstitutionCode;
            _tmpOriginalForwardingInstitutionCode = _cursor.getString(_cursorIndexOfOriginalForwardingInstitutionCode);
            _item.setOriginalForwardingInstitutionCode(_tmpOriginalForwardingInstitutionCode);
            final String _tmpCardHolderName;
            _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
            _item.setCardHolderName(_tmpCardHolderName);
            final String _tmpCardExpiry;
            _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
            _item.setCardExpiry(_tmpCardExpiry);
            final String _tmpTSI;
            _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
            _item.setTSI(_tmpTSI);
            final String _tmpTVR;
            _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
            _item.setTVR(_tmpTVR);
            final String _tmpAID;
            _tmpAID = _cursor.getString(_cursorIndexOfAID);
            _item.setAID(_tmpAID);
            final long _tmpAmount;
            _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            _item.setAmount(_tmpAmount);
            final long _tmpAdditionalAmount;
            _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
            _item.setAdditionalAmount(_tmpAdditionalAmount);
            final long _tmpLongDateTime;
            _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
            _item.setLongDateTime(_tmpLongDateTime);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item.setTransactionType(_tmpTransactionType);
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
  public LiveData<List<CardTransactionResult>> findDeclinedTransactionsInDateRange(long minDate,
      long maxDate, String responsecode) {
    final String _sql = "SELECT * FROM CardTransactionResult WHERE responseCode !=? AND LongDateTime >= ? AND LongDateTime <= ? ORDER BY LongDateTime DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (responsecode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, responsecode);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, minDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, maxDate);
    return new ComputableLiveData<List<CardTransactionResult>>() {
      private Observer _observer;

      @Override
      protected List<CardTransactionResult> compute() {
        if (_observer == null) {
          _observer = new Observer("CardTransactionResult") {
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
          final int _cursorIndexOfIsoTransmissionDateTime = _cursor.getColumnIndexOrThrow("isoTransmissionDateTime");
          final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
          final int _cursorIndexOfOriginalForwardingInstitutionCode = _cursor.getColumnIndexOrThrow("originalForwardingInstitutionCode");
          final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
          final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
          final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
          final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
          final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
          final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final List<CardTransactionResult> _result = new ArrayList<CardTransactionResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CardTransactionResult _item;
            _item = new CardTransactionResult();
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpRRN;
            _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
            _item.setRRN(_tmpRRN);
            final String _tmpAuthID;
            _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
            _item.setAuthID(_tmpAuthID);
            final String _tmpSTAN;
            _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
            _item.setSTAN(_tmpSTAN);
            final String _tmpPAN;
            _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
            _item.setPAN(_tmpPAN);
            final String _tmpTransactionStatus;
            _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
            _item.setTransactionStatus(_tmpTransactionStatus);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _item.setResponseCode(_tmpResponseCode);
            final String _tmpTransactionStatusReason;
            _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
            _item.setTransactionStatusReason(_tmpTransactionStatusReason);
            final String _tmpAccountType;
            _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
            _item.setAccountType(_tmpAccountType);
            final String _tmpBatchNumber;
            _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
            _item.setBatchNumber(_tmpBatchNumber);
            final String _tmpMerchantID;
            _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
            _item.setMerchantID(_tmpMerchantID);
            final String _tmpIsoTransmissionDateTime;
            _tmpIsoTransmissionDateTime = _cursor.getString(_cursorIndexOfIsoTransmissionDateTime);
            _item.setIsoTransmissionDateTime(_tmpIsoTransmissionDateTime);
            final String _tmpTerminalID;
            _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
            _item.setTerminalID(_tmpTerminalID);
            final String _tmpOriginalForwardingInstitutionCode;
            _tmpOriginalForwardingInstitutionCode = _cursor.getString(_cursorIndexOfOriginalForwardingInstitutionCode);
            _item.setOriginalForwardingInstitutionCode(_tmpOriginalForwardingInstitutionCode);
            final String _tmpCardHolderName;
            _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
            _item.setCardHolderName(_tmpCardHolderName);
            final String _tmpCardExpiry;
            _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
            _item.setCardExpiry(_tmpCardExpiry);
            final String _tmpTSI;
            _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
            _item.setTSI(_tmpTSI);
            final String _tmpTVR;
            _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
            _item.setTVR(_tmpTVR);
            final String _tmpAID;
            _tmpAID = _cursor.getString(_cursorIndexOfAID);
            _item.setAID(_tmpAID);
            final long _tmpAmount;
            _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            _item.setAmount(_tmpAmount);
            final long _tmpAdditionalAmount;
            _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
            _item.setAdditionalAmount(_tmpAdditionalAmount);
            final long _tmpLongDateTime;
            _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
            _item.setLongDateTime(_tmpLongDateTime);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item.setTransactionType(_tmpTransactionType);
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
  public LiveData<List<CardTransactionResult>> findApprovedTransactionsInDateRange(long minDate,
      long maxDate, String responsecode) {
    final String _sql = "SELECT * FROM CardTransactionResult WHERE responseCode =? AND LongDateTime >= ? AND LongDateTime <= ? ORDER BY LongDateTime DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (responsecode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, responsecode);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, minDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, maxDate);
    return new ComputableLiveData<List<CardTransactionResult>>() {
      private Observer _observer;

      @Override
      protected List<CardTransactionResult> compute() {
        if (_observer == null) {
          _observer = new Observer("CardTransactionResult") {
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
          final int _cursorIndexOfIsoTransmissionDateTime = _cursor.getColumnIndexOrThrow("isoTransmissionDateTime");
          final int _cursorIndexOfTerminalID = _cursor.getColumnIndexOrThrow("terminalID");
          final int _cursorIndexOfOriginalForwardingInstitutionCode = _cursor.getColumnIndexOrThrow("originalForwardingInstitutionCode");
          final int _cursorIndexOfCardHolderName = _cursor.getColumnIndexOrThrow("cardHolderName");
          final int _cursorIndexOfCardExpiry = _cursor.getColumnIndexOrThrow("cardExpiry");
          final int _cursorIndexOfTSI = _cursor.getColumnIndexOrThrow("TSI");
          final int _cursorIndexOfTVR = _cursor.getColumnIndexOrThrow("TVR");
          final int _cursorIndexOfAID = _cursor.getColumnIndexOrThrow("AID");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfAdditionalAmount = _cursor.getColumnIndexOrThrow("additionalAmount");
          final int _cursorIndexOfLongDateTime = _cursor.getColumnIndexOrThrow("longDateTime");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final List<CardTransactionResult> _result = new ArrayList<CardTransactionResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CardTransactionResult _item;
            _item = new CardTransactionResult();
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpRRN;
            _tmpRRN = _cursor.getString(_cursorIndexOfRRN);
            _item.setRRN(_tmpRRN);
            final String _tmpAuthID;
            _tmpAuthID = _cursor.getString(_cursorIndexOfAuthID);
            _item.setAuthID(_tmpAuthID);
            final String _tmpSTAN;
            _tmpSTAN = _cursor.getString(_cursorIndexOfSTAN);
            _item.setSTAN(_tmpSTAN);
            final String _tmpPAN;
            _tmpPAN = _cursor.getString(_cursorIndexOfPAN);
            _item.setPAN(_tmpPAN);
            final String _tmpTransactionStatus;
            _tmpTransactionStatus = _cursor.getString(_cursorIndexOfTransactionStatus);
            _item.setTransactionStatus(_tmpTransactionStatus);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _item.setResponseCode(_tmpResponseCode);
            final String _tmpTransactionStatusReason;
            _tmpTransactionStatusReason = _cursor.getString(_cursorIndexOfTransactionStatusReason);
            _item.setTransactionStatusReason(_tmpTransactionStatusReason);
            final String _tmpAccountType;
            _tmpAccountType = _cursor.getString(_cursorIndexOfAccountType);
            _item.setAccountType(_tmpAccountType);
            final String _tmpBatchNumber;
            _tmpBatchNumber = _cursor.getString(_cursorIndexOfBatchNumber);
            _item.setBatchNumber(_tmpBatchNumber);
            final String _tmpMerchantID;
            _tmpMerchantID = _cursor.getString(_cursorIndexOfMerchantID);
            _item.setMerchantID(_tmpMerchantID);
            final String _tmpIsoTransmissionDateTime;
            _tmpIsoTransmissionDateTime = _cursor.getString(_cursorIndexOfIsoTransmissionDateTime);
            _item.setIsoTransmissionDateTime(_tmpIsoTransmissionDateTime);
            final String _tmpTerminalID;
            _tmpTerminalID = _cursor.getString(_cursorIndexOfTerminalID);
            _item.setTerminalID(_tmpTerminalID);
            final String _tmpOriginalForwardingInstitutionCode;
            _tmpOriginalForwardingInstitutionCode = _cursor.getString(_cursorIndexOfOriginalForwardingInstitutionCode);
            _item.setOriginalForwardingInstitutionCode(_tmpOriginalForwardingInstitutionCode);
            final String _tmpCardHolderName;
            _tmpCardHolderName = _cursor.getString(_cursorIndexOfCardHolderName);
            _item.setCardHolderName(_tmpCardHolderName);
            final String _tmpCardExpiry;
            _tmpCardExpiry = _cursor.getString(_cursorIndexOfCardExpiry);
            _item.setCardExpiry(_tmpCardExpiry);
            final String _tmpTSI;
            _tmpTSI = _cursor.getString(_cursorIndexOfTSI);
            _item.setTSI(_tmpTSI);
            final String _tmpTVR;
            _tmpTVR = _cursor.getString(_cursorIndexOfTVR);
            _item.setTVR(_tmpTVR);
            final String _tmpAID;
            _tmpAID = _cursor.getString(_cursorIndexOfAID);
            _item.setAID(_tmpAID);
            final long _tmpAmount;
            _tmpAmount = _cursor.getLong(_cursorIndexOfAmount);
            _item.setAmount(_tmpAmount);
            final long _tmpAdditionalAmount;
            _tmpAdditionalAmount = _cursor.getLong(_cursorIndexOfAdditionalAmount);
            _item.setAdditionalAmount(_tmpAdditionalAmount);
            final long _tmpLongDateTime;
            _tmpLongDateTime = _cursor.getLong(_cursorIndexOfLongDateTime);
            _item.setLongDateTime(_tmpLongDateTime);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item.setTransactionType(_tmpTransactionType);
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
}
