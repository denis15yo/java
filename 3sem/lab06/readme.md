# Задание 6

## Java: Строки (1 неделя)

Программа выводит параметры командной строки.

    public class Primer {
        public static void main(String[] args) {
            for (int i = 0; i < args.length; ++ i) {
                System.out.println("N " + i + " = " + args[i]);
            }
        }
    }

В индивидуальных заданиях входные данные задаются как параметры командной строки.

При работе со строками использовать классы `StringBuffer` или `StringBuilder`. Если нужно разбивать строку на элементы, использовать класс `StringTokenizer`.

### Вариант 10. 
Дана строка – группы символов, разделенных пробелами (одним или несколькими). В тех словах, которые оканчиваются сочетанием букв `ing`, заменить это окончание на `ed`.