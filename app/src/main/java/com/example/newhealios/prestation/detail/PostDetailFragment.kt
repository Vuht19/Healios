package com.example.newhealios.prestation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healios.ui.adapter.CommentAdapter
import com.example.healios.utils.Status
import com.example.newhealios.R
import com.example.newhealios.domain.model.Post
import com.example.newhealios.domain.model.PostDetail
import com.example.newhealios.prestation.base.BaseFragment
import com.example.newhealios.prestation.list.PostListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post_detail.*
import kotlinx.android.synthetic.main.fragment_post_list.*
import java.io.IOException

@AndroidEntryPoint
class PostDetailFragment : BaseFragment() {

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
        rvComment.layoutManager = LinearLayoutManager(context)
        mCommentAdapter = context?.let { CommentAdapter(it, arrayListOf()) }!!
        rvComment.addItemDecoration(
            DividerItemDecoration(
                context,
                (rvComment.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvComment.adapter = mCommentAdapter

        ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        lifecycleScope.launchWhenStarted {
            mDetailPostViewModel.getPostDetail(mPost)
        }
    }

    override fun setupObserver() {
        mDetailPostViewModel.getResourceLiveData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                    llContent.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    hideLoading()

                    val message: String
                    message = if (it.throwable is IOException) {
                        requireContext().getString(R.string.txt_error_network)
                    } else {
                        requireContext().getString(R.string.txt_error_process)
                    }
                    if (mCommentAdapter.itemCount == 0) {
                        llContent.visibility = View.INVISIBLE
                        rvComment.visibility = View.INVISIBLE
                    }
                    showDialogAlert(context, message)
                }
                Status.EMPTY_DATA -> {
                    llContent.visibility = View.GONE
                    rvComment.visibility = View.GONE
                }
                else -> Unit
            }
        })

        mDetailPostViewModel.getPostDetailLiveData().observe(viewLifecycleOwner, {
            hideLoading()
            llContent.visibility = View.VISIBLE
            rvComment.visibility = View.VISIBLE
            setDataToView(it)
        })
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
            } else {
                llContent.visibility = View.INVISIBLE
                rvComment.visibility = View.INVISIBLE
            }
        }
    }
}