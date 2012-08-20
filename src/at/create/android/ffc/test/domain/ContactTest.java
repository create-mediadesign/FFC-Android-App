package at.create.android.ffc.test.domain;

import java.io.IOException;

import android.test.AndroidTestCase;
import at.create.android.ffc.test.factory.ContactFactory;

public final class ContactTest extends AndroidTestCase {
    private ContactFactory cf;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        cf = new ContactFactory();
    }
    
    // getName
    
    public void testGetName() throws IOException {
        assertEquals("Philipp Ullmann",
                     cf.build("Philipp", "Ullmann").getName());
    }
}
