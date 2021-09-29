package com.example.newhealios.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Post
import com.example.domain.model.PostDetail
import com.example.domain.usecase.GetPostDetailUseCase
import com.example.newhealios.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(private val getPostDetailUseCase: GetPostDetailUseCase) :
    BaseViewModel() {

    private val postDetailLiveData = MutableLiveData<PostDetail>()

    fun getPostDetail(post: Post) {
        viewModelScope.launch {
            getPostDetailUseCase.execute(post)
                .onStart { showLoading() }
                .onCompletion {
                    getPostDetailUseCase.getPostDetailFromNetwork(post)
                        .onCompletion { hideLoading() }
                        .catch {
                            showDialogError(it)
                        }.collect {
                            postDetailLiveData.postValue(it)
                        }
                }.flowOn(Dispatchers.IO)
                .catch {
                    showDialogError(it)
                }.collect {
                    postDetailLiveData.postValue(it)
                }
        }
    }

    fun getPostDetailLiveData(): LiveData<PostDetail> {
        return postDetailLiveData
    }
}