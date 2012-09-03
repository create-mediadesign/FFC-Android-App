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

import java.io.IOException;

import roboguice.inject.InjectView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import at.create.android.ffc.R;
import at.create.android.ffc.R.id;
import at.create.android.ffc.R.menu;
import at.create.android.ffc.domain.Contact;

/**
 * @author Philipp Ullmann
 * Displays the details of a contact.
 */
public final class ContactDetailsActivity extends RoboSherlockActivity {
    protected static final String TAG = ContactDetailsActivity.class.getSimpleName();
    private Contact               contact;
    @InjectView(R.id.name)
    private TextView              nameView;
    @InjectView(R.id.title)
    private TextView              title;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);
        buildContact();
        populateViews();
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getBaseContext(),
                                   ContactListActivity.class);
        startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu optionMenu) {
        getSupportMenuInflater().inflate(menu.contact_details,
                                         optionMenu);
        MenuItem emailItem       = optionMenu.findItem(R.id.email);
        MenuItem phoneItem       = optionMenu.findItem(R.id.phone);
        MenuItem phoneMobileItem = optionMenu.findItem(R.id.phone_mobile);
        
        if (contact.hasEmailAddress()) {
            emailItem.setVisible(true);
        } else {
            emailItem.setVisible(false);
        }
        
        if (contact.hasPhoneNumber()) {
            phoneItem.setVisible(true);
        } else {
            phoneItem.setVisible(false);
        }
        
        if (contact.hasMobilePhoneNumber()) {
            phoneMobileItem.setVisible(true);
        } else {
            phoneMobileItem.setVisible(false);
        }
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case id.email:
                sendEmail();
                return true;
            case id.phone:
                makePhoneCall(contact.getPhone());
                return true;
            case id.phone_mobile:
                makePhoneCall(contact.getMobil());
                return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private void buildContact() {
        try {
            contact = Contact.fromXML(getIntent().getExtras().getString("contact"));
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
    
    private void populateViews() {
        nameView.setText(contact.getName());
        title.setText(contact.getTitle());
    }
    
    private void sendEmail() {
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("text/plain");
        mailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                            new String[] { contact.getEmail() });
        Intent mailer = Intent.createChooser(mailIntent,
                                             null);
        startActivity(mailer);
    }
    
    private void makePhoneCall(String number) {
        Intent dialIntent   = new Intent(Intent.ACTION_DIAL,
                                         Uri.parse("tel:" + number.trim()));
        startActivity(dialIntent);
    }
}
