package at.create.android.ffc.domain;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import at.create.android.ffc.http.MockHttpInputMessage;

/**
 * @author Philipp Ullmann
 * App settings helper class for development only.
 * Read from "assets/setting.xml"
 */
@Root(name = "setting")
public final class Setting {
    @Element
    private String baseUri;
    @Element
    private String username;
    @Element
    private String password;
    
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
