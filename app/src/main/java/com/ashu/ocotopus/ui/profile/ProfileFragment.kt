package com.ashu.ocotopus.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.ProfileUser
import com.ashu.ocotopus.databinding.FragmentProfileBinding
import com.ashu.ocotopus.util.Status
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>()
    private val sharedPreferences by lazy { context?.getSharedPreferences("preference_key", Context.MODE_PRIVATE) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initializeUI()
        return root
    }

    private fun initializeUI() {
        binding.imageProfile.bringToFront()
        val userId = sharedPreferences?.getString("user_uuid", null)
        viewModel.fetchProfileData(userId)

        viewModel.profile.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                populateProfileFields(it.data)
            }
        }
    }

    private fun populateProfileFields(user: ProfileUser?) {
        binding.apply {
            user?.let {
                Glide.with(requireContext())
                    .load(it.profilePhoto)
                    .placeholder(R.drawable.ic_office_worker_icon)
                    .into(imageProfile)

                if (it.name.isNullOrEmpty()) {
                    textProfileName.visibility = View.GONE
                } else {
                    textProfilePhone.visibility = View.VISIBLE
                }

                if (it.email.isNullOrEmpty()) {
                    textProfileEmail.visibility = View.GONE
                } else {
                    textProfileEmail.visibility = View.VISIBLE
                }

                if (it.mediumOfRegistration.isNullOrEmpty()) {
                    textProfileMedium.visibility = View.GONE
                } else {
                    textProfileMedium.visibility = View.VISIBLE
                }

                if (it.phoneNumber.isNullOrEmpty()) {
                    textProfilePhone.visibility = View.GONE
                } else {
                    textProfilePhone.visibility = View.VISIBLE
                }

                textProfileName.text = it.name
                textProfileEmail.text = it.email
                textProfileMedium.text = it.mediumOfRegistration
                textProfilePhone.text = it.phoneNumber
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}