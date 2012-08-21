package at.create.android.ffc.activity;

import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import at.create.android.ffc.R;
import at.create.android.ffc.domain.Contact;
import at.create.android.ffc.domain.Setting;
import at.create.android.ffc.http.CookiePreserveHttpRequestInterceptor;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_list, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.logout:
            CookiePreserveHttpRequestInterceptor.getInstance().clear();
            Intent intent = new Intent(this,
                                       MainActivity.class);
            startActivity(intent);
            break;
        default:
            break;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onBackPressed() {}
    
    @Override
    public void onStart() {
        super.onStart();
        TextView noItemsFound = (TextView) findViewById(R.id.empty);
        noItemsFound.setVisibility(View.INVISIBLE);
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
                FetchContacts fetcher     = new FetchContacts(setting.getString("baseUri", ""));
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
