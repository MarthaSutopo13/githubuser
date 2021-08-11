package com.martha.user.ui.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martha.muridkreatif.network.model.User
import com.martha.user.data.network.Resource
import com.martha.user.data.network.response.UserResponse
import com.martha.user.data.repository.UserRepo
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepo: UserRepo
) : ViewModel() {

    private val _userResponse: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    val userResponse: LiveData<Resource<List<User>>>
        get() = _userResponse

    private val _userBySearchResponse: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val userBySearchResponse: LiveData<Resource<UserResponse>>
        get() = _userBySearchResponse

    fun getListUser(page: Int)
            = viewModelScope.launch {
        _userResponse.value = userRepo.getListUser(page)
    }

    fun getListUserByName(search: String, page: Int)
            = viewModelScope.launch {
        _userBySearchResponse.value = userRepo.getListUserByName(search, page)
    }
}