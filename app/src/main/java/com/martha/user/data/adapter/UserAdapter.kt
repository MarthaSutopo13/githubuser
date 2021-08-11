package com.martha.user.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.martha.muridkreatif.network.model.User
import com.martha.user.R
import com.squareup.picasso.Picasso

class UserAdapter(val context: Context): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val user: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return user.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindModel(user[position])
    }

    fun clearUser(){
        user.clear()
        notifyDataSetChanged()
    }

    fun setUser(data: List<User>) {
        user.clear()
        user.addAll(data)
        notifyDataSetChanged()
    }

    fun getUser(): MutableList<User> {
        return user
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.tv_name)
        val img: ImageView = itemView.findViewById(R.id.pic_profile)

        fun bindModel(u: User) {
            nameTxt.text = u.name
            Picasso.get().load(u.picture).into(img)
        }
    }
}