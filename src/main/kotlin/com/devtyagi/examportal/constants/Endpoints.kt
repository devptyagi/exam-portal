package com.devtyagi.examportal.constants

class Endpoints {
    companion object {
        const val BASE_URL = "/api/v1"
    }

    class AdminAPI {
        companion object {
            private const val ADMIN_BASE_URL = "/admin"

            const val ADD_TEACHER = "${ADMIN_BASE_URL}/add-teacher"
        }
    }

    class TeacherAPI {
        companion object {
            private const val TEACHER_BASE_URL = "/teacher"
        }
    }
}