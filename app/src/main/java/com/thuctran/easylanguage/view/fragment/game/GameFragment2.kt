package com.thuctran.easylanguage.view.fragment.game

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.resources.R
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.databinding.FragmentGame1Binding
import com.thuctran.easylanguage.databinding.FragmentGame2Binding
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.activity.ReviewActivity
import com.thuctran.easylanguage.view.dialog.BaseDialog
import com.thuctran.easylanguage.view.dialog.DialogCheck
import com.thuctran.easylanguage.view.fragment.BaseFragment
import com.thuctran.easylanguage.view.fragment.learn.P2LearnFragment

class GameFragment2 : BaseFragment<FragmentGame2Binding>(),BaseDialog.CallBack {
    companion object {
        var TAG:String = GameFragment2::class.java.name
    }
    private var dialog:DialogCheck? = null

    override fun initViewBinding(): FragmentGame2Binding{
        return FragmentGame2Binding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        //chỉ học những từ trong danh sách listReview, học xong nhảy sang listRemembered

        binding!!.tvVietnamese.text = App.INSTANCE.STORAGE.word!!.TRANSLATE
        binding!!.tvType.text = "(" + App.INSTANCE.STORAGE.word!!.TYPE + ")"
        var wordRight:Word = App.INSTANCE.STORAGE.word!!
        var wordWrong1: Word
        var wordWrong2: Word

        App.INSTANCE.STORAGE.listWordShuffle.shuffle()
        if (App.INSTANCE.STORAGE.listWordShuffle[0] == App.INSTANCE.STORAGE.word!!) {
            wordWrong1 = App.INSTANCE.STORAGE.listWordShuffle[2]
        } else {
            wordWrong1 = App.INSTANCE.STORAGE.listWordShuffle[0]
        }

        if (App.INSTANCE.STORAGE.listWordShuffle[1] == App.INSTANCE.STORAGE.word!!) {
            wordWrong2 = App.INSTANCE.STORAGE.listWordShuffle[3]
        } else {
            wordWrong2 = App.INSTANCE.STORAGE.listWordShuffle[1]
        }

        var listAns:MutableList<Word> = mutableListOf(wordRight,wordWrong1,wordWrong2)
        listAns.shuffle()

        binding!!.tvAns1.text = listAns[0].WORD
        binding!!.tvAns2.text = listAns[1].WORD
        binding!!.tvAns3.text = listAns[2].WORD

        binding!!.tvAns1.setOnClickListener(this)
        binding!!.tvAns2.setOnClickListener(this)
        binding!!.tvAns3.setOnClickListener(this)

    }

    override fun clickView(v: View?) {
        v!!.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
        CommonUtils.INSTANCE.savePrefs(P2LearnFragment.CHECK_WORD,(v as TextView).text.toString())
        if(CommonUtils.INSTANCE.getPrefs(P2LearnFragment.CHECK_WORD) == App.INSTANCE.STORAGE.word!!.WORD){
            App.INSTANCE.STORAGE.listWordRemembered.add(App.INSTANCE.STORAGE.word!!)
            App.INSTANCE.STORAGE.listWordReview.remove(App.INSTANCE.STORAGE.word!!)
            v.setBackgroundResource(com.thuctran.easylanguage.R.color.colorGreen)
        }else{
            v.setBackgroundResource(com.thuctran.easylanguage.R.color.colorRed)
        }

        Log.i(TAG,"vừa học được danh sách từ: ${App.INSTANCE.STORAGE.listWordRemembered}")
        binding!!.tvAns1.setOnClickListener{
            v.setClickable(false)
            v.setEnabled(false)
        }

        binding!!.tvAns2.setOnClickListener{
            v.setClickable(false)
            v.setEnabled(false)
        }

        binding!!.tvAns3.setOnClickListener{
            v.setClickable(false)
            v.setEnabled(false)
        }

        showDialog()

        binding!!.tvContinue.setOnClickListener{
            binding!!.tvContinue.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            binding!!.tvContinue.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_fade_in))
            callBack1!!.toNextWordgame1()
        }
    }

    override fun showDialog() {
        dialog = DialogCheck(context1!!)
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog!!.CALLBACK = this      //ghi đè PT showDialog() chính là cái PT đang diễn ra này
        dialog!!.show()
    }
}