package com.chaitanya.creditapp.credit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chaitanya.creditapp.R
import com.chaitanya.creditapp.databinding.ItemCreditPlanBinding

class CreditPlanAdapter(private val items: List<EmiItem>) :
    RecyclerView.Adapter<CreditPlanAdapter.PlanViewHolder>() {

    //differnt colors for RV item
    private val colorOptions = listOf(
        R.color.rv_item1_bg,
        R.color.rv_item2_bg,
        R.color.rv_item3_bg
    )
    //selected item
    companion object{
        var selectedPosition: Int = RecyclerView.NO_POSITION
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ItemCreditPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = items[position]

        //bind data and color
        holder.bind(plan, position == selectedPosition, colorOptions[position % colorOptions.size])

        //selecting plan
        holder.binding.root.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = if (position == selectedPosition) RecyclerView.NO_POSITION else position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int = items.size

    class PlanViewHolder(val binding: ItemCreditPlanBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EmiItem, isSelected: Boolean, colorRes: Int) {
            binding.amountPerMonth.text = item.emi
            binding.duration.text = item.duration
            binding.seeCal.text = item.subtitle

            //Apply background tint
            binding.rvCv.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, colorRes)

            //Set tickButton background
            binding.tickButton.setBackgroundResource(
                when {
                    isSelected && colorRes == R.color.rv_item1_bg -> R.drawable.brown_circular_button_with_tick
                    isSelected && colorRes == R.color.rv_item2_bg -> R.drawable.violet_circular_button_with_tick
                    isSelected && colorRes == R.color.rv_item3_bg -> R.drawable.blue_circular_button_with_tick
                    colorRes == R.color.rv_item1_bg -> R.drawable.brown_circular_button_background
                    colorRes == R.color.rv_item2_bg -> R.drawable.violet_circular_button_background
                    else -> R.drawable.blue_circular_button_background
                }
            )
        }
    }

}
