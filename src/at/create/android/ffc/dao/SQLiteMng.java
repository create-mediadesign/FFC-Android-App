package at.create.android.ffc.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Philipp Ullmann
 * A helper class to manage database creation and version management.
 */
public final class SQLiteMng extends SQLiteOpenHelper {
    public static final String DB_NAME  = "ffc.db";
    public static final int DB_VERSION  = 1;
    
    public SQLiteMng(Context ctx) {
        super(ctx,
              DB_NAME,
              null,
              DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactDAO.tableCreateStmt());
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContactDAO.tableDeleteStmt());
        onCreate(db);
    }
}
