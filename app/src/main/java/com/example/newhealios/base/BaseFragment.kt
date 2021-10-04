package com.example.newhealios.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.data.base.ResultWrapper
import com.example.domain.model.PostResult
import com.example.newhealios.R
import com.example.newhealios.utils.view.StableView
import java.io.IOException

abstract class BaseFragment : Fragment() {

    protected lateinit var stableView: StableView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return LayoutInflater.from(context).inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    abstract fun setupUI()
    abstract fun setupObserver()
    abstract fun <T> updateViewContentView(data: T)

    abstract val layout: Int

    protected fun <T> updateStateView(resultWrapper: ResultWrapper<T>) {
        val state: Int
        when (resultWrapper) {
            is ResultWrapper.Loading -> {
                state = StableView.VIEW_TYPE_LOADING
            }
            is ResultWrapper.NetworkError -> {
                state = StableView.VIEW_TYPE_ERROR
                val message: String
                (requireContext().getString(R.string.txt_error_network)).also {
                    message = it
                    stableView.setTextError(message)
                }
            }
            is ResultWrapper.GenericError -> {
                //Handle Error
                state = StableView.VIEW_TYPE_ERROR
                val message: String
                (if (resultWrapper is IOException) {
                    requireContext().getString(R.string.txt_error_network)
                } else {
                    requireContext().getString(R.string.txt_error_process)
                }).also {
                    message = it
                    stableView.setTextError(message)
                }
            }
            is ResultWrapper.Success -> {
                state = StableView.VIEW_TYPE_SUCCESS
                updateViewContentView(resultWrapper.value)
            }
            else -> TODO()
        }
        stableView.setStateView(state)
    }
}