package com.thuctran.easylanguage.view.fragment.learn

import android.graphics.drawable.AnimationDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.animation.AnimationUtils
import androidx.appcompat.R
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.databinding.FragmentLearnP1Binding
import com.thuctran.easylanguage.view.fragment.BaseFragment
import java.util.*


class P1LearnFragment : BaseFragment<FragmentLearnP1Binding>() {
    companion object{
        var TAG:String = P1LearnFragment::class.java.name
    }

    override fun initViewBinding(): FragmentLearnP1Binding {
        return FragmentLearnP1Binding.inflate(layoutInflater)
    }

    override fun initViews() {
        val player = MediaPlayer()
        player.setAudioStreamType(AudioManager.STREAM_MUSIC)
        player.setDataSource(App.INSTANCE.STORAGE.word!!.MP3)
        player.prepare()
        player.start()

        binding!!.ivSound.setOnClickListener{
            var anim:AnimationDrawable = binding!!.ivSound.drawable as AnimationDrawable
            anim.start()
            binding!!.ivSound.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))
            player.start()
        }


        binding!!.tvContinue.setOnClickListener{
            binding!!.tvContinue.startAnimation(AnimationUtils.loadAnimation(context1,androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom))

            callBack1!!.showFragmentLearnDetail(P2LearnFragment.TAG,null,false)
        }

    }
}