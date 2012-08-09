package at.create.android.ffc.test.http;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.Setting;
import at.create.android.ffc.http.CookiePreserveHttpRequestInterceptor;
import at.create.android.ffc.http.FormBasedAuthentication;

public abstract class HttpBase extends AndroidTestCase {
    protected Setting setting;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setting = Setting.load(HttpBase.class.getResourceAsStream("/assets/setting.xml"));
        authenticate();
    }
    
    protected boolean authenticate() {
        if (!CookiePreserveHttpRequestInterceptor.getInstance().hasCookies()) {
            FormBasedAuthentication auth = new FormBasedAuthentication(setting.getUsername(),
                                                                       setting.getPassword(),
                                                                       setting.getBaseUri());
            
            return auth.authenticate();
        }
        
        return true;
    }
    
    protected void resetAuthentication() {
        CookiePreserveHttpRequestInterceptor.getInstance().clear();
    }
}
