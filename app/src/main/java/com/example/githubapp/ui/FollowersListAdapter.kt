package com.example.githubapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.response.UserFollowersResponseItem
import com.example.githubapp.databinding.ItemUsernameBinding
import com.squareup.picasso.Picasso

class FollowersListAdapter:ListAdapter<UserFollowersResponseItem,FollowersListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding = ItemUsernameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(val viewBinding: ItemUsernameBinding):RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(review: UserFollowersResponseItem){
            viewBinding.itemText.text = review.login
            Picasso.get().load(review.avatarUrl).into(viewBinding.itemImage)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFollowersResponseItem>() {
            override fun areItemsTheSame(oldItem: UserFollowersResponseItem,newItem: UserFollowersResponseItem,): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UserFollowersResponseItem,newItem: UserFollowersResponseItem,): Boolean {
                return oldItem == newItem
            }
        }
    }

}