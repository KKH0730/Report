package com.example.base.extensions

import java.util.regex.Pattern

const val PATTERN_PHONE_NUMBER = "^(010)\\d{8}\$"
const val PATTERN_PASSWORD_COMBINATION_1 = "^(?=.*[A-Za-z]).{0,}$"
const val PATTERN_PASSWORD_COMBINATION_2 = "^(?=.*[0-9]).{0,}$"
const val PATTERN_PASSWORD_COMBINATION_3 =  "^(?=.*[!@#$%^&*()_=+]).{0,}$"
const val PATTERN_PASSWORD_LENGTH = "^[A-Za-z\\d!@#\$%^&*()_=+]{8,12}\$"
const val PATTERN_BIRTH = "^\\d{6}$"

fun String.isValidPhoneNumber() : Boolean {

    val pattern = Pattern.compile(PATTERN_PHONE_NUMBER)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isValidPassword() : Boolean {
    val isValidCombination = isValidPasswordCombination()
    val isValidLength = isValidPasswordLength()
    return isValidCombination && isValidLength
}

fun String.isValidPasswordLength() : Boolean {
    val pattern = Pattern.compile(PATTERN_PASSWORD_LENGTH)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isValidPasswordCombination() : Boolean {
    val pattern1 = Pattern.compile(PATTERN_PASSWORD_COMBINATION_1)
    val isPattern1Match = pattern1.matcher(this).matches()

    val pattern2 = Pattern.compile(PATTERN_PASSWORD_COMBINATION_2)
    val isPattern2Match = pattern2.matcher(this).matches()

    val pattern3 = Pattern.compile(PATTERN_PASSWORD_COMBINATION_3)
    val isPattern3Match = pattern3.matcher(this).matches()

    return (isPattern1Match && isPattern2Match) || (isPattern2Match && isPattern3Match) || (isPattern1Match && isPattern3Match)
}

fun String.isValidBirth() : Boolean {
    val pattern = Pattern.compile(PATTERN_BIRTH)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}