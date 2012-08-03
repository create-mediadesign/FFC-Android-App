package at.create.android.ffc.test.http;

import at.create.android.ffc.http.FetchContacts;

public final class FetchContactsTest extends HttpBase {
    private FetchContacts fetcher;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fetcher = new FetchContacts(setting.getBaseUri());
        fetcher.fetch();
    }
    
    public void testFetchingOfContacts() {
        assertTrue(fetcher.getContacts().size() > 10);
    }
}
