package notes

class MenuArchive : Menu() {
    override var menuTitle = "Список архивов:"
    override val menuEntity = "Архив"
    override val menuCreateTitle = "Введите название архива:"

    override fun create() {
        println(menuCreateTitle)
        try {
            //могуть быть побелы
            val input = getUserInput()
            val menuNotes = MenuNotes()
            //Получем пункт меню "Выход"
            val exitItem = menuItems.toList()[menuItems.size - 1]
            menuItems.remove(exitItem.first)
            menuItems[input] = {
                menuNotes.start(false)
            }
            //Вставляем в конец пункт меню "Выход"
            menuItems[exitItem.first] = exitItem.second
        } catch (ise: IllegalStateException) {
            println(ise.message)
        }
    }
}