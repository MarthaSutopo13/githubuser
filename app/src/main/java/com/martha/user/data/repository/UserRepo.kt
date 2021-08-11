package com.martha.user.data.repository

import com.martha.muridkreatif.network.api.UserApi
import com.martha.user.base.BaseRepository

class UserRepo(val api: UserApi): BaseRepository() {
    suspend fun getListUser(page: Int) = handlingApiCall {
        api.getListUser(page)
    }

    suspend fun getListUserByName(search: String, page: Int) = handlingApiCall {
        api.getListUserByName(search, page)
    }
}