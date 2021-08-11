package com.martha.user.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martha.muridkreatif.network.api.UserApi
import com.martha.user.data.repository.UserRepo
import com.martha.user.ui.user.viewmodel.UserViewModel

class ViewModelFactory(
    var context: Context
) : ViewModelProvider.NewInstanceFactory() {
    protected val remoteDataSource = RemoteDataSource(context)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(
                UserRepo(
                    remoteDataSource.buildApi(UserApi::class.java),
                )
            ) as T
            else -> throw IllegalArgumentException("Viewmodel class is not found")
        }
    }
}