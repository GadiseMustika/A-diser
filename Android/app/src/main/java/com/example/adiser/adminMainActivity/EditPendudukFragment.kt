package com.example.adiser.adminMainActivity

import android.database.sqlite.SQLiteDatabase.deleteDatabase
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.adiser.Constants
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.example.adiser.databinding.EditPendudukAdminBinding
import com.example.adiser.model.UserModel


class EditPendudukFragment : Fragment() {

    private var _binding: EditPendudukAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var userId: String
    private lateinit var nik: String
    private lateinit var nama: String
    private lateinit var ttl: String
    private lateinit var jk: String
    private lateinit var agama: String
    private lateinit var pekerjaan: String
    private lateinit var warga: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditPendudukAdminBinding.inflate(inflater, container, false)

        userId = ""
        userId = arguments?.getString(Constants.USER_ID).toString()
        nik = arguments?.getString(Constants.USER_NIK).toString()
        nama = arguments?.getString(Constants.USER_NAMA).toString()
        ttl = arguments?.getString(Constants.USER_TTL).toString()
        jk = arguments?.getString(Constants.USER_JK).toString()
        agama = arguments?.getString(Constants.USER_AGAMA).toString()
        pekerjaan = arguments?.getString(Constants.USER_PEKERJAAN).toString()
        warga = arguments?.getString(Constants.USER_WARGA).toString()

        with(binding){
            editEditPendudukAdminNik.setText(nik)
            editEditPendudukAdminNama.setText(nama)
            editEditPendudukAdminTtl.setText(ttl)
            editEditPendudukAdminJk.setText(jk)
            editEditPendudukAdminAgama.setText(agama)
            editEditPendudukAdminPekerjaan.setText(pekerjaan)
            editEditPendudukAdminWarga.setText(warga)
        }

        binding.btnEditPendudukAdminUbah.setOnClickListener {

            if (binding.editEditPendudukAdminNik.text.toString().trim().isEmpty()) {
                binding.editEditPendudukAdminNik.error = "Masukkan NIK"
                binding.editEditPendudukAdminNik.requestFocus()
                return@setOnClickListener
            }

            if (binding.editEditPendudukAdminNama.text.toString().trim().isEmpty()) {
                binding.editEditPendudukAdminNama.error = "Masukkan Nama"
                binding.editEditPendudukAdminNama.requestFocus()
                return@setOnClickListener
            }

            if (binding.editEditPendudukAdminTtl.text.toString().trim().isEmpty()) {
                binding.editEditPendudukAdminTtl.error = "Masukkan Tempat Tanggal dan Lahir"
                binding.editEditPendudukAdminTtl.requestFocus()
                return@setOnClickListener
            }

            if (binding.editEditPendudukAdminJk.text.toString().trim().isEmpty()) {
                binding.editEditPendudukAdminJk.error = "Masukkan Jenis Kelamin"
                binding.editEditPendudukAdminJk.requestFocus()
                return@setOnClickListener
            }

            if (binding.editEditPendudukAdminAgama.text.toString().trim().isEmpty()) {
                binding.editEditPendudukAdminAgama.error = "Masukkan Agama"
                binding.editEditPendudukAdminAgama.requestFocus()
                return@setOnClickListener
            }

            if (binding.editEditPendudukAdminPekerjaan.text.toString().trim().isEmpty()) {
                binding.editEditPendudukAdminPekerjaan.error = "Masukkan Pekerjaan"
                binding.editEditPendudukAdminPekerjaan.requestFocus()
                return@setOnClickListener
            }

            if (binding.editEditPendudukAdminWarga.text.toString().trim().isEmpty()) {
                binding.editEditPendudukAdminWarga.error = "Masukkan Kewarganegaraan"
                binding.editEditPendudukAdminWarga.requestFocus()
                return@setOnClickListener
            }

            storeToDatabase()
        }

        return binding.root
    }

    private fun storeToDatabase() {
        //simpan di database
        val reference = FirebaseDatabase.getInstance().reference.child("Users")
            .child(userId)

        val value = UserModel(
            nik = nik,
            nama = nama,
            ttl = ttl,
            jk = jk,
            agama = agama,
            pekerjaan = pekerjaan,
            warga = warga
        )

        reference.setValue(value).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(com.example.adiser.R.id.action_editPendudukFragment_to_dataMasterFragment)
                Constants.showSnackbar(
                    requireContext(),
                    binding.container,
                    "Data berhasil diubah"
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