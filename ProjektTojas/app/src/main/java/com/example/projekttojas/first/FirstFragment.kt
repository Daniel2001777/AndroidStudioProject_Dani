package com.example.projekttojas.first

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projekttojas.data.User
import com.example.projekttojas.data.UserViewModel
import com.example.projekttojas.databinding.FragmentFirstBinding
import com.example.projekttojas.databinding.Welcome


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container,false)

        val welcome = Welcome("Boldog húsvétot!")
        binding.welcome = welcome

        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel =  ViewModelProvider(this).get(UserViewModel::class.java)
        binding.apply {
            startButton.setOnClickListener {
                val direction =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(name.text.toString())
                findNavController().navigate(direction)
                insertDataToDatabase()
            }
        }
    }

    private fun insertDataToDatabase() {
        binding.apply {
            val userName = name.text.toString()
            if(inputCheck(userName)){
                val user = User(0, userName, 0)
                mUserViewModel.addUser(user)
                //findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            }else{
                Toast.makeText(requireContext(), "Írd be a neved!", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun inputCheck(name: String):Boolean{
        return !(TextUtils.isEmpty(name))
    }
}