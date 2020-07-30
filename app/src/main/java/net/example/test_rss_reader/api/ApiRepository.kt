package net.example.test_rss_reader.api

import net.example.test_rss_reader.model.Feed
import net.newstyleservice.common_ktx.extension.createRetrofitService
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object ApiRepository {
    private val service by lazy {
        "https://b.hatena.ne.jp/".createRetrofitService(
            ApiService::class.java,
            // TODO: XmlConverterは自作必要？ https://qiita.com/toastkidjp/items/8a699c4c8ca38f0081f3
            converterFactory = SimpleXmlConverterFactory.create()
        )
    }

    suspend fun requestHomeNews(): List<Feed>? = service.requestHomeNews()?.feedList

    suspend fun requestSocialNews(): List<Feed>? =
        service.requestSocialNews()?.feedList

    suspend fun requestEconomicsNews(): List<Feed>? =
        service.requestEconomicsNews()?.feedList

    suspend fun requestLifeNews(): List<Feed>? = service.requestLifeNews()?.feedList
}
