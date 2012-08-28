package at.create.android.ffc.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteStatement;
import android.text.format.Time;
import android.util.TimeFormatException;

/**
 * @author Philipp Ullmann
 * Base class for all dao's.
 */
public abstract class BaseDAO {
    protected SQLiteDatabase db = null;
    protected SQLiteMng dbMng   = null;
    
    /**
     * Builds an SQLiteMng instance from the given context.
     * @param ctx Context
     */
    public BaseDAO(Context ctx) {
        dbMng = new SQLiteMng(ctx);
    }
    
    /**
     * Create or open a database that will be used for reading and writing.
     */
    public void open() {
        db = dbMng.getWritableDatabase();
    }
    
    /**
     * Close database object.
     */
    public void close() {
        dbMng.close();
    }
    
    /**
     * @return Max updatedAt DateTime value. If no entries exist Jan 1, 1970 is returned.
     */
    public Time lastUpdatedAt() {
        String sql = "SELECT MAX(updatedAt) FROM " + tableName();
        SQLiteStatement statement = db.compileStatement(sql);
        Time time = new Time();
        
        try {
            String value = statement.simpleQueryForString();
            
            if (value != null) {
                time.parse3339(value);
            }
        } catch (TimeFormatException e) {
        } catch (SQLiteDoneException e) {
        }
        
        return time;
    }
    
    /**
     * Deletes all rows.
     * @return the number of rows affected
     */
    public int deleteAll() {
        int rows = db.delete(tableName(), null, null);
        return rows;
    }
    
    /**
     * @return The amount of rows.
     */
    public int count() {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tableName(),
                                    null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    
    protected void bindNullString(SQLiteStatement insert, int pos, String value) {
        if (value != null) {
            insert.bindString(pos, value);
        } else {
            insert.bindNull(pos);
        }
    }
    
    protected abstract String tableName();
}
