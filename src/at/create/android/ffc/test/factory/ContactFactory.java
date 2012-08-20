package at.create.android.ffc.test.factory;

import java.io.IOException;

import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import at.create.android.ffc.domain.Contact;
import at.create.android.ffc.http.MockHttpInputMessage;

/**
 * @author Philipp Ullmann
 * Contact factory.
 */
public final class ContactFactory {
    private SimpleXmlHttpMessageConverter xmlConverter;
    
    public ContactFactory() {
        xmlConverter = new SimpleXmlHttpMessageConverter();
    }
    /**
     * @param firstName
     * @param lastName
     * @return contact with a first and last name and with an id of 1
     * @throws IOException 
     */
    public Contact build(String firstName, String lastName) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<contact>");
            sb.append("<id>1</id>");
            sb.append("<first-name>");
            sb.append(firstName);
            sb.append("</first-name>");
            sb.append("<last-name>");
            sb.append(lastName);
            sb.append("</last-name>");
        sb.append("</contact>");
        
        return buildFromXML(sb.toString());
    }
    
    private Contact buildFromXML(String xml) throws IOException {
        MockHttpInputMessage inputMessage = new MockHttpInputMessage(xml.getBytes("UTF-8"));
        return (Contact) xmlConverter.read(Contact.class, inputMessage);
    }
}
