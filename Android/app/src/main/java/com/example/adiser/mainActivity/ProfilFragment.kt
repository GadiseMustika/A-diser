package com.example.adiser.mainActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.R
import com.example.adiser.databinding.ProfilUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfilFragment : Fragment() {

    private lateinit var binding: ProfilUserBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfilUserBinding.inflate(layoutInflater, container, false)

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val uid = user.uid

        val reference = FirebaseDatabase.getInstance().reference
            .child("Users").child(uid)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val uname = snapshot.child("uname").getValue(String::class.java).toString()
                val nama = snapshot.child("nama").getValue(String::class.java).toString()
                val email = snapshot.child("email").getValue(String::class.java).toString()
                val nik = snapshot.child("nik").getValue(String::class.java).toString()
                binding.txtProfilUser.setText(uname)
                binding.editProfilUserNama.setText(nama)
                binding.editProfilUserEmail.setText(email)
                binding.editProfilUserNik.setText(nik)

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })



        binding.menuBawah.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.beranda -> findNavController().navigate(R.id.action_profilFragment_to_dashboardFragment)
                R.id.layanan -> findNavController().navigate(R.id.action_profilFragment_to_layananFragment)

            }
            true
        }
        return binding.root
    }
}