package com.thuctran.easylanguage.view.dialog

import android.content.Context
import android.view.animation.AnimationUtils
import com.thuctran.easylanguage.databinding.DialogCloseAppBinding
import com.thuctran.easylanguage.databinding.DialogCloseLearnBinding

class DialogCloseApp(context:Context) : BaseDialog<DialogCloseAppBinding>(context) {
    companion object {
        private val TAG: String = DialogCloseApp::class.java.name
    }

    override fun initViewBinding(): DialogCloseAppBinding {
        return DialogCloseAppBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding!!.btNo.setOnClickListener{
            binding!!.btNo.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            dismiss()
        }

        binding!!.btYes.setOnClickListener{
            binding!!.btYes.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            callBack1!!.closeApp()
        }
    }

}