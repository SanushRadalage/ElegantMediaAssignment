package com.sanush.elegantmedia.presentation.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanush.elegantmedia.domain.model.Cat
import com.sanush.elegantmedia.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class ListViewModel  @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val cats: MutableState<List<Cat>> = mutableStateOf(ArrayList())

    val loading = mutableStateOf(false)

    init {
        get()
    }

    fun get() {
        viewModelScope.launch {
            loading.value = true
            delay(2000)
            val result = repository.get()
            cats.value = result
            loading.value = false

        }
    }

}