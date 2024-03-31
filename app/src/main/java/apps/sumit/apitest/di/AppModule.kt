package apps.sumit.apitest.di

import apps.sumit.apitest.common.Constants.BASE_URL
import apps.sumit.apitest.features.data.db.model.RealmModel
import apps.sumit.apitest.features.data.db.repository.NewsRepoRealm
import apps.sumit.apitest.features.data.remote.NewsApi
import apps.sumit.apitest.features.data.remote.repository.NewsRepoRemote
import apps.sumit.apitest.features.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Remote

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Realm

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    @Remote
    fun provideRemoteRepository(api: NewsApi): NewsRepository {
        return NewsRepoRemote(api)
    }

    @Provides
    @Singleton
    fun proveRealm(): Realm {
        val configuration = RealmConfiguration.create(schema = setOf(RealmModel::class))
        return Realm.open(configuration)
    }

    @Provides
    @Singleton
    @apps.sumit.apitest.di.Realm
    fun provideRealmRepository(realm: Realm): NewsRepository {
        return NewsRepoRealm(realm)
    }

}
