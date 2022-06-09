package com.example.adiser.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adiser.Firebase
import com.example.adiser.model.SuratModel

class SuratProsesViewModel : ViewModel() {

    fun getDataSuratProses(status: String): LiveData<MutableList<SuratModel>> {
        val mutableData = MutableLiveData<MutableList<SuratModel>>()
        Firebase.getSuratAdminProses(status).observeForever {
            mutableData.value = it
        }
        Log.i("testFirebase", "getDataSurat: $mutableData")
        return mutableData
    }
}
