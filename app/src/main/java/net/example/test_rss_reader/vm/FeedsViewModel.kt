package net.example.test_rss_reader.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.example.test_rss_reader.api.ApiRepository
import net.example.test_rss_reader.model.Feed
import net.example.test_rss_reader.model.PageType

class FeedsViewModel : ViewModel() {
    private var feedList = MutableLiveData<List<Feed>>()

    fun getFeedList(): LiveData<List<Feed>> = feedList

    fun requestFeed(pageType: PageType) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {
            when (pageType) {
                PageType.TOP_PAGE -> ApiRepository.requestHomeNews()
                PageType.SOCIAL -> ApiRepository.requestSocialNews()
                PageType.ECONOMICS -> ApiRepository.requestEconomicsNews()
                PageType.LIFE -> ApiRepository.requestLifeNews()
            }
        }
        feedList.postValue(result)
    }
}
