package at.create.android.ffc.test.factory;

import java.io.IOException;

import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import android.text.TextUtils;
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
    public Contact buildWithFirstNameAndLastName(String firstName, String lastName) throws IOException {
        String xml = tagContact(tagId(),
                                 tagFirstName(firstName),
                                 tagLastName(lastName));
        
        return buildFromXML(xml);
    }
    
    /**
     * @param email
     * @return contact
     * @throws IOException 
     */
    public Contact buildWithEmail(String email) throws IOException {
        String xml = tagContactValid(tagEmail(email));
        return buildFromXML(xml);
    }
    
    public Contact buildWithPhone(String phone) throws IOException {
        String xml = tagContactValid(tagPhone(phone));
        return buildFromXML(xml);
    }
    
    public Contact buildWithMobile(String mobil) throws IOException {
        String xml = tagContactValid(tagMobil(mobil));
        return buildFromXML(xml);
    }
    
    private Contact buildFromXML(String xml) throws IOException {
        MockHttpInputMessage inputMessage = new MockHttpInputMessage(xml.getBytes("UTF-8"));
        return (Contact) xmlConverter.read(Contact.class, inputMessage);
    }
    
    private String tagId() {
        return tag("id", "1");
    }
    
    private String tagFirstName() {
        return tag("first-name", "Philipp");
    }
    
    private String tagFirstName(String firstName) {
        return tag("first-name", firstName);
    }
    
    private String tagLastName() {
        return tag("last-name", "Ullmann");
    }
    
    private String tagLastName(String lastName) {
        return tag("last-name", lastName);
    }
    
    private String tagEmail(String email) {
        return tag("email", email);
    }
    
    private String tagPhone(String phone) {
        return tag("phone", phone);
    }
    
    private String tagMobil(String mobil) {
        return tag("mobil", mobil);
    }
    
    private String tag(String name, String value) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<");
        sb.append(name);
        
        if (TextUtils.isEmpty(value)) {
            sb.append(" />");
        } else {
            sb.append(">");
            sb.append(value);
            sb.append("</");
            sb.append(name);
            sb.append(">");
        }
        
        return sb.toString();
    }
    
    private String tagContact(String... tags) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<contact>");
        
        for (String tag : tags) {
            sb.append(tag);
        }
        
        sb.append("</contact>");
        
        return sb.toString();
    }
    
    private String tagContactValid(String... tags) {
        StringBuilder sb = new StringBuilder();
        
        for (String tag : tags) {
            sb.append(tag);
        }
        
        return tagContact(tagId(),
                          tagFirstName(),
                          tagLastName(),
                          sb.toString());
    }
}
