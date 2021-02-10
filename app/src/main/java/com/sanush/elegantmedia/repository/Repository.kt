package com.sanush.elegantmedia.repository

import com.sanush.elegantmedia.domain.model.Cat

interface Repository {
    suspend fun get(): List<Cat>
}