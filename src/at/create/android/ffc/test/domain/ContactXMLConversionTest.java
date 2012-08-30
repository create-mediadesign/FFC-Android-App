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
                                          "<email>pu@create.at</email>" +
                                          "<facebook />" +
                                          "<fax>+43 (0) 111 1111 - 111</fax>" +
                                          "<first-name>Philipp</first-name>" +
                                          "<id>158</id>" +
                                          "<last-name>Ullmann</last-name>" +
                                          "<linkedin />" +
                                          "<mobile />" +
                                          "<phone>+43 (0) 111 1111 - 111</phone>" +
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
        assertEquals("Philipp",
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
