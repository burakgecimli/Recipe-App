package com.example.sisterslabprojectrecipes.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentSignInUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInUpFragment : Fragment() {
    private var _binding: FragmentSignInUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInUpBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_signInUpFragment_to_commentFragment)
        }

        binding.buttonSignUp.setOnClickListener {
            email = binding.editTextName.text.toString()
            password = binding.editTextPassword.text.toString()

            if (email != "" && password != "") {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Empty Value", Toast.LENGTH_SHORT).show()
            }
        }


        binding.buttonSignIn.setOnClickListener {
            email = binding.editTextName.text.toString()
            password = binding.editTextPassword.text.toString()

            if (email != "" && password != "") {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser?.email.toString()
                        Toast.makeText(requireContext(), "Welcome $currentUser", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_signInUpFragment_to_commentFragment)
                    }
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Empty Value", Toast.LENGTH_SHORT).show()
            }
        }
    }

}