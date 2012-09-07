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

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Philipp Ullmann
 * Authenticate with username and password.
 */
public final class FormBasedAuthentication extends HttpBase {
    private static final String PATH = "/authentication";
    private final String username;
    private final String password;
    
    /**
     * Stores the given parameters into instance variables.
     * @param username Username
     * @param password Password
     * @param baseUri Base uri to Fat Free CRM web application
     */
    public FormBasedAuthentication(final String username, final String password, final String baseUri) {
        super(baseUri);
        this.username = username;
        this.password = password;
    }
    
    @Override
    protected String getUrl() {
        return baseUri + PATH;
    }
    
    /**
     * Authentication via username and password.
     * @return True if the authentication succeeded, otherwise false is returned.
     */
    public boolean authenticate() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("authentication[username]", username);
        formData.add("authentication[password]", password);
        formData.add("authentication[remember_me]", "1");
        
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.postForLocation(getUrl(), formData);
        
        return CookiePreserveHttpRequestInterceptor.getInstance().hasCookieWithName("user_credentials");
    }
}
