package com.martha.muridkreatif.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("avatar_url")
    @Expose
    var picture: String? = null

    @SerializedName("login")
    @Expose
    var name: String? = null
}