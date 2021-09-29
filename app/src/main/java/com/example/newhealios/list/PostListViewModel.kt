package com.example.newhealios.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Post
import com.example.domain.usecase.GetPostListUseCase
import com.example.newhealios.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(val getPostListUseCase: GetPostListUseCase) :
    BaseViewModel() {

    private val postLiveData = MutableLiveData<List<Post>>()

    fun getPostList() {
        viewModelScope.launch {
            getPostListUseCase.execute()
                .onStart { showLoading() }
                .onCompletion {
                    getPostListUseCase.getPostsFromNetwork()
                        .onCompletion { hideLoading() }
                        .catch {
                            showDialogError(it)
                        }.collect {
                            postLiveData.postValue(it)
                        }
                }.flowOn(Dispatchers.IO)
                .catch {
                    showDialogError(it)
                }.collect {
                    postLiveData.postValue(it)
                }
        }
    }

    fun getPostLiveData(): LiveData<List<Post>> {
        return postLiveData
    }
}