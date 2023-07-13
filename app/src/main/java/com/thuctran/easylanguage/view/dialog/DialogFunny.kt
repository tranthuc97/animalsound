package com.thuctran.easylanguage.view.dialog

import android.content.Context
import android.view.animation.AnimationUtils
import com.thuctran.easylanguage.databinding.DialogCloseLearnBinding
import com.thuctran.easylanguage.databinding.DialogFunnyBinding

class DialogFunny(context:Context) : BaseDialog<DialogFunnyBinding>(context) {
    companion object {
        private val TAG: String = DialogFunny::class.java.name
    }

    override fun initViewBinding(): DialogFunnyBinding {
        return DialogFunnyBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding!!.btNo.setOnClickListener{
            binding!!.btNo.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            callBack1!!.showNotify()
        }

        binding!!.btYes.setOnClickListener{
            binding!!.btYes.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            callBack1!!.closeAppTrue()
        }
    }

}