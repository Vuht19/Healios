package com.example.newhealios.utils.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.newhealios.databinding.StableLayoutBinding

class StableView : FrameLayout {

    companion object {
        const val VIEW_TYPE_ERROR = 0
        const val VIEW_TYPE_EMPTY = 1
        const val VIEW_TYPE_LOADING = 2
        const val VIEW_TYPE_SUCCESS = 3
    }

    private lateinit var mBinding: StableLayoutBinding
    private lateinit var contentView: ViewGroup

    constructor(context: Context, contentView: ViewGroup) : super(context) {
        this.contentView = contentView
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
    ) : super(context, attrs, defStyleAttr)

    init {
        initView()
    }

    private fun initView() {
        mBinding = StableLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setStateView(state: Int) {
        mBinding.llContainerLoading.visibility = VISIBLE
        mBinding.flContainerError.visibility = GONE
        mBinding.flContainerEmpty.visibility = GONE
        contentView.visibility = GONE
        when (state) {
            VIEW_TYPE_ERROR -> {
//                mBinding.flContainerError.visibility = VISIBLE
//                mBinding.flContainerEmpty.visibility = GONE
//                mBinding.llContainerLoading.visibility = GONE
//                contentView.visibility = GONE
            }
            VIEW_TYPE_EMPTY -> {
//                mBinding.flContainerEmpty.visibility = VISIBLE
//                mBinding.flContainerError.visibility = GONE
//                mBinding.llContainerLoading.visibility = GONE
//                contentView.visibility = GONE
            }
            VIEW_TYPE_LOADING -> {
//                mBinding.llContainerLoading.visibility = VISIBLE
//                mBinding.flContainerError.visibility = GONE
//                mBinding.flContainerEmpty.visibility = GONE
//                contentView.visibility = GONE
            }
            VIEW_TYPE_SUCCESS -> {
//                mBinding.llContainerLoading.visibility = GONE
//                contentView.visibility = VISIBLE
//                mBinding.flContainerError.visibility = GONE
//                mBinding.flContainerEmpty.visibility = GONE
            }
        }
    }

    fun setTextEmpty(content: String) {
        mBinding.tvEmpty.text = content
    }

    fun setTextError(content: String) {
        mBinding.tvError.text = content
    }

    fun setContentView(viewGroup: ViewGroup) {
        this.contentView = viewGroup
    }

    fun getContentView(): ViewGroup {
        return contentView
    }
}
