package com.example.newhealios.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.base.ResultWrapper
import com.example.domain.model.Post
import com.example.domain.model.PostResult
import com.example.domain.usecase.GetPostListUseCase
import com.example.newhealios.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val getPostListUseCase: GetPostListUseCase) :
    BaseViewModel() {

    private val resourceLiveData = MutableLiveData<ResultWrapper<PostResult>>()

    fun getPostList() {
        viewModelScope.launch {
            getPostListUseCase.execute()
                .flowOn(Dispatchers.IO)
                .collect {
                    resourceLiveData.postValue(it)
                }
        }
    }

    fun getResourceLiveData(): LiveData<ResultWrapper<PostResult>> {
        return resourceLiveData
    }
}