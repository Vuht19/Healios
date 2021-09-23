package com.example.newhealios.prestation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newhealios.data.repository.HealiosRepository
import com.example.newhealios.domain.model.Post
import com.example.newhealios.domain.model.PostDetail
import com.example.newhealios.domain.usecase.GetPostDetailUseCase
import com.example.newhealios.prestation.base.BaseViewModel
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
                .onCompletion { hideLoading() }
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e("dladlalsaldada ", "catch: " + it.localizedMessage)
                    showDialogError(it)
                }.collect {
                    Log.e("dladlalsaldada ", "collect: " + it.comments.size)
                    postDetailLiveData.postValue(it)
                }
        }
    }

    fun getPostDetailLiveData(): LiveData<PostDetail> {
        return postDetailLiveData
    }
}
