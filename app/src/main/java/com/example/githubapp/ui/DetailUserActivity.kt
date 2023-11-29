package com.example.githubapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.githubapp.R
import com.example.githubapp.data.response.DetailUserResponse
import com.example.githubapp.database.Loved
import com.example.githubapp.databinding.ActivityDetailUserBinding
import com.example.githubapp.factory.LovedViewModelFactory
import com.example.githubapp.ui.page.LovedAddUpdateViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class DetailUserActivity: AppCompatActivity(), View.OnClickListener {



    private var isEdit = false
    private var loved: Loved? = null

    private lateinit var lovedAddUpdateViewModel: LovedAddUpdateViewModel

    private lateinit var viewBinding:ActivityDetailUserBinding


    companion object {
        const val EXTRA_LOVED = "extra_loved"

        const val EXTRA_TITLE = "gizipp"
        const val EXTRA_IMG_ACCOUNT = "extra_img"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var txt: TextView
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val getName = intent.getStringExtra(EXTRA_TITLE)
        val getUrlImg = intent.getStringExtra(EXTRA_IMG_ACCOUNT)

        val bundle = Bundle()
        bundle.putString(EXTRA_TITLE,getName)

        val sectionPagerAdapter = SectionPagerAdapter(this,bundle)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)

        TabLayoutMediator(tabs,viewPager) {
            tab,position -> tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

        supportActionBar?.elevation = 0f

        viewBinding.bckBtn.setOnClickListener(this)



        val fromDetailUserViewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        val detailUserViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            DetailUserViewModel::class.java
        )

        fromDetailUserViewModel.findDetailUsernameAccount(getName.toString())

        detailUserViewModel.detailuserview.observe(this){
            username -> setReviewNameData(username)
        }

        detailUserViewModel.isloading.observe(this){
            showLoading(it)
        }

        lovedAddUpdateViewModel = obtainViewModel(this@DetailUserActivity)


        loved = intent.getParcelableExtra(EXTRA_LOVED)

        if (loved != null) {
            isEdit = true
        } else {
            loved = Loved()
        }

        lovedAddUpdateViewModel.getAllLovedByName(getName.toString()).observe(this, Observer {
            lovedData ->
            if(lovedData != null){
                isEdit = true
                loved = Loved(lovedData.id,lovedData.username,lovedData.avatar)
                Log.d("test flag 1","Loged data != null cuy")
                viewBinding.floatingLove.setImageResource(R.drawable.ic_favorite_fill)
            }else{
                loved = Loved()
                Log.d("test flag 2","Loged data null cuy")
                viewBinding.floatingLove.setImageResource(R.drawable.ic_favorite_border)
            }
        })


        viewBinding.floatingLove.setOnClickListener{

                    loved.let { love ->
                        love!!.username = getName.toString()
                        love.avatar = getUrlImg
                    }
                    if (isEdit) {
                        lovedAddUpdateViewModel.delete(loved as Loved)
                        showToast("Deleted loved account!")
                        Log.d("test flag 1","mencoba hapus data cuy")
                        viewBinding.floatingLove.setImageResource(R.drawable.ic_favorite_border)
                    } else {
                        lovedAddUpdateViewModel.insert(loved as Loved)
                        showToast("New Loved Account")
                        viewBinding.floatingLove.setImageResource(R.drawable.ic_favorite_fill)
                    }
                    isEdit = true
//                    finish()

        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): LovedAddUpdateViewModel {
        val factory = LovedViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LovedAddUpdateViewModel::class.java)
    }

    private fun setReviewNameData(usernameData:DetailUserResponse){
        viewBinding.tvName.text = usernameData.name
        viewBinding.tvUsername.text = usernameData.login
        viewBinding.tvFollowers.text = "${usernameData.followers} Followers"
        viewBinding.tvFollowing.text = "${usernameData.following} Following"
        Picasso.get().load(usernameData.avatarUrl).into(viewBinding.profileImage)

    }


    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            viewBinding.progressBar.visibility = View.VISIBLE
        } else {
            viewBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bck_btn -> {
                onBackPressed()
            }
        }
    }


}