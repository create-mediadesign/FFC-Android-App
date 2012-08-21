package at.create.android.ffc.test.domain;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.Setting;

public final class SettingTest extends AndroidTestCase {
    private String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                         "<setting>" +
                           "<baseUri>http://create.at</baseUri>" +
                           "<username>philipp</username>" +
                           "<password>secret</password>" +
                         "</setting>";
    private Setting setting;
    
    public void testSettingOfBaseUri() throws IOException {
        buildSettingViaXML();
        assertEquals("http://create.at",
                     setting.getBaseUri());
    }
    
    public void testSettingOfUsername() throws IOException {
        buildSettingViaXML();
        assertEquals("philipp",
                     setting.getUsername());
    }
    
    public void testSettingOfPassword() throws IOException {
        buildSettingViaXML();
        assertEquals("secret",
                     setting.getPassword());
    }
    
    public void testBaseUriIsBlankWithABlankValue() {
        setting = new Setting(null, null, null);
        assertTrue(setting.baseUriIsBlank());
    }
    
    public void testBaseUriIsBlankWithAnNonBlankValue() {
        setting = new Setting("http://create.at", null, null);
        assertFalse(setting.baseUriIsBlank());
    }
    
    public void testUsernameIsBlankWithABlankValue() {
        setting = new Setting(null, null, null);
        assertTrue(setting.usernameIsBlank());
    }
    
    public void testUsernameIsBlankWithAnNonBlankValue() {
        setting = new Setting(null, "philipp", null);
        assertFalse(setting.usernameIsBlank());
    }
    
    public void testPasswordIsBlankWithABlankValue() {
        setting = new Setting(null, null, null);
        assertTrue(setting.passwordIsBlank());
    }
    
    public void testPasswordIsBlankWithAnNonBlankValue() {
        setting = new Setting(null, null, "secret");
        assertFalse(setting.passwordIsBlank());
    }
    
    public void testBaseUriIsAnUrlWithoutHttp() {
        setting = new Setting("create.at", null, null);
        assertFalse(setting.baseUriIsAnUrl());
    }
    
    public void testBaseUriIsAnUrlWithNull() {
        setting = new Setting(null, null, null);
        assertFalse(setting.baseUriIsAnUrl());
    }
    
    public void testBaseUriIsAnUrlWithValidUrl() {
        setting = new Setting("http://create.at", null, null);
        assertTrue(setting.baseUriIsAnUrl());
    }
    
    private void buildSettingViaXML() throws IOException {
        setting = Setting.load(new ByteArrayInputStream(xml.getBytes()));
    }
}
