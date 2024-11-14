package com.rbb.minireddit.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbb.minireddit.data.HomeRepository
import com.rbb.minireddit.domain.models.RedditPost
import com.rbb.minireddit.network.model.asDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _redditPosts = MutableStateFlow<List<RedditPost>>(emptyList())
    val redditPosts: StateFlow<List<RedditPost>> = _redditPosts

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getHotPosts() {
        viewModelScope.launch {
            repository.getHotPosts()
                .collect { result ->
                    result.onSuccess { posts ->
                        _redditPosts.value = posts.asDomain()

                    }.onFailure { exception ->
                        _error.postValue(exception.message)
                    }
                }
        }
    }
}