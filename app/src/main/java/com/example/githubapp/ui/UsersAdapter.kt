package com.example.githubapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.response.ItemsItem
import com.example.githubapp.databinding.ItemUsernameBinding
import com.squareup.picasso.Picasso

class UsersAdapter : ListAdapter<ItemsItem,UsersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding = ItemUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }


    class MyViewHolder(val viewBinding: ItemUsernameBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(review: ItemsItem){
            val ctx = viewBinding.root.context
            viewBinding.itemText.text = "${review.login}"
            Picasso.get().load(review.avatarUrl).into(viewBinding.itemImage)
            viewBinding.itemCard.setOnClickListener{
                val intent = Intent(ctx, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_TITLE,review.login)
                intent.putExtra(DetailUserActivity.EXTRA_IMG_ACCOUNT,review.avatarUrl)
                ctx.startActivity(intent)

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}