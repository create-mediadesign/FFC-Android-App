package at.create.android.ffc.http;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import at.create.android.ffc.domain.Contact;
import at.create.android.ffc.domain.ContactList;

/**
 * @author Philipp Ullmann
 * Fetches contacts from "/contacts"
 */
public final class FetchContacts extends HttpBase {
    private static final String PATH = "/contacts.xml";
    private ContactList contactList  = null;
    
    public FetchContacts(String baseUri) {
        super(baseUri);
    }
    
    @Override
    protected String getUrl() {
        return baseUri + PATH;
    }
    
    public List<Contact> getContacts() {
        return contactList.getContacts();
    }
    
    /**
     * Fetches the contacts.
     */
    public void fetch() {
        setAcceptHeaderApplicationXml();
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter());
        
        // Perform the HTTP GET request
        ResponseEntity<ContactList> responseEntity = restTemplate.exchange(getUrl(),
                                                                           HttpMethod.GET,
                                                                           requestEntity,
                                                                           ContactList.class);
        
        contactList = responseEntity.getBody();
    }
}
