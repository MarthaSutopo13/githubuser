package com.martha.user.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<vm: ViewModel, vb: ViewBinding>: AppCompatActivity() {

    protected lateinit var bind: vb
    protected lateinit var viewModel: vm
    protected val remoteDataSource = RemoteDataSource(this)
    var dataReceived: Bundle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataReceived = intent.extras
        if (intent != null) {
            dataReceived = intent.extras
        }
        bind = getActivityBinding(layoutInflater)
        val factory =
            ViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        setContentView(bind.root)
    }

    abstract fun getViewModel(): Class<vm>
    abstract fun getActivityBinding(layoutInflater: LayoutInflater): vb
}