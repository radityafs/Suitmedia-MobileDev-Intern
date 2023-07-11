package com.suitmedia.km.test.utils

import androidx.compose.ui.text.toLowerCase

fun isPalindrome(input: String): Boolean {
    val forward = input.replace("\\s".toRegex(), "").toLowerCase()
    val backward = forward.reversed()

    return forward == backward
}