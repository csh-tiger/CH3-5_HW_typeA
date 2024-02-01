package com.example.ch3_5hw_api_typea

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ch3_5hw_api_typea.NetWorkClient.dustNetWork
import com.example.ch3_5hw_api_typea.databinding.FragmentImageListBinding
import com.example.ch3_5hw_api_typea.databinding.ItemRecyclerViewGridBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ImageListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: Adapter

    var items = mutableListOf<DataItem>()
    var storageList:ArrayList<Int> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnImgListSearch.setOnClickListener {
            val searchText = binding.editImgListSearch.text.toString()

            lifecycleScope.launch {
                val responseData = communicateNetWork(searchText)

                if (responseData != null) {
                    adapter = Adapter(responseData)
                    binding.recyclerImgList.adapter = adapter
                    binding.recyclerImgList.layoutManager = GridLayoutManager(requireContext(), 2)
                    Log.d("ImageFragment", "item=${responseData}")
                }
            }

//            communicateNetWork(searchText)
//
//            adapter = Adapter(items)
//            binding.recyclerImgList.adapter = adapter
//            binding.recyclerImgList.layoutManager = GridLayoutManager(requireContext(), 2)
//            Log.d("ImageFragment", "item=$items")
        }

//        adapter = Adapter(items)
//        adapter.itemClick = object : Adapter.ItemClick {
//            override fun onClick(view: View, position: Int) {
//                Log.d("ImageFragment", "item Clicked")
//                val itemFavorite = adapter.Holder(view as ItemRecyclerViewGridBinding).itemFavorite
//                itemFavorite.visibility = ImageView.VISIBLE
//
//                storageList.add(position)
//                val fragmentToStorage = StorageListFragment.newInstance(storageList)
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.layout_main_fragment, fragmentToStorage)
//                    .addToBackStack(null)
//                    .commit()
//            }
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    private fun communicateNetWork(query:String) = lifecycleScope.launch() {
////    private fun communicateNetWork(query:String) = CoroutineScope(Dispatchers.IO).launch() {
//        val authKey = "KakaoAK 6b59faaf5abcd2b644ef4de2d858419f"
//        val responseData = dustNetWork.getData(authKey,query,"recency",1,80)
////        val responseData = dustNetWork.getData(authKey,query,"recency",1,80).execute()
//        items=responseData?.documents!!
//
////        if(responseData.isSuccessful){
////            val data=responseData.body()
////            data?.documents.let { DataItem ->
////                items.clear()
////                DataItem?.let { items.addAll(it) }
////            }
////        }else{
////            Log.e("NetworkError","Response error: ${responseData.errorBody()}")
////        }
//
//    }

    private suspend fun communicateNetWork(query: String): MutableList<DataItem>? {
        val authKey = "KakaoAK 6b59faaf5abcd2b644ef4de2d858419f"
        val responseData = dustNetWork.getData(authKey, query, "recency", 1, 80)
        items=responseData?.documents!!
        return items
    }
}