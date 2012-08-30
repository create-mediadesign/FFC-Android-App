/*
 * Fat Free CRM Android App
 * Copyright 2012 create mediadesign GmbH
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
