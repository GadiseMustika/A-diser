package com.example.adiser.adminMainActivity

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.databinding.DashboardAdminBinding
import com.example.adiser.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminDashboardFragment : Fragment() {

    private var _binding: DashboardAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DashboardAdminBinding.inflate(inflater, container, false)

        val referenceSurat = FirebaseDatabase.getInstance().reference
            .child("Surat")
        referenceSurat.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val jmlSurat = snapshot.childrenCount.toString()
                binding.editDashboardAdminTsurat.setText(jmlSurat + " Surat")

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        val referencePenduduk = FirebaseDatabase.getInstance().reference
            .child("Users")
        referencePenduduk.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val jmlPenduduk = (snapshot.childrenCount-1).toString()
                binding.editDashboardAdminTpenduduk.setText(jmlPenduduk + " Penduduk")

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        binding.menuBawah.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.data_sosial->findNavController().navigate(R.id.action_adminDashboardFragment_to_dataSosialFragment)
                R.id.data_master->findNavController().navigate(R.id.action_adminDashboardFragment_to_dataMasterFragment)

            }
            true
        }

        return binding.root
    }
}