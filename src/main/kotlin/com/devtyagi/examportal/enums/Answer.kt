package com.devtyagi.examportal.enums

import com.devtyagi.examportal.util.EnumLookupUtil

enum class Answer {
    OPTION_1,
    OPTION_2,
    OPTION_3,
    OPTION_4;

    companion object {
        fun lookup(value: String?): Answer? {
            return value?.let { EnumLookupUtil.lookup(Answer::class.java, it) }
        }
    }
}