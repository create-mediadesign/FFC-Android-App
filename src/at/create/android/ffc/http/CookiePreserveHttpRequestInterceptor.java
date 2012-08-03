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
}
