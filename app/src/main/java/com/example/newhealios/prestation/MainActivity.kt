package com.example.newhealios.prestation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newhealios.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}