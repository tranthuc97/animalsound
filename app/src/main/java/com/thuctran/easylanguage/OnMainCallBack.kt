package com.thuctran.easylanguage

import com.thuctran.easylanguage.model.Word

interface OnMainCallBack {
    fun callBack(key:String,data:Any?) {}
    fun showFragment(tag:String,data: Any?,isBacked:Boolean) {}
    fun showFragment2(tag:String,data: Any?,isBacked:Boolean) {}
    fun showFragmentLearnDetail(tag:String,data: Any?,isBacked:Boolean) {}
    fun showFragmentReviewDetail(tag:String,data: Any?,isBacked:Boolean) {}
    fun closeApp() {}
    fun goMainAct() {}
    fun goLearnDetailAct(data: ArrayList<Word>) {}
    fun toNextWord(){}
    fun updateProgress(i:Int){}
    fun backToMainActivity(){}
    fun goToReviewAct() {}
    fun toNextWordgame2() {}
    fun toNextWordgame1() {}
    fun execOnMainThread(runnable: Runnable) {}
    fun showDialogCloseApp() {}
}