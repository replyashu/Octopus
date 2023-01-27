package com.ashu.ocotopus.ui.hungry

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashu.ocotopus.R
import com.ashu.ocotopus.databinding.FragmentHungryBinding
import com.ashu.ocotopus.util.ManagePermissions
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HungryFragment : Fragment() {

    private var _binding: FragmentHungryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val PermissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions

    private val REQUEST_CODE = 100

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
        binding.imageUploadDish.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (managePermissions.checkPermissions()) {
                    openGalleryForImages()
                } else {
                    managePermissions.checkPermissions()
                }
            }
        }

        zeroCount()

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

            }

        })
    }

    private fun zeroCount() {
        binding.textCharCount.text = getString(R.string.char_count, 0)
        binding.textWordCount.text = getString(R.string.word_count, 0)
    }

    private fun openGalleryForImages() {
        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            intent.action = Intent.ACTION_GET_CONTENT
            activityResult.launch(Intent.createChooser(intent, "Choose Pictures"))
        }
        else { // For latest versions API LEVEL 19+
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            activityResult.launch(intent)
        }
    }

    private val activityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    val data = it.data
                    data?.let {
                        val imageUri = data.data
                        Glide.with(requireContext()).load(imageUri)
                            .placeholder(R.drawable.empty_plate)
                            .into(binding.imageUploadDish)
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}