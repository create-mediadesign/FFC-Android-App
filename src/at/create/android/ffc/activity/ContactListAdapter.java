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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.create.android.ffc.R;
import at.create.android.ffc.domain.Contact;

/**
 * @author Philipp Ullmann
 * Populate contact list item with values.
 */
public final class ContactListAdapter extends ArrayAdapter<Contact> {
    private Context ctx;
    private List<Contact> items;
    
    public ContactListAdapter(Context ctx, int textViewResourceId, List<Contact> items) {
        super(ctx, textViewResourceId, items);
        this.ctx   = ctx;
        this.items = items;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v                 = vi.inflate(R.layout.contact_item,
                                           null);
        }
        
        Contact contact = items.get(position);
        
        if (contact != null) {
            TextView name = (TextView) v.findViewById(R.id.name);
            name.setText(contact.getName());
            v.setId(contact.getId());
        }
        
        return v;
    }
}
