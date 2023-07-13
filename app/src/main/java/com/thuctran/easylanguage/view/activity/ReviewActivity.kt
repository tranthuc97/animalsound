package com.thuctran.easylanguage.view.activity

import android.content.Intent
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.databinding.ActivityReviewBinding
import com.thuctran.easylanguage.view.dialog.BaseDialog
import com.thuctran.easylanguage.view.dialog.DialogCheck
import com.thuctran.easylanguage.view.dialog.DialogCloseLearn
import com.thuctran.easylanguage.view.fragment.game.CompleteReviewFragment
import com.thuctran.easylanguage.view.fragment.game.GameFragment1
import com.thuctran.easylanguage.view.fragment.game.GameFragment2
import com.thuctran.easylanguage.viewmodel.GameVM

class ReviewActivity : BaseAct<ActivityReviewBinding,GameVM>(),BaseDialog.CallBack {
    companion object {
        var TAG:String = ReviewActivity::class.java.name
    }
    private var dialog:DialogCloseLearn? = null

    override fun getClassVM(): GameVM {
        return GameVM()
    }

    override fun initViewBinding(): ActivityReviewBinding {
        return ActivityReviewBinding.inflate(layoutInflater)
    }

    override fun initViews() {

        if(App.INSTANCE.STORAGE.listWordCommon.isEmpty()) {
            viewModel!!.toList()
        }

        binding!!.ivClose.setOnClickListener{
            binding!!.ivClose.startAnimation(AnimationUtils.loadAnimation(this,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            showDialogCloseLearn()
        }
         if(viewModel!!.INDEX.value == 0) {
            App.INSTANCE.STORAGE.word = App.INSTANCE.STORAGE.listWordCommon[0]
            showFragmentReviewDetail(GameFragment1.TAG, null, false)
        }
    }

    override fun toNextWordgame2() {
        viewModel!!.INDEX.value = viewModel!!.INDEX.value!! + 1
        if(viewModel!!.INDEX.value == App.INSTANCE.STORAGE.listWordCommon.size-1){
            showFragmentReviewDetail(CompleteReviewFragment.TAG,null,false)
        }else{
            App.INSTANCE.STORAGE.word = App.INSTANCE.STORAGE.listWordCommon[viewModel!!.INDEX.value!!]
            showFragmentReviewDetail(GameFragment2.TAG,null,false)
        }
    }

    override fun toNextWordgame1() {
        viewModel!!.INDEX.value = viewModel!!.INDEX.value!! + 1
        if(viewModel!!.INDEX.value == App.INSTANCE.STORAGE.listWordCommon.size-1){
            showFragmentReviewDetail(CompleteReviewFragment.TAG,null,false)
        }else{
            App.INSTANCE.STORAGE.word = App.INSTANCE.STORAGE.listWordCommon[viewModel!!.INDEX.value!!]
            showFragmentReviewDetail(GameFragment1.TAG,null,false)
        }
    }

    override fun backToMainActivity() {
        startActivity(Intent(this,MainActivity::class.java))
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

    override fun closeApp() {
        //do nothing
    }
}