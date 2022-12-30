package dinhtc.app.mylearning.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dinhtc.app.mylearning.BuildConfig.DEBUG
import dinhtc.app.mylearning.common.Constants.BASE_URL
import dinhtc.app.mylearning.common.Constants.CONNECT_TIMEOUT
import dinhtc.app.mylearning.common.Constants.READ_TIMEOUT
import dinhtc.app.mylearning.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyLearningModule {

    @Provides
    fun providerApiInterface(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)

        val logging = HttpLoggingInterceptor()
        if (DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }

        okHttpClient.addInterceptor(logging)
        return okHttpClient.build()
    }

    /*
     context in @Module :
        (@ApplicationContext context: Context)
     */
}