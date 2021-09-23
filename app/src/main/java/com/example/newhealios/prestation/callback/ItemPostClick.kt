package com.example.healios.ui.fragment.callback

import com.example.newhealios.domain.model.Post

interface ItemPostClick {
    fun onClickItem(pos: Int, post: Post?)
}