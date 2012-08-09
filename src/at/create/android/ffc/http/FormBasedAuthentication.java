package at.create.android.ffc.http;

import java.net.URI;

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
        URI uri = restTemplate.postForLocation(getUrl(), formData);
        
        return !uri.getPath().equals("/login");
    }
}
