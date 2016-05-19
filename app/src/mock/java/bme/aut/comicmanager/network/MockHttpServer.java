package bme.aut.comicmanager.network;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mhidvegi on 2016. 05. 09..
 */
public class MockHttpServer {
    public static Response call(Request request){
        MockInterceptor mockInterceptor = new MockInterceptor();
        return mockInterceptor.process(request);
    }
}
