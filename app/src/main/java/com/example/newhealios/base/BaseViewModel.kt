package com.example.newhealios.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.base.Resource
import com.example.data.base.Status

open class BaseViewModel : ViewModel() {

    protected val resourceLiveData = MutableLiveData<Resource>()

    fun showLoading() {
        resourceLiveData.postValue(Resource(Status.LOADING, null))
    }

    fun hideLoading() {
        resourceLiveData.postValue(Resource(Status.HIDE_LOADING, null))
    }

    fun showDialogError(throwable: Throwable) {
        resourceLiveData.postValue(
            Resource(Status.ERROR, throwable)
        )
    }

    fun updateViewEmptyData() {
        resourceLiveData.postValue(Resource(Status.EMPTY_DATA, null))
    }

    fun getResourceLiveData(): LiveData<Resource> {
        return resourceLiveData
    }
}