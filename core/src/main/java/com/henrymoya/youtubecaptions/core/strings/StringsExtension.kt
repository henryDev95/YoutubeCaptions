package com.henrymoya.youtubecaptions.core.strings

import java.text.DecimalFormat
import java.util.regex.Pattern

fun String.clearEnters(): String =  this.replace("\n", "").trim()
fun String.getYoutubeVideoId():String {
    var videoId = ""
    val regex = "https?://(?:m.)?(?:www\\.)?youtu(?:\\.be/|(?:be-nocookie|be)\\.com/(?:watch|\\w+\\?(?:feature=\\w+.\\w+&)?v=|v/|e/|embed/|live/|shorts/|user/(?:[\\w#]+/)+))([^&#?\\n]+)"
    val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    if(matcher.find()){
        videoId = matcher.group(1)?.toString() ?: ""
    }
    return videoId
}

fun String.toSecondMinute():String {
    var minute = this.toFloat()/60;
    val minuteRoundo = DecimalFormat("0.00").format(minute).toString()
    var formatMinute = minuteRoundo.replace(".", ":")
    return formatMinute
}