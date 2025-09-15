package org.example

fun agoToText(secondsAgo: Int) {
    val text = when (secondsAgo) {
        in 0..60 -> "был(а) только что"
        in 61..3600 -> "был(а) ${minutesToText(secondsAgo / 60)} назад"
        in 3601..86400 -> "был(а) ${hoursToText(secondsAgo / 3600)} назад"
        in 86401..172800 -> "был(а) вчера"
        in 172801..259200 -> "был(а) позавчера"
        else -> "был(а) давно"
    }
    println(text)
}

private fun minutesToText(minutes: Int): String {
    return when {
        minutes % 10 == 1 && minutes % 100 != 11 -> "$minutes минуту"
        minutes % 10 in 2..4 && minutes % 100 !in 12..14 -> "$minutes минуты"
        else -> "$minutes минут"
    }
}

private fun hoursToText(hours: Int): String {
    return when (hours) {
        1, 21 -> "$hours час"
        in 2..4, in 22..24 -> "$hours часа"
        else -> "$hours часов"
    }
}

fun main() {
    agoToText(readln().toInt())

}