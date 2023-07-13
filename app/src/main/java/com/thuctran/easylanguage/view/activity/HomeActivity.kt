package com.thuctran.easylanguage.view.activity

import android.content.Intent
import com.thuctran.easylanguage.databinding.ActivityHomeBinding
import com.thuctran.easylanguage.viewmodel.CommonVM
import com.thuctran.easylanguage.view.fragment.SplashFragment

class HomeActivity : BaseAct<ActivityHomeBinding, CommonVM>() {
    override fun getClassVM(): CommonVM {
        return CommonVM()
    }

    override fun initViewBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        showFragment(SplashFragment.TAG,null,false)       //show splash lên trước
    }

    override fun goMainAct() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}