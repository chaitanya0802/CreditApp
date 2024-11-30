package com.chaitanya.creditapp.credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaitanya.creditapp.databinding.SecondViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SecondViewSheet(private val item2: Item ,
                      private val clickListener: ()-> Unit): BottomSheetDialogFragment() {
    private var _binding : SecondViewBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = SecondViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set data
        binding.i2IntroTv.text = item2.open_state.body.title
        binding.i2InstructionTv.text = item2.open_state.body.subtitle
        binding.createPlanButton.text = item2.open_state.body.footer
        binding.nextButton2.text = item2.cta_text

        //recyclerview for emi plans
        binding.planRecyclerview.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.planRecyclerview.adapter = CreditPlanAdapter(item2.open_state.body.items)

        //on clicking select bank account
        binding.nextButton2.setOnClickListener {
            //proceed only if plan is selected
            if(CreditPlanAdapter.selectedPosition != RecyclerView.NO_POSITION){
                clickListener()
            }
            else{
                Toast.makeText(context,"Please select a Plan", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onStart() {
        super.onStart()
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }
    }


}