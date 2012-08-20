package at.create.android.ffc.activity;

import java.util.List;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import at.create.android.ffc.R;
import at.create.android.ffc.domain.Contact;
import at.create.android.ffc.domain.Setting;
import at.create.android.ffc.http.FetchContacts;

/**
 * @author Philipp Ullmann
 * Listing of contacts.
 */
public final class ContactListActivity extends AbstractAsyncListActivity {
    protected static final String TAG = ContactListActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        new DownloadContactTask().execute();
    }
    
    private void refreshStates(List<Contact> contacts) {
        if (contacts == null) {
            return;
        }
        
        ContactListAdapter adapter = new ContactListAdapter(this,
                                                            R.layout.contact_item,
                                                            contacts);
        setListAdapter(adapter);
    }
    
    private class DownloadContactTask extends AsyncTask<Void, Void, List<Contact>> {
        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        
        @Override
        protected List<Contact> doInBackground(Void... params) {
            try {
                SharedPreferences setting = getSharedPreferences(Setting.SHARED_PREF, 0);
                FetchContacts fetcher     = new FetchContacts(setting.getString("url", ""));
                fetcher.fetch();
                return fetcher.getContacts();
            } catch (Exception e) {
                Log.e(TAG,
                      e.getMessage(),
                      e);
            }
            
            return null;
        }
        
        @Override
        protected void onPostExecute(List<Contact> result) {
            dismissProgressDialog();
            refreshStates(result);
        }
    }
}
