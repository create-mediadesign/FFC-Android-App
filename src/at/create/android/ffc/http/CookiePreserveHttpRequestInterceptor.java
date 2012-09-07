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

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author Philipp Ullmann
 * Intercepts a HTTP request to preserve cookies over requests.
 */
public class CookiePreserveHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static CookiePreserveHttpRequestInterceptor instance = null;
    private static final String SET_COOKIE                       = "set-cookie";
    private static final String COOKIE                           = "cookie";
    private List<String> cookies                                 = null;
    
    private CookiePreserveHttpRequestInterceptor() {}
    
    /**
     * @return the singleton instance.
     */
    public static CookiePreserveHttpRequestInterceptor getInstance() {
        if (instance == null) {
            instance = new CookiePreserveHttpRequestInterceptor();
        }
        
        return instance;
    }
    
    @Override
    public synchronized ClientHttpResponse intercept(HttpRequest request, byte[] byteArray, ClientHttpRequestExecution execution) throws IOException {
        List<String> setCookies = request.getHeaders().get(COOKIE);
        
        // If the header doesn't exist and we have stored cookies, add any existing, saved cookies.
        if (setCookies == null && hasCookies()) {
            for (String cookie : cookies) {
                request.getHeaders().add(COOKIE, cookie);
            }
        }
        
        // Execute the request.
        ClientHttpResponse response = execution.execute(request, byteArray);
        // Pull any cookies off and store them.
        cookies = response.getHeaders().get(SET_COOKIE);
        
        return response;
    }
    
    /**
     * Resets the cookie storage.
     */
    public void clear() {
        cookies = null;
    }
    
    /**
     * @return true if the cookie storage is not empty, otherwise false is returned.
     */
    public boolean hasCookies() {
        return cookies != null &&
               !cookies.isEmpty();
    }
    
    /**
     * @param name
     * @return true, if a cookie with the given name exists, otherwise false is returned.
     */
    public boolean hasCookieWithName(String name) {
        for (String cookie : cookies) {
            if (cookie.contains(name))
                return true;
        }
        
        return false;
    }
}
