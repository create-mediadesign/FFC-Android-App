package at.create.android.ffc.test;

import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import android.test.AndroidTestCase;
import at.create.android.ffc.ContactList;
import at.create.android.ffc.MockHttpInputMessage;

public class ContactListXMLConversionTest extends AndroidTestCase {
    private SimpleXmlHttpMessageConverter xmlConverter;
    private ContactList contactList;
    private final static String XML = "<contacts>" +
                                          "<contact>" +
                                              "<id>1</id>" +
                                              "<first-name>A</first-name>" +
                                              "<last-name>B</last-name>" +
                                          "</contact>" +
                                          "<contact>" +
                                              "<id>2</id>" +
                                              "<first-name>A</first-name>" +
                                              "<last-name>B</last-name>" +
                                          "</contact>" +
                                      "</contacts>";
    
    @Override
    protected void setUp() throws Exception {
        xmlConverter                      = new SimpleXmlHttpMessageConverter();
        MockHttpInputMessage inputMessage = new MockHttpInputMessage(XML.getBytes("UTF-8"));
        contactList                       = (ContactList) xmlConverter.read(ContactList.class, inputMessage);
        super.setUp();
    }
    
    public void testListSize() {
        assertEquals(2,
                     contactList.getContacts().size());
    }
}
