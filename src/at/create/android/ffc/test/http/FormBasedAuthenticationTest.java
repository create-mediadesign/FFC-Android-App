package at.create.android.ffc.test.http;

import java.io.IOException;

import at.create.android.ffc.http.FormBasedAuthentication;

public final class FormBasedAuthenticationTest extends HttpBase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        resetAuthentication();
    }
    
    public void testSuccessfulAuthentication() throws IOException {
        assertTrue(authenticate());
    }
    
    public void testFailingAuthentication() throws IOException {
        FormBasedAuthentication auth = new FormBasedAuthentication("Unknown",
                                                                   setting.getPassword(),
                                                                   setting.getBaseUri());
        assertFalse(auth.authenticate());
    }
}
