package com.thuctran.easylanguage.viewmodel

import androidx.lifecycle.MutableLiveData
import com.thuctran.easylanguage.App
import com.thuctran.easylanguage.model.Word

class GameVM : BaseViewModel() {
    companion object {
        private val TAG: String = GameVM::class.java.name
    }

    private var wordSchool: MutableLiveData<Word> = MutableLiveData(null)
    private var index:MutableLiveData<Int> = MutableLiveData(0)
    private var th: Thread? = null

    val WORDSCHOOL: MutableLiveData<Word>
        get() = wordSchool

    var INDEX:MutableLiveData<Int>
        get() = index
        set(value) {
            index = value
        }


    fun toList() {
        App.INSTANCE.STORAGE.listWordCommon.addAll(App.INSTANCE.STORAGE.listWordReview)
        App.INSTANCE.STORAGE.listWordCommon.shuffle()
        App.INSTANCE.STORAGE.listWordShuffle.addAll(App.INSTANCE.STORAGE.listWordCommon)
    }
}