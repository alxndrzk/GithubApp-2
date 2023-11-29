package com.example.githubapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.response.UserFollowersResponseItem
import com.example.githubapp.databinding.FragmentFollowersBinding


class FollowersListFragment() : Fragment() {

    private lateinit var followersrecyclerView: RecyclerView
    private lateinit var binding: FragmentFollowersBinding
    private lateinit var viewModel: FollowersListViewModel
    private lateinit var userfollowersListAdapter: FollowersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments
        val usrLogin:String = data?.getString(DetailUserActivity.EXTRA_TITLE).toString()

        binding = FragmentFollowersBinding.bind(view)
        followersrecyclerView = binding.rvFollowers
        userfollowersListAdapter = FollowersListAdapter()
        followersrecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        followersrecyclerView.adapter = userfollowersListAdapter

        val itemDecorator = DividerItemDecoration(context,LinearLayoutManager(context).orientation)
        followersrecyclerView.addItemDecoration(itemDecorator)


        val followersListViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowersListViewModel::class.java)

        showLoading(true)
        followersListViewModel.findUsernameFollowAccount(usrLogin)


        followersListViewModel.followersList.observe(viewLifecycleOwner){
                username -> setReveiewNameData(username)
        }


        followersListViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }


    }


    private fun setReveiewNameData(usernameData: List<UserFollowersResponseItem>){
        val adapter = FollowersListAdapter()
        adapter.submitList(usernameData)
        binding.rvFollowers.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}