/*
 * Fat Free CRM Android App
 * Copyright 2012 create mediadesign GmbH
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
