package com.ingmicha.nextu.rappiapp.data.api.retrofit;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

public class ApiInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request origin = chain.request();
        Request request = origin.newBuilder()
                .method(origin.method(),origin.body()).build();
        return chain.proceed(request);
    }

}
