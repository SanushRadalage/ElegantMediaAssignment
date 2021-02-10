package com.sanush.elegantmedia.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import com.sanush.elegantmedia.R
import com.sanush.elegantmedia.presentation.components.CardView
import com.sanush.elegantmedia.presentation.components.CircularIndeterminateProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    private lateinit var toolBar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val cats = viewModel.cats.value
                val loading = viewModel.loading.value

                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp)
                    ) {
                        LazyColumn {
                            itemsIndexed(
                                items = cats
                            ) { index, cat ->
                                CardView(cat = cat, onClick = {
                                    val bundle = Bundle()
                                    bundle.putParcelable("DETAILS", cat)
                                    NavHostFragment.findNavController(this@ListFragment).navigate(
                                        R.id.action_listFragment_to_detailFragment,
                                        bundle
                                    )
                                })
                            }
                        }
                        CircularIndeterminateProgressBar(isDisplayed = loading)
                    }
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBar = requireActivity().findViewById(R.id.mainToolBar)
        toolBar.navigationIcon = null
        toolBar.title = getString(R.string.listView)

        backCallBack()
    }

    private fun backCallBack() {

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this as LifecycleOwner, callback)
    }
}