package bme.aut.comicmanager.network;

import okhttp3.OkHttpClient;

/**
 * Created by mhidvegi on 2016. 05. 09..
 */
public class UnsafeClientFactory {
    public static OkHttpClient.Builder getUnsafeClient(){
        return new OkHttpClient.Builder();
    }
}
