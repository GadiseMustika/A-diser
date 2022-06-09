package com.example.adiser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.adiser.model.SuratModel
import com.example.adiser.model.UserModel

object Firebase {
    fun getSuratUser(suratId: String): LiveData<MutableList<SuratModel>> {
        val mutableData = MutableLiveData<MutableList<SuratModel>>()
        val data = mutableListOf<SuratModel>()
        val reference = FirebaseDatabase.getInstance().reference
            .child("Surat").orderByChild("suratId").equalTo(suratId)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    val value = dataSnapshot.getValue(SuratModel::class.java)
                    if (value != null) {
                        data.add(value)
                    }
                }
                mutableData.value = data
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return mutableData
    }

    fun getDataUser(role: String): LiveData<MutableList<UserModel>> {
        val mutableData = MutableLiveData<MutableList<UserModel>>()
        val data = mutableListOf<UserModel>()
        val reference = FirebaseDatabase.getInstance().reference
            .child("Users").orderByChild("role").equalTo(role)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    val value = dataSnapshot.getValue(UserModel::class.java)
                    if (value != null) {
                        data.add(value)
                    }
                }
                mutableData.value = data
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return mutableData
    }

    fun getSuratAdminProses(status: String): LiveData<MutableList<SuratModel>> {
        val mutableData = MutableLiveData<MutableList<SuratModel>>()
        val data = mutableListOf<SuratModel>()
        val reference = FirebaseDatabase.getInstance().reference
            .child("Surat").orderByChild("suratStatus").equalTo(status)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    val value = dataSnapshot.getValue(SuratModel::class.java)
                    if (value != null) {
                        data.add(value)
                    }
                }
                mutableData.value = data
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return mutableData
    }
}