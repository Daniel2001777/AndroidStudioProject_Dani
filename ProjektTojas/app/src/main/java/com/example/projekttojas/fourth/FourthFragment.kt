package com.example.projekttojas.fourth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.projekttojas.R
import com.example.projekttojas.data.UserViewModel
import com.example.projekttojas.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {
    lateinit var binding: FragmentFourthBinding
    private val args by navArgs<FourthFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourthBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.apply {
            nameShow.text = args.currentUser.userName
            scoreShow.text = args.currentUser.score.toString()

            delete.setOnClickListener {
                mUserViewModel.deleteUser(args.currentUser)
                Toast.makeText(requireContext(), "Sikeresen eltávolítva: ${args.currentUser.userName}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_fourthFragment_to_thirdFragment)
            }
        }
    }
}