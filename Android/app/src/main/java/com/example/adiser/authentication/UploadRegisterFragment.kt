package com.example.adiser.authentication

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.adiser.Constants
import com.example.adiser.R
import com.example.adiser.databinding.RegisterUploadBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class UploadRegisterFragment : Fragment() {

    private var _binding: RegisterUploadBinding? = null
    private val binding get() = _binding!!
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private var uriImage: Uri? = null
    private lateinit var idOCR: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterUploadBinding.inflate(inflater)

        idOCR = getRandomString(10)
        binding.btnRegUploadKtp.setOnClickListener {
            getResultImage.launch("image/*")
        }

        binding.btnRegUploadLanjut.setOnClickListener{
            uploadPicture(uriImage)
        }

        return binding.root
    }

    private fun uploadPicture(imageUri: Uri?) {
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        val tmp: StorageReference = storageReference.child("OCR/$idOCR.jpg")
        if (imageUri != null) {
            tmp.putFile(imageUri).addOnSuccessListener {
                tmp.downloadUrl.addOnSuccessListener {
                    val urlImage = it.toString()
                    storeToDatabase(urlImage)
                }
            }.addOnFailureListener {
                Constants.showSnackbar(requireContext(), binding.container, "Error upload image")
            }
        }
    }

    private fun storeToDatabase(urlImage: String) {

        val reference = FirebaseDatabase.getInstance().reference.child("OCR")
            .child(idOCR)

        reference.setValue(urlImage).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(R.id.action_uploadRegisterFragment_to_registerFragment)
            } else {
                Constants.showSnackbar(requireContext(), binding.container, "Error from database")
            }
        }
    }

    private val getResultImage = registerForActivityResult(ActivityResultContracts.GetContent()) {

        uriImage = it
        if (uriImage !== null) {
            Constants.showSnackbar(requireContext(), binding.container, "Gambar telah diupload")
        } else {
            Constants.showSnackbar(requireContext(), binding.container, "Terjadi kesalahan")
        }
    }

    private fun getRandomString(sizeOfRandomString: Int): String {
        val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return sb.toString()
    }
}
