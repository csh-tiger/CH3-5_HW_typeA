package com.example.ch3_5hw_api_typea

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ch3_5hw_api_typea.databinding.FragmentImageListBinding
import com.example.ch3_5hw_api_typea.databinding.FragmentStorageListBinding

private const val ARG_PARAM1 = "param1"


class StorageListFragment : Fragment() {
    private var param1: ArrayList<Int>? = null

    private var _binding: FragmentStorageListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getIntegerArrayList(ARG_PARAM1)
            Log.d("StorageFragment","favorite=$param1")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStorageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var favoriteList=param1
        favoriteList?.forEach { it-> }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<Int>) =
            StorageListFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(ARG_PARAM1, param1)
                }
            }
    }
}