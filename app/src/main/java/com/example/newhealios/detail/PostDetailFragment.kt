package com.example.newhealios.detail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.base.ResultWrapper
import com.example.newhealios.R
import com.example.domain.model.Post
import com.example.domain.model.PostDetail
import com.example.healios.ui.adapter.CommentAdapter
import com.example.newhealios.base.BaseFragment
import com.example.newhealios.list.PostListFragment
import com.example.newhealios.utils.view.StableView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post_detail.*
import kotlinx.android.synthetic.main.fragment_post_detail.view.*
import kotlinx.android.synthetic.main.fragment_post_list.*
import kotlinx.android.synthetic.main.fragment_post_list.view.*
import java.io.IOException

@AndroidEntryPoint
class PostDetailFragment : BaseFragment() {

    private lateinit var contentView: ViewGroup

    companion object {
        val TAG = PostListFragment::class.java.simpleName

        const val BUNDLE_EXTRAS_POST = "BUNDLE_EXTRAS_POST"

        fun newInstance(post: Post): PostDetailFragment {
            val args = Bundle()
            val fragment = PostDetailFragment()
            fragment.arguments = args
            args.putSerializable(BUNDLE_EXTRAS_POST, post)
            return fragment
        }
    }

    private val mDetailPostViewModel: PostDetailViewModel by viewModels()
    private lateinit var mPost: Post
    private lateinit var mCommentAdapter: CommentAdapter

    override val layout: Int
        get() = R.layout.fragment_post_detail

    override fun setupUI() {
        if (arguments != null && requireArguments().containsKey(BUNDLE_EXTRAS_POST)) {
            mPost = requireArguments().getSerializable(BUNDLE_EXTRAS_POST) as Post
        }

        stableView = stateViewDetail
        stableView.setContentView(llContent)
        contentView = stableView.getContentView()
        contentView.rvComment.layoutManager = LinearLayoutManager(context)
        contentView.rvComment.addItemDecoration(
            DividerItemDecoration(
                context,
                (contentView.rvComment.layoutManager as LinearLayoutManager).orientation
            )
        )

        contentView.rvComment.layoutManager = LinearLayoutManager(context)

        mCommentAdapter = context?.let { CommentAdapter(it, arrayListOf()) }!!
        contentView.rvComment.addItemDecoration(
            DividerItemDecoration(
                context,
                (stableView.rvComment.layoutManager as LinearLayoutManager).orientation
            )
        )
        contentView.rvComment.adapter = mCommentAdapter

        ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        lifecycleScope.launchWhenStarted {
            mDetailPostViewModel.getPostDetail(mPost)
        }
    }

    override fun setupObserver() {
        mDetailPostViewModel.getPostDetailLiveData().observe(viewLifecycleOwner, {
            updateStateView(it)
        })
    }

    override fun <T> updateViewContentView(data: T) {
        if (data is PostDetail) setDataToView(data)
    }

    private fun setDataToView(postDetail: PostDetail?) {
        postDetail?.let {
            if (!it.comments.isNullOrEmpty()) {
                val user = it.user
                tvID.text = user.idUser.toString()
                tvName.text = user.name
                tvUserName.text = user.username
                tvEmail.text = user.email
                tvPhone.text = user.phone
                tvWebsite.text = user.website
                tvCompany.text = user.company.toString()
                tvAddress.text = user.address.toString()
                mCommentAdapter.setDataList(it.comments)
            }
        }
    }
}