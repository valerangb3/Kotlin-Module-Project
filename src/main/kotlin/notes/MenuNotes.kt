package notes

open class MenuNotes : Menu() {
    override var menuTitle = "Список заметок:"
    override val menuEntity = "Заметка"
    override val menuCreateTitle = "Введите название заметки:"
    private val menuCreateText = "Введите текст заметки:"


    private fun readNoteAttr(attr: String = "Ввидети атрибут заметки:"): String {
        var input = ""
        do {
            println(attr)
            try {
                input = getUserInput()
            } catch (ise: IllegalStateException) {
                println(ise.message)
            }
        } while (input.isEmpty())
        return input
    }

    private fun getNote(): Note = Note(
        readNoteAttr(menuCreateTitle),
        readNoteAttr(menuCreateText)
    )

    private fun showRootMenu(textNote: String) {
        println("Заметка\n")
        println("$textNote \n")
        println("0. $EXIT_TITLE")
    }

    override fun create() {
        //Получем созданную заметку
        val noteItem = getNote()
        val size = menuItems.size
        //Получем пункт меню "Выход"
        val (keyExit, valueExit) = this.menuItems.toList()[size - 1]
        this.menuItems.remove(keyExit)
        val menuNotes = MenuNotes()
        this.menuItems[noteItem.noteName] = {
            menuNotes.setNotesMenu(
                mutableMapOf(
                    EXIT_KEY to { menuNotes.stopExecute() }
                )
            )
            while (!menuNotes.stopExecute) {
                menuNotes.showRootMenu(noteItem.noteText)
                menuNotes.readNumMenu()
            }
        }
        this.menuItems[keyExit] = valueExit
    }

}