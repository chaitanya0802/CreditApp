package com.chaitanya.creditapp.credit

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.chaitanya.creditapp.databinding.FragmentCreditBinding
import com.chaitanya.creditapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class CreditFragment : Fragment() {
    //use view binding
    private var _binding: FragmentCreditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentCreditBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initially
        binding.proceed.setOnClickListener {
            //for loading dialog
            val progressDialog = ProgressDialog(context).apply {
                setMessage("Fetching data...")
                setCancelable(false)
                show()
            }

            lifecycleScope.launch {
                try {
                    //fetch data from api
                    val data = RetrofitInstance.apiService.fetchData()
                    progressDialog.dismiss()

                    //view 1
                    val firstSheetFragment = FirstViewSheet(data.items[0]) {
                        //view 2
                        val secondSheetFragment = SecondViewSheet(data.items[1]) {
                            //view 3
                            val thirdSheetFragment = ThirdViewSheet(data.items[2]) {
                                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                            }
                            thirdSheetFragment.show(parentFragmentManager, "ThirdSheetFragment")
                        }
                        secondSheetFragment.show(parentFragmentManager, "SecondSheetFragment")
                    }
                    firstSheetFragment.show(parentFragmentManager, "FirstSheetFragment")

                }
                catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}