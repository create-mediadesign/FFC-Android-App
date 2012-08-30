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

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.Setting;
import at.create.android.ffc.http.CookiePreserveHttpRequestInterceptor;
import at.create.android.ffc.http.FormBasedAuthentication;

public abstract class HttpBase extends AndroidTestCase {
    protected Setting setting;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setting = Setting.load(HttpBase.class.getResourceAsStream("/assets/test_setting.xml"));
        authenticate();
    }
    
    protected boolean authenticate() {
        if (!CookiePreserveHttpRequestInterceptor.getInstance().hasCookies()) {
            FormBasedAuthentication auth = new FormBasedAuthentication(setting.getUsername(),
                                                                       setting.getPassword(),
                                                                       setting.getBaseUri());
            
            return auth.authenticate();
        }
        
        return true;
    }
    
    protected void resetAuthentication() {
        CookiePreserveHttpRequestInterceptor.getInstance().clear();
    }
}
