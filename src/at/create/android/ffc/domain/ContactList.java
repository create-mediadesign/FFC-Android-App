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
package at.create.android.ffc.domain;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


/**
 * @author Philipp Ullmann
 * Holds a list of contacts.
 */
@Root(name = "contacts", strict = false)
public final class ContactList {
    @ElementList(inline = true)
    private List<Contact> contacts;
    
    /**
     * @return the contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }
}
