package at.create.android.ffc.dao;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import at.create.android.ffc.domain.Contact;

/**
 * @author Philipp Ullmann
 * Database access to "contacts" table.
 */
public final class ContactDAO extends BaseDAO {
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
        
        stmt.append("CREATE TABLE ");
        stmt.append(TABLE_NAME).append(" (");
        stmt.append(BaseColumns._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stmt.append(TITLE).append(stringNull);
        stmt.append(FIRST_NAME).append(stringNotNull);
        stmt.append(LAST_NAME).append(stringNotNull);
        stmt.append(DEPARTMENT).append(stringNull);
        stmt.append(EMAIL).append(stringNull);
        stmt.append(PHONE).append(stringNull);
        stmt.append(MOBILE).append(stringNull);
        stmt.append(FAX).append(stringNull);
        stmt.append(BORN_ON).append(" DATETIME, ");
        stmt.append(BACKGROUND_INFO).append(" TEXT, ");
        stmt.append(BLOG).append(stringNull);
        stmt.append(LINKEDIN).append(stringNull);
        stmt.append(FACEBOOK).append(stringNull);
        stmt.append(TWITTER).append(stringNull);
        stmt.append(SKYPE).append(stringNull);
        
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
    
    private Contact cursorToContact(Cursor cursor) {
        Contact contact = new Contact();
        
        contact.setId(cursor.getInt(0));
        contact.setTitle(cursor.getString(1));
        contact.setFirstName(cursor.getString(2));
        contact.setLastName(cursor.getString(3));
        contact.setDepartment(cursor.getString(4));
        contact.setEmail(cursor.getString(5));
        contact.setPhone(cursor.getString(6));
        contact.setMobil(cursor.getString(7));
        contact.setFax(cursor.getString(8));
        
        return contact;
    }
}
