package net.example.test_rss_reader.api

import net.example.test_rss_reader.model.FeedResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/hotentry.rss")
    suspend fun requestHomeNews(): FeedResponse?

    @GET("/hotentry/social.rss")
    suspend fun requestSocialNews(): FeedResponse?

    @GET("/hotentry/economics.rss")
    suspend fun requestEconomicsNews(): FeedResponse?

    @GET("/hotentry/life.rss")
    suspend fun requestLifeNews(): FeedResponse?
}
