package com.example.adiser.authentication

import  android.os.Bundle
import android.util.Log
import android.util.Patterns
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
import com.example.adiser.R
import com.example.adiser.databinding.RegisterKonfirmasiBinding
import com.example.adiser.model.UserModel

class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: RegisterKonfirmasiBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterKonfirmasiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.btnRegKonfirmasiDaftar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_reg_konfirmasi_daftar -> {
                if (binding.editRegKonfirmasiUname.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiUname.error = "Please enter username"
                    binding.editRegKonfirmasiUname.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiEmail.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiEmail.error = "Please enter email"
                    binding.editRegKonfirmasiEmail.requestFocus()
                    return onClick(view)
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(binding.editRegKonfirmasiEmail.text.toString().trim())
                        .matches()
                ) {
                    binding.editRegKonfirmasiEmail.error = "Please enter a valid email"
                    binding.editRegKonfirmasiEmail.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiPass.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiPass.error = "Please enter password"
                    binding.editRegKonfirmasiPass.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiPass.text.toString().trim().length < 6) {
                    binding.editRegKonfirmasiPass.error = "Password less than 6 character"
                    binding.editRegKonfirmasiPass.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiNik.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiNik.error = "Please enter NIK"
                    binding.editRegKonfirmasiNik.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiNama.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiNama.error = "Please enter nama"
                    binding.editRegKonfirmasiNama.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiTtl.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiTtl.error = "Please enter TTL"
                    binding.editRegKonfirmasiTtl.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiJk.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiJk.error = "Please enter jenis kelamin"
                    binding.editRegKonfirmasiJk.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiAgama.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiAgama.error = "Please enter agama"
                    binding.editRegKonfirmasiAgama.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiPekerjaan.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiPekerjaan.error = "Please enter pekerjaan"
                    binding.editRegKonfirmasiPekerjaan.requestFocus()
                    return onClick(view)
                }

                if (binding.editRegKonfirmasiWarga.text.toString().trim().isEmpty()) {
                    binding.editRegKonfirmasiWarga.error = "Please enter kewarganegaraan"
                    binding.editRegKonfirmasiWarga.requestFocus()
                    return onClick(view)
                }

                checkUsernameAvailability(username = binding.editRegKonfirmasiUname.text.toString().trim())
            }
        }
    }

    private fun checkUsernameAvailability(username: String) {
        val query = FirebaseDatabase.getInstance().reference.child("Users")
            .orderByChild("username")
            .equalTo(username)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                if (data.exists()) {
                    showSnackbar(requireContext(), binding.container, "Username has been taken!")
                } else {
                    cekEmailRegistered()
                }
            }

            override fun onCancelled(data: DatabaseError) {
                Log.d("RegisterFragment", data.message)
            }

        })
    }

    private fun cekEmailRegistered() {
        //cek email has been registered?
        auth.fetchSignInMethodsForEmail(binding.editRegKonfirmasiEmail.text.toString().trim())
            .addOnCompleteListener {
                Log.i("cekEmail", "cekEmailRegistered: ${it.result?.signInMethods?.size}")
                //cek email terdafar
                if (it.result?.signInMethods?.size != 0) {
                    showSnackbar(requireContext(), binding.container, "Email has been registered!")
                } else {
                    createAccount()
                }
            }
    }

    private fun createAccount() {
        auth.createUserWithEmailAndPassword(
            binding.editRegKonfirmasiEmail.text.toString().trim(),
            binding.editRegKonfirmasiPass.text.toString().trim()
        )
            .addOnCompleteListener(AuthenticationActivity()) { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser as FirebaseUser
                    val reference = FirebaseDatabase.getInstance().reference.child("Users")
                        .child(user.uid)

                    val value = UserModel(
                        user.uid,
                        binding.editRegKonfirmasiUname.text.toString().trim(),
                        binding.editRegKonfirmasiEmail.text.toString().trim(),
                        binding.editRegKonfirmasiNik.text.toString().trim(),
                        binding.editRegKonfirmasiNama.text.toString().trim(),
                        binding.editRegKonfirmasiTtl.text.toString().trim(),
                        binding.editRegKonfirmasiJk.text.toString().trim(),
                        binding.editRegKonfirmasiAgama.text.toString().trim(),
                        binding.editRegKonfirmasiPekerjaan.text.toString().trim(),
                        binding.editRegKonfirmasiWarga.text.toString().trim(),
                        "user"
                    )

                    reference.setValue(value).addOnCompleteListener {
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            showSnackbar(
                                requireContext(),
                                binding.container,
                                "Your account has been registered"
                            )

                        } else {
                            showSnackbar(
                                requireContext(),
                                binding.container,
                                "Error from database")
                        }
                    }

                }else {
                }
            }
    }

}
