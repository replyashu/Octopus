package com.ashu.ocotopus.ui.favorite

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashu.ocotopus.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val sharedPreferences: SharedPreferences by lazy { requireContext().getSharedPreferences("preference_key", Context.MODE_PRIVATE) }


    private var _binding: FragmentFavoriteBinding? = null

    private var adapter: FavoriteAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initValues()
        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    private fun initValues() {
        val userId = sharedPreferences.getString("user_uuid", null)

        userId?.let {
            viewModel.fetchFavoriteDish(userId)
        }

        viewModel.favoriteDish.observe(viewLifecycleOwner) {
            binding.recyclerFavoriteDish.layoutManager = LinearLayoutManager(context)
            adapter = FavoriteAdapter(it.data)

            binding.recyclerFavoriteDish.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}