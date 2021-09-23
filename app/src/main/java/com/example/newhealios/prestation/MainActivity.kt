package com.example.newhealios.prestation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.newhealios.prestation.list.PostListFragment
import com.example.newhealios.R
import com.example.newhealios.prestation.list.PostListFragment.Companion.TAG
import com.example.newhealios.prestation.utils.FragmentUtils
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
                false,
                TAG
            )
        }
    }
}