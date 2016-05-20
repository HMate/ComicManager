package bme.aut.comicmanager.network;

import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.aut.comicmanager.comics.Comic;
import bme.aut.comicmanager.comics.InlineResponse200;
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

        String responseString = "ERROR";
        int responseCode = 503;
        Headers headers = request.headers();

        if(!isInitialized){
            Comic c1 = new Comic();
            c1.setComicId(0L);
            c1.setTitle("Spider-Man");
            comics.add(c1);

            isInitialized = true;
        }

        String uriPath = uri.getPath();
        String method = request.method();

        if (pathIsComics(uriPath) && method.equals("GET")){
            InlineResponse200 response200 = new InlineResponse200();
            response200.setData(comics);

            responseCode = 200;
            responseString = GsonHelper.getGson().toJson(response200);

        } else if (pathIsComicsWithID(uriPath)){
            if(method.equals("GET")){

                InlineResponse200 response200 = new InlineResponse200();
                response200.setData(comics);

                responseCode = 200;
                responseString = GsonHelper.getGson().toJson(response200);

            } else if(method.equals("POST")){
                // TODO
            } else if(method.equals("DELETE")){
                // TODO
            }
        } else if (pathIsNewComics(uriPath) && method.equals("POST")){

            InlineResponse200 response200 = new InlineResponse200();
            response200.setData(comics);

            responseCode = 200;
            responseString = GsonHelper.getGson().toJson(response200);
            // TODO
        } else if (pathIsIssues(uriPath) && method.equals("GET")){

            InlineResponse200 response200 = new InlineResponse200();
            response200.setData(comics);

            responseCode = 200;
            responseString = GsonHelper.getGson().toJson(response200);
            // TODO
        } else if (pathIsNewIssues(uriPath) && method.equals("POST")){

            InlineResponse200 response200 = new InlineResponse200();
            response200.setData(comics);

            responseCode = 200;
            responseString = GsonHelper.getGson().toJson(response200);
            // TODO
        } else if (pathIsIssuesWithID(uriPath)){
            if(method.equals("GET")){

                InlineResponse200 response200 = new InlineResponse200();
                response200.setData(comics);

                responseCode = 200;
                responseString = GsonHelper.getGson().toJson(response200);
                // TODO

            } else if(method.equals("POST")){
                // TODO
            } else if(method.equals("DELETE")){
                // TODO
            }
        }

        Response response = MockHelper.makeResponse(request, headers, responseCode, responseString);
        return response;
    }

    private static boolean pathIsComics(String uriPath){
        boolean result = uriPath.equals(NetworkConfig.ENDPOINT_PREFIX + "comics");
        return result;
    }

    private static boolean pathIsNewComics(String uriPath){
        boolean result = uriPath.equals(NetworkConfig.ENDPOINT_PREFIX + "comics/new");
        return result;
    }

    private static boolean pathIsComicsWithID(String uriPath){
        boolean result = false;
        boolean startIsRight = uriPath.startsWith(NetworkConfig.ENDPOINT_PREFIX + "comics/");

        if(startIsRight) {
            int idBegin = uriPath.lastIndexOf('/');
            String idString = uriPath.substring(idBegin + 1);
            try {
                long id = Long.valueOf(idString);
                result = true;
            } catch (NumberFormatException nfe) {
                Log.d("mock comic server", "Not a valid long id: " + idString);
                result = false;
            }
        }
        return result;
    }

    private static boolean pathIsIssues(String uriPath){
        boolean result = uriPath.equals(NetworkConfig.ENDPOINT_PREFIX + "issues");
        return result;
    }

    private static boolean pathIsNewIssues(String uriPath){
        boolean result = uriPath.equals(NetworkConfig.ENDPOINT_PREFIX + "issues/new");
        return result;
    }

    private static boolean pathIsIssuesWithID(String uriPath){
        boolean result = false;
        boolean startIsRight = uriPath.startsWith(NetworkConfig.ENDPOINT_PREFIX + "issues/");

        if(startIsRight) {
            int idBegin = uriPath.lastIndexOf('/');
            String idString = uriPath.substring(idBegin + 1);
            try {
                long id = Long.valueOf(idString);
                result = true;
            } catch (NumberFormatException nfe) {
                Log.d("mock comic server", "Not a valid long id: " + idString);
                result = false;
            }
        }
        return result;
    }

    private static long getIdFromPath(String uriPath){
        int idBegin = uriPath.lastIndexOf('/');
        String idString = uriPath.substring(idBegin + 1);
        long id = 0;
        try {
            id = Long.valueOf(idString);
        } catch (NumberFormatException nfe) {
            Log.d("mock comic server", "Tried to use an invalid long id: " + idString + nfe.getMessage());
        }
        return id;
    }
}
