package at.create.android.ffc.http;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Philipp Ullmann
 * Authenticate with username and password.
 */
public final class FormBasedAuthentication {
    private static final String PATH = "/authentication";
    private final String username;
    private final String password;
    private final String url;
    
    /**
     * Stores the given parameters into instance variables.
     * @param username Username
     * @param password Password
     * @param baseUri Base uri to Fat Free CRM web application
     */
    public FormBasedAuthentication(final String username, final String password, final String baseUri) {
        this.username = username;
        this.password = password;
        this.url      = baseUri + PATH;
    }
    
    /**
     * Authentication via username and password.
     * @return True if the authentication succeeded, otherwise false is returned.
     */
    public boolean authenticate() {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
        formData.add("authentication[username]", username);
        formData.add("authentication[password]", password);
        formData.add("authentication[remember_me]", "1");
        
        HttpHeaders requestHeaders                              = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
        RestTemplate restTemplate                               = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        List<ClientHttpRequestInterceptor> interceptors         = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new MyClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        ResponseEntity<String> response                         = restTemplate.exchange(url,
                                                                                        HttpMethod.POST,
                                                                                        requestEntity,
                                                                                        String.class);
        return !response.getBody().contains("authentication[username]");
    }
}
