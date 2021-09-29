package com.example.newhealios.list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhealios.adapter.PostAdapter
import com.example.newhealios.callback.ItemPostClick
import com.example.data.base.Status
import com.example.newhealios.R
import com.example.domain.model.Post
//import com.example.newhealios.domain.usecase.GetUserListUseCase
import com.example.newhealios.base.BaseFragment
import com.example.newhealios.detail.PostDetailFragment
import com.example.newhealios.utils.FragmentUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post_list.*
import java.io.IOException

@AndroidEntryPoint
class PostListFragment : BaseFragment(), ItemPostClick {


    private val mPostListViewModel: PostListViewModel by viewModels()
    private lateinit var mAdapter: PostAdapter

    companion object {

        val TAG = PostListFragment::class.java.simpleName

        fun newInstance() = PostListFragment()
    }

    override val layout: Int
        get() = R.layout.fragment_post_list

    override fun setupUI() {
        rvPost.layoutManager = LinearLayoutManager(context)
        mAdapter = context?.let { PostAdapter(it, arrayListOf()) }!!
        mAdapter.seListener(this)
        rvPost.addItemDecoration(
            DividerItemDecoration(
                context,
                (rvPost.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvPost.adapter = mAdapter
        lifecycleScope.launchWhenStarted {
            mPostListViewModel.getPostList()
        }
    }

    override fun setupObserver() {
        mPostListViewModel.getResourceLiveData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                    rvPost.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    hideLoading()

                    var message = ""
                    if (it.throwable is IOException) {
                        message = requireContext().getString(R.string.txt_error_network)
                    } else {
                        message = requireContext().getString(R.string.txt_error_process)
                    }
                    showDialogAlert(context, message)
                }
                else -> Unit
            }
        })

        mPostListViewModel.getPostLiveData().observe(viewLifecycleOwner, {
            hideLoading()
            it?.let { posts -> renderList(posts) }
            rvPost.visibility = View.VISIBLE
        })
    }

    private fun renderList(posts: List<Post>) {
        mAdapter.setDataList(posts)
        mAdapter.notifyDataSetChanged()
    }

    override fun onClickItem(pos: Int, post: Post?) {
        activity?.let {
            FragmentUtils.addFragment(
                it.supportFragmentManager,
                PostDetailFragment.newInstance(post!!),
                R.id.container,
                true,
                PostDetailFragment.TAG
            )
        }
    }
}