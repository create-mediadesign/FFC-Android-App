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
package at.create.android.ffc.activity;

import java.util.List;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockListActivity;

import roboguice.util.RoboAsyncTask;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import at.create.android.ffc.R;
import at.create.android.ffc.R.menu;
import at.create.android.ffc.dao.ContactDAO;
import at.create.android.ffc.domain.Contact;
import at.create.android.ffc.domain.Setting;
import at.create.android.ffc.http.CookiePreserveHttpRequestInterceptor;
import at.create.android.ffc.http.FetchContacts;

/**
 * @author Philipp Ullmann
 * Listing of contacts.
 */
public final class ContactListActivity extends RoboSherlockListActivity {
    protected static final String TAG           = ContactListActivity.class.getSimpleName();
    private ContactDAO            contactDAO    = null;
    private List<Contact>         contacts      = null;
    private AsyncActivityImpl     asyncActivity = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        asyncActivity = new AsyncActivityImpl(this);
        contactDAO    = new ContactDAO(this);
        contactDAO.open();
    }
    
    @Override
    protected void onDestroy() {
        if (contactDAO != null)
            contactDAO.close();
        
        super.onDestroy();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        getSupportMenuInflater().inflate(menu.contact_list,
                                         optionMenu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                CookiePreserveHttpRequestInterceptor.getInstance().clear();
                contactDAO.deleteAll();
                Intent intent = new Intent(this,
                                           MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.sync:
                contactSync();
                return true;
            case R.id.search:
                onSearchRequested();
                return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getBaseContext(),
                                   ContactDetailsActivity.class);
        try {
            intent.putExtra("contact",
                            contacts.get(position).toXML());
        } catch (Exception e) {
            Log.e(TAG,
                  e.getMessage(),
                  e);
        }
        startActivity(intent);
    }
    
    @Override
    public void onBackPressed() {}
    
    @Override
    public void onStart() {
        super.onStart();
        
        if (getIntent().getAction() != null &&
            getIntent().getAction().equals(Intent.ACTION_SEARCH)) {
            contactSearch(getIntent().getStringExtra(SearchManager.QUERY));
            refreshList();
        } else {
            if (contactDAO.count() > 0) {
                contactLoadAllFromDB();
                refreshList();
            } else {
                contactSync();
            }
        }
    }
    
    private void refreshList() {
        if (contacts == null)
            return;
        
        ContactListAdapter adapter = new ContactListAdapter(this,
                                                            R.layout.contact_item,
                                                            contacts);
        setListAdapter(adapter);
    }
    
    private void contactLoadAllFromDB() {
        contacts = contactDAO.all();
    }
    
    private void contactSearch(String searchTerm) {
        contacts = contactDAO.findAllByFirstNameAndLastName(searchTerm);
    }
    
    private void contactSync() {
        asyncActivity.showLoadingProgressDialog();
        
        RoboAsyncTask<List<Contact>> loadContactsTask = new RoboAsyncTask<List<Contact>>(this) {
            @Override
            public List<Contact> call() throws Exception {
                SharedPreferences setting = getSharedPreferences(Setting.SHARED_PREF, 0);
                FetchContacts fetcher     = new FetchContacts(setting.getString("baseUri", ""));
                fetcher.fetch();
                return fetcher.getContacts();
            }
            
            @Override
            protected void onException(Exception e) throws RuntimeException {
                asyncActivity.dismissProgressDialog();
                Log.d(TAG,
                      "Syncing of contacts failed",
                      e);
                asyncActivity.showAlert(getString(R.string.syncing_of_contacts_failed));
            }
            
            @Override
            protected void onSuccess(List<Contact> contacts) throws Exception {
                contactDAO.deleteAll();
                contactDAO.save(contacts);
                asyncActivity.dismissProgressDialog();
                contactLoadAllFromDB();
                refreshList();
            }
        };
        
        loadContactsTask.execute();
    }
}
