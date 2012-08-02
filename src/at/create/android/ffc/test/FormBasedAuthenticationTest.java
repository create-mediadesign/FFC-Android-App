package at.create.android.ffc.test;

import java.io.IOException;

import android.test.AndroidTestCase;
import at.create.android.ffc.FormBasedAuthentication;
import at.create.android.ffc.Setting;

public final class FormBasedAuthenticationTest extends AndroidTestCase {
    private Setting setting;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setting = Setting.load(FormBasedAuthenticationTest.class.getResourceAsStream("/assets/setting.xml"));
    }
    
    public void testSuccessfulAuthentication() throws IOException {
        FormBasedAuthentication auth = new FormBasedAuthentication(setting.getUsername(),
                                                                   setting.getPassword(),
                                                                   setting.getUrl());
        assertTrue(auth.authenticate());
    }
    
    public void testFailingAuthentication() throws IOException {
        FormBasedAuthentication auth = new FormBasedAuthentication("Unknown",
                                                                   setting.getPassword(),
                                                                   setting.getUrl());
        assertFalse(auth.authenticate());
    }
}
