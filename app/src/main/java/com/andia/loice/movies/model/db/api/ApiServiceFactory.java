package com.andia.loice.movies.model.db.api;

import com.andia.loice.movies.BuildConfig;
import com.andia.loice.movies.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {

    private Gson gson;

    @Inject
    public ApiServiceFactory() {
        gson = new GsonBuilder().create();
    }

    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new
                HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    private Interceptor getQueryParamsInterceptor() {
        return chain -> {
            Request original = chain.request();
            HttpUrl url = original.url();
            HttpUrl newUrl = url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.api_key)
                    .addQueryParameter("language", "en-US")
                    .addQueryParameter("include_adult", "false")
                    .build();
            Request.Builder builder = original.newBuilder().url(newUrl);
            Request request = builder.build();
            return chain.proceed(request);
        };
    }


    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    public OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getQueryParamsInterceptor())
                .addInterceptor(provideLoggingInterceptor())
                .build();
    }
    public ApiService providesApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(provideClient())
                .baseUrl(Constants.BASE_URL)
                .build();
        return retrofit.create(ApiService.class);

    }
}
