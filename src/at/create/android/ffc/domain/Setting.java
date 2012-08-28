package at.create.android.ffc.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import android.text.TextUtils;
import at.create.android.ffc.http.MockHttpInputMessage;

/**
 * @author Philipp Ullmann
 * App settings helper class for development only.
 * Read from "assets/setting.xml"
 */
@Root(name = "setting")
public final class Setting {
    public static final String SHARED_PREF = "setting";
    @Element
    private String baseUri;
    @Element
    private String username;
    @Element
    private String password;
    
    /**
     * @param baseUri
     * @param username
     * @param password
     */
    public Setting(String baseUri, String username, String password) {
        this.baseUri  = baseUri;
        this.username = username;
        this.password = password;
    }
    
    /**
     * @return true if base URI is blank, otherwise false is returned 
     */
    public boolean baseUriIsBlank() {
        return TextUtils.isEmpty(baseUri);
    }
    
    /**
     * @return true if the base URI is a valid URL, otherwise false is returned.
     */
    public boolean baseUriIsAnUrl() {
        try {
            new URL(baseUri);
        } catch (MalformedURLException e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * @return true if username is blank, otherwise false is returned 
     */
    public boolean usernameIsBlank() {
        return TextUtils.isEmpty(username);
    }
    
    /**
     * @return true if password is blank, otherwise false is returned 
     */
    public boolean passwordIsBlank() {
        return TextUtils.isEmpty(password);
    }
    
    /**
     * @return the baseUri
     */
    public String getBaseUri() {
        return baseUri;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Builds a setting instance from the given input stream.
     * @param is Input stream
     * @return Setting instance
     * @throws IOException
     */
    public static Setting load(InputStream is) throws IOException {
        SimpleXmlHttpMessageConverter xmlConverter = new SimpleXmlHttpMessageConverter();
        MockHttpInputMessage inputMessage          = new MockHttpInputMessage(IOUtils.toByteArray(is));
        return (Setting) xmlConverter.read(Setting.class, inputMessage);
    }
}
