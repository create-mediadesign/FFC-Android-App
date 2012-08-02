package at.create.android.ffc.domain;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


/**
 * @author Philipp Ullmann
 * Holds a list of contacts.
 */
@Root(name = "contacts")
public final class ContactList {
    @ElementList(inline = true)
    private List<Contact> contacts;
    
    /**
     * @return the contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }
}
