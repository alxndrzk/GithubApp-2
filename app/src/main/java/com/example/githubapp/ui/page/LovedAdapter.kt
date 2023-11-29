package com.example.githubapp.ui.page

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.ui.DetailUserActivity
import com.example.githubapp.database.Loved
import com.example.githubapp.databinding.ItemUsernameBinding
import com.example.githubapp.helper.LovedDiffCallback
import com.squareup.picasso.Picasso

class LovedAdapter: RecyclerView.Adapter<LovedAdapter.LovedViewHolder>() {


    private val listLoved = ArrayList<Loved>()
    fun setListLoved(listNotes: List<Loved>) {
        val diffCallback = LovedDiffCallback(this.listLoved, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listLoved.clear()
        this.listLoved.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LovedViewHolder {
        val binding = ItemUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LovedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listLoved.size
    }

    override fun onBindViewHolder(holder: LovedViewHolder, position: Int) {
        holder.bind(listLoved[position])
    }
    class LovedViewHolder(private val binding: ItemUsernameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loved: Loved) {
            val ctx = binding.root.context
            with(binding) {
                itemText.text = loved.username
                Picasso.get().load(loved.avatar).into(binding.itemImage)
                itemCard.setOnClickListener {
                    val intent = Intent(ctx, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_TITLE,loved.username)
                    intent.putExtra(DetailUserActivity.EXTRA_IMG_ACCOUNT,loved.avatar)
                    intent.putExtra(DetailUserActivity.EXTRA_LOVED,loved)
                    ctx.startActivity(intent)
                }
            }
        }
    }
}