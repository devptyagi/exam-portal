package com.devtyagi.examportal.util

import com.devtyagi.examportal.exception.InvalidEnumValueException

class EnumLookupUtil {

    companion object {
        fun <E : Enum<E>?> lookup(e: Class<E>, id: String): E {
            return try {
                java.lang.Enum.valueOf(e, id)
            } catch (illegalArgumentException: IllegalArgumentException) {
                throw InvalidEnumValueException("Invalid value for enum " + e.simpleName + ": " + id)
            }
        }
    }

}