package bme.aut.comicmanager.network;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static bme.aut.comicmanager.network.MockHelper.makeResponse;

/**
 * Created by mhidvegi on 2016. 05. 09..
 */
public class MockInterceptor implements Interceptor{
    public Response process(Request request) {

        Uri uri = Uri.parse(request.url().toString());

        Log.d("Test Http Client", "URL call: " + uri.toString());
        Headers headers = request.headers();

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "comics")) {
            return ComicsMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "comics/new")) {
            return ComicsMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "issues")) {
            return ComicsMock.process(request);
        }else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "issues/new")) {
            return ComicsMock.process(request);
        }else {
            return makeResponse(request, headers, 404, "Unknown");
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }
}
