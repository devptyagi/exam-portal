package com.devtyagi.examportal.constants

class Endpoints {
    companion object {
        const val BASE_URL = "/api/v1"
    }

    class AdminAPI {
        companion object {
            private const val ADMIN_BASE_URL = "/admin"

            const val LOGIN = "${ADMIN_BASE_URL}/login"

            const val ADD_TEACHER = "${ADMIN_BASE_URL}/add-teacher"

            const val ADD_SUBJECT = "${ADMIN_BASE_URL}/add-subject"

            const val GET_ALL_SUBJECTS = "${ADMIN_BASE_URL}/get-subjects"
        }
    }

    class TeacherAPI {
        companion object {
            private const val TEACHER_BASE_URL = "/teacher"

            const val LOGIN = "${TEACHER_BASE_URL}/login"

            const val UPLOAD_IMAGE = "${TEACHER_BASE_URL}/{questionId}/upload-image"

            const val GET_IMAGE = "${TEACHER_BASE_URL}/{questionId}/get-image"

            const val ADD_QUESTION = "${TEACHER_BASE_URL}/add-question"

            const val CREATE_EXAM = "${TEACHER_BASE_URL}/add-exam"
        }
    }
}