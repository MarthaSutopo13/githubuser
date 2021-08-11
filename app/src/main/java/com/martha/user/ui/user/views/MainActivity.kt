package com.martha.user.ui.user.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martha.user.base.BaseActivity
import com.martha.user.data.adapter.UserAdapter
import com.martha.user.data.network.Resource
import com.martha.user.databinding.ActivityMainBinding
import com.martha.user.ui.user.viewmodel.UserViewModel

class MainActivity : BaseActivity<UserViewModel, ActivityMainBinding>(){

    private lateinit var userAdapter : UserAdapter
    val linearLayoutManager = LinearLayoutManager(this)
    var limit: Int = 20
    var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initAction()
    }

    fun initView() {
        viewModel.getListUser(limit)
        reloadData()

        bind.rvUser.layoutManager = linearLayoutManager
        userAdapter = UserAdapter(this)
        bind.rvUser.adapter = userAdapter
    }

    fun initAction() {
        bind.rvUser.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    var lItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    var vItem = linearLayoutManager.childCount

                    if (vItem + lItem >= limit) {
                        limit += 20
                        bind.pgUser.visibility = View.VISIBLE
                        if (isClicked) {
                            viewModel.getListUserByName(bind.etName.text.toString().trim(), limit)
                        } else {
                            viewModel.getListUser(limit)
                        }
                    }
                }
            }
        })

        bind.btnSearch.setOnClickListener {
            limit = 20
            isClicked = true
            viewModel.getListUserByName(bind.etName.text.toString().trim(), limit)
        }

        bind.ivClose.setOnClickListener {
            limit = 20
            isClicked = false
            bind.etName.setText("")
            viewModel.getListUser(limit)
        }
    }

    fun reloadData() {
        viewModel.userResponse.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    userAdapter.setUser(it.value!!)
                    bind.pgUser.visibility = View.GONE
                }

                is Resource.Failure -> Toast.makeText(this, "load data failure", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.userBySearchResponse.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                        if (limit == 20) userAdapter.clearUser()
                        userAdapter.setUser(it.value.data!!)
                        bind.pgUser.visibility = View.GONE
                }

                is Resource.Failure -> Toast.makeText(this, "load data failure", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun getViewModel() = UserViewModel::class.java
    override fun getActivityBinding(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)


}