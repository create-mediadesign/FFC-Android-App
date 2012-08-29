package at.create.android.ffc.test.domain;

import java.io.IOException;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.Contact;

public final class ContactXMLConversionTest extends AndroidTestCase {
    private Contact contact;
    private final static String XML = "<contact>" +
                                          "<background-info />" +
                                          "<blog/>" +
                                          "<born-on>2012-08-29</born-on>" +
                                          "<department />" +
                                          "<email>Ca.Bauer@silhouette.com</email>" +
                                          "<facebook />" +
                                          "<fax>+43 (0) 732 3848 - 420</fax>" +
                                          "<first-name>Carina</first-name>" +
                                          "<id>158</id>" +
                                          "<last-name>Bauer</last-name>" +
                                          "<linkedin />" +
                                          "<mobile />" +
                                          "<phone>+43 (0) 732 3848 - 220</phone>" +
                                          "<skype />" +
                                          "<title>IT</title>" +
                                          "<twitter />" +
                                      "</contact>";
    
    @Override
    protected void setUp() throws Exception {
        contact = Contact.fromXML(XML);
        super.setUp();
    }
    
    public void testSettingOfInteger() throws IOException {
        assertEquals(158,
                     (int) contact.getId());
    }
    
    public void testSettingOfString() throws IOException {
        assertEquals("Carina",
                     contact.getFirstName());
    }
    
    public void testSettingOfDate() {
        assertEquals("2012-08-29",
                     contact.getBornOn().toString());
    }
    
    public void testSettingOfNull() throws IOException {
        assertNull(contact.getBlog());
    }
}
