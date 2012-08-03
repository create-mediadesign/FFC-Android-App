package at.create.android.ffc.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
        formData.add("authentication[username]", username);
        formData.add("authentication[password]", password);
        formData.add("authentication[remember_me]", "1");
        
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
        ResponseEntity<String> response                         = restTemplate.exchange(getUrl(),
                                                                                        HttpMethod.POST,
                                                                                        requestEntity,
                                                                                        String.class);
        return !response.getHeaders().getLocation().getPath().equals("/login");
    }
}
