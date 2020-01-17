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
public class EodDoa_Impl implements EodDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEodData;

  public EodDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEodData = new EntityInsertionAdapter<EodData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `EodData`(`id`,`transactionRef`,`transactionType`,`type`,`specificTransaction`,`dateTime`,`responseCode`,`amount`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EodData value) {
        stmt.bindLong(1, value.getId());
        if (value.getTransactionRef() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTransactionRef());
        }
        if (value.getTransactionType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTransactionType());
        }
        if (value.getType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getType());
        }
        if (value.getSpecificTransaction() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSpecificTransaction());
        }
        stmt.bindLong(6, value.getDateTime());
        if (value.getResponseCode() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getResponseCode());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAmount());
        }
      }
    };
  }

  @Override
  public Long saveEodData(EodData eodData) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfEodData.insertAndReturnId(eodData);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<EodData>> getAllEodTransactions() {
    final String _sql = "Select * From EodData ORDER BY dateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<EodData>>() {
      private Observer _observer;

      @Override
      protected List<EodData> compute() {
        if (_observer == null) {
          _observer = new Observer("EodData") {
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
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfSpecificTransaction = _cursor.getColumnIndexOrThrow("specificTransaction");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfResponseCode = _cursor.getColumnIndexOrThrow("responseCode");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final List<EodData> _result = new ArrayList<EodData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EodData _item;
            _item = new EodData();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            _item.setTransactionRef(_tmpTransactionRef);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item.setTransactionType(_tmpTransactionType);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpSpecificTransaction;
            _tmpSpecificTransaction = _cursor.getString(_cursorIndexOfSpecificTransaction);
            _item.setSpecificTransaction(_tmpSpecificTransaction);
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            _item.setDateTime(_tmpDateTime);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _item.setResponseCode(_tmpResponseCode);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            _item.setAmount(_tmpAmount);
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
  public LiveData<EodData> getEod(int id) {
    final String _sql = "SELECT * FROM EodData WHERE id LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<EodData>() {
      private Observer _observer;

      @Override
      protected EodData compute() {
        if (_observer == null) {
          _observer = new Observer("EodData") {
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
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfSpecificTransaction = _cursor.getColumnIndexOrThrow("specificTransaction");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfResponseCode = _cursor.getColumnIndexOrThrow("responseCode");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final EodData _result;
          if(_cursor.moveToFirst()) {
            _result = new EodData();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result.setId(_tmpId);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            _result.setTransactionRef(_tmpTransactionRef);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _result.setTransactionType(_tmpTransactionType);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _result.setType(_tmpType);
            final String _tmpSpecificTransaction;
            _tmpSpecificTransaction = _cursor.getString(_cursorIndexOfSpecificTransaction);
            _result.setSpecificTransaction(_tmpSpecificTransaction);
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            _result.setDateTime(_tmpDateTime);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _result.setResponseCode(_tmpResponseCode);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            _result.setAmount(_tmpAmount);
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
  public LiveData<List<EodData>> findAllEod() {
    final String _sql = "SELECT * FROM EodData ORDER BY dateTime DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<EodData>>() {
      private Observer _observer;

      @Override
      protected List<EodData> compute() {
        if (_observer == null) {
          _observer = new Observer("EodData") {
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
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfSpecificTransaction = _cursor.getColumnIndexOrThrow("specificTransaction");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfResponseCode = _cursor.getColumnIndexOrThrow("responseCode");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final List<EodData> _result = new ArrayList<EodData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EodData _item;
            _item = new EodData();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            _item.setTransactionRef(_tmpTransactionRef);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item.setTransactionType(_tmpTransactionType);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpSpecificTransaction;
            _tmpSpecificTransaction = _cursor.getString(_cursorIndexOfSpecificTransaction);
            _item.setSpecificTransaction(_tmpSpecificTransaction);
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            _item.setDateTime(_tmpDateTime);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _item.setResponseCode(_tmpResponseCode);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            _item.setAmount(_tmpAmount);
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
  public LiveData<List<EodData>> findDeclinedEodInDateRange(Long minDate, Long maxDate,
      String responsecode) {
    final String _sql = "SELECT * FROM EodData WHERE responseCode !=? AND dateTime >= ? AND dateTime <= ? ORDER BY dateTime DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (responsecode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, responsecode);
    }
    _argIndex = 2;
    if (minDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, minDate);
    }
    _argIndex = 3;
    if (maxDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, maxDate);
    }
    return new ComputableLiveData<List<EodData>>() {
      private Observer _observer;

      @Override
      protected List<EodData> compute() {
        if (_observer == null) {
          _observer = new Observer("EodData") {
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
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfSpecificTransaction = _cursor.getColumnIndexOrThrow("specificTransaction");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfResponseCode = _cursor.getColumnIndexOrThrow("responseCode");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final List<EodData> _result = new ArrayList<EodData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EodData _item;
            _item = new EodData();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            _item.setTransactionRef(_tmpTransactionRef);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item.setTransactionType(_tmpTransactionType);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpSpecificTransaction;
            _tmpSpecificTransaction = _cursor.getString(_cursorIndexOfSpecificTransaction);
            _item.setSpecificTransaction(_tmpSpecificTransaction);
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            _item.setDateTime(_tmpDateTime);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _item.setResponseCode(_tmpResponseCode);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            _item.setAmount(_tmpAmount);
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
  public LiveData<List<EodData>> findApprovedEodInDateRange(Long minDate, Long maxDate,
      String responsecode) {
    final String _sql = "SELECT * FROM EodData WHERE responseCode =? AND dateTime >= ? AND dateTime <= ? ORDER BY dateTime DESC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (responsecode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, responsecode);
    }
    _argIndex = 2;
    if (minDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, minDate);
    }
    _argIndex = 3;
    if (maxDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, maxDate);
    }
    return new ComputableLiveData<List<EodData>>() {
      private Observer _observer;

      @Override
      protected List<EodData> compute() {
        if (_observer == null) {
          _observer = new Observer("EodData") {
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
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfSpecificTransaction = _cursor.getColumnIndexOrThrow("specificTransaction");
          final int _cursorIndexOfDateTime = _cursor.getColumnIndexOrThrow("dateTime");
          final int _cursorIndexOfResponseCode = _cursor.getColumnIndexOrThrow("responseCode");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final List<EodData> _result = new ArrayList<EodData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EodData _item;
            _item = new EodData();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            _item.setTransactionRef(_tmpTransactionRef);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item.setTransactionType(_tmpTransactionType);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpSpecificTransaction;
            _tmpSpecificTransaction = _cursor.getString(_cursorIndexOfSpecificTransaction);
            _item.setSpecificTransaction(_tmpSpecificTransaction);
            final long _tmpDateTime;
            _tmpDateTime = _cursor.getLong(_cursorIndexOfDateTime);
            _item.setDateTime(_tmpDateTime);
            final String _tmpResponseCode;
            _tmpResponseCode = _cursor.getString(_cursorIndexOfResponseCode);
            _item.setResponseCode(_tmpResponseCode);
            final String _tmpAmount;
            _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            _item.setAmount(_tmpAmount);
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
