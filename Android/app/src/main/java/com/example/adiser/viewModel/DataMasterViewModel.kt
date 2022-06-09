package com.example.adiser.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adiser.Firebase
import com.example.adiser.model.UserModel

class DataMasterViewModel : ViewModel() {

    fun getDataUserAdmin(role: String): LiveData<MutableList<UserModel>> {
        val mutableData = MutableLiveData<MutableList<UserModel>>()
        Firebase.getDataUser(role).observeForever {
            mutableData.value = it
        }
        Log.i("testFirebase", "getDataSurat: $mutableData")
        return mutableData
    }
}