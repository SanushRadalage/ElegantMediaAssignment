package com.sanush.elegantmedia.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.sanush.elegantmedia.R
import com.sanush.elegantmedia.domain.model.Cat
import com.sanush.elegantmedia.presentation.ui.map.MapsActivity
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var toolBar: Toolbar

    private lateinit var locationButton: ImageButton

    lateinit var cat: Cat

    private lateinit var titleText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var imageView: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBar = requireActivity().findViewById(R.id.mainToolBar)
        toolBar.navigationIcon = ResourcesCompat.getDrawable(
            requireActivity().resources,
            R.drawable.ic_baseline_arrow_back_24,
            null
        )
        toolBar.title = getString(R.string.details)

        toolBar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        locationButton = setUpLocationButton()
        toolBar.addView(locationButton)

        cat = requireArguments().getParcelable("DETAILS")!!

        titleText = requireView().findViewById(R.id.titleTxt)
        descriptionText = requireView().findViewById(R.id.descriptionTxt)
        imageView = requireView().findViewById(R.id.imageView)

        titleText.text = cat.title
        descriptionText.text = cat.description
        Glide.with(this)
            .asBitmap()
            .load(cat.image.medium)
            .placeholder(R.drawable.empty_image)
            .circleCrop()
            .useAnimationPool(true)
            .into(imageView)


        backCallBack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        toolBar.removeView(locationButton)
    }

    private fun backCallBack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                NavHostFragment.findNavController(this@DetailFragment).popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this as LifecycleOwner, callback)
    }

    private fun setUpLocationButton(): ImageButton {

        val b1 = ImageButton(requireContext())
        b1.setImageResource(R.drawable.ic_baseline_location_on_24)
        b1.scaleType = ImageView.ScaleType.CENTER_CROP
        b1.setBackgroundResource(android.R.color.transparent)
        val l3 = Toolbar.LayoutParams(
            Toolbar.LayoutParams.WRAP_CONTENT,
            Toolbar.LayoutParams.WRAP_CONTENT
        )
        l3.setMargins(0, 0, 30, 0)
        l3.gravity = Gravity.END
        b1.layoutParams = l3


        b1.setOnClickListener {
            val intent = Intent(requireActivity(), MapsActivity::class.java)
            intent.putExtra("Lat", cat.latitude)
            intent.putExtra("Lon", cat.longitude)
            intent.putExtra("Address", cat.address)
            startActivity(intent)
        }

        return b1
    }
}