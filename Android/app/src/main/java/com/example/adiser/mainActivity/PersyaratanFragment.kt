package com.example.adiser.mainActivity

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.Constants
import com.example.adiser.R
import com.example.adiser.databinding.PersyaratanUserBinding
import com.example.adiser.model.SuratModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PersyaratanFragment : Fragment() {

    private lateinit var binding: PersyaratanUserBinding
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private var uriKtp: Uri? = null
    private var uriLatar: Uri? = null
    private var uriAkta: Uri? = null
    private var uriKk: Uri? = null
    private lateinit var noSurat: String
    private lateinit var nama: String
    private lateinit var nik: String
    private lateinit var urlKtp: String
    private lateinit var urlLatar: String
    private lateinit var urlAkta: String
    private lateinit var urlKk: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersyaratanUserBinding.inflate(layoutInflater, container, false)
        noSurat = ""
        val reference = FirebaseDatabase.getInstance().reference
            .child("Surat")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val count = snapshot.childrenCount
                noSurat = (count + 1).toString()

                Log.d("TAG", "Count: $count")

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val uid = user.uid

        nama = ""
        nik = ""
        val referenceUser = FirebaseDatabase.getInstance().reference
            .child("Users").child(uid)
        referenceUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nama = snapshot.child("nama").getValue(String::class.java).toString()
                nik = snapshot.child("nik").getValue(String::class.java).toString()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        urlKtp = ""
        urlLatar = ""
        urlAkta = ""
        urlKk = ""
        binding.btnPersyaratanUserKtp.setOnClickListener {
            getResultImageKtp.launch("image/*")
        }
        binding.btnPersyaratanUserLatar.setOnClickListener {
            getResultImageLatar.launch("image/*")
        }
        binding.btnPersyaratanUserAkta.setOnClickListener {
            getResultImageAkta.launch("image/*")
        }
        binding.btnPersyaratanUserKk.setOnClickListener {
            getResultImageKk.launch("image/*")
        }
        binding.btnPersyaratanUserLanjut.setOnClickListener {

            uploadPicture(uriKtp, uriLatar, uriAkta, uriKk)
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadPicture(uriKTP: Uri?, uriLatar: Uri?, uriAkta: Uri?, uriKk: Uri?) {
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        val tmpKtp: StorageReference = storageReference.child("Surat/$noSurat-ktp.jpg")
        if (uriKTP != null) {
            tmpKtp.putFile(uriKTP).addOnSuccessListener {
                tmpKtp.downloadUrl.addOnSuccessListener {
                    urlKtp = it.toString()

                }
            }.addOnFailureListener {
                Constants.showSnackbar(requireContext(), binding.container, "Error upload image")
            }
        }
        val tmpLatar: StorageReference = storageReference.child("Surat/$noSurat-latar.jpg")
        if (uriLatar != null) {
            tmpLatar.putFile(uriLatar).addOnSuccessListener {
                tmpLatar.downloadUrl.addOnSuccessListener {
                    urlLatar = it.toString()

                }
            }.addOnFailureListener {
                Constants.showSnackbar(requireContext(), binding.container, "Error upload image")
            }
        }
        val tmpAkta: StorageReference = storageReference.child("Surat/$noSurat-akta.jpg")
        if (uriAkta != null) {
            tmpAkta.putFile(uriAkta).addOnSuccessListener {
                tmpAkta.downloadUrl.addOnSuccessListener {
                    urlAkta = it.toString()

                }
            }.addOnFailureListener {
                Constants.showSnackbar(requireContext(), binding.container, "Error upload image")
            }
        }
        val tmpKk: StorageReference = storageReference.child("Surat/$noSurat-kk.jpg")
        if (uriKk != null) {
            tmpKk.putFile(uriKk).addOnSuccessListener {
                tmpKk.downloadUrl.addOnSuccessListener {
                    urlKk = it.toString()

                }
            }.addOnFailureListener {
                Constants.showSnackbar(requireContext(), binding.container, "Error upload image")
            }
        }
        storeToDatabase(urlKtp, urlLatar, urlAkta, urlKk)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun storeToDatabase(urlKtp: String, urlLatar: String, urlAkta: String, urlKk: String) {

        val reference = FirebaseDatabase.getInstance().reference.child("Surat")
            .child(noSurat)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatter)

        val jenis = arguments?.getString(Constants.SURAT_JENIS)
        val value = jenis?.let {
            SuratModel(
                suratId = user.uid,
                suratNomor = noSurat,
                suratNik = nik,
                suratNama = nama,
                suratJenis = it,
                suratTglPermohonan = date,
                suratStatus = "Sedang Diproses",
                suratFotoKTP = urlKtp,
                suratFotoLatar = urlLatar,
                suratFotoAkta = urlAkta,
                suratFotoKK = urlKk
            )
        }

        reference.setValue(value).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(R.id.action_persyaratanFragment_to_konfirmasiFragment)
                Constants.showSnackbar(
                    requireContext(),
                    binding.container,
                    "Surat berhasil diajukan"
                )
            } else {
                Constants.showSnackbar(requireContext(), binding.container, "Error from database")
            }
        }
    }

    private val getResultImageKtp = registerForActivityResult(ActivityResultContracts.GetContent()) {

        uriKtp = it
        if (uriKtp !== null) {
            Constants.showSnackbar(requireContext(), binding.container, "Gambar telah diupload")
        } else {
            Constants.showSnackbar(requireContext(), binding.container, "Terjadi kesalahan")
        }
    }

    private val getResultImageLatar = registerForActivityResult(ActivityResultContracts.GetContent()) {

        uriLatar = it
        if (uriLatar !== null) {
            Constants.showSnackbar(requireContext(), binding.container, "Gambar telah diupload")
        } else {
            Constants.showSnackbar(requireContext(), binding.container, "Terjadi kesalahan")
        }
    }

    private val getResultImageAkta = registerForActivityResult(ActivityResultContracts.GetContent()) {

        uriAkta = it
        if (uriAkta !== null) {
            Constants.showSnackbar(requireContext(), binding.container, "Gambar telah diupload")
        } else {
            Constants.showSnackbar(requireContext(), binding.container, "Terjadi kesalahan")
        }
    }

    private val getResultImageKk = registerForActivityResult(ActivityResultContracts.GetContent()) {

        uriKk = it
        if (uriKk !== null) {
            Constants.showSnackbar(requireContext(), binding.container, "Gambar telah diupload")
        } else {
            Constants.showSnackbar(requireContext(), binding.container, "Terjadi kesalahan")
        }
    }
}