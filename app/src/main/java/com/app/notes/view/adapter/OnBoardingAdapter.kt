package com.app.notes.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.notes.R
import com.app.notes.databinding.OnboardingItemBinding
import com.app.notes.model.OnBoardingModel
import com.bumptech.glide.Glide

class OnBoardingAdapter(private val list: List<OnBoardingModel>, private val context: Context):RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {
    class ViewHolder(val binding:OnboardingItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(OnboardingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].icon).into(holder.binding.iconImv)
        holder.binding.headLine.text = list[position].headText
        holder.binding.tailLine.text = list[position].tailText
        if (position == 0){
            holder.binding.view1.setBackgroundResource(R.drawable.dot_out)
            holder.binding.view2.setBackgroundResource(R.drawable.dot_on)
            holder.binding.view3.setBackgroundResource(R.drawable.dot_on)

        }else if (position ==1){
            holder.binding.view2.setBackgroundResource(R.drawable.dot_out)
            holder.binding.view1.setBackgroundResource(R.drawable.dot_on)
            holder.binding.view3.setBackgroundResource(R.drawable.dot_on)

        }else if (position ==2){
            holder.binding.view3.setBackgroundResource(R.drawable.dot_out)
            holder.binding.view1.setBackgroundResource(R.drawable.dot_on)
            holder.binding.view2.setBackgroundResource(R.drawable.dot_on)

        }
    }
}