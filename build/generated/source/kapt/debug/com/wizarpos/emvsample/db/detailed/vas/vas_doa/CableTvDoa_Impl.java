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
import com.wizarpos.emvsample.db.detailed.vas.model.CableTvCardResult;
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.CableTvEntity;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class CableTvDoa_Impl implements CableTvDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCableTvEntity;

  public CableTvDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCableTvEntity = new EntityInsertionAdapter<CableTvEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CableTvEntity`(`id`,`card_id`,`stan`,`amount`,`walletId`,`marchantAddress`,`marchantTid`,`marchantName`,`merchantId`,`product`,`transactionStatusMessage`,`vasTid`,`transactionRef`,`paymentmethod`,`logo`,`dateTime`,`vasType`,`error`,`iuc`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CableTvEntity value) {
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
        if (value.getVasType() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getVasType());
        }
        final int _tmp;
        _tmp = value.getError() ? 1 : 0;
        stmt.bindLong(18, _tmp);
        if (value.getIuc() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getIuc());
        }
      }
    };
  }

  @Override
  public long saveCableTvData(CableTvEntity vasTransactionResult) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCableTvEntity.insertAndReturnId(vasTransactionResult);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CableTvEntity> getAllCableTvData() {
    final String _sql = "Select * From CableTvEntity ORDER BY dateTime DESC";
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
      final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
      final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
      final int _cursorIndexOfIuc = _cursor.getColumnIndexOrThrow("iuc");
      final List<CableTvEntity> _result = new ArrayList<CableTvEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CableTvEntity _item;
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
        final String _tmpVasType;
        _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
        final boolean _tmpError;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfError);
        _tmpError = _tmp != 0;
        final String _tmpIuc;
        _tmpIuc = _cursor.getString(_cursorIndexOfIuc);
        _item = new CableTvEntity(_tmpId,_tmpCardrowId,_tmpStan,_tmpAmount,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpVasType,_tmpError,_tmpIuc);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<CableTvEntity> getCableTvPurchased(int id) {
    final String _sql = "SELECT * FROM CableTvEntity WHERE transactionRef LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<CableTvEntity>() {
      private Observer _observer;

      @Override
      protected CableTvEntity compute() {
        if (_observer == null) {
          _observer = new Observer("CableTvEntity") {
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
          final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
          final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
          final int _cursorIndexOfIuc = _cursor.getColumnIndexOrThrow("iuc");
          final CableTvEntity _result;
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
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            final String _tmpIuc;
            _tmpIuc = _cursor.getString(_cursorIndexOfIuc);
            _result = new CableTvEntity(_tmpId,_tmpCardrowId,_tmpStan,_tmpAmount,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpVasType,_tmpError,_tmpIuc);
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
  public LiveData<List<CableTvCardResult>> getCableTvResult() {
    final String _sql = "Select CardTransactionResult.*, CableTvEntity.* from  CableTvEntity LEFT JOIN CardTransactionResult on   CableTvEntity.card_id LIKE CardTransactionResult.id ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<CableTvCardResult>>() {
      private Observer _observer;

      @Override
      protected List<CableTvCardResult> compute() {
        if (_observer == null) {
          _observer = new Observer("CableTvEntity","CardTransactionResult") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
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
          final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
          final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
          final int _cursorIndexOfIuc = _cursor.getColumnIndexOrThrow("iuc");
          final List<CableTvCardResult> _result = new ArrayList<CableTvCardResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CableTvCardResult _item;
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
            final String _tmpStan;
            _tmpStan = _cursor.getString(_cursorIndexOfStan);
            final Long _tmpAmount_1;
            if (_cursor.isNull(_cursorIndexOfAmount_1)) {
              _tmpAmount_1 = null;
            } else {
              _tmpAmount_1 = _cursor.getLong(_cursorIndexOfAmount_1);
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
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            final String _tmpIuc;
            _tmpIuc = _cursor.getString(_cursorIndexOfIuc);
            _item = new CableTvCardResult(_tmpRRN,_tmpAuthID,_tmpSTAN,_tmpPAN,_tmpTransactionStatus,_tmpResponseCode,_tmpTransactionStatusReason,_tmpAccountType,_tmpBatchNumber,_tmpMerchantID,_tmpTerminalID,_tmpCardHolderName,_tmpCardExpiry,_tmpTSI,_tmpTVR,_tmpAID,_tmpAmount,_tmpAdditionalAmount,_tmpLongDateTime,null,_tmpStan,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpError,_tmpVasType,_tmpIuc);
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
  public LiveData<CableTvCardResult> getCableTvResultwithRRN(String RRN) {
    final String _sql = "SELECT * FROM  CableTvEntity  INNER JOIN CardTransactionResult ON CardTransactionResult.id = CableTvEntity.card_id Where CardTransactionResult.RRN = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (RRN == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, RRN);
    }
    return new ComputableLiveData<CableTvCardResult>() {
      private Observer _observer;

      @Override
      protected CableTvCardResult compute() {
        if (_observer == null) {
          _observer = new Observer("CableTvEntity","CardTransactionResult") {
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
          final int _cursorIndexOfVasType = _cursor.getColumnIndexOrThrow("vasType");
          final int _cursorIndexOfError = _cursor.getColumnIndexOrThrow("error");
          final int _cursorIndexOfIuc = _cursor.getColumnIndexOrThrow("iuc");
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
          final CableTvCardResult _result;
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
            final String _tmpVasType;
            _tmpVasType = _cursor.getString(_cursorIndexOfVasType);
            final boolean _tmpError;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfError);
            _tmpError = _tmp != 0;
            final String _tmpIuc;
            _tmpIuc = _cursor.getString(_cursorIndexOfIuc);
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
            _result = new CableTvCardResult(_tmpRRN,_tmpAuthID,_tmpSTAN,_tmpPAN,_tmpTransactionStatus,_tmpResponseCode,_tmpTransactionStatusReason,_tmpAccountType,_tmpBatchNumber,_tmpMerchantID,_tmpTerminalID,_tmpCardHolderName,_tmpCardExpiry,_tmpTSI,_tmpTVR,_tmpAID,_tmpAmount,_tmpAdditionalAmount,_tmpLongDateTime,null,_tmpStan,_tmpWalletId,_tmpMarchantAddress,_tmpMarchantTid,_tmpMarchantName,_tmpMerchantId,_tmpProduct,_tmpTransactionStatusMessage,_tmpVasTid,_tmpTransactionRef,_tmpPaymentmethod,_tmpLogo,_tmpDateTime,_tmpError,_tmpVasType,_tmpIuc);
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
