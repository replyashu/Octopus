package com.ashu.ocotopus.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashu.ocotopus.R
import com.ashu.ocotopus.databinding.FragmentHomeBinding
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpUIElements()
        return root
    }

    private fun setUpUIElements() {

        binding.apply {

            imageView0.setOnClickListener {
                carouselDishOfTheDay.transitionToIndex(2, 100)
            }
            imageView1.setOnClickListener {
                carouselDishOfTheDay.transitionToIndex(3,100)
            }
            imageView2.setOnClickListener {
                carouselDishOfTheDay.transitionToIndex(4, 100)
            }
            imageView3.setOnClickListener {
                carouselDishOfTheDay.transitionToIndex(5,100)
            }
            imageView4.setOnClickListener {
                carouselDishOfTheDay.transitionToIndex(0, 1000)
            }
            carouselDishOfTheDay.setAdapter(object : Carousel.Adapter {
                override fun count(): Int {
                    return 5
                }

                override fun populate(view: View?, index: Int) {
//                    if (view is AppCompatImageView) {
//
//                    }
                }

                override fun onNewItem(index: Int) {

                }

            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}