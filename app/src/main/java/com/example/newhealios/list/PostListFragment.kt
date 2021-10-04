package com.example.newhealios.list

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhealios.callback.ItemPostClick
import com.example.newhealios.R
import com.example.domain.model.Post
import com.example.domain.model.PostResult
import com.example.healios.ui.adapter.PostAdapter
import com.example.newhealios.base.BaseFragment
import com.example.newhealios.detail.PostDetailFragment
import com.example.newhealios.utils.FragmentUtils
import com.example.newhealios.utils.view.StableView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post_list.*
import kotlinx.android.synthetic.main.fragment_post_list.view.*
import kotlinx.android.synthetic.main.stable_layout.view.*

@AndroidEntryPoint
class PostListFragment : BaseFragment(), ItemPostClick {


    private val mPostListViewModel: PostListViewModel by viewModels()
    private lateinit var mAdapter: PostAdapter
    private lateinit var contentView: ViewGroup

    companion object {

        val TAG = PostListFragment::class.java.simpleName

        fun newInstance() = PostListFragment()
    }

    override val layout: Int
        get() = R.layout.fragment_post_list

    override fun setupUI() {
        mAdapter = context?.let { PostAdapter(it, arrayListOf()) }!!
        mAdapter.seListener(this)
        
        stableView = stateViewContainer
        stableView.setContentView(flContentView)
        contentView = stableView.getContentView()
        contentView.rvPost.layoutManager = LinearLayoutManager(context)
        contentView.rvPost.addItemDecoration(
            DividerItemDecoration(
                context,
                (contentView.rvPost.layoutManager as LinearLayoutManager).orientation
            )
        )
        contentView.rvPost.adapter = mAdapter

        lifecycleScope.launchWhenStarted {
            mPostListViewModel.getPostList()
        }
    }

    override fun setupObserver() {
        mPostListViewModel.getResourceLiveData().observe(viewLifecycleOwner, {
            updateStateView(it)
        })
    }

    override fun <T> updateViewContentView(data: T) {
        if (data is PostResult) renderList(data.posList)
    }

    private fun renderList(posts: List<Post>) {
        mAdapter.setDataList(posts)
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