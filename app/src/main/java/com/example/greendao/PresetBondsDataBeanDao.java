package com.example.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.stockx.bean.PresetBondsDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PRESET_BONDS_DATA_BEAN".
*/
public class PresetBondsDataBeanDao extends AbstractDao<PresetBondsDataBean, Long> {

    public static final String TABLENAME = "PRESET_BONDS_DATA_BEAN";

    /**
     * Properties of entity PresetBondsDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AccountId = new Property(1, Long.class, "accountId", false, "ACCOUNT_ID");
        public final static Property StockName = new Property(2, String.class, "stockName", false, "STOCK_NAME");
        public final static Property CostPrice = new Property(3, double.class, "costPrice", false, "COST_PRICE");
        public final static Property StopLossPrice = new Property(4, double.class, "stopLossPrice", false, "STOP_LOSS_PRICE");
        public final static Property TargetPrice = new Property(5, double.class, "targetPrice", false, "TARGET_PRICE");
        public final static Property BondsNum = new Property(6, int.class, "bondsNum", false, "BONDS_NUM");
    }


    public PresetBondsDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public PresetBondsDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PRESET_BONDS_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ACCOUNT_ID\" INTEGER," + // 1: accountId
                "\"STOCK_NAME\" TEXT," + // 2: stockName
                "\"COST_PRICE\" REAL NOT NULL ," + // 3: costPrice
                "\"STOP_LOSS_PRICE\" REAL NOT NULL ," + // 4: stopLossPrice
                "\"TARGET_PRICE\" REAL NOT NULL ," + // 5: targetPrice
                "\"BONDS_NUM\" INTEGER NOT NULL );"); // 6: bondsNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PRESET_BONDS_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PresetBondsDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long accountId = entity.getAccountId();
        if (accountId != null) {
            stmt.bindLong(2, accountId);
        }
 
        String stockName = entity.getStockName();
        if (stockName != null) {
            stmt.bindString(3, stockName);
        }
        stmt.bindDouble(4, entity.getCostPrice());
        stmt.bindDouble(5, entity.getStopLossPrice());
        stmt.bindDouble(6, entity.getTargetPrice());
        stmt.bindLong(7, entity.getBondsNum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PresetBondsDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long accountId = entity.getAccountId();
        if (accountId != null) {
            stmt.bindLong(2, accountId);
        }
 
        String stockName = entity.getStockName();
        if (stockName != null) {
            stmt.bindString(3, stockName);
        }
        stmt.bindDouble(4, entity.getCostPrice());
        stmt.bindDouble(5, entity.getStopLossPrice());
        stmt.bindDouble(6, entity.getTargetPrice());
        stmt.bindLong(7, entity.getBondsNum());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PresetBondsDataBean readEntity(Cursor cursor, int offset) {
        PresetBondsDataBean entity = new PresetBondsDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // accountId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // stockName
            cursor.getDouble(offset + 3), // costPrice
            cursor.getDouble(offset + 4), // stopLossPrice
            cursor.getDouble(offset + 5), // targetPrice
            cursor.getInt(offset + 6) // bondsNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PresetBondsDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAccountId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setStockName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCostPrice(cursor.getDouble(offset + 3));
        entity.setStopLossPrice(cursor.getDouble(offset + 4));
        entity.setTargetPrice(cursor.getDouble(offset + 5));
        entity.setBondsNum(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PresetBondsDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PresetBondsDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PresetBondsDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
