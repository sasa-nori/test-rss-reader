package net.example.test_rss_reader.model

import java.io.Serializable

enum class PageType(val position: Int) : Serializable {
    /** 総合 */
    TOP_PAGE(0),

    /** 世の中 */
    SOCIAL(1),

    /** 政治と経済 */
    ECONOMICS(2),

    /** 暮らし */
    LIFE(3)
}
