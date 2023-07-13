package com.thuctran.easylanguage.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.view.GravityCompat
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.databinding.ActivityMainBinding
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.dialog.BaseDialog
import com.thuctran.easylanguage.view.dialog.DialogCheck
import com.thuctran.easylanguage.view.dialog.DialogCloseApp
import com.thuctran.easylanguage.view.dialog.DialogFunny
import com.thuctran.easylanguage.view.fragment.*
import com.thuctran.easylanguage.viewmodel.AddListVM

class MainActivity : BaseAct<ActivityMainBinding,AddListVM>(),BaseDialog.CallBack {
    companion object {
        var TAG:String = MainActivity::class.java.name
        val LIST_WORD: String = "LIST_WORD"
        var KEY_ICON_REVIEW:String = "KEY_ICON_REVIEW"
        var KEY_ICON_LEARN:String = "KEY_ICON_LEARN"
        var KEY_ICON_SEARCH:String = "KEY_ICON_SEARCH"
    }

    private var dialog:DialogCloseApp? = null
    private var dialog2:DialogFunny? = null

    override fun getClassVM(): AddListVM {
        return AddListVM()
    }


    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {

        if(App.INSTANCE.STORAGE.listTopic.isEmpty()) {
            viewModel!!.addList()
            binding!!.itemMenu.ivReview.setImageLevel(0)
            binding!!.itemMenu.ivLearn.setImageLevel(1)
            binding!!.itemMenu.ivSearch.setImageLevel(1)
        }
        showFragment2(ReviewFragment.TAG,null,false)

        binding!!.itemMenu.ivReview.setOnClickListener{v->
            binding!!.itemMenu.ivReview.startAnimation(AnimationUtils.loadAnimation(this,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            changeSelectedItem(v as ImageView)
            showFragment2(ReviewFragment.TAG,null,true)
        }

        binding!!.itemMenu.ivLearn.setOnClickListener{v->
            binding!!.itemMenu.ivLearn.startAnimation(AnimationUtils.loadAnimation(this,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            changeSelectedItem(v as ImageView)
            showFragment2(LearnFragment.TAG,null,true)
        }

        binding!!.itemMenu.ivSearch.setOnClickListener{v->
            binding!!.itemMenu.ivSearch.startAnimation(AnimationUtils.loadAnimation(this,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            changeSelectedItem(v as ImageView)
            showFragment2(SearchFragment.TAG,null,true)
        }

        binding!!.lnInformation.tvName.text = CommonUtils.INSTANCE.getPrefs(EntrainFragment.USER_NAME)
        binding!!.lnInformation.tvAge.text = CommonUtils.INSTANCE.getPrefs(EntrainFragment.AGE)
        binding!!.itemActionBar.ivInformation.setOnClickListener{
            binding!!.itemActionBar.ivInformation.startAnimation(AnimationUtils.loadAnimation(this,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            binding!!.drHomeAct.openDrawer(GravityCompat.START)
        }

        binding!!.itemActionBar.ivSetting.setOnClickListener{
            binding!!.itemActionBar.ivSetting.startAnimation(AnimationUtils.loadAnimation(this,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            binding!!.drHomeAct.openDrawer(GravityCompat.END)       //đóng mở ngăn kéo
        }
    }

    private fun changeSelectedItem(v: ImageView) {
        binding!!.itemMenu.ivReview.setImageLevel(1)
        binding!!.itemMenu.ivLearn.setImageLevel(1)
        binding!!.itemMenu.ivSearch.setImageLevel(1)

        v.setImageLevel(0)
    }

    override fun goLearnDetailAct(data: ArrayList<Word>) {
        var intent = Intent(this,LearnActivity::class.java)
        var data1 = Bundle()
        data1.putSerializable(LIST_WORD,data)
        intent.putExtras(data1)

        startActivity(intent)
        finish()
    }

    override fun goToReviewAct() {
        startActivity(Intent(this,ReviewActivity::class.java))
    }

    override fun showDialogCloseApp() {
        dialog = DialogCloseApp(this)
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog!!.CALLBACK = this      //ghi đè PT showDialog() chính là cái PT đang diễn ra này
        dialog!!.show()
    }

    override fun closeApp() {
        showDialogFunny()
    }

    private fun showDialogFunny() {
        dialog2 = DialogFunny(this)
        dialog2!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog2!!.CALLBACK = this      //ghi đè PT showDialog() chính là cái PT đang diễn ra này
        dialog2!!.show()
    }

    override fun showNotify() {
        showNotify("error...")
    }

    override fun closeAppTrue() {
        finish()
    }
}