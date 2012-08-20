package at.create.android.ffc.test.domain;

import android.test.AndroidTestCase;
import at.create.android.ffc.domain.StringHelper;

public final class StringHelperTest extends AndroidTestCase {
    private StringHelper sh = new StringHelper();
    
    // isBlank
    
    public void testIsBlankWithNull() {
        assertTrue(sh.isBlank(null));
    }
    
    public void testIsBlankWithEmptyString() {
        assertTrue(sh.isBlank(""));
    }
    
    public void testIsBlankWithWhitespaceCharacters() {
        assertTrue(sh.isBlank("      "));
    }
    
    public void testIsBlankWithCharacters() {
        assertFalse(sh.isBlank("    a       "));
    }
}
