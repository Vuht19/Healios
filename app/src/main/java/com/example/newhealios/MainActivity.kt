package com.example.newhealios

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.newhealios.list.PostListFragment
import com.example.newhealios.list.PostListFragment.Companion.TAG
import com.example.newhealios.utils.FragmentUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(FragmentUtils) {
            addFragment(
                supportFragmentManager,
                PostListFragment.newInstance(),
                R.id.container,
                true,
                "TAG"
            )
        }
    }
}