package at.create.android.ffc.activity;

import android.app.ListActivity;
import android.os.Bundle;
import at.create.android.ffc.R;

/**
 * @author Philipp Ullmann
 * Listing of contacts.
 */
public final class ContactListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
    }
}
