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
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class AirtimeDoa_Impl implements AirtimeDoa {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAirtimeEntity;

  public AirtimeDoa_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAirtimeEntity = new EntityInsertionAdapter<AirtimeEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `AirtimeEntity`(`id`,`card_id`,`transactionRef`,`recepiant_phone`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AirtimeEntity value) {
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
        if (value.getTransactionRef() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTransactionRef());
        }
        if (value.getRecepiant_phone() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRecepiant_phone());
        }
      }
    };
  }

  @Override
  public long saveAirtimeData(AirtimeEntity vasTransactionResult) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfAirtimeEntity.insertAndReturnId(vasTransactionResult);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<AirtimeEntity> getAllAirtimeData() {
    final String _sql = "Select * From AirtimeEntity ORDER BY card_id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCardrowId = _cursor.getColumnIndexOrThrow("card_id");
      final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
      final int _cursorIndexOfRecepiantPhone = _cursor.getColumnIndexOrThrow("recepiant_phone");
      final List<AirtimeEntity> _result = new ArrayList<AirtimeEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AirtimeEntity _item;
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
        final String _tmpTransactionRef;
        _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
        final String _tmpRecepiant_phone;
        _tmpRecepiant_phone = _cursor.getString(_cursorIndexOfRecepiantPhone);
        _item = new AirtimeEntity(_tmpId,_tmpCardrowId,_tmpTransactionRef,_tmpRecepiant_phone);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<AirtimeEntity> getAirtimePurchased(int id) {
    final String _sql = "SELECT * FROM AirtimeEntity WHERE transactionRef = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<AirtimeEntity>() {
      private Observer _observer;

      @Override
      protected AirtimeEntity compute() {
        if (_observer == null) {
          _observer = new Observer("AirtimeEntity") {
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
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfRecepiantPhone = _cursor.getColumnIndexOrThrow("recepiant_phone");
          final AirtimeEntity _result;
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
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            final String _tmpRecepiant_phone;
            _tmpRecepiant_phone = _cursor.getString(_cursorIndexOfRecepiantPhone);
            _result = new AirtimeEntity(_tmpId,_tmpCardrowId,_tmpTransactionRef,_tmpRecepiant_phone);
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
  public LiveData<AirtimeEntity> getAirtimewithrequestId(String transactionRef) {
    final String _sql = "SELECT * FROM  AirtimeEntity  WHERE  AirtimeEntity.transactionRef LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (transactionRef == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, transactionRef);
    }
    return new ComputableLiveData<AirtimeEntity>() {
      private Observer _observer;

      @Override
      protected AirtimeEntity compute() {
        if (_observer == null) {
          _observer = new Observer("AirtimeEntity") {
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
          final int _cursorIndexOfTransactionRef = _cursor.getColumnIndexOrThrow("transactionRef");
          final int _cursorIndexOfRecepiantPhone = _cursor.getColumnIndexOrThrow("recepiant_phone");
          final AirtimeEntity _result;
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
            final String _tmpTransactionRef;
            _tmpTransactionRef = _cursor.getString(_cursorIndexOfTransactionRef);
            final String _tmpRecepiant_phone;
            _tmpRecepiant_phone = _cursor.getString(_cursorIndexOfRecepiantPhone);
            _result = new AirtimeEntity(_tmpId,_tmpCardrowId,_tmpTransactionRef,_tmpRecepiant_phone);
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
