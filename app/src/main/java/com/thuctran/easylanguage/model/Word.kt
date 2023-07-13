package com.thuctran.easylanguage.model

import java.io.Serializable

class Word(
    private var id: Int?,
    private var word: String?,
    private var spelling: String?,
    private var type: String?,
    private var translate: String?,
    private var hint: String?,
    private var trasHint: String?,
    private var mp3: String?,
    private var picture: String?,
) : Serializable {
    override fun toString(): String {
        return id.toString() + ":" + word + ":" + spelling + ":" + type + ":" + translate + ":" + hint + ":" + trasHint + ":" + mp3 + ":" + picture + ":"
    }

    val ID: Int?
        get() = id

    val WORD: String?
        get() = word

    val SPELLING: String?
        get() = spelling

    val TYPE: String?
        get() = type

    val TRANSLATE: String?
        get() = translate

    val HINT: String?
        get() = hint

    val TRANSHINT: String?
        get() = trasHint

    val MP3: String?
        get() = mp3

    val PICTURE: String?
        get() = picture

    constructor(word: String) : this(null, word, null, null, null, null, null, null, null) {
        this.word = word
    }

    override fun equals(other: Any?): Boolean {
        var i:Word = other as Word
        if(word.equals(i.word)) {
            return true
        }
        return false
    }

}