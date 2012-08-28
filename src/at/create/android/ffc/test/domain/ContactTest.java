package at.create.android.ffc.test.domain;

import java.io.IOException;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.Contact;

public final class ContactTest extends AndroidTestCase {
    private Contact contact;
    
    @Override
    protected void setUp() throws Exception {
        contact = new Contact();
        super.setUp();
    }
    
    // getName
    
    public void testGetName() throws IOException {
        contact.setFirstName("Philipp");
        contact.setLastName("Ullmann");
        
        assertEquals("Philipp Ullmann",
                     contact.getName());
    }
    
    // hasEmailAddress
    
    public void testHasEmailAddressIfNull() throws IOException {
        assertFalse(contact.hasEmailAddress());
    }
    
    public void testHasEmailAddressIfEmpty() throws IOException {
        contact.setEmail("");
        assertFalse(contact.hasEmailAddress());
    }
    
    public void testHasEmailAddressWithValue() throws IOException {
        contact.setEmail("pu@create.at");
        assertTrue(contact.hasEmailAddress());
    }
    
    // hasPhoneNumber
    
    public void testHasPhoneNumberIfNull() throws IOException {
        assertFalse(contact.hasPhoneNumber());
    }
    
    public void testHasPhoneNumberIfEmpty() throws IOException {
        contact.setPhone("");
        assertFalse(contact.hasPhoneNumber());
    }
    
    public void testHasPhoneNumberWithValue() throws IOException {
        contact.setPhone("+43111111");
        assertTrue(contact.hasPhoneNumber());
    }
    
    // hasMobilePhoneNumber
    
    public void testHasMobilePhoneNumberIfNull() throws IOException {
        assertFalse(contact.hasMobilePhoneNumber());
    }
    
    public void testHasMobilePhoneNumberIfEmpty() throws IOException {
        contact.setMobil("");
        assertFalse(contact.hasMobilePhoneNumber());
    }
    
    public void testHasMobilePhoneNumberWithValue() throws IOException {
        contact.setMobil("+43111111");
        assertTrue(contact.hasMobilePhoneNumber());
    }
    
    // toXML
    
    public void testXMLStringSerialization() throws IOException, Exception {
        contact.setId(1);
        contact.setFirstName("Philipp");
        contact.setLastName("Ullmann");
        
        assertEquals("<contact><first-name>Philipp</first-name><id>1</id><last-name>Ullmann</last-name></contact>",
                     contact.toXML().replaceAll("\\s", ""));
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
