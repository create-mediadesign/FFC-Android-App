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
package at.create.android.ffc.test.http;

import at.create.android.ffc.http.FetchContacts;

public final class FetchContactsTest extends HttpBase {
    private FetchContacts fetcher;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fetcher = new FetchContacts(setting.getBaseUri());
        fetcher.fetch();
    }
    
    public void testFetchingOfContacts() {
        assertTrue(fetcher.getContacts().size() > 10);
    }
}
