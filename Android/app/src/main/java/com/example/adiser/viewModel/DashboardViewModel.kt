package com.example.adiser.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adiser.Firebase
import com.example.adiser.model.SuratModel

class DashboardViewModel : ViewModel() {

    fun getDataSurat(suratId: String): LiveData<MutableList<SuratModel>> {
        val mutableData = MutableLiveData<MutableList<SuratModel>>()
        Firebase.getSuratUser(suratId).observeForever {
            mutableData.value = it
        }
        Log.i("testFirebase", "getDataSurat: $mutableData")
        return mutableData
    }
}
