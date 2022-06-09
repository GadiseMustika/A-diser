package com.example.adiser.adminMainActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.Constants
import com.example.adiser.R
import com.example.adiser.databinding.DsosialAdminBinding

class DataSosialFragment : Fragment() {

    private lateinit var binding: DsosialAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DsosialAdminBinding.inflate(layoutInflater, container, false)

        binding.btnDsosialAdminProses.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.SURAT_STATUS, "Sedang Diproses")
            findNavController().navigate(R.id.action_dataSosialFragment_to_suratProsesFragment, bundle)
        }
        binding.btnDsosialAdminSelesai.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.SURAT_STATUS, "Selesai Diproses")
            findNavController().navigate(R.id.action_dataSosialFragment_to_suratSelesaiFragment, bundle)
        }


        binding.menuBawah.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dashboard -> findNavController().navigate(R.id.action_dataSosialFragment_to_adminDashboardFragment)
                R.id.data_master -> findNavController().navigate(R.id.action_dataSosialFragment_to_dataMasterFragment)

            }
            true
        }

        return binding.root
    }
}