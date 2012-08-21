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
                     cf.build("Philipp", "Ullmann").getName());
    }
    
    // toXML
    
    public void testXMLStringSerialization() throws IOException, Exception {
        assertEquals("<contact><first-name>Philipp</first-name><id>1</id><last-name>Ullmann</last-name></contact>",
                     cf.build("Philipp", "Ullmann").toXML().replaceAll("\\s", ""));
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
