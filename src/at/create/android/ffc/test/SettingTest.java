package at.create.android.ffc.test;

import java.io.ByteArrayInputStream;

import android.test.AndroidTestCase;
import at.create.android.ffc.Setting;

public final class SettingTest extends AndroidTestCase {
    private String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                         "<setting>" +
                           "<url>http://create.at</url>" +
                           "<username>philipp</username>" +
                           "<password>secret</password>" +
                         "</setting>";
    private Setting setting;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setting = Setting.load(new ByteArrayInputStream(xml.getBytes()));
    }
    
    public void testSettingOfUrl() {
        assertEquals("http://create.at",
                     setting.getUrl());
    }
    
    public void testSettingOfUsername() {
        assertEquals("philipp",
                     setting.getUsername());
    }
    
    public void testSettingOfPassword() {
        assertEquals("secret",
                     setting.getPassword());
    }
}
