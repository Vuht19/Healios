package com.example.newhealios.callback

import com.example.domain.model.Post

interface ItemPostClick {
    fun onClickItem(pos: Int, post: Post?)
}