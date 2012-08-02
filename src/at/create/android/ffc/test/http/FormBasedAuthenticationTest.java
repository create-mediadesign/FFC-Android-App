package at.create.android.ffc.test.http;

import java.io.IOException;

import at.create.android.ffc.http.FormBasedAuthentication;

public final class FormBasedAuthenticationTest extends HttpBase {
    public void testSuccessfulAuthentication() throws IOException {
        FormBasedAuthentication auth = new FormBasedAuthentication(setting.getUsername(),
                                                                   setting.getPassword(),
                                                                   setting.getBaseUri());
        assertTrue(auth.authenticate());
    }
    
    public void testFailingAuthentication() throws IOException {
        FormBasedAuthentication auth = new FormBasedAuthentication("Unknown",
                                                                   setting.getPassword(),
                                                                   setting.getBaseUri());
        assertFalse(auth.authenticate());
    }
}
