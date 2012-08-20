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
