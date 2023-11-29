package com.example.githubapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.response.UserFollowingResponseItem
import com.example.githubapp.databinding.ItemUsernameBinding
import com.squareup.picasso.Picasso

class FollowingListAdapter:ListAdapter<UserFollowingResponseItem, FollowingListAdapter.MyFollowingHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyFollowingHolder {
        val viewBinding = ItemUsernameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyFollowingHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyFollowingHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class MyFollowingHolder(val viewBinding: ItemUsernameBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(following: UserFollowingResponseItem){

            viewBinding.itemText.text = following.login
            Picasso.get().load(following.avatarUrl).into(viewBinding.itemImage)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFollowingResponseItem>() {
            override fun areItemsTheSame(oldItem: UserFollowingResponseItem, newItem: UserFollowingResponseItem,): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UserFollowingResponseItem, newItem: UserFollowingResponseItem,): Boolean {
                return oldItem == newItem
            }
        }
    }
}