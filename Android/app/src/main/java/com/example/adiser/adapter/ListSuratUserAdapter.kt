package com.example.adiser.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adiser.Constants
import com.example.adiser.databinding.ItemSuratUserBinding
import com.example.adiser.model.SuratModel

class ListSuratUserAdapter :
    RecyclerView.Adapter<ListSuratUserAdapter.SuratViewHolder>() {
    private var dataSuratUser = ArrayList<SuratModel>()

    fun setdata(data: List<SuratModel>?) {
        if (data == null) return
        this.dataSuratUser.clear()
        this.dataSuratUser.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuratViewHolder {
        val itemSuratUser =
            ItemSuratUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuratViewHolder(itemSuratUser)
    }

    override fun onBindViewHolder(holder: SuratViewHolder, position: Int) {
        val data = dataSuratUser[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = dataSuratUser.size

    class SuratViewHolder(private val binding: ItemSuratUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SuratModel) {
            with(binding) {
                txtItemSuratUserOutputNosurat.text = data.suratNomor
                txtItemSuratUserOutputJenis.text = data.suratJenis
                txtItemSuratUserOutputStatus.text = data.suratStatus
            }
            val mBundle = Bundle()
            mBundle.putString(Constants.SURAT_ID, data.suratId)
            mBundle.putString(Constants.SURAT_NOMOR, data.suratNomor)
            mBundle.putString(Constants.SURAT_NIK, data.suratNik)
            mBundle.putString(Constants.SURAT_NAMA, data.suratNama)
            mBundle.putString(Constants.SURAT_JENIS, data.suratJenis)
            mBundle.putString(Constants.SURAT_TGL, data.suratTglPermohonan)
            mBundle.putString(Constants.SURAT_STATUS, data.suratStatus)
            mBundle.putString(Constants.SURAT_FKTP, data.suratFotoKTP)
            mBundle.putString(Constants.SURAT_FLATAR, data.suratFotoLatar)
            mBundle.putString(Constants.SURAT_FAKTA, data.suratFotoAkta)
            mBundle.putString(Constants.SURAT_FKK, data.suratFotoKK)

        }
    }
}