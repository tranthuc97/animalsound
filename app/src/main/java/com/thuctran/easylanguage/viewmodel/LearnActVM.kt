package com.thuctran.easylanguage.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.thuctran.easylanguage.CommonUtils
import com.thuctran.easylanguage.model.Word
import com.thuctran.easylanguage.view.fragment.learn.P3LearnFragment


class LearnActVM : BaseViewModel() {
    companion object {
        val TAG: String = LearnActVM::class.java.name
    }

    private var index:MutableLiveData<Int> = MutableLiveData(0)
    private var index2:MutableLiveData<Int> = MutableLiveData(0)

    var INDEX:MutableLiveData<Int>
        get() = index
        set(value) {
            index = value
        }

    var INDEX2:MutableLiveData<Int>
        get() = index2
        set(value) {
            index2 = value
        }

}