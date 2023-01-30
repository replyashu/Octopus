package com.ashu.ocotopus.ui.hungry

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.requests.AddDish
import com.ashu.ocotopus.databinding.FragmentHungryBinding
import com.ashu.ocotopus.util.ManagePermissions
import com.ashu.ocotopus.util.clickWithDebounce
import com.ashu.ocotopus.util.toBase64
import com.ashu.ocotopus.util.toUri
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.collections.HashMap


@AndroidEntryPoint
class HungryFragment : Fragment() {

    private var _binding: FragmentHungryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val PermissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions

    private val REQUEST_CODE = 100

    private var dishUrl: File? = null
    private var dishName: String? = null
    private var dishDescription: String? = null
    private var imageUrl: String? = null

    private val viewModel by viewModels<HungryViewModel>()

    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences("preference_key", Context.MODE_PRIVATE) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(HungryViewModel::class.java)

        _binding = FragmentHungryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val list = listOf<String>(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        // Initialize a new instance of ManagePermissions class
        managePermissions = ManagePermissions(requireActivity(),list,PermissionsRequestCode)

        initUI()
        return root
    }

    private fun initUI() {
        binding.imageUploadDish.clickWithDebounce {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (managePermissions.checkPermissions()) {
                    chooseSource()
                } else {
                    managePermissions.checkPermissions()
                }
            }
        }

        zeroCount()

        binding.textDishName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkReadytoSubmit()
            }

        })


        binding.textDishDescription.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty()!!) {
                    val words = s.toString().trim().split(" ").size
                    binding.textCharCount.text = getString(R.string.char_count, s.toString().length.toString())
                    binding.textWordCount.text = getString(R.string.word_count, words)
                } else {
                    zeroCount()
                }

                checkReadytoSubmit()
            }
        })

        binding.buttonSubmit.clickWithDebounce() {
            val userId = sharedPreferences.getString("user_uuid", null)
            val addDish = AddDish(binding.textDishName.editableText.toString(),
                binding.textDishDescription.editableText.toString(),
                imageUrl, userId)
            viewModel.postDish(addDish)
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

    private fun openCamera(intent: Intent) {
        activityResultForCamera.launch(intent)
    }

    private fun zeroCount() {
        binding.textCharCount.text = getString(R.string.char_count, 0)
        binding.textWordCount.text = getString(R.string.word_count, 0)
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
                        val bm =MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri);
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
                            .into(binding.imageUploadDish)
                        checkReadytoSubmit()
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
                    val data: Uri  = it.toUri(requireContext())
                    imageUrl = selectedImage.toBase64()
                    doRequest(data.toString())
                    Glide.with(requireContext()).load(it)
                        .placeholder(R.drawable.empty_plate)
                        .into(binding.imageUploadDish)
                    checkReadytoSubmit()
                }
            }
        }
    }

    private fun doRequest(uri: String) {
        val file = File(Uri.parse(uri).path!!)
        val requestFile = file.asRequestBody(requireActivity().contentResolver.getType(Uri.parse(uri))?.toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
        dishUrl = file
    }

    private fun checkReadytoSubmit() {
        dishDescription = binding.textDishDescription.editableText.toString()
        if (!imageUrl.isNullOrEmpty() && !dishDescription.isNullOrEmpty()) {
            binding.buttonSubmit.visibility = View.VISIBLE
        } else {
            binding.buttonSubmit.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}