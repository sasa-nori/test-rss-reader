package net.example.test_rss_reader.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
data class FeedResponse(
    @field:ElementList(entry = "item", inline = true)
    @param:ElementList(entry = "item", inline = true)
    val feedList: List<Feed>?
)
