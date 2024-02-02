package com.example.ch3_5hw_api_typea

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ch3_5hw_api_typea.databinding.FragmentImageListBinding
import com.example.ch3_5hw_api_typea.databinding.FragmentStorageListBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StorageListFragment : Fragment() {
    private var param1: ArrayList<Int>? = null
    private var param2: MutableList<DataItem>? = null

    private var _binding: FragmentStorageListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getIntegerArrayList(ARG_PARAM1)
            param2 = it.getParcelableArrayList<DataItem>(ARG_PARAM2)?.toMutableList() ?: mutableListOf()
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
        storageListFunction()
        Log.d("StorageFragment","#csh storageListFunction()")
    }

    fun storageListFunction(){
        val mainactivity=activity as MainActivity
//        val favoriteList = mainactivity.favoriteList
        val dataList = mainactivity.dataItems
        val storageData=mutableListOf<DataItem>()
//        favoriteList?.forEach { index ->
//            dataList?.get(index)?.let { dataItem ->
//                storageData.add(dataItem)
//            }
//        }
        Log.d("StorageFragment","#csh favoriteData=$storageData")

        val adapter=Adapter(dataList)
        binding.recyclerStorageList.adapter=adapter
        binding.recyclerStorageList.layoutManager=GridLayoutManager(requireContext(),2)

        adapter.itemClick = object : Adapter.ItemClick {
            override fun onClick(view: View, position: Int) {
//                storageData.removeAt(position)
                dataList.removeAt(position)
                adapter.notifyDataSetChanged()


            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<Int>, param2: MutableList<DataItem>) =
            StorageListFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(ARG_PARAM1, param1)
                    putParcelableArrayList(ARG_PARAM2, ArrayList<DataItem>(param2))
                }
            }
    }
}