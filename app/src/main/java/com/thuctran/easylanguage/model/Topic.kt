package com.thuctran.easylanguage.model

class Topic(var topic: String?, var translate: String?, var picture: String?) {
    val TOPIC: String?
        get() = topic

    val TRANSLATE: String?
        get() = translate

    val PICTURE: String?
        get() = picture

    constructor(topic: String) : this(topic, null, null) {
        this.topic = topic
    }

    override fun toString(): String {
        return topic.toString() +", "+translate.toString()+", "+picture.toString()
    }
}