package at.create.android.ffc.http;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import at.create.android.ffc.domain.Contact;
import at.create.android.ffc.domain.ContactList;

/**
 * @author Philipp Ullmann
 * Fetches contacts from "/contacts"
 */
public final class FetchContacts {
    private static final String PATH = "/contacts.xml";
    private ContactList contactList  = null;
    private final String url;
    
    /**
     * @param baseUri Base uri to Fat Free CRM web application
     */
    public FetchContacts(final String baseUri) {
        this.url = baseUri + PATH;
    }
    
    public List<Contact> getContacts() {
        return contactList.getContacts();
    }
    
    /**
     * Fetches the contacts.
     */
    public void fetch() {
        // Set the Accept header for "application/xml"
        HttpHeaders requestHeaders           = new HttpHeaders();
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        requestHeaders.setAccept(acceptableMediaTypes);
        
        // Populate the headers in an HttpEntity object to use for the request
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter());
        
        // Perform the HTTP GET request
        ResponseEntity<ContactList> responseEntity = restTemplate.exchange(url,
                                                                           HttpMethod.GET,
                                                                           requestEntity,
                                                                           ContactList.class);
        
        contactList = responseEntity.getBody();
    }
}
