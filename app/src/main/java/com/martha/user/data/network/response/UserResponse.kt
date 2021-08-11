package com.martha.user.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.martha.muridkreatif.network.model.User

class UserResponse {
    @SerializedName("items")
    @Expose
    var data: List<User>? = null
}