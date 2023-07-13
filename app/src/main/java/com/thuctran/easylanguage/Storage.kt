package com.thuctran.easylanguage

import com.thuctran.easylanguage.model.Topic
import com.thuctran.easylanguage.model.Word

class Storage  {

    var listWordExamination: ArrayList<Word> = arrayListOf()
    var listTopic:MutableList<Topic> = mutableListOf()
    var word:Word? = null

    var listWordReview:ArrayList<Word> = arrayListOf()
    var listWordRemembered:ArrayList<Word> = arrayListOf()
    var listWordCommon:ArrayList<Word> = arrayListOf()
    var listWordShuffle:ArrayList<Word> = arrayListOf()

    var listWordWrong:ArrayList<Word> = arrayListOf()

    var listWordSchool:ArrayList<Word> = arrayListOf()
    var allWordInApp:ArrayList<Word> = arrayListOf()

}