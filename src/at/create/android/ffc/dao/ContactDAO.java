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

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import android.util.Log;
import at.create.android.ffc.domain.Contact;

/**
 * @author Philipp Ullmann
 * Database access to "contacts" table.
 */
public final class ContactDAO extends BaseDAO {
    private static final String  TAG            = ContactDAO.class.getSimpleName();
    private static final String TABLE_NAME      = "contacts";
    private static final String TITLE           = "title";
    private static final String FIRST_NAME      = "first_name";
    private static final String LAST_NAME       = "last_name";
    private static final String DEPARTMENT      = "department";
    private static final String EMAIL           = "email";
    private static final String PHONE           = "phone";
    private static final String MOBILE          = "mobile";
    private static final String FAX             = "fax";
    private static final String BORN_ON         = "born_on";
    private static final String BACKGROUND_INFO = "background_info";
    private static final String BLOG            = "blog";
    private static final String LINKEDIN        = "linkedin";
    private static final String FACEBOOK        = "facebook";
    private static final String TWITTER         = "twitter";
    private static final String SKYPE           = "skype";
    
    public ContactDAO(Context ctx) {
        super(ctx);
    }
    
    /**
     * @return SQL statement to create the "contacts" table.
     */
    public static String tableCreateStmt() {
        StringBuilder stmt      = new StringBuilder();
        String stringNotNull    = " STRING NOT NULL, ";
        String stringNull       = " STRING, ";
        
        stmt.append("CREATE TABLE ").
        append(TABLE_NAME).append(" (").
        append(BaseColumns._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ").
        append(TITLE).append(stringNull).
        append(FIRST_NAME).append(stringNotNull).
        append(LAST_NAME).append(stringNotNull).
        append(DEPARTMENT).append(stringNull).
        append(EMAIL).append(stringNull).
        append(PHONE).append(stringNull).
        append(MOBILE).append(stringNull).
        append(FAX).append(stringNull).
        append(BORN_ON).append(" DATE, ").
        append(BACKGROUND_INFO).append(" TEXT, ").
        append(BLOG).append(stringNull).
        append(LINKEDIN).append(stringNull).
        append(FACEBOOK).append(stringNull).
        append(TWITTER).append(stringNull).
        append(SKYPE).append(" STRING)");
        
        return stmt.toString();
    }
    
    /**
     * @return SQL statement to delete the "contacts" table.
     */
    public static String tableDeleteStmt() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
    
    @Override
    protected String tableName() {
        return TABLE_NAME;
    }
    
    /**
     * @return all contacts sorted by first name ascending.
     */
    public List<Contact> all() {
        Cursor cursor = db.query(TABLE_NAME,
                                 columnNames(),
                                 null,
                                 null,
                                 null,
                                 null,
                                 FIRST_NAME);
        
        return cursorToContacts(cursor);
    }
    
    /**
     * Saves contacts.
     * @param contacts
     * @return the amount of rows created
     */
    public int save(List<Contact> contacts) {
        SQLiteStatement insert = insertStmt();
        
        db.beginTransaction();
        
        for (Contact contact : contacts) {
            bindNullString(insert, 1, contact.getTitle());
            insert.bindString(2, contact.getFirstName());
            insert.bindString(3, contact.getLastName());
            bindNullString(insert, 4, contact.getDepartment());
            bindNullString(insert, 5, contact.getEmail());
            bindNullString(insert, 6, contact.getPhone());
            bindNullString(insert, 7, contact.getMobil());
            bindNullString(insert, 8, contact.getFax());
            
            if (contact.getBornOn() != null)
                insert.bindString(9, contact.getBornOn().toString());
            else
                insert.bindNull(9);
            
            bindNullString(insert, 10, contact.getBackgroundInfo());
            bindNullString(insert, 11, contact.getBlog());
            bindNullString(insert, 12, contact.getLinkedin());
            bindNullString(insert, 13, contact.getFacebook());
            bindNullString(insert, 14, contact.getTwitter());
            bindNullString(insert, 15, contact.getSkype());
            
            try {
                insert.execute();
            } catch (SQLException e) {
                Log.d(TAG, "Saving of contact \"" + contact.getName() + "\" failed");
            }
        }
        
        db.setTransactionSuccessful();
        db.endTransaction();
        return contacts.size();
    }
    
    private Contact cursorToContact(Cursor cursor) {
        Contact contact = new Contact();
        
        contact.setId(cursor.getInt(0));
        
        if (!cursor.isNull(1))
            contact.setTitle(cursor.getString(1));
        
        contact.setFirstName(cursor.getString(2));
        contact.setLastName(cursor.getString(3));
        
        if (!cursor.isNull(4))
            contact.setDepartment(cursor.getString(4));
        if (!cursor.isNull(5))
            contact.setEmail(cursor.getString(5));
        if (!cursor.isNull(6))
            contact.setPhone(cursor.getString(6));
        if (!cursor.isNull(7))
            contact.setMobil(cursor.getString(7));
        if (!cursor.isNull(8))
            contact.setFax(cursor.getString(8));
        if (!cursor.isNull(9))
            contact.setBornOn(cursor.getString(9));
        if (!cursor.isNull(10))
            contact.setBackgroundInfo(cursor.getString(10));
        if (!cursor.isNull(11))
            contact.setBlog(cursor.getString(11));
        if (!cursor.isNull(12))
            contact.setLinkedin(cursor.getString(12));
        if (!cursor.isNull(13))
            contact.setFacebook(cursor.getString(13));
        if (!cursor.isNull(14))
            contact.setTwitter(cursor.getString(14));
        if (!cursor.isNull(15))
            contact.setSkype(cursor.getString(15));
        
        return contact;
    }
    
    private List<Contact> cursorToContacts(Cursor cursor) {
        ArrayList<Contact> contacts = new ArrayList<Contact>(0);
        
        if (cursor.getCount() > 0) {
            contacts = new ArrayList<Contact>(cursor.getCount());
            
            while (cursor.moveToNext())
                contacts.add(cursorToContact(cursor));
        }
        
        return contacts;
    }
    
    private String[] columnNames() {
        return new String[] { BaseColumns._ID,
                              TITLE,
                              FIRST_NAME,
                              LAST_NAME,
                              DEPARTMENT,
                              EMAIL,
                              PHONE,
                              MOBILE,
                              FAX,
                              BORN_ON,
                              BACKGROUND_INFO,
                              BLOG,
                              LINKEDIN,
                              FACEBOOK,
                              TWITTER,
                              SKYPE };
    }
    
    private SQLiteStatement insertStmt() {
        StringBuilder stmt = new StringBuilder();
        String delimiter   = ", ";
        
        stmt.append("INSERT INTO ").
        append(TABLE_NAME).append(" (").
        append(TITLE).append(delimiter).
        append(FIRST_NAME).append(delimiter).
        append(LAST_NAME).append(delimiter).
        append(DEPARTMENT).append(delimiter).
        append(EMAIL).append(delimiter).
        append(PHONE).append(delimiter).
        append(MOBILE).append(delimiter).
        append(FAX).append(delimiter).
        append(BORN_ON).append(delimiter).
        append(BACKGROUND_INFO).append(delimiter).
        append(BLOG).append(delimiter).
        append(LINKEDIN).append(delimiter).
        append(FACEBOOK).append(delimiter).
        append(TWITTER).append(delimiter).
        append(SKYPE).append(") ").
        append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        return db.compileStatement(stmt.toString());
    }
}
