package com.example.adiser.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var userId : String = "",
    var uname : String = "",
    var email : String = "",
    var nik : String = "",
    var nama : String = "",
    var ttl : String = "",
    var jk : String = "",
    var agama : String = "",
    var pekerjaan : String = "",
    var warga : String = "",
    var role : String = "",
) : Parcelable