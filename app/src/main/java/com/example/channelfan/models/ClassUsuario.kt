package com.example.channelfan.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClassUsuario(
    @SerializedName("_id")
    val id: String? = null,

    @Expose(serialize = false)
    @SerializedName("firstName")
    var firstName: String? = null,

    @Expose(serialize = false)
    @SerializedName("lastName")
    var lastName: String? = null,

    @Expose(serialize = false)
    @SerializedName("email")
    var email: String? = null,

    @Expose(serialize = false)
    @SerializedName("password")
    var password: String? = null,

    @Expose(serialize = false)
    @SerializedName("address")
    var address: String? = null,

    @Expose(serialize = false)
    @SerializedName("avatar")
    var avatar: String? = null,

    @Expose(serialize = false)
    @SerializedName("userType")
    var userType: String? = null
)
