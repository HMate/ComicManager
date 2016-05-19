package bme.aut.comicmanager.network;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.MockComicsDb;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mhidvegi on 2016. 05. 09..
 */
public class ComicsMock {

    private static List<Comic> comics = new ArrayList<Comic>();
    private static boolean isInitialized = false;

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        // TODO: implement mocked server responses
        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "comics")
                && request.method().equals("GET")){
            if(!isInitialized){
                Comic c1 = new Comic();
                c1.setComicId(0L);
                c1.setTitle("Spider-Man");
                comics.add(c1);

                isInitialized = true;
            }

            responseCode = 200;
            responseString = GsonHelper.getGson().toJson(comics);
        }
        else {
            responseCode = 503;
            responseString = "ERROR";
        }

        return MockHelper.makeResponse(request, headers, responseCode, responseString);
    }
}
