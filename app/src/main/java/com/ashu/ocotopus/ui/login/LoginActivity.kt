package com.ashu.ocotopus.ui.login

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ashu.ocotopus.MainActivity
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.requests.RegisterUser
import com.ashu.ocotopus.databinding.ActivityLoginBinding
import com.ashu.ocotopus.databinding.FragmentHomeBinding
import com.ashu.ocotopus.util.Status
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity

    private val viewModel by viewModels<LoginViewModel>()

    private var _binding: ActivityLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        oneTapClient = Identity.getSignInClient(this)
        signInRequest = viewModel.getSigninRequest(getString(R.string.web_client))

       binding?.googleButton?.setOnClickListener(this)

       viewModel.register.observe(this) {
           when(it.status) {
               Status.ALREADY_REGISTERED -> {
                   Toast.makeText(this, it.message + "  " + it.data, Toast.LENGTH_LONG).show()
                   showDashBoard()
               }
               Status.SUCCESS -> {
                   Toast.makeText(this, "Welcome New User", Toast.LENGTH_LONG).show()
                   showDashBoard()
               }
               Status.ERROR ->
                   Toast.makeText(this, "Some error" + it.message, Toast.LENGTH_LONG).show()
               else -> {

               }
           }
       }
    }

    private fun googleSignIn() {
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, REQ_ONE_TAP,
                        null, 0, 0, 0, null)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("Login1", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                Log.d("Login2", e.localizedMessage)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    viewModel.loginVerification(oneTapClient, data)
                } catch (e: ApiException) {
                    viewModel.handleLoginException(e)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.google_button ->
                googleSignIn()
        }
    }

    private fun showDashBoard() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}