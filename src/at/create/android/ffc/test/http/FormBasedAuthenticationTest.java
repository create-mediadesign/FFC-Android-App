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

import java.io.IOException;

import at.create.android.ffc.http.FormBasedAuthentication;

public final class FormBasedAuthenticationTest extends HttpBase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        resetAuthentication();
    }
    
    public void testSuccessfulAuthentication() throws IOException {
        assertTrue(authenticate());
    }
    
    public void testFailingAuthentication() throws IOException {
        FormBasedAuthentication auth = new FormBasedAuthentication("Unknown",
                                                                   setting.getPassword(),
                                                                   setting.getBaseUri());
        assertFalse(auth.authenticate());
    }
}
