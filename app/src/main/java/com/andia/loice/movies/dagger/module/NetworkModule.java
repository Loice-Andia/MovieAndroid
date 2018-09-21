package com.andia.loice.movies.dagger.module;

import com.andia.loice.movies.BuildConfig;
import com.andia.loice.movies.model.db.ApiService;
import com.andia.loice.movies.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new
                HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    public RxJava2CallAdapterFactory provideRxJavaCallAdapter() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    public OkHttpClient provideClient(HttpLoggingInterceptor interceptor) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
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

    public ApiService providesApiService(OkHttpClient client,
                                         HttpLoggingInterceptor interceptor, RxJava2CallAdapterFactory
                                            rxCallFactory, GsonConverterFactory gsonFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(rxCallFactory)
                .addConverterFactory(gsonFactory)
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .build();
        return retrofit.create(ApiService.class);

    }
}
