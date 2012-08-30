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

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * @author Philipp Ullmann
 * Base class for all HTTP request classes.
 */
public abstract class HttpBase {
    protected String baseUri;
    protected HttpHeaders requestHeaders = new HttpHeaders();
    protected RestTemplate restTemplate  = new RestTemplate();
    
    public HttpBase(String baseUri) {
        this.baseUri = baseUri;
        preserveCookiesOverRequests();
    }
    
    /**
     * Adds an interceptor to the rest template, in order to preserve cookies over requests.
     */
    private void preserveCookiesOverRequests() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(1);
        interceptors.add(CookiePreserveHttpRequestInterceptor.getInstance());
        restTemplate.setInterceptors(interceptors);
    }
    
    /**
     * Set the Accept header for "application/xml".
     */
    protected void setAcceptHeaderApplicationXml() {
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        requestHeaders.setAccept(acceptableMediaTypes);
    }
    
    /**
     * @return the complete URL to get the requested resource.
     */
    protected abstract String getUrl();
}
