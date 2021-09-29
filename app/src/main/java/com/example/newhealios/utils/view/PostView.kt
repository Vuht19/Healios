package com.example.newhealios.utils.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.newhealios.R
import kotlinx.android.synthetic.main.post_view.view.*

class PostView(context: Context, private val screen: Int) : FrameLayout(context) {

    companion object {
        const val POST_LIST_SCREEN = 0
        const val POST_DETAIL_SCREEN = 1
    }

    init {
        initView(screen)
    }

    private fun initView(screen: Int) {
        LayoutInflater.from(context).inflate(R.layout.post_view, null, false)
        if (screen == POST_LIST_SCREEN) {
            cvUserInfo.visibility = GONE
        } else {
            cvUserInfo.visibility = VISIBLE
        }
    }

//    fun setState(resourceWrapper: ResourceWrapper<Any>) {
//        when (resourceWrapper) {
//            is ResourceWrapper.Success -> updateViewSuccess(resourceWrapper.getResourceValue() as ResourceWrapper.Success<List<Post>>)
//            is ResourceWrapper.Loading -> updateViewLoading(resourceWrapper.getResourceValue() as Boolean)
//            is ResourceWrapper.Error -> updateViewError(resourceWrapper.getResourceValue() as Throwable)
//        }
//    }

    private fun updateViewError(throwable: Throwable?) {

    }

    private fun updateViewLoading(showLoading: Boolean) {
        if (showLoading) {
            llLoading.visibility = VISIBLE
        } else {
            llLoading.visibility = GONE
        }
    }

//    private fun updateViewSuccess(resourceWrapper: ResourceWrapper.Success<Any>) {
//
//    }
}