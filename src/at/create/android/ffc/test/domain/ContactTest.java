package at.create.android.ffc.test.domain;

import java.io.IOException;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.Contact;
import at.create.android.ffc.test.factory.ContactFactory;

public final class ContactTest extends AndroidTestCase {
    private ContactFactory cf;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        cf = new ContactFactory();
    }
    
    // getName
    
    public void testGetName() throws IOException {
        assertEquals("Philipp Ullmann",
                     cf.buildWithFirstNameAndLastName("Philipp", "Ullmann").getName());
    }
    
    // hasEmailAddress
    
    public void testHasEmailAddressIfNull() throws IOException {
        Contact contact = cf.buildWithEmail(null);
        assertFalse(contact.hasEmailAddress());
    }
    
    public void testHasEmailAddressIfEmpty() throws IOException {
        Contact contact = cf.buildWithEmail("");
        assertFalse(contact.hasEmailAddress());
    }
    
    public void testHasEmailAddressWithValue() throws IOException {
        Contact contact = cf.buildWithEmail("pu@create.at");
        assertTrue(contact.hasEmailAddress());
    }
    
    // hasPhoneNumber
    
    public void testHasPhoneNumberIfNull() throws IOException {
        Contact contact = cf.buildWithPhone(null);
        assertFalse(contact.hasPhoneNumber());
    }
    
    public void testHasPhoneNumberIfEmpty() throws IOException {
        Contact contact = cf.buildWithPhone("");
        assertFalse(contact.hasPhoneNumber());
    }
    
    public void testHasPhoneNumberWithValue() throws IOException {
        Contact contact = cf.buildWithPhone("+43111111");
        assertTrue(contact.hasPhoneNumber());
    }
    
    // hasMobilePhoneNumber
    
    public void testHasMobilePhoneNumberIfNull() throws IOException {
        Contact contact = cf.buildWithMobile(null);
        assertFalse(contact.hasMobilePhoneNumber());
    }
    
    public void testHasMobilePhoneNumberIfEmpty() throws IOException {
        Contact contact = cf.buildWithMobile("");
        assertFalse(contact.hasMobilePhoneNumber());
    }
    
    public void testHasMobilePhoneNumberWithValue() throws IOException {
        Contact contact = cf.buildWithMobile("+43111111");
        assertTrue(contact.hasMobilePhoneNumber());
    }
    
    // toXML
    
    public void testXMLStringSerialization() throws IOException, Exception {
        assertEquals("<contact><first-name>Philipp</first-name><id>1</id><last-name>Ullmann</last-name></contact>",
                     cf.buildWithFirstNameAndLastName("Philipp", "Ullmann").toXML().replaceAll("\\s", ""));
    }
    
    // fromXML
    
    public void testXMLDeserialization() throws IOException {
        Contact contact = Contact.fromXML("<contact><first-name>Philipp</first-name><id>1</id><last-name>Ullmann</last-name></contact>");
        
        assertEquals(1,
                     (int) contact.getId());
        assertEquals("Philipp",
                     contact.getFirstName());
        assertEquals("Ullmann",
                     contact.getLastName());
    }
}
