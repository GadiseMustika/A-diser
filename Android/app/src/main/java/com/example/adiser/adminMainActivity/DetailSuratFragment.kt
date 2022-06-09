package com.example.adiser.adminMainActivity

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.adiser.Constants
import com.example.adiser.databinding.DetailSuratAdminBinding
import com.example.adiser.model.SuratModel
import com.google.firebase.database.FirebaseDatabase

class DetailSuratFragment : Fragment() {

    private var _binding: DetailSuratAdminBinding? = null
    private val binding get() = _binding!!
    private var category = ""
    private var noSurat = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailSuratAdminBinding.inflate(inflater, container, false)

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            resources.getStringArray(com.example.adiser.R.array.drop_down_status_array)
        )
        binding.spinnerSurat.adapter = adapter
        binding.spinnerSurat.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> category = "Sedang Diproses"
                        1 -> category = "Selesai Diproses"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

        binding.editDetailSuratAdminNoSurat.keyListener = null
        binding.editDetailSuratAdminNik.keyListener = null
        binding.editDetailSuratAdminNama.keyListener = null
        binding.editDetailSuratAdminJenis.keyListener = null
        binding.editDetailSuratAdminTgl.keyListener = null

        noSurat = arguments?.getString(Constants.SURAT_NOMOR).toString()
        var nik = arguments?.getString(Constants.SURAT_NIK)
        var nama = arguments?.getString(Constants.SURAT_NAMA)
        var jenis = arguments?.getString(Constants.SURAT_JENIS)
        var tgl= arguments?.getString(Constants.SURAT_TGL)


        with(binding) {
            editDetailSuratAdminNoSurat.setText(noSurat)
            editDetailSuratAdminNik.setText(nik)
            editDetailSuratAdminNama.setText(nama)
            editDetailSuratAdminJenis.setText(jenis)
            editDetailSuratAdminTgl.setText(tgl)
        }

        binding.btnDetailSuratAdminDelete.setOnClickListener {
            deleteDatabase(noSurat)
            findNavController().navigate(com.example.adiser.R.id.action_detailSuratFragment_to_dataSosialFragment)
            Constants.showSnackbar(
                requireContext(),
                binding.container,
                "Surat berhasil dihapus"
            )

        }

        binding.btnDetailSuratAdminSimpan.setOnClickListener {
            storeToDatabase()
        }

        return binding.root
    }

    private fun deleteDatabase(noSurat: String) {
        val reference = FirebaseDatabase.getInstance().reference
            .child("Surat").child(noSurat)
        reference.removeValue()
    }

    private fun storeToDatabase() {
        //simpan di database
        val reference = FirebaseDatabase.getInstance().reference.child("Surat")
            .child(noSurat)

        val value = SuratModel(
            suratStatus = category,
        )

        reference.setValue(value).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(com.example.adiser.R.id.action_detailSuratFragment_to_dataSosialFragment)
                Constants.showSnackbar(
                    requireContext(),
                    binding.container,
                    "Status surat berhasil diubah"
                )
            } else {
                Constants.showSnackbar(requireContext(), binding.container, "Error from database")

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}