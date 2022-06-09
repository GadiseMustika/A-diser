package com.example.adiser

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

object Constants {

    const val CHECK_USERNAME = "check_username"
    const val SURAT_ID = "surat_id"
    const val SURAT_NOMOR = "surat_nomor"
    const val SURAT_NIK = "surat_nik"
    const val SURAT_NAMA = "surat_nama"
    const val SURAT_JENIS = "surat_jenis"
    const val SURAT_TGL = "surat_tgl"
    const val SURAT_STATUS = "surat_status"
    const val SURAT_FKTP = "surat_fktp"
    const val SURAT_FLATAR = "surat_flatar"
    const val SURAT_FAKTA = "surat_fakta"
    const val SURAT_FKK = "surat_fkk"
    const val USER_ID = "user_id"
    const val USER_USERNAME = "user_username"
    const val USER_EMAIL = "user_email"
    const val USER_NIK = "user_nik"
    const val USER_NAMA = "user_nama"
    const val USER_TTL = "user_ttl"
    const val USER_JK = "user_jk"
    const val USER_AGAMA = "user_agama"
    const val USER_PEKERJAAN = "user_pekerjaan"
    const val USER_WARGA = "user_warga"
    const val USER_ROLE = "user_role"

    fun showSnackbar(context: Context, view: View, text: String) {
        val snackbar = Snackbar.make(
            view, text,
            Snackbar.LENGTH_SHORT
        ).setAction("Ok", null)
            .setActionTextColor(ContextCompat.getColor(context, R.color.white))
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.theme
            )
        )
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 14f
        snackbar.show()
    }
}