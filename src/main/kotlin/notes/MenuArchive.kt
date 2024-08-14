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
            val size = menuItems.size
            //Получем пункт меню "Выход"
            val (keyExit, valueExit) = this.menuItems.toList()[size - 1]
            menuItems.remove(keyExit)
            menuItems[input] = {
                menuNotes.start(false)
            }
            //Вставляем в конец пункт меню "Выход"
            menuItems[keyExit] = valueExit
        } catch (ise: IllegalStateException) {
            println(ise.message)
        }
    }
}