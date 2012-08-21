package at.create.android.ffc.test.domain;

import java.io.IOException;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.Contact;

public final class ContactXMLConversionTest extends AndroidTestCase {
    private Contact contact;
    private final static String XML = "<contact>" +
                                          "<access>Public</access>" +
                                          "<alt-email/>" +
                                          "<assigned-to type=\"integer\" nil=\"true\"/>" +
                                          "<background-info nil=\"true\"/>" +
                                          "<blog/>" +
                                          "<born-on type=\"date\" nil=\"true\"/>" +
                                          "<cf-akademischer-grad/>" +
                                          "<cf-geschlecht>female</cf-geschlecht>" +
                                          "<created-at type=\"datetime\">2012-07-25T15:25:59+03:00</created-at>" +
                                          "<deleted-at type=\"datetime\" nil=\"true\"/>" +
                                          "<department/>" +
                                          "<do-not-call type=\"boolean\">false</do-not-call>" +
                                          "<email>Ca.Bauer@silhouette.com</email>" +
                                          "<facebook/>" +
                                          "<fax>+43 (0) 732 3848 - 420</fax>" +
                                          "<first-name>Carina</first-name>" +
                                          "<id type=\"integer\">158</id>" +
                                          "<last-name>Bauer</last-name>" +
                                          "<lead-id type=\"integer\" nil=\"true\"/>" +
                                          "<linkedin/>" +
                                          "<mobile/>" +
                                          "<phone>+43 (0) 732 3848 - 220</phone>" +
                                          "<reports-to type=\"integer\" nil=\"true\"/>" +
                                          "<skype/><source nil=\"true\"/>" +
                                          "<subscribed-users type=\"yaml\">--- !ruby/object:Set hash: {}</subscribed-users>" +
                                          "<title>IT</title>" +
                                          "<twitter/>" +
                                          "<updated-at type=\"datetime\">2012-07-25T15:26:37+03:00</updated-at>" +
                                          "<user-id type=\"integer\">1</user-id>" +
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
    
    public void testSettingOfNull() throws IOException {
        assertNull(contact.getBlog());
    }
}
