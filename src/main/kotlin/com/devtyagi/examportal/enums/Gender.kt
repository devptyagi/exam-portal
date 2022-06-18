package com.devtyagi.examportal.enums

import com.devtyagi.examportal.util.EnumLookupUtil

enum class Gender {
    MALE,
    FEMALE;

    companion object {
        fun lookup(value: String?): Gender? {
            return value?.let { EnumLookupUtil.lookup(Gender::class.java, it) }
        }
    }
}