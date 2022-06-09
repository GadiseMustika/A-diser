package com.example.adiser.adminMainActivity

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.Constants
import com.example.adiser.databinding.DetailPendudukAdminBinding
import com.google.firebase.database.FirebaseDatabase

class DetailPendudukFragment : Fragment() {

    private var _binding: DetailPendudukAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var userId : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailPendudukAdminBinding.inflate(inflater, container, false)

        userId = ""
        userId = arguments?.getString(Constants.USER_ID).toString()
        var nik = arguments?.getString(Constants.USER_NIK)
        var nama = arguments?.getString(Constants.USER_NAMA)
        var ttl = arguments?.getString(Constants.USER_TTL)
        var jk = arguments?.getString(Constants.USER_JK)
        var agama = arguments?.getString(Constants.USER_AGAMA)
        var pekerjaan = arguments?.getString(Constants.USER_PEKERJAAN)
        var warga = arguments?.getString(Constants.USER_WARGA)

        with(binding) {
            editDetailPendudukAdminNik.setText(nik)
            editDetailPendudukAdminNama.setText(nama)
            editDetailPendudukAdminTtl.setText(ttl)
            editDetailPendudukAdminJk.setText(jk)
            editDetailPendudukAdminAgama.setText(agama)
            editDetailPendudukAdminPekerjaan.setText(pekerjaan)
            editDetailPendudukAdminWarga.setText(warga)
        }

        binding.btnDetailPendudukAdminDelete.setOnClickListener {
            deleteDatabase(userId)
            findNavController().navigate(com.example.adiser.R.id.action_detailPendudukFragment_to_dataMasterFragment)
            Constants.showSnackbar(
                requireContext(),
                binding.container,
                "Data berhasil dihapus"
            )

        }

        val mBundle = Bundle()
        mBundle.putString(Constants.USER_ID, userId)
        mBundle.putString(Constants.USER_NIK, nik)
        mBundle.putString(Constants.USER_NAMA, nama)
        mBundle.putString(Constants.USER_TTL, ttl)
        mBundle.putString(Constants.USER_JK, jk)
        mBundle.putString(Constants.USER_AGAMA, agama)
        mBundle.putString(Constants.USER_PEKERJAAN, pekerjaan)
        mBundle.putString(Constants.USER_WARGA, warga)

        binding.btnDetailPendudukAdminEdit.setOnClickListener {
            findNavController().navigate(com.example.adiser.R.id.action_detailPendudukFragment_to_editPendudukFragment, mBundle)
        }

        return binding.root
    }

    private fun deleteDatabase(noSurat: String) {
        val reference = FirebaseDatabase.getInstance().reference
            .child("Surat").child(noSurat)
        reference.removeValue()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}