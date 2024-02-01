package com.example.ch3_5hw_api_typea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.ch3_5hw_api_typea.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var items = mutableListOf<DataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.apply {
            btnMainImgSearch.setOnClickListener {
                setFragment(ImageListFragment())
            }
            btnMainStorage.setOnClickListener {
                setFragment(StorageListFragment())
            }
        }
        setFragment(ImageListFragment())


    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {      //프래그먼트 추가 또는 삭제
            replace(R.id.layout_main_fragment, frag)    //어느 프레임레이아웃에 띄울건지, 어떤 프래그먼트인지
            setReorderingAllowed(true)      //프래그먼트 상태변경 최적화시킴(애니메이션 전환)
            addToBackStack("")      //뒤로가기 버튼 클릭시
        }
    }

    //Coroutine Async(비동기)
//    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch() {
//        val responseData = NetWorkClient.dustNetWork.getData(param)
//        //val responseData = NetWorkClient.dustNetWork.getData(param).execute() //동기
//        Log.d("Parsing Dust ::", responseData.toString())
//
//        val adapter = IconSpinnerAdapter(binding.spinnerViewGoo)
//        items = responseData.documents!!
//
//        val goo = ArrayList<String>()
//        items.forEach {
//            Log.d("add Item :", it.stationName)
//            goo.add(it.stationName)
//        }
//
//        //메인 쓰레드 외의 다른 쓰레드에서는 UI를 건드릴 수 없지만, 건드려야할 필요가 있기 때문에 그럴떈 runOnUiThread사용
//        runOnUiThread {
//            binding.spinnerViewGoo.setItems(goo)
//        }
//
//    }

}