# Задание 12

### Реализовать приложение со следующим интерфейсом

1. Компоновка `BorderLayout`. Кнопки перемещают выделенные элементы (все сразу) из одного списка в другой. Элементы списка произвольны.

2. Компоновка `GridLayout`. При попадании мыши на кнопку меняется ее фон, при перемещении за границу кнопки фон становится старым. При нажатии по кнопке текст на ней меняется на «Clicked!», при отпускании становится прежним. Не использовать методы, работающие при перемещении мыши (`mouseMoved`, `mouseDragged`).

### Сделать общего слушателя для всех кнопок

3. Реализовать группу `RadioButtons` с картинками выбора/не выбора, помещения мыши над ними, помещения и удержания (одна картинка на одно действие). Картинки нарисовать программно через `Icon` или взять существующие, инициализировать однократно. Использовать методы `setIcon` и тому подобное. `RadioButtons` могут быть об областях Беларуси или т.п.