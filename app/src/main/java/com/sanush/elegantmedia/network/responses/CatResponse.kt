package com.sanush.elegantmedia.network.responses

import com.sanush.elegantmedia.domain.model.Cat

data class CatResponse(
    val `data`: List<Cat>,
    val status: Int
)