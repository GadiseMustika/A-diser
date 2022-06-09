package com.example.adiser.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuratModel(
    var suratId: String = "",
    var suratNomor: String = "",
    var suratNik: String = "",
    var suratNama: String = "",
    var suratJenis: String = "",
    var suratTglPermohonan: String = "",
    var suratStatus: String = "",
    var suratFotoKTP: String = "",
    var suratFotoLatar: String = "",
    var suratFotoAkta: String = "",
    var suratFotoKK: String = "",
) : Parcelable
