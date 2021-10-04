package com.example.newhealios.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.base.ResultWrapper
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

    private val postDetailLiveData = MutableLiveData<ResultWrapper<PostDetail>>()

    fun getPostDetail(post: Post) {
        viewModelScope.launch {
            getPostDetailUseCase.execute(post)
                .flowOn(Dispatchers.IO)
                .collect {
                    postDetailLiveData.postValue(it)
                }
        }
    }

    fun getPostDetailLiveData(): LiveData<ResultWrapper<PostDetail>> {
        return postDetailLiveData
    }
}
