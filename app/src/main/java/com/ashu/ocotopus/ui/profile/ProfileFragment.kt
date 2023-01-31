package com.ashu.ocotopus.ui.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.ProfileUser
import com.ashu.ocotopus.databinding.FragmentProfileBinding
import com.ashu.ocotopus.util.Status
import com.ashu.ocotopus.util.clickWithDebounce
import com.ashu.ocotopus.util.toBase64
import com.ashu.ocotopus.util.toUri
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>()
    private val sharedPreferences by lazy { context?.getSharedPreferences("preference_key", Context.MODE_PRIVATE) }

    private var profileUser: ProfileUser? = null

    private var imgSrc: String? = null
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
        binding.imageEditProfile.bringToFront()
        val userId = sharedPreferences?.getString("user_uuid", null)
        viewModel.fetchProfileData(userId)

        viewModel.profile.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                populateProfileFields(it.data)
            }
        }

        binding.imageEditProfile.clickWithDebounce {
            val transaction = parentFragmentManager.beginTransaction()
            val bundle = Bundle()
            val fragment = EditProfileFragment.createInstance()
            bundle.putSerializable("user_data", profileUser)
            fragment.arguments = bundle
            transaction.add(R.id.profile_container, fragment)
                .addToBackStack("profile")
                .setReorderingAllowed(true)
                .commit()
        }
    }

    private fun populateProfileFields(user: ProfileUser?) {
        binding.apply {
            user?.let {
                profileUser = it
                imgSrc = it.profileSrc
                if (imgSrc.isNullOrEmpty()) {

                    imgSrc = it.profilePhoto
                    Glide.with(requireContext())
                        .load(imgSrc)
                        .placeholder(R.drawable.ic_office_worker_icon)
                        .into(imageProfile)
                } else {
                    val imageByteArray: ByteArray = Base64.decode(imgSrc, Base64.DEFAULT)
                    val bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
                    Glide.with(requireContext()).load(bmp)
                        .error(R.drawable.octopus).placeholder(R.drawable.octopus).into(imageProfile)
                }

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