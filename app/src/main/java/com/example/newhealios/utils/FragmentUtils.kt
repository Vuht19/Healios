package com.example.newhealios.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {
    fun addFragment(
        supportFragment: FragmentManager,
        fragment: Fragment,
        idContainer: Int,
        isAddBackStack: Boolean,
        fragmentTag: String
    ) {
        val beginTransaction = supportFragment.beginTransaction()
        if (isAddBackStack) {
            beginTransaction.add(idContainer, fragment, fragmentTag).addToBackStack(fragmentTag)
                .commit()
        } else {
            beginTransaction.add(idContainer, fragment, fragmentTag).disallowAddToBackStack()
                .commit()
        }
    }
}