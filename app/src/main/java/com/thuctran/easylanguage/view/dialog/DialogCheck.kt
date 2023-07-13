package com.thuctran.easylanguage.view.dialog

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.animation.AnimationUtils
import androidx.appcompat.R
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.databinding.DialogCheckBinding
import com.thuctran.easylanguage.view.fragment.learn.P2LearnFragment

class DialogCheck(context: Context) : BaseDialog<DialogCheckBinding>(context) {
    companion object {
        private val TAG: String = DialogCheck::class.java.name
    }

    override fun initViewBinding(): DialogCheckBinding {
        return DialogCheckBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding!!.ivSound.setOnClickListener {
            binding!!.ivSound.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            var anim: AnimationDrawable = binding!!.ivSound.drawable as AnimationDrawable
            anim.start()
            binding!!.ivSound.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.abc_fade_in
                )
            )
            val player = MediaPlayer()
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            player.setDataSource(App.INSTANCE.STORAGE.word!!.MP3)
            player.prepare()
            player.start()
        }

        binding!!.tvWord.text = App.INSTANCE.STORAGE.word!!.WORD
        binding!!.tvSpelling.text = App.INSTANCE.STORAGE.word!!.SPELLING
        binding!!.tvHint.text = App.INSTANCE.STORAGE.word!!.HINT
        binding!!.tvTranslate.text = App.INSTANCE.STORAGE.word!!.TRANSLATE

        binding!!.ivTranslate.setOnClickListener {
            binding!!.ivTranslate.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            binding!!.ivTranslate.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.abc_fade_in
                )
            )
            if (binding!!.tvHint.text == App.INSTANCE.STORAGE.word!!.HINT) {
                binding!!.tvHint.text = App.INSTANCE.STORAGE.word!!.TRANSHINT
            } else if (binding!!.tvHint.text == App.INSTANCE.STORAGE.word!!.TRANSHINT) {
                binding!!.tvHint.text = App.INSTANCE.STORAGE.word!!.HINT
            }
        }

        if ((CommonUtils.INSTANCE.getPrefs(P2LearnFragment.CHECK_WORD) == App.INSTANCE.STORAGE.word!!.WORD) || (CommonUtils.INSTANCE.getPrefs(P2LearnFragment.CHECK_WORD) == App.INSTANCE.STORAGE.word!!.TRANSLATE)) {
            binding!!.lnDialogCheck.setBackgroundResource(com.thuctran.easylanguage.R.color.colorGreen)
            if (App.INSTANCE.STORAGE.listWordWrong.contains(App.INSTANCE.STORAGE.word!!)){
                App.INSTANCE.STORAGE.listWordWrong.remove(App.INSTANCE.STORAGE.word!!)
            }
        } else {
            binding!!.lnDialogCheck.setBackgroundResource(com.thuctran.easylanguage.R.color.colorRed)
            if (App.INSTANCE.STORAGE.listWordWrong.contains(App.INSTANCE.STORAGE.word!!)) {
                dismiss()
            } else {
                App.INSTANCE.STORAGE.listWordWrong.add(App.INSTANCE.STORAGE.word!!)
            }
        }

        binding!!.btOk.setOnClickListener {
            binding!!.btOk.startAnimation(AnimationUtils.loadAnimation(context,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            dismiss()
        }
    }

}