package com.example.toof.egetdatarepogithub

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class UserAdapter(private val context: Context, private val list: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.user_item, p0, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user = list[p1]
        user.owner?.avatar?.let { Glide.with(context).load(it).into(p0.mImageView) }
        user.full_name?.let { p0.mTextViewFullName.text = it }
        user.owner?.login?.let { p0.mTextViewLogin.text = it }
    }

    class ViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        var mImageView: ImageView = view.findViewById(R.id.image_view)
        var mTextViewFullName: TextView = view.findViewById(R.id.text_full_name)
        var mTextViewLogin: TextView = view.findViewById(R.id.text_login)
    }
}