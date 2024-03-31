package apps.sumit.apitest.features.data.db.model

import apps.sumit.apitest.features.domain.model.News
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmModel : RealmObject {
    @PrimaryKey
    var _id = ObjectId()
    var type: String = ""
    var name: String? = null
    var title: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var content: String? = null
    var description: String? = null
}

fun RealmModel.toNews(): News {
    return News(
        name, title, url, urlToImage, content, description
    )
}
