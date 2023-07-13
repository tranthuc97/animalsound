package com.thuctran.easylanguage.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.databinding.ActivityLearnBinding
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.dialog.BaseDialog
import com.thuctran.easylanguage.view.dialog.DialogCheck
import com.thuctran.easylanguage.view.dialog.DialogCloseLearn
import com.thuctran.easylanguage.view.fragment.learn.CompleteFragment
import com.thuctran.easylanguage.view.fragment.learn.P1LearnFragment
import com.thuctran.easylanguage.view.fragment.learn.P2LearnFragment
import com.thuctran.easylanguage.view.fragment.learn.P3LearnFragment
import com.thuctran.easylanguage.viewmodel.LearnActVM
import java.util.*

@Suppress("DEPRECATION", "UNCHECKED_CAST")
class LearnActivity : BaseAct<ActivityLearnBinding, LearnActVM>(),BaseDialog.CallBack {
    companion object {
        private val TAG: String = LearnActivity::class.java.name
    }

    private var listWord:MutableList<Word>? = null
    private var dialog:DialogCloseLearn? = null

    override fun getClassVM(): LearnActVM {
        return LearnActVM()
    }

    override fun initViewBinding(): ActivityLearnBinding {
        return ActivityLearnBinding.inflate(layoutInflater)
    }

    override fun initViews() {

        binding!!.progressBar.prCount.max = 10  //để thanh max lên đây sẽ ko bị loạn.
        binding!!.progressBar.ivClose.setOnClickListener{
            binding!!.progressBar.ivClose.startAnimation(AnimationUtils.loadAnimation(this,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            showDialogCloseLearn()
        }

        var intent = intent
        var data: Bundle = intent.extras!!
        listWord = data.getSerializable(MainActivity.LIST_WORD) as MutableList<Word>

        Log.i(TAG, "danh sách từ: $listWord")
        if(viewModel!!.INDEX.value == 0) {
            App.INSTANCE.STORAGE.word = listWord!![0]     //mới đầu vào show vị trí 0
            showFragmentLearnDetail(P1LearnFragment.TAG, null, false)
        }
    }

    override fun closeApp() {
        //do nothing
    }

    override fun toNextWord() {
        viewModel!!.INDEX.value = viewModel!!.INDEX.value!! + 1     //từ tiếp theo, index sẽ được +1

        if (viewModel!!.INDEX.value!! > 9) {
            reviewWrongWord()
        } else {
            App.INSTANCE.STORAGE.word = listWord!![viewModel!!.INDEX.value!!]
            CommonUtils.INSTANCE.savePrefs(P2LearnFragment.WRONG_WORD,"")
            showFragmentLearnDetail(P1LearnFragment.TAG, null, false)
        }
    }

    private fun reviewWrongWord() {
        if (App.INSTANCE.STORAGE.listWordWrong.size == 0) {
            showFragmentLearnDetail(CompleteFragment.TAG, listWord, false)
        } else {
            if (viewModel!!.INDEX2.value!! > App.INSTANCE.STORAGE.listWordWrong.size - 1) {
                viewModel!!.INDEX2.value = 0
            }
            App.INSTANCE.STORAGE.word = App.INSTANCE.STORAGE.listWordWrong[viewModel!!.INDEX2.value!!]
            viewModel!!.INDEX2.value = viewModel!!.INDEX2.value!! + 1       //gán từ xong, vị trí tiếp theo của ds từ sai được +1
            CommonUtils.INSTANCE.savePrefs(P2LearnFragment.WRONG_WORD,"")
            showFragmentLearnDetail(P2LearnFragment.TAG, null, false)
        }
    }

    override fun updateProgress(i: Int) {
        Handler().postDelayed({
            binding!!.progressBar.prCount.progress = i
        },500)
    }

    override fun showDialogCloseLearn() {
        dialog = DialogCloseLearn(this)
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog!!.CALLBACK = this      //ghi đè PT showDialog() chính là cái PT đang diễn ra này
        dialog!!.show()
    }

    override fun closeLearn() {
        startActivity(Intent(this,MainActivity::class.java))
    }

    override fun backToMainActivity() {
        startActivity(Intent(this,MainActivity::class.java))
    }
}

//viewModel!!.listWord()
//        Log.i(TAG,"từ được chọn: ${viewModel!!.WORDSCHOOL}")
//        viewModel!!.WORDSCHOOL.observe(this){
//            App.INSTANCE.STORAGE.word = it
//            Log.i(TAG,"từ được chọn: $it")
//            showFragmentLearnDetail(P1LearnFragment.TAG,null,true)