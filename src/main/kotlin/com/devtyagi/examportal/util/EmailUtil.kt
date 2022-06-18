package com.devtyagi.examportal.util

import com.devtyagi.examportal.dao.User
import com.sendgrid.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class EmailUtil(
    @Value("\${email.apikey}")
    private val API_KEY: String,

    @Value("\${email.sender}")
    private val sender: String
) {

    @Throws(IOException::class)
    fun sendWelcomeEmail(user: User, password: String) {
        val subject = "Welcome to Exam Portal"
        val emailBody = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\n" +
                "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\n" +
                "    <div style=\"border-bottom:1px solid #eee\">\n" +
                "      <h2 style=\"color: #00466a\">Exam Portal</h2>\n" +
                "    </div>\n" +
                "    <p style=\"font-size:1.1em\">Hi, ${user.name}</p>\n" +
                "    <p>You've been successfully onboarded on the online exam portal. Use the following credentials to login.</p>\n" +
                "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">Email : ${user.email}</h2>\n" +
                "    <br>\n" +
                "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">Password : ${password}</h2>\n" +
                "    <hr style=\"border:none;border-top:1px solid #eee\" />\n" +
                "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\n" +
                "      <p>Online Exam Portal</p>\n" +
                "      <p>Dev Pratap Tyagi and Hritik Rai</p>\n" +
                "      <p>iNeuron & JetBrains Hackathon</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>"
        sendEmail(user.email, subject, emailBody)
    }

    @Throws(IOException::class)
    private fun sendEmail(receiver: String, subject: String, body: String): String? {
        val from = Email(sender)
        val to = Email(receiver)
        val content = Content("text/html", body)
        val mail = Mail(from, subject, to, content)
        val sg = SendGrid(API_KEY)
        val request = Request()
        request.method = Method.POST
        request.endpoint = "mail/send"
        request.body = mail.build()
        val response = sg.api(request)
        return response.body
    }
}