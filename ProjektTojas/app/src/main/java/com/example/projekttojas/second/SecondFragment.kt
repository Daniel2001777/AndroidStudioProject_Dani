package com.example.projekttojas.second

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.projekttojas.data.User
import com.example.projekttojas.data.UserViewModel
import com.example.projekttojas.databinding.FragmentSecondBinding
import com.example.projekttojas.third.ThirdFragmentDirections

class SecondFragment : Fragment() {

    lateinit var viewModel: SecondViewModel
    lateinit var viewModelFactory: SecondViewModelFactory
    private lateinit var mUserViewModel: UserViewModel

    lateinit var binding: FragmentSecondBinding

    private val args : SecondFragmentArgs by navArgs()

    private var name = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel =  ViewModelProvider(this).get(UserViewModel::class.java)
        name=args.name

        /*binding.apply {
            tvName.text=name
        }*/
        viewModelFactory = SecondViewModelFactory(name)
        viewModel = ViewModelProvider(this, viewModelFactory)[SecondViewModel::class.java]

        binding.apply {
            tvName.text = viewModel.name()
        }

        viewModel.startTimer()

        viewModel.seconds().observe(this, Observer {
            binding.apply {
                countDown.text = it
            }
        })
        viewModel.finished.observe(this, Observer {
            if(it){
                val direction =
                    SecondFragmentDirections.actionSecondFragmentToThirdFragment()
                findNavController().navigate(direction)
            }
        })

        viewModel.currentNumber.observe(this, Observer {
            binding.apply {
                tvTextView.text = it.toString()
            }
        })
        incrementText()
        /*val currentUser: User = mUserViewModel.readLastUser
        val updatedUser = User(currentUser.id, currentUser.userName, viewModel.number)
        mUserViewModel.updateUser(updatedUser)*/
    }

    private fun incrementText() {
        binding.apply {
            imageButton.setOnClickListener{
                viewModel.currentNumber.value = ++viewModel.number
            }
        }
    }
}