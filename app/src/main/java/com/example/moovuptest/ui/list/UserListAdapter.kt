package com.example.moovuptest.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moovuptest.R
import com.example.moovuptest.model.DataModel

class UserListAdapter(
    var users: ArrayList<DataModel>, private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    fun updateUserList(newUsers: List<DataModel>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun getItemCount() = users.size
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val imageView = view.findViewById<ImageView>(R.id.userAvatar)
        private val userName = view.findViewById<TextView>(R.id.userFullName)

        fun bind(user: DataModel) {
            userName.text = user.name.firstName.plus(" " + user.name.lastName)
            loadImage(imageView, user.picture)
        }

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onItemClick(adapterPosition)
        }


    }

    interface OnItemClickListener {
        fun onItemClick(adapterPosition: Int)
    }

}


private fun loadImage(imageView: ImageView, avatar: String) {
    Glide.with(imageView).load(avatar).placeholder(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter().into(imageView)
}
