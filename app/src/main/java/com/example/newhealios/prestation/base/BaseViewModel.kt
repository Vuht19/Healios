package com.example.newhealios.prestation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healios.utils.Resource
import com.example.healios.utils.Status

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