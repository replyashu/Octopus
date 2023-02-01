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
import com.ashu.ocotopus.databinding.FragmentEditProfileBinding
import com.ashu.ocotopus.databinding.FragmentProfileBinding
import com.ashu.ocotopus.util.Status
import com.ashu.ocotopus.util.clickWithDebounce
import com.ashu.ocotopus.util.toBase64
import com.ashu.ocotopus.util.toUri
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream

@AndroidEntryPoint
class EditProfileFragment: Fragment() {

    private var _binding: FragmentEditProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()

    private var imageUrl: String? = null

    private val sharedPreferences by lazy { context?.getSharedPreferences("preference_key", Context.MODE_PRIVATE) }


    companion object {
        fun createInstance(): EditProfileFragment {
            return EditProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initializeUI()
        return root
    }

    private fun initializeUI() {
        binding.imageProfile.bringToFront()
        val userData: ProfileUser? = arguments?.getParcelable("user_data") as ProfileUser?
//        val profileImage = arguments?.getString("profile_image")
//        val profileName = arguments?.getString("profile_name")
//        val profileEmail = arguments?.getString("profile_email")
//        val profilePhone = arguments?.getString("profile_phone")
//        val profileSrc = arguments?.getString("profile_src")

        binding.apply {
            try {
                if (userData?.transferImage != null) {
                    Glide.with(requireContext())
                        .load(userData.transferImage)
                        .placeholder(R.drawable.ic_office_worker_icon)
                        .into(imageProfile)
                    imageUrl = userData.transferImage?.toBase64()
                } else {
                    Glide.with(requireContext())
                        .load(userData?.profilePhoto)
                        .placeholder(R.drawable.ic_office_worker_icon)
                        .into(imageProfile)
                    imageUrl = userData?.profilePhoto
                }

            } catch (e: Exception) {
                Glide.with(requireContext())
                    .load(userData?.profilePhoto)
                    .placeholder(R.drawable.ic_office_worker_icon)
                    .into(imageProfile)
                imageUrl = userData?.profilePhoto
            }

            textProfileName.setText(userData?.name)
            textProfileEmail.setText(userData?.email)
            textProfilePhone.setText(userData?.phoneNumber)

            imageProfile.clickWithDebounce {
                chooseSource()
            }

            buttonSaveProfile.clickWithDebounce {
                val name = textProfileName.editableText.toString()
                val email = textProfileEmail.editableText.toString()
                val phone = textProfilePhone.editableText.toString()

                val userId = sharedPreferences?.getString("user_uuid", null)

                val profileUser = ProfileUser(email, name, phone, imageUrl, imageUrl)
                viewModel.updateProfile(userId, profileUser)
            }
        }

        viewModel.editProfile.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun chooseSource() {
        val optionsMenu = arrayOf<CharSequence>(
            "Take Photo",
            "Choose from Gallery",
            "Exit"
        ) // create a menuOption Array

        // create a dialog for showing the optionsMenu
        // create a dialog for showing the optionsMenu
        val builder = AlertDialog.Builder(context)
        // set the items in builder
        // set the items in builder
        builder.setItems(optionsMenu) { dialogInterface, i ->
            if (optionsMenu[i] == "Take Photo") {
                // Open the camera and get the photo
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                openCamera(takePicture)
            } else if (optionsMenu[i] == "Choose from Gallery") {
                // choose from  external storage
                openGalleryForImages()
            } else if (optionsMenu[i] == "Exit") {
                dialogInterface.dismiss()
            }
        }
        builder.show()
    }

    private val activityResultForGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        when (it.resultCode) {
            Activity.RESULT_OK -> {
                val data = it.data
                data?.let {
                    val imageUri: Uri = data.data!!
                    val imageStream: InputStream? =
                        requireActivity().contentResolver.openInputStream(imageUri)
                    val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val bm =
                        MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri);
                    imageUrl = bm.toBase64()
                    // Get the cursor

                    // Get the cursor
                    val cursor: Cursor? = requireContext().contentResolver.query(
                        imageUri,
                        filePathColumn, null, null, null
                    )
                    // Move to first row
                    // Move to first row
//                        cursor?.moveToFirst()
//
//                        val columnIndex: Int = cursor?.getColumnIndex(filePathColumn[0])!!
//                        val imgDecodableString = cursor?.getString(columnIndex)
//                        cursor.close()
//                        doRequest(imgDecodableString)
                    Glide.with(requireContext()).load(imageUri)
                        .placeholder(R.drawable.empty_plate)
                        .into(binding.imageProfile)
                }
            }
        }
    }

    private val activityResultForCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { it1 ->
        when (it1.resultCode) {
            Activity.RESULT_OK -> {
                val selectedImage: Bitmap = it1.data?.extras?.get("data") as Bitmap
                selectedImage.let { it ->
                    val data: Uri = it.toUri(requireContext())
                    imageUrl = selectedImage.toBase64()
                    Glide.with(requireContext()).load(it)
                        .placeholder(R.drawable.empty_plate)
                        .into(binding.imageProfile)
                }
            }
        }
    }

    private fun openCamera(intent: Intent) {
        activityResultForCamera.launch(intent)
    }

    private fun openGalleryForImages() {
        if (Build.VERSION.SDK_INT < 19) {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            intent.action = Intent.ACTION_GET_CONTENT
            activityResultForGallery.launch(Intent.createChooser(intent, "Choose Pictures"))
        }
        else { // For latest versions API LEVEL 19+
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            activityResultForGallery.launch(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}