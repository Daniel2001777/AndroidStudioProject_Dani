package com.example.projekttojas.third

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projekttojas.R
import com.example.projekttojas.data.User
import com.example.projekttojas.databinding.CustomRowBinding
import com.example.projekttojas.second.SecondFragmentDirections

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    /*class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        //var nameTxt: TextView = itemView.findViewById(R.id.name_txt)
        //var scoreTxt: TextView = itemView.findViewById(R.id.score_txt)
    }*/

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val nameTxt : TextView = itemView.findViewById(R.id.name_txt)
        val scoreTxt : TextView = itemView.findViewById(R.id.score_txt)
        val rowLayout: View = itemView.findViewById(R.id.rowLayout)
    }


    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_row,
            parent,false)
        return MyViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.nameTxt.text = currentItem.userName
        holder.scoreTxt.text = currentItem.score.toString()

        holder.rowLayout.setOnClickListener{
            val action = ThirdFragmentDirections.actionThirdFragmentToFourthFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    /*fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }*/
    fun setData(newUserList: List<User>){
        val diffUtil = ListDiffUtil(userList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        userList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}