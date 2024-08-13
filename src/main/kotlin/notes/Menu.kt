package notes


abstract class Menu {
    protected open var menuTitle = "Список элементов:"
    protected open val menuEntity = "Элемент"
    protected open val menuCreateTitle = "Введите название элемента:"
    protected open var stopExecute = false
    protected open var isRoot = false

    protected open var menuItems: MutableMap<String, () -> Unit> = mutableMapOf(
        "Create" to { create() },
        "Exit" to { stopExecute = true }
    )

    protected abstract fun create()

    protected fun getUserInput(): String {
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

    //Метод для считывания пунктов меню
    protected open fun readNumMenu() {
        try {
            val input = this.getUserInput()
            val listItem = menuItems.toList()
            when {
                input.toInt() !in listItem.indices -> println("Такой цифры в меню не существует. Повторите попытку.")
                input.toInt() in 0..listItem.size -> listItem[input.toInt()].second.invoke()
            }
        } catch (ise: IllegalStateException ) {
            println(ise.message)
        } catch (nfe: NumberFormatException ) {
            println("Ошибка ввода. Необходимо ввести цело число. Повторите попытку.")
        }
    }

    protected open fun showMenu() {
        if (menuItems.isNotEmpty()) {
            menuItems.onEachIndexed { index, entry -> run {
                when {
                    index == 0 && !isRoot -> println("0. $CREATE_TITLE")
                    index >= 0 && index < menuItems.size - 1 -> println("$index. ${entry.key}")
                    index == menuItems.size - 1 -> println("$index. $EXIT_TITLE")
                }
            } }
        }
    }

    fun setNotesMenu(items: MutableMap<String, () -> Unit>) {
        menuItems = items
    }

    fun setRoot() {
        isRoot = true
    }

    fun stopExecute() {
        stopExecute = true
    }


    //1. Отображаем меню
    //2. Считываем ввод пльзователя
    fun start(stop: Boolean = false) {
        stopExecute = stop
        while (!stopExecute) {
            println(menuTitle)
            showMenu()
            if (!stopExecute) {
                readNumMenu()
            }
        }
    }

    companion object {
        const val EXIT_TITLE = "Выход"
        const val CREATE_TITLE = "Создать"
    }
}