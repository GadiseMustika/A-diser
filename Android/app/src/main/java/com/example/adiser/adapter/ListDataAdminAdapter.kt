package com.example.adiser.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.adiser.Constants
import com.example.adiser.R
import com.example.adiser.databinding.ItemDataAdminBinding
import com.example.adiser.model.UserModel

class ListDataAdminAdapter :
    RecyclerView.Adapter<ListDataAdminAdapter.UserViewHolder>() {
    private var dataUserAdmin = ArrayList<UserModel>()

    fun setdata(data: List<UserModel>?) {
        if (data == null) return
        this.dataUserAdmin.clear()
        this.dataUserAdmin.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemDataAdmin =
            ItemDataAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemDataAdmin)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = dataUserAdmin[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = dataUserAdmin.size

    class UserViewHolder(private val binding: ItemDataAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserModel) {
            with(binding) {
                txtItemDataNama.text = data.nama
                txtItemDataNik.text = data.nik
            }
            val mBundle = Bundle()
            mBundle.putString(Constants.USER_ID, data.userId)
            mBundle.putString(Constants.USER_USERNAME, data.uname)
            mBundle.putString(Constants.USER_EMAIL, data.email)
            mBundle.putString(Constants.USER_NIK, data.nik)
            mBundle.putString(Constants.USER_NAMA, data.nama)
            mBundle.putString(Constants.USER_TTL, data.ttl)
            mBundle.putString(Constants.USER_JK, data.jk)
            mBundle.putString(Constants.USER_AGAMA, data.agama)
            mBundle.putString(Constants.USER_PEKERJAAN, data.pekerjaan)
            mBundle.putString(Constants.USER_WARGA, data.warga)
            mBundle.putString(Constants.USER_ROLE, data.role)

            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_dataMasterFragment_to_detailPendudukFragment, mBundle)
            }
        }
    }
}