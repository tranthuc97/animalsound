package com.thuctran.easylanguage.view.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.databinding.FragmentReviewBinding
import com.thuctran.easylanguage.view.activity.MainActivity
import com.thuctran.easylanguage.viewmodel.CommonVM

class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    companion object{
        var TAG:String = ReviewFragment::class.java.name
    }
    override val viewModel by viewModels<CommonVM>()
    private var hA:Int? = null
    private var hB:Int? = null
    private var maxHeight:Int? = null
    override fun initViewBinding(): FragmentReviewBinding {
        return FragmentReviewBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        callBack1!!.callBack(MainActivity.KEY_ICON_REVIEW,null)
        var tong:Int = App.INSTANCE.STORAGE.listWordReview.size + App.INSTANCE.STORAGE.listWordRemembered.size
        binding!!.tvNoteBook.text = "Sổ tay đang có $tong từ"    //tổng số từ đã học
        binding!!.tvColumn1.text = App.INSTANCE.STORAGE.listWordReview.size.toString() + " từ"
        binding!!.tvColumn2.text = App.INSTANCE.STORAGE.listWordRemembered.size.toString() + " từ"

        maxHeight = resources.getDimension(com.thuctran.easylanguage.R.dimen.d_200).toInt()     //100%
        if(tong == 0){
            hA = 0
            hB = 0
        }else{
            hA = (App.INSTANCE.STORAGE.listWordReview.size/tong)*maxHeight!!
            hB = (App.INSTANCE.STORAGE.listWordRemembered.size/tong)*maxHeight!!

        }
        Thread(Runnable {
            kotlin.run {
                for (i in 0 until maxHeight!! step 1){
                    //updateColum1(maxHeight!!,i,hA!!,binding!!.vColumn1,binding!!.tvColumn1)
                }
            }
        }).start()

        binding!!.tvReviewNow.setOnClickListener{
            binding!!.tvReviewNow.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            if(App.INSTANCE.STORAGE.listWordReview.isEmpty()){
                showNotify("Hiện chưa có từ nào trong sổ tay")
            }else {
                callBack1!!.goToReviewAct()
            }
        }

        //PT điều khiển nút backPress
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callBack1!!.showDialogCloseApp()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateColum1(maxHeight:Int, i: Int, hA: Int, vColumn: View, tvColumn: TextView) {

        if(i<= hA) {
            var params1:LayoutParams = vColumn.layoutParams as LayoutParams      //khai báo param
            params1.height = i

            callBack1!!.execOnMainThread(Runnable(){
                kotlin.run {
                    vColumn.layoutParams = params1       //set trở lại cho view

                    vColumn.postInvalidate()     //start
                    Log.i(TAG,"chiều cao cột 1 update: $hA")


                }
            })
            Thread.sleep(5)
        }
    }
}