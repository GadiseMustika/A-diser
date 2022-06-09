package com.example.adiser.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.adiser.Constants.showSnackbar
import com.example.adiser.mainActivity.MainActivity
import com.example.adiser.R
import com.example.adiser.adminMainActivity.AdminMainActivity
import com.example.adiser.databinding.LoginBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: LoginBinding
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null
    private var userId = ""
    private var email = ""
    private lateinit var role: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginBinding.inflate(layoutInflater, container, false)

        role = ""
        binding.txtLoginBuat.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_uploadRegisterFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                val username = binding.editLoginUname.text.toString().trim()
                val password = binding.editLoginPass.text.toString().trim()

                if (username.isEmpty()) {
                    binding.editLoginUname.error = "Please enter username"
                    binding.editLoginUname.requestFocus()
                    return onClick(view)
                }

                if (password.isEmpty()) {
                    binding.editLoginPass.error = "Please enter password"
                    binding.editLoginPass.requestFocus()
                    return onClick(view)
                }

                getUserId(username)
            }
        }
    }

    private fun getUserId(username: String) {
        val query = FirebaseDatabase.getInstance().reference.child("Users")
            .orderByChild("uname").equalTo(username)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                for (ds in data.children) {
                    userId = ds.key.toString()
                }

                getEmail(userId)
            }

            override fun onCancelled(data: DatabaseError) {
                Log.d("LoginFragment", data.message)
            }

        })
    }

    private fun getEmail(userId: String) {
        FirebaseDatabase.getInstance().reference.child("Users")
            .child(userId).child("email").get().addOnSuccessListener { er ->
                if (er.exists()) {
                    email = er.value.toString()
                    login()
                } else {
                    showSnackbar(
                        requireContext(),
                        binding.container,
                        "Wrong username or password. Please try again!"
                    )
                }
            }
    }

    private fun login() {

        auth.signInWithEmailAndPassword(
            email,
            binding.editLoginPass.text.toString().trim()
        )
            .addOnCompleteListener(AuthenticationActivity()) { task ->
                user = auth.currentUser
                if (task.isSuccessful) {
                    val reference =
                        FirebaseDatabase.getInstance().reference.child("Users")
                            .child(user!!.uid)
                    reference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val user = auth.currentUser
                            role = dataSnapshot.child("role").getValue(String::class.java).toString()
                            updateUI(user)
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
                } else {
                    updateUI(null)
                    showSnackbar(
                        requireContext(),
                        binding.container,
                        "Wrong username or password. Please try again!"
                    )
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            updateUI(auth.currentUser)
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null && role == "user") {
            requireContext().startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        } else if (currentUser != null && role == "admin"){
            requireContext().startActivity(Intent(context, AdminMainActivity::class.java))
            activity?.finish()
        }

    }
}
