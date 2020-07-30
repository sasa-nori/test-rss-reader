package net.example.test_rss_reader.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class Feed @JvmOverloads constructor(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String?,
    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)
    val description: String?,
    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: String?,
    @field:Element(name = "date")
    @param:Element(name = "date")
    val date: String?,
    @field:Element(name = "imageurl", required = false)
    @param:Element(name = "imageurl", required = false)
    val imageUrl: String? = null
) {
    fun formatDate(): String {
        //2020-07-30T07:25:59Z
        return date?.split("T")?.get(0)?.replace("-", "/") ?: ""
    }
}
