package com.example.adiser.mainActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.R
import com.example.adiser.databinding.KonfirmasiUserBinding

class KonfirmasiFragment : Fragment() {

    private lateinit var binding: KonfirmasiUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = KonfirmasiUserBinding.inflate(layoutInflater, container, false)

        binding.btnKonfirmasi.setOnClickListener{
            findNavController().navigate(R.id.action_konfirmasiFragment_to_dashboardFragment)
        }

        return binding.root
    }
}
