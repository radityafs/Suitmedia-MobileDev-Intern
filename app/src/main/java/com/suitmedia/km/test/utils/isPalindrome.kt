package com.suitmedia.km.test.utils

fun isPalindrome(input: String): Boolean {
    return input.equals(input.reversed(), ignoreCase = true)
}