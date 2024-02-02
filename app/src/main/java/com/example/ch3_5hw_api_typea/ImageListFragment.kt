package com.example.ch3_5hw_api_typea

import android.content.Context
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
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

interface FragmentDataListener {
    fun onDataReceived(data:MutableList<DataItem>)
}

class ImageListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    var listener: FragmentDataListener? = null

    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!

    var items = mutableListOf<DataItem>()
    var favoriteList: ArrayList<Int> = ArrayList()

    private var adapter = Adapter(items)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentDataListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }
    }

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

        imgListFunction()
        Log.d("ImageFragment", "#csh imgListFunction()")
    }

    fun imgListFunction(){
        val itemClick = object : Adapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val itemFavorite = binding.recyclerImgList.findViewHolderForAdapterPosition(position) as Adapter.Holder
                if(itemFavorite.itemFavorite.visibility==View.INVISIBLE || itemFavorite.itemFavorite.visibility==View.GONE){
                    itemFavorite.itemFavorite.visibility = View.VISIBLE
                    favoriteList.add(position)

                }else{
                    itemFavorite.itemFavorite.visibility = View.INVISIBLE
                    favoriteList.remove(position)
                }
                favoriteList.sort()

                val storageData=mutableListOf<DataItem>()
                favoriteList?.forEach { index ->
                    items?.get(index)?.let { dataItem ->
                        storageData.add(dataItem)
                    }
                }



                Log.d("ImageFragment", "#csh storageList=$storageData")
                listener?.onDataReceived(storageData)
            }
        }

        //검색버튼 클릭시 검색결과 리스트 출력
        binding.btnImgListSearch.setOnClickListener {
            val searchText = binding.editImgListSearch.text.toString()

            lifecycleScope.launch {
                items = communicateNetWork(searchText)


                if (items != null) {
                    adapter = Adapter(items)
                    binding.recyclerImgList.adapter = adapter
//                    binding.recyclerImgList.layoutManager = LinearLayoutManager(context)
                    binding.recyclerImgList.layoutManager = GridLayoutManager(requireContext(), 2)
                    Log.d("ImageFragment", "#csh item=${items}")

                    adapter.itemClick = itemClick
                }
            }
        }
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

    private suspend fun communicateNetWork(query: String): MutableList<DataItem> {
        val authKey = "KakaoAK 6b59faaf5abcd2b644ef4de2d858419f"
        val responseData = dustNetWork.getData(authKey, query, "recency", 1, 80)
        val result = responseData?.documents!!
        return result
    }
}

//    private fun communicateNetWork(query:String) = lifecycleScope.launch() {
//        val authKey = "KakaoAK 6b59faaf5abcd2b644ef4de2d858419f"
//        val responseData = dustNetWork.getData(authKey,query,"recency",1,80)
//        items=responseData?.documents!!
//
//    }

//동기식
//    private fun communicateNetWork(query:String) = CoroutineScope(Dispatchers.IO).launch() {
//        val authKey = "KakaoAK 6b59faaf5abcd2b644ef4de2d858419f"
//        val responseData = dustNetWork.getData(authKey,query,"recency",1,80)
//        val responseData = dustNetWork.getData(authKey,query,"recency",1,80).execute()
//
//        if(responseData.isSuccessful){
//            val data=responseData.body()
//            data?.documents.let { DataItem ->
//                items.clear()
//                DataItem?.let { items.addAll(it) }
//            }
//        }else{
//            Log.e("NetworkError","Response error: ${responseData.errorBody()}")
//        }
//
//    }