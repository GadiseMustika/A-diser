package com.example.adiser.mainActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.Constants
import com.example.adiser.R
import com.example.adiser.databinding.LayananUserBinding

class LayananFragment : Fragment() {

    private lateinit var binding: LayananUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayananUserBinding.inflate(layoutInflater, container, false)

        binding.btnLayananUserPindah.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.SURAT_JENIS, "Pindah")
            findNavController().navigate(R.id.action_layananFragment_to_persyaratanFragment, bundle)
        }
        binding.btnLayananUserTmp.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.SURAT_JENIS, "Tidak Mampu")
            findNavController().navigate(R.id.action_layananFragment_to_persyaratanFragment, bundle)
        }
        binding.btnLayananUserMilik.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.SURAT_JENIS, "Kepemilikan")
            findNavController().navigate(R.id.action_layananFragment_to_persyaratanFragment, bundle)
        }
        binding.btnLayananUserNikah.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.SURAT_JENIS, "Nikah")
            findNavController().navigate(R.id.action_layananFragment_to_persyaratanFragment, bundle)
        }
        binding.btnLayananUserPenghasilan.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.SURAT_JENIS, "Penghasilan")
            findNavController().navigate(R.id.action_layananFragment_to_persyaratanFragment, bundle)
        }

        binding.menuBawah.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.beranda -> findNavController().navigate(R.id.action_layananFragment_to_dashboardFragment)
                R.id.profile -> findNavController().navigate(R.id.action_layananFragment_to_profilFragment)

            }
            true
        }

        return binding.root
    }
}