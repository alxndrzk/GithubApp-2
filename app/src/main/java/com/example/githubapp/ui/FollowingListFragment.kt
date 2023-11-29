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
import com.example.githubapp.data.response.UserFollowingResponseItem
import com.example.githubapp.databinding.FragmentFollowingBinding

class FollowingListFragment : Fragment() {


    private lateinit var followingRecyclerView: RecyclerView
    private var _binding: FragmentFollowingBinding? = null
    private lateinit var userFollowingListAdapter: FollowingListAdapter
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments
        val usrLogin:String = data?.getString(DetailUserActivity.EXTRA_TITLE).toString()

        followingRecyclerView = binding.rvFollowing
        userFollowingListAdapter = FollowingListAdapter()
        followingRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        followingRecyclerView.adapter = userFollowingListAdapter

        val itemDecorator = DividerItemDecoration(context,LinearLayoutManager(context).orientation)
        followingRecyclerView.addItemDecoration(itemDecorator)


        val followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)

        followingViewModel.fetchUsernameFollowingData(usrLogin)

        followingViewModel.followingList.observe(viewLifecycleOwner){
                username -> setFollowingNameData(username)
        }


        followingViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
       _binding = null
    }

    private fun setFollowingNameData(usernameData: List<UserFollowingResponseItem?>){
        val adapter = FollowingListAdapter()
        adapter.submitList(usernameData)
        binding.rvFollowing.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }



}