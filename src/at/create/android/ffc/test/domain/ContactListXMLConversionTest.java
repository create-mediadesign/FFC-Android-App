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
package at.create.android.ffc.test.domain;

import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.ContactList;
import at.create.android.ffc.http.MockHttpInputMessage;

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
