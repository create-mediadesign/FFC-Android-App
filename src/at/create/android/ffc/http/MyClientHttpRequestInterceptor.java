package at.create.android.ffc.http;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import android.util.Log;

public final class MyClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final String SET_COOKIE = "set-cookie";
    private static final String COOKIE = "cookie";
    private static final String COOKIE_STORE = "cookieStore";

    /* (non-Javadoc)
     * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] byteArray,
            ClientHttpRequestExecution execution) throws IOException {

        Log.d(getClass().getSimpleName(), ">>> entering intercept");
        List<String> cookies = request.getHeaders().get(COOKIE);
        // if the header doesn't exist, add any existing, saved cookies
        if (cookies == null) {
            @SuppressWarnings("unchecked")
            List<String> cookieStore = (List<String>) StaticCacheHelper.retrieveObjectFromCache(COOKIE_STORE);
            // if we have stored cookies, add them to the headers
            if (cookieStore != null) {
                for (String cookie : cookieStore) {
                    request.getHeaders().add(COOKIE, cookie);
                }
            }
        }
        // execute the request
        ClientHttpResponse response = execution.execute(request, byteArray);
        // pull any cookies off and store them
        cookies = response.getHeaders().get(SET_COOKIE);
        if (cookies != null) {
            for (String cookie : cookies) {
                Log.d(getClass().getSimpleName(), ">>> response cookie = " + cookie);
            }
            StaticCacheHelper.storeObjectInCache(COOKIE_STORE, cookies);
        }
        Log.d(getClass().getSimpleName(), ">>> leaving intercept");
        return response;
    }
}
