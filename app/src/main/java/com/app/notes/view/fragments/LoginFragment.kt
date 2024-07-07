package com.app.notes.view.fragments

import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.app.notes.R
import com.app.notes.databinding.FragmentLoginBinding
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.app.notes.dataStoreManager.SharedPreferencesManager
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.material.snackbar.Snackbar
// Fragment for handling user login via Google One Tap sign-in
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest
    private lateinit var btnGoogle: Button
    // Activity result launcher for handling One Tap sign-in result
    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        try {
            // Initialize SharedPreferencesManager for managing login state
           val sharedPreferencesManager = SharedPreferencesManager(requireContext())
            // Retrieve Google credential from the sign-in result
            val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
            val idToken = credential.googleIdToken
            if (idToken != null) {
                // Successfully retrieved Google ID token, user is considered logged in
                sharedPreferencesManager.isLoggedIn = true

                // Navigate to the HomeFragment upon successful login
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, HomeFragment())
                    .commit()

            }
        } catch (e: ApiException) {
            when (e.statusCode) {
                CommonStatusCodes.CANCELED -> {
                    Log.d("OneTap", "One-tap dialog was closed.")
                }
                CommonStatusCodes.NETWORK_ERROR -> {
                    Log.d("OneTap", "One-tap encountered a network error.")
                }
                else -> {
                    Log.d("OneTap", "Couldn't get credential from result. (${e.localizedMessage})")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize views and Google SignInClient
        btnGoogle = binding.getStartedBtn
        val serverClientId = getString(R.string.web_client)
        oneTapClient = Identity.getSignInClient(requireActivity())
        // Build sign-in request with Google ID token options
        signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(serverClientId)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
        // Set click listener for Google sign-in button
        btnGoogle.setOnClickListener {
            startSignInIntent()
        }
    }
    // Function to start One Tap sign-in flow
    private fun startSignInIntent() {
        oneTapClient.beginSignIn(signUpRequest)
            .addOnSuccessListener { result ->
                try {
                    val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                    activityResultLauncher.launch(intentSenderRequest)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("OneTap", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener { e ->
                Log.d("OneTap", e.localizedMessage ?: "Unknown error")
                Toast.makeText(requireContext(), "Sign In Failed", Toast.LENGTH_SHORT).show()
            }
    }
}