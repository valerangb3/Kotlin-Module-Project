package notes

fun getUserInput(): String {
    var input = ""
    try {
        input = readln()
        if (input.trim().isEmpty()) {
            throw IllegalStateException("Значение не может быть пустым. Повторите попытку.")
        }
    }
    catch (cce: CharacterCodingException) {
        println("Произошла ошибка ввода. Повторите попытку ввода.")
    }
    return input
}