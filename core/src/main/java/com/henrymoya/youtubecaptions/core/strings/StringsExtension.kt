package com.henrymoya.youtubecaptions.core.strings

fun String.clearEnters(): String =  this.replace("\n", "").trim()
