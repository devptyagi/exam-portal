package com.devtyagi.examportal.util

class RandomPasswordUtil {

    companion object {
        fun generateRandomPassword(): String {
            val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return List(16) { chars.random() }.joinToString("")
        }
    }

}