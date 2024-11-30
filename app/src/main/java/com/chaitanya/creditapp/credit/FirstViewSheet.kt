package com.chaitanya.creditapp.credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chaitanya.creditapp.databinding.FirstViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.tankery.lib.circularseekbar.CircularSeekBar

class FirstViewSheet(private val item1: Item, private val clickListener: ()-> Unit):
    BottomSheetDialogFragment() {

    private var _binding : FirstViewBinding? = null
    private  val binding get() = _binding!!
    private var amount: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FirstViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set data
        binding.i1IntroTv.text = item1.open_state.body.title
        binding.i1InstructionTv.text = item1.open_state.body.subtitle
        binding.header.text = item1.open_state.body.card.header
        binding.description.text = item1.open_state.body.card.description
        amount = item1.open_state.body.card.min_range
        binding.footer.text = item1.open_state.body.footer
        binding.circularSeekBar.max = item1.open_state.body.card.max_range.toFloat()
        binding.nextButton1.text = item1.cta_text

        //Set up CircularSeekBar change listener
        binding.circularSeekBar.setOnSeekBarChangeListener(object : CircularSeekBar.OnCircularSeekBarChangeListener {
            override fun onProgressChanged(seekBar: CircularSeekBar?, progress: Float, fromUser: Boolean) {
                //get amount(progress) from user
                amount = progress.toInt()
                progress.toInt().toString().also {
                    binding.amount.text = buildString {
                        append("₹ ")
                        append(it)
                    }
                }
            }
            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {}
            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {}
        })


        //on clicking Proceed to EMI selection
        binding.nextButton1.setOnClickListener {
            if(amount > item1.open_state.body.card.min_range){
                clickListener()
            }
            else{
                Toast.makeText(context, "Please Select the amount greater than ₹ ${item1.open_state.body.card.min_range}", Toast.LENGTH_LONG).show()
            }

        }

        //close buttn
        binding.close.setOnClickListener {
            dismiss()
        }

        //help button
        binding.help.setOnClickListener {
            Toast.makeText(context, "Help",Toast.LENGTH_SHORT).show()
        }
    }

    //expand required
    override fun onStart() {
        super.onStart()
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }
    }


}