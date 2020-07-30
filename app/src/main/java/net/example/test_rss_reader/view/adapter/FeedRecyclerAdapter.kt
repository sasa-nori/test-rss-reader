package net.example.test_rss_reader.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import net.example.test_rss_reader.R
import net.example.test_rss_reader.model.Feed
import net.newstyleservice.common_ktx.extension.inflate
import net.newstyleservice.common_ktx.extension.setOnSingleClickListener

class FeedRecyclerAdapter(
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val feedList = mutableListOf<Feed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TOP_NEWS_TYPE -> TopNewsViewHolder(parent.inflate(layout = R.layout.list_item_large))
            else -> NormalNewsViewHolder(parent.inflate(layout = R.layout.list_item_normal))
        }

    override fun getItemCount(): Int = feedList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = feedList[holder.adapterPosition]
        holder.itemView.let {
            it.setOnSingleClickListener {
                itemClickListener.onItemClick(feed)
            }
            val titleView: TextView = holder.itemView.findViewById(R.id.title)
            titleView.text = feed.title
            val descriptionView: TextView = holder.itemView.findViewById(R.id.description)
            descriptionView.text = feed.description
            val dateView: TextView = holder.itemView.findViewById(R.id.date)
            dateView.text = feed.formatDate()
            val thumbnailView: ImageView = holder.itemView.findViewById(R.id.thumbnail)
            thumbnailView.load(feed.imageUrl) {
                error(R.drawable.ic_no_image)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> TOP_NEWS_TYPE
        else -> super.getItemViewType(position)
    }

    fun updateList(list: List<Feed>) {
        if (feedList.isNotEmpty()) {
            feedList.clear()
        }
        feedList.addAll(list)
        notifyDataSetChanged()
    }

    inner class TopNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class NormalNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface ItemClickListener {
        fun onItemClick(feed: Feed)
    }

    companion object {
        private const val TOP_NEWS_TYPE = 1
    }
}
