package net.example.test_rss_reader.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedProgressViewModel : ViewModel() {

    private val progressState = MutableLiveData<State>()

    fun getProgressState(): LiveData<State> = progressState

    fun updateProgressState(state: State) {
        progressState.postValue(state)
    }

    enum class State {
        SHOW,
        HIDE
    }
}
