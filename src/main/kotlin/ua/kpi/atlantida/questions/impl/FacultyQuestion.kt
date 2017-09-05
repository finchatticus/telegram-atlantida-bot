package ua.kpi.atlantida.questions.impl

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow
import ua.kpi.atlantida.questions.Question
import java.util.*

class FacultyQuestion : Question() {

    override fun requestQuestion() = SendMessage().apply {
        text = questionProperties.faculty
        replyMarkup = getFacultiesKeyboard()
    }

    override fun checkAnswer(message: Message) = true

    override fun showError() = SendMessage().apply { text = "Faculty error" }

    private fun getFacultiesKeyboard() = ReplyKeyboardMarkup().apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
        keyboard = ArrayList<KeyboardRow>(questionProperties.faculties.size).apply {
            val faculties = questionProperties.faculties
            for (i in 0 until faculties.size - (faculties.size % 3) step 3) {
                add(KeyboardRow().apply {
                    add(faculties[i])
                    add(faculties[i + 1])
                    add(faculties[i + 2])
                })
            }
            val row = KeyboardRow()
            for (i in (faculties.size - faculties.size % 3)..(faculties.size - 1)) {
                row.add(faculties[i])
            }
            if (row.isNotEmpty()) {
                add(row)
            }
        }
    }
}