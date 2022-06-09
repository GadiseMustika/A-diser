package com.example.adiser.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.adiser.Constants
import com.example.adiser.R
import com.example.adiser.databinding.ItemSuratAdminBinding
import com.example.adiser.model.SuratModel

class ListSuratAdminAdapter :
    RecyclerView.Adapter<ListSuratAdminAdapter.SuratAdminViewHolder>() {
    private var dataSuratAdmin = ArrayList<SuratModel>()

    fun setdata(data: List<SuratModel>?) {
        if (data == null) return
        this.dataSuratAdmin.clear()
        this.dataSuratAdmin.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuratAdminViewHolder {
        val itemSuratAdmin =
            ItemSuratAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuratAdminViewHolder(itemSuratAdmin)
    }

    override fun onBindViewHolder(holder: SuratAdminViewHolder, position: Int) {
        val data = dataSuratAdmin[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = dataSuratAdmin.size

    class SuratAdminViewHolder(private val binding: ItemSuratAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SuratModel) {
            with(binding) {
                txtItemSuratAdminOutputNosurat.text = data.suratNomor
                txtItemSuratAdminOutputNama.text = data.suratNama
                txtItemSuratAdminOutputNik.text = data.suratNik
                txtItemSuratAdminOutputJenis.text = data.suratJenis
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

            itemView.setOnClickListener {
                if (data.suratStatus == "Sedang Diproses"){
                    it.findNavController().navigate(R.id.action_suratProsesFragment_to_detailSuratFragment, mBundle)
                } else if (data.suratStatus == "Selesai Diproses"){
                    it.findNavController().navigate(R.id.action_suratSelesaiFragment_to_detailSuratFragment, mBundle)
                }

            }
        }
    }
}