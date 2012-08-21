package at.create.android.ffc.activity;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import at.create.android.ffc.R;
import at.create.android.ffc.domain.Contact;

/**
 * @author Philipp Ullmann
 * Displays the details of a contact.
 */
public final class ContactDetailsActivity extends Activity {
    protected static final String TAG = ContactDetailsActivity.class.getSimpleName();
    private Contact contact;
    private TextView nameView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);
        findViews();
        buildContact();
        populateViews();
    }
    
    private void findViews() {
        nameView = (TextView) findViewById(R.id.name);
    }
    
    private void buildContact() {
        try {
            contact = Contact.fromXML(getIntent().getExtras().getString("contact"));
        } catch (IOException e) {
            Log.e(TAG,
                  e.getMessage(),
                  e);
        }
    }
    
    private void populateViews() {
        nameView.setText(contact.getName());
    }
}
