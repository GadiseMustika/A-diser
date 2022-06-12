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
    private var idSurat = ""
    private var noSurat = ""
    private var nikSurat = ""
    private var namaSurat = ""
    private var jenisSurat = ""
    private var tglSurat = ""
    private var statusSurat = ""
    private var ktpSurat = ""
    private var latarSurat = ""
    private var aktaSurat = ""
    private var kkSurat = ""

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

        idSurat = arguments?.getString(Constants.SURAT_ID).toString()
        noSurat = arguments?.getString(Constants.SURAT_NOMOR).toString()
        nikSurat = arguments?.getString(Constants.SURAT_NIK).toString()
        namaSurat= arguments?.getString(Constants.SURAT_NAMA).toString()
        jenisSurat = arguments?.getString(Constants.SURAT_JENIS).toString()
        tglSurat = arguments?.getString(Constants.SURAT_TGL).toString()
        statusSurat = arguments?.getString(Constants.SURAT_STATUS).toString()
        ktpSurat = arguments?.getString(Constants.SURAT_FKTP).toString()
        latarSurat = arguments?.getString(Constants.SURAT_FLATAR).toString()
        aktaSurat = arguments?.getString(Constants.SURAT_FAKTA).toString()
        kkSurat = arguments?.getString(Constants.SURAT_FKK).toString()


        with(binding) {
            editDetailSuratAdminNoSurat.setText(noSurat)
            editDetailSuratAdminNik.setText(nikSurat)
            editDetailSuratAdminNama.setText(namaSurat)
            editDetailSuratAdminJenis.setText(jenisSurat)
            editDetailSuratAdminTgl.setText(tglSurat)
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
            suratId = idSurat,
            suratNomor = noSurat,
            suratNik = nikSurat,
            suratNama = namaSurat,
            suratJenis = jenisSurat,
            suratTglPermohonan = tglSurat,
            suratStatus = category,
            suratFotoKTP = ktpSurat,
            suratFotoLatar = latarSurat,
            suratFotoAkta = aktaSurat,
            suratFotoKK = kkSurat
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