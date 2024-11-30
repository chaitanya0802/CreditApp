package com.chaitanya.creditapp.credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.chaitanya.creditapp.R
import com.chaitanya.creditapp.databinding.ThirdViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ThirdViewSheet(private val item3: Item ,
                     private val clickListener: ()-> Unit): BottomSheetDialogFragment() {

    private var _binding : ThirdViewBinding? = null
    private  val binding get() = _binding!!

    private var isSelected = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = ThirdViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set data
        binding.i3IntroTv.text = item3.open_state.body.title
        binding.i3InstructionTv.text = item3.open_state.body.subtitle
        binding.bankName.text = item3.open_state.body.items[0].title
        binding.accountNumber.text = item3.open_state.body.items[0].subtitle
        binding.changeAccount.text = item3.open_state.body.footer
        binding.nextButton3.text = item3.cta_text

        //on clicking bank
        binding.bankCv.setOnClickListener {
            if(!isSelected){
                binding.selectAccount.setBackgroundResource(R.drawable.select_bank_circular_button_tick)
                isSelected = true
            }
            else{
                binding.selectAccount.setBackgroundResource(R.drawable.select_bank_circular_button)
                isSelected = false
            }
        }

        Glide.with(binding.bankLogo.context)
            .load("https://companieslogo.com/img/orig/HDB-bb6241fe.png?t=1720244492")
            .into(binding.bankLogo)

        //on clicking 1 click KYC
        binding.nextButton3.setOnClickListener {
            //proceed only if selected
            if (isSelected) {
                Toast.makeText(context, "Proceeding to KYC", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context, "Please, Select Bank Account", Toast.LENGTH_LONG).show()
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