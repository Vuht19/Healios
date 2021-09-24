package com.example.newhealios.prestation.list

import android.os.Debug
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newhealios.domain.model.Post
import com.example.newhealios.domain.usecase.GetPostListUseCase
import com.example.newhealios.prestation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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