/*
 * Fat Free CRM Android App
 * Copyright 2012 create mediadesign GmbH
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package at.create.android.ffc.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

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
        if (value != null)
            insert.bindString(pos, value);
        else
            insert.bindNull(pos);
    }
    
    protected abstract String tableName();
}
