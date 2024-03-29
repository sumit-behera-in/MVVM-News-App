package apps.sumit.apitest.features.data.db.repository

import apps.sumit.apitest.features.data.db.RealmModel
import apps.sumit.apitest.features.data.db.toNews
import apps.sumit.apitest.features.domain.model.NewsList
import apps.sumit.apitest.features.domain.repository.NewsRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class NewsRepoRealm(private val realm: Realm) : NewsRepository {
    override suspend fun getBreakingNews(): NewsList {
        return NewsList(
            type = "Breaking news",
            newsList = realm.query<RealmModel>("type = $0", "Breaking news").find().map {
                it.toNews()
            }
        )
    }

    override suspend fun search(q: String): NewsList {
        return NewsList(
            type = q,
            newsList = realm.query<RealmModel>("type= $0", q).find().map {
                it.toNews()
            }
        )
    }
}